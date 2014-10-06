/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sketchproject.mbapp.models.credit;

import com.sketchproject.mbapp.core.Config;
import com.sketchproject.mbapp.core.MysqlDatabase;
import com.sketchproject.mbapp.models.AppModel;
import com.sketchproject.mbapp.utility.DateModifier;
import com.sketchproject.mbapp.utility.TableData;
import java.sql.SQLException;
import java.util.Date;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Asus
 */
public class InstalmentModel extends MysqlDatabase{
    
    public DefaultTableModel getInstalmentData(Object keyword) {        
        try {
            mysqlConnect();
            String query = "SELECT cr_loan_creditor.lcr_id, cdr_name, lon_loan, (concat(count_instalment,\"/\",lon_total_month)) AS progress, IF(count_instalment=lon_total_month,'LUNAS','BELUM LUNAS') AS lcr_status, lcr_date, lcr_fine, lcr_policenum, lcr_year, lcr_merk FROM cr_creditor INNER JOIN cr_loan_creditor ON cr_loan_creditor.lcr_creditor = cr_creditor.cdr_id INNER JOIN cr_loan ON cr_loan_creditor.lcr_loan = lon_id  INNER JOIN (SELECT lcr_id, COUNT(ins_id)AS count_instalment FROM cr_instalment RIGHT JOIN cr_loan_creditor ON cr_instalment.ins_loan_creditor = cr_loan_creditor.lcr_id GROUP BY lcr_id) i_count ON cr_loan_creditor.lcr_id = i_count.lcr_id";
            if(keyword.toString().trim().isEmpty()){
                psSelect = connection.prepareStatement(query);
            }
            else{
                psSelect = connection.prepareStatement(query+" WHERE cdr_id LIKE '%"+keyword.toString()+"%' OR cdr_name LIKE '%"+keyword.toString()+"%'");
            }
            
            statements.add(psSelect);
            rs = psSelect.executeQuery();   
            
            connection.commit();
            
            String[] colum = {"ID Pinjaman","Creditor","Pinjaman","Bulan","Status","Tanggal","Jaminan", "No Polisi", "Tahun", "Merk"};
            return TableData.buildTableModel(rs,colum);
        } catch (SQLException sqle) {
            printSQLException(sqle);
        } finally {
            releaseMysqlResource();
        }
        return null;
    }
    
    public Object[] getInstalmentStatus(int id) {        
        try {
            mysqlConnect();
            String query = "SELECT cdr_id, cr_loan_creditor.lcr_id, cdr_name, lon_loan, lon_instalment_value, (concat(count_instalment,\"/\",lon_total_month)) AS progress, IF(count_instalment=lon_total_month,'LUNAS','BELUM LUNAS') AS lcr_status, lcr_date FROM cr_creditor INNER JOIN cr_loan_creditor ON cr_loan_creditor.lcr_creditor = cr_creditor.cdr_id INNER JOIN cr_loan ON cr_loan_creditor.lcr_loan = lon_id  INNER JOIN (SELECT lcr_id, COUNT(ins_id)AS count_instalment FROM cr_instalment RIGHT JOIN cr_loan_creditor ON cr_instalment.ins_loan_creditor = cr_loan_creditor.lcr_id GROUP BY lcr_id) i_count ON cr_loan_creditor.lcr_id = i_count.lcr_id HAVING lcr_id=?";
            psSelect = connection.prepareStatement(query);            
            statements.add(psSelect);
            
            psSelect.setInt(1, id);
            rs = psSelect.executeQuery();   
            
            Object[] data = new Object[8];
            
            if(rs.next()){
                data[0] = rs.getInt("cdr_id");
                data[1] = rs.getInt("lcr_id");
                data[2] = rs.getString("cdr_name");
                data[3] = rs.getInt("lon_loan");
                data[4] = rs.getInt("lon_instalment_value");
                data[5] = rs.getString("progress");
                data[6] = rs.getString("lcr_status");
                data[7] = rs.getDate("lcr_date");
            }
            
            if(data[6].toString().equals("LUNAS")){
                psUpdate = connection.prepareStatement("UPDATE cr_loan_creditor SET lcr_status='PAID' WHERE lcr_id=?");
                statements.add(psUpdate);
                psUpdate.setInt(1, id);
                psUpdate.executeUpdate();
            }
            else{
                psUpdate = connection.prepareStatement("UPDATE cr_loan_creditor SET lcr_status='DEBT' WHERE lcr_id=?");
                statements.add(psUpdate);
                psUpdate.setInt(1, id);
                psUpdate.executeUpdate();
            }
            
            connection.commit();
            
            return data;
        } catch (SQLException sqle) {
            printSQLException(sqle);
        } finally {
            releaseMysqlResource();
        }
        return null;
    }
        
