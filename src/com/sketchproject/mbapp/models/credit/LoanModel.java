/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sketchproject.mbapp.models.credit;

import com.sketchproject.mbapp.core.Config;
import com.sketchproject.mbapp.core.MysqlDatabase;
import com.sketchproject.mbapp.utility.TableData;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Asus
 */
public class LoanModel extends MysqlDatabase{
    
    public Object[][] getDataLoanType(){
        try {
            mysqlConnect();
            
            psSelect = connection.prepareStatement("SELECT * FROM cr_loan");
            statements.add(psSelect);
            rs = psSelect.executeQuery(); 
            
            rs.last();
            int row = rs.getRow();
            rs.beforeFirst();
            
            Object[][] data = new Object[row][4];
            
            int i = 0;
            while(rs.next()){
                data[i][0] = rs.getInt("lon_id");
                data[i][1] = rs.getString("lon_loan");
                data[i][2] = rs.getString("lon_total_month");
                data[i][3] = rs.getString("lon_instalment_value");
                i++;
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
    
    public Object[][] getTypeLoan(){
        try {
            mysqlConnect();
            
            psSelect = connection.prepareStatement("SELECT lon_id,lon_loan FROM cr_loan GROUP BY lon_loan");
            statements.add(psSelect);
            rs = psSelect.executeQuery(); 
            
            rs.last();
            int row = rs.getRow();
            rs.beforeFirst();
            
            Object[][] data = new Object[row][2];
            
            int i = 0;
            while(rs.next()){
                data[i][0] = rs.getInt("lon_id");
                data[i][1] = rs.getString("lon_loan");
                i++;
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
    
    public Object[][] getLoanMonth(int value){
        try {
            mysqlConnect();
            
            psSelect = connection.prepareStatement("SELECT lon_id,lon_total_month FROM cr_loan WHERE lon_loan=? ORDER BY lon_total_month");
            statements.add(psSelect);
            psSelect.setInt(1, value);
            rs = psSelect.executeQuery(); 
            
            rs.last();
            int row = rs.getRow();
            rs.beforeFirst();
            
            Object[][] data = new Object[row][2];
            
            int i = 0;
            while(rs.next()){
                data[i][0] = rs.getInt("lon_id");
                data[i][1] = rs.getString("lon_total_month");
                i++;
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
    
    public int getInstalmentValue(int id, int month){
        try {
            mysqlConnect();
            
            psSelect = connection.prepareStatement("SELECT lon_instalment_value FROM cr_loan WHERE lon_loan = ? AND lon_total_month=?");
            statements.add(psSelect);
            
            psSelect.setInt(1, id); 
            psSelect.setInt(2, month); 
            rs = psSelect.executeQuery(); 
            
            rs.last();
            int row = rs.getRow();
            rs.beforeFirst();
            
            Object[][] data = new Object[row][3];
            
            int result = 0;
            if(rs.next()){            
                result = rs.getInt("lon_instalment_value");
            }
            connection.commit();
            
            return result;
        } catch (SQLException sqle) {
            printSQLException(sqle);
        } finally {
            releaseMysqlResource();
        }
        return 0;
    }
            
    public DefaultTableModel getLoanData() {        
        try {
            mysqlConnect();
            
            psSelect = connection.prepareStatement("SELECT lcr_id, cdr_id, cdr_name, lon_loan, lon_total_month,lon_instalment_value, lcr_date, lcr_status, lcr_fine, lcr_policenum, lcr_year, lcr_merk FROM cr_loan_creditor INNER JOIN cr_loan ON cr_loan_creditor.lcr_loan = cr_loan.lon_id INNER JOIN cr_creditor ON cr_loan_creditor.lcr_creditor = cr_creditor.cdr_id");
            statements.add(psSelect);
            rs = psSelect.executeQuery();   
            
            connection.commit();
            
            String[] colum = {"ID","ID Customer","Creditor","Pinjaman","Bulan","Cicilan","Tanggal","Status", "Jaminan", "No Polisi", "Tahun", "Merk"};
            return TableData.buildTableModel(rs,colum);
        } catch (SQLException sqle) {
            printSQLException(sqle);
        } finally {
            releaseMysqlResource();
        }
        return null;
    }
    
    public Object[] getLoan(int id) {        
        try {
            mysqlConnect();
            
            psSelect = connection.prepareStatement("SELECT * FROM cr_loan_creditor WHERE lcr_id=?");
            statements.add(psSelect);
            psSelect.setInt(1, id);
            rs = psSelect.executeQuery();   
            
            Object[] data = new Object[6];
            if(rs.next()){
                data[0] = rs.getString("lcr_loan");
                data[1] = rs.getString("lcr_date");
                data[2] = rs.getString("lcr_fine");
                data[3] = rs.getString("lcr_policenum");
                data[4] = rs.getString("lcr_year");
                data[5] = rs.getString("lcr_merk");
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
    
    public boolean addLoan(Object[] data){
        try{
            mysqlConnect();
            
            psInsert = connection.prepareStatement("INSERT into cr_loan_creditor(lcr_creditor,lcr_loan,lcr_date, lcr_fine, lcr_policenum,lcr_year,lcr_merk, lcr_user) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            statements.add(psInsert);

            psInsert.setString(1, data[0].toString());
            psInsert.setString(2, data[1].toString());
            psInsert.setString(3, data[2].toString());
            psInsert.setString(4, data[3].toString());
            psInsert.setString(5, data[4].toString());
            psInsert.setString(6, data[5].toString());
            psInsert.setString(7, data[6].toString());
            psInsert.setString(8, Config.userId);
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
    
    public boolean addLoanType(Object[] data){
        try{
            mysqlConnect();
            
            psInsert = connection.prepareStatement("INSERT into cr_loan(lon_loan,lon_total_month,lon_instalment_value) VALUES (?,?,?)");
            statements.add(psInsert);

            psInsert.setString(1, data[0].toString());
            psInsert.setString(2, data[1].toString());
            psInsert.setString(3, data[2].toString());
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
    
    public boolean updateLoan(Object[] data, int id){
        try{
            mysqlConnect();
            
            psUpdate = connection.prepareStatement("UPDATE cr_loan_creditor SET lcr_creditor=?, lcr_loan=?, lcr_date=?, lcr_fine=?, lcr_policenum=?, lcr_year=?, lcr_merk=? WHERE lcr_id=?");
            statements.add(psUpdate);

            psUpdate.setString(1, data[0].toString());
            psUpdate.setString(2, data[1].toString());
            psUpdate.setString(3, data[2].toString());
            psUpdate.setString(4, data[3].toString());
            
            psUpdate.setString(5, data[4].toString());
            psUpdate.setString(6, data[5].toString());
            psUpdate.setString(7, data[6].toString());
            psUpdate.setInt(8, id);
            
            psUpdate.executeUpdate();
            
            connection.commit();
            
            return true;
            
        } catch (SQLException sqle) {
            printSQLException(sqle);
        } finally {
            releaseMysqlResource();
        }
        return false;
    }
    
    public boolean deleteLoan(int id){
        try{
            mysqlConnect();
            
            s = connection.createStatement();
            statements.add(s);
            
            psDelete = connection.prepareStatement("DELETE from cr_loan_creditor where lcr_id=?");
            psDelete.setInt(1, id);
            
            psDelete.executeUpdate();
            
            psDelete = connection.prepareStatement("DELETE from cr_instalment where ins_loan_creditor=?");
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
    
    public boolean deleteLoanType(int id){
        try{
            mysqlConnect();
                        
            psDelete = connection.prepareStatement("DELETE from cr_loan_creditor where lcr_loan=?");
            statements.add(psDelete);
            
            psDelete.setInt(1, id);            
            psDelete.executeUpdate();
            
            psDelete = connection.prepareStatement("DELETE from cr_loan where lon_id=?");
            statements.add(psDelete);
            
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
