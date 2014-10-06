/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sketchproject.mbapp.models.credit;

import com.sketchproject.mbapp.core.MysqlDatabase;
import com.sketchproject.mbapp.utility.TableData;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Asus
 */
public class ReportModel extends MysqlDatabase{
    
    public DefaultTableModel getReportData(String from, String to) {
        try {
            mysqlConnect();
            
            String sql;
            if(from.equals("-1") || to.equals("-1")){
                sql = "SELECT ins_date, cdr_name, lon_loan,lon_instalment_value, lon_total_month, ins_penalty,ROUND((ins_payment - (lon_loan/lon_total_month))) AS margin, lcr_fine, lcr_policenum, lcr_year, lcr_merk FROM cr_instalment INNER JOIN cr_loan_creditor ON ins_loan_creditor = lcr_id INNER JOIN cr_creditor ON lcr_creditor = cdr_id INNER JOIN cr_loan ON lcr_loan = lon_id ORDER BY ins_date DESC";
                psSelect = connection.prepareStatement(sql);
                statements.add(psSelect);
            }
            else{
                sql = "SELECT ins_date, cdr_name, lon_loan,lon_instalment_value, lon_total_month, ins_penalty,ROUND((ins_payment - (lon_loan/lon_total_month))) AS margin, lcr_fine, lcr_policenum, lcr_year, lcr_merk FROM cr_instalment INNER JOIN cr_loan_creditor ON ins_loan_creditor = lcr_id INNER JOIN cr_creditor ON lcr_creditor = cdr_id INNER JOIN cr_loan ON lcr_loan = lon_id AND ins_date BETWEEN ? AND ? ORDER BY ins_date DESC";
                      
                psSelect = connection.prepareStatement(sql);
                statements.add(psSelect);
                psSelect.setString(1, from);
                psSelect.setString(2, to);
            }

            rs = psSelect.executeQuery();

            connection.commit();

            String[] colum = {"Tanggal", "Customer", "Pinjaman", "Cicilan", "Jumlah X", "Denda", "Margin","Jaminan", "No Polisi", "Tahun", "Merk"};
            return TableData.buildTableModel(rs, colum);
        } catch (SQLException sqle) {
            printSQLException(sqle);
        } finally {
            releaseMysqlResource();
        }
        return null;
    }
    
    public Object[] getRevenue(String from, String to) {
        try {
            mysqlConnect();

            String sql;
            if(from.equals("-1") || to.equals("-1")){
                sql = "SELECT ROUND(SUM((lon_loan/lon_total_month))) AS loan, ROUND(SUM(ins_payment)) instalment, ROUND(SUM((ins_payment - (lon_loan/lon_total_month)))) AS revenue FROM cr_instalment INNER JOIN cr_loan_creditor ON ins_loan_creditor = lcr_id INNER JOIN cr_creditor ON lcr_creditor = cdr_id INNER JOIN cr_loan ON lcr_loan = lon_id";
                psSelect = connection.prepareStatement(sql);
                statements.add(psSelect);
            }
            else{
                sql = "SELECT ROUND(SUM((lon_loan/lon_total_month))) AS loan, ROUND(SUM(ins_payment)) instalment, ROUND(SUM((ins_payment - (lon_loan/lon_total_month)))) AS revenue FROM cr_instalment INNER JOIN cr_loan_creditor ON ins_loan_creditor = lcr_id INNER JOIN cr_creditor ON lcr_creditor = cdr_id INNER JOIN cr_loan ON lcr_loan = lon_id WHERE ins_date BETWEEN ? AND ?";
                
                psSelect = connection.prepareStatement(sql);
                statements.add(psSelect);
                psSelect.setString(1, from);
                psSelect.setString(2, to);
            }

            rs = psSelect.executeQuery();

            rs.next();
            
            Object[] data = new Object[3];
            data[0] = rs.getString("loan");
            data[1] = rs.getString("instalment");
            data[2] = rs.getString("revenue");

            connection.commit();

            return data;

        } catch (SQLException sqle) {
            printSQLException(sqle);
        } finally {
            releaseMysqlResource();
        }
        return null;
    }
    
    public static int getTotalCutomer(){
        try {
            mysqlConnect();
            
            psSelect = connection.prepareStatement("SELECT IFNULL(COUNT(*),0) as customer FROM cr_creditor");
            statements.add(psSelect);
            rs = psSelect.executeQuery(); 
            
            int result = 0;
            if(rs.next()){
                result = rs.getInt("customer");
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
    
    public static int getTotalLoan(){
        try {
            mysqlConnect();
            
            psSelect = connection.prepareStatement("SELECT SUM((lon_loan/lon_total_month)) AS loan, SUM(ins_payment) instalment, SUM((ins_payment - (lon_loan/lon_total_month))) AS revenue FROM cr_instalment INNER JOIN cr_loan_creditor ON ins_loan_creditor = lcr_id INNER JOIN cr_creditor ON lcr_creditor = cdr_id INNER JOIN cr_loan ON lcr_loan = lon_id");
            statements.add(psSelect);
            rs = psSelect.executeQuery(); 
            
            int result = 0;
            if(rs.next()){
                result = rs.getInt("loan");
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
    
    public static int getTotalInstalment(){
        try {
            mysqlConnect();
            
            psSelect = connection.prepareStatement("SELECT SUM((lon_loan/lon_total_month)) AS loan, SUM(ins_payment) instalment, SUM((ins_payment - (lon_loan/lon_total_month))) AS revenue FROM cr_instalment INNER JOIN cr_loan_creditor ON ins_loan_creditor = lcr_id INNER JOIN cr_creditor ON lcr_creditor = cdr_id INNER JOIN cr_loan ON lcr_loan = lon_id");
            statements.add(psSelect);
            rs = psSelect.executeQuery(); 
            
            int result = 0;
            if(rs.next()){
                result = rs.getInt("instalment");
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
    
    public static int getTotalRevenue(){
        return getTotalInstalment()-getTotalLoan();
    }
}