    public DefaultTableModel getLoanInstalment(int id) {        
        try {
            mysqlConnect();
            
            psSelect = connection.prepareStatement("SELECT ins_id,ins_date,ins_instalment_for,ins_overdue,ins_penalty,ins_payment FROM cr_instalment WHERE ins_loan_creditor = ? ORDER BY ins_instalment_for");
            statements.add(psSelect);
            psSelect.setInt(1, id);
            rs = psSelect.executeQuery();   
            
            connection.commit();
            
            String[] colum = {"ID","Tanggal","Bulan","Telat","Denda","Bayar"};
            return TableData.buildTableModel(rs,colum);
        } catch (SQLException sqle) {
            printSQLException(sqle);
        } finally {
            releaseMysqlResource();
        }
        return null;
    }
    
    public int getCurrentMonthInstalment(int id){
        try {
            mysqlConnect();
            String query = "SELECT IFNULL(MAX(ins_instalment_for + 1),1) AS ins_instalment_for FROM cr_instalment WHERE ins_loan_creditor = ?";
            psSelect = connection.prepareStatement(query);
            
            statements.add(psSelect);
            psSelect.setInt(1, id);
            rs = psSelect.executeQuery();   
            
            int data = 1;
            
            if(rs.next()){
                data = rs.getInt("ins_instalment_for");
            }
            
            connection.commit();
            
            return data;
        } catch (SQLException sqle) {
            printSQLException(sqle);
        } finally {
            releaseMysqlResource();
        }
        return 1;
    }
    
    public Object[] checkPenalty(Object[] data){
        // prepare initial data
        int payFor = Integer.parseInt(data[0].toString());
        Date loan = DateModifier.stringToDate(data[1].toString());
        Date instalment = DateModifier.stringToDate(data[2].toString());
        for(int i=0;i<data.length;i++){
            System.out.println(i+" "+data[i].toString());
        }
        // set month after start date
        Date targetDate = DateModifier.dateIncrementDecrement(loan, 0, payFor, 0);
        
        // calculate difference between start date to target
        int target = DateModifier.differenceBetweenDate(loan, targetDate);  
        // calculate difference between start date end date
        int now = DateModifier.differenceBetweenDate(loan, instalment);
        // subtract target to now
        int penalty = target - now;
        
        int overdue = 0;
        if(penalty<0){
            overdue = Math.abs(penalty);
        }
        AppModel mSetting = new AppModel();
        Object setting[] = mSetting.getSetting("APPCR"); 
        float fine = Float.parseFloat(setting[4].toString());
        
        int nominal = (int)(fine/100*Integer.parseInt(data[3].toString()));
        int fineTotal = nominal * overdue;
        
        Object[] result = new Object[3];
        result[0] = overdue;
        result[1] = fineTotal;
        result[2] = Integer.parseInt(data[3].toString()) + fineTotal;
        
        return result;
    }
    
    public boolean addInstalment(Object[] data){
        try{
            mysqlConnect();
            
            psInsert = connection.prepareStatement("INSERT into cr_instalment(ins_loan_creditor,ins_date,ins_instalment_for, ins_overdue, ins_penalty, ins_payment, ins_user) VALUES (?, ?, ?, ?, ?, ?, ?)");
            statements.add(psInsert);

            psInsert.setString(1, data[0].toString());
            psInsert.setString(2, data[1].toString());
            psInsert.setString(3, data[2].toString());
            psInsert.setString(4, data[3].toString());
            psInsert.setString(5, data[4].toString());
            psInsert.setString(6, data[5].toString());
            psInsert.setString(7, Config.userId);
            psInsert.executeUpdate();
            
            connection.commit();
            
            return true;
            
        } catch (SQLException sqle) {
            printSQLException(sqle);
        } finally {
            releaseMysqlResource();
        }
        return false;
    }
    
    public boolean deleteInstalment(int id){
        try{
            mysqlConnect();
            
            s = connection.createStatement();
            statements.add(s);
            
            psDelete = connection.prepareStatement("DELETE from cr_instalment where ins_id=?");
            psDelete.setInt(1, id);
            
            psDelete.executeUpdate();
            
            connection.commit();
            
            return true;
            
        } catch (SQLException sqle) {
            printSQLException(sqle);
        } finally {
            releaseMysqlResource();
        }
        return false;
    }
}
