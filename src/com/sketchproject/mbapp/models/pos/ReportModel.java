/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sketchproject.mbapp.models.pos;

import com.sketchproject.mbapp.core.MysqlDatabase;
import com.sketchproject.mbapp.utility.TableData;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Asus
 */
public class ReportModel extends MysqlDatabase {

    public DefaultTableModel getReportData(String from, String to, Object filter, String name, String contact) {
        try {
            mysqlConnect();
            
            String sql = "";
            if(from.equals("-1") && to.equals("-1")){
                sql = "SELECT trn_id, DATE(trn_created_at) as date, cus_name,mtr_name,mtr_purchase_price, mtr_resale_price, mtr_resale_price-mtr_purchase_price as revenue FROM ps_transaction INNER JOIN ps_customer ON ps_transaction.trn_customer = ps_customer.cus_id INNER JOIN ps_motor ON ps_transaction.trn_motor = ps_motor.mtr_id WHERE cus_name LIKE '%"+name+"%' AND cus_contact LIKE '%"+contact+"%' ORDER BY trn_date DESC";
                psSelect = connection.prepareStatement(sql);
                statements.add(psSelect);
            }
            else{
                switch (filter.toString()) {
                case "ALL":
                    sql = "SELECT trn_id, DATE(trn_created_at) as date, cus_name,mtr_name,mtr_purchase_price, mtr_resale_price, mtr_resale_price-mtr_purchase_price as revenue FROM ps_transaction INNER JOIN ps_customer ON ps_transaction.trn_customer = ps_customer.cus_id INNER JOIN ps_motor ON ps_transaction.trn_motor = ps_motor.mtr_id WHERE cus_name LIKE '%"+name+"%' AND cus_contact LIKE '%"+contact+"%' AND trn_date BETWEEN ? AND ? ORDER BY trn_date DESC";
                    
                    break;
                case "ADIRA":
                    sql = "SELECT trn_id, DATE(trn_created_at) as date, cus_name,mtr_name,mtr_purchase_price, mtr_resale_price, mtr_resale_price-mtr_purchase_price as revenue FROM ps_transaction INNER JOIN ps_customer ON ps_transaction.trn_customer = ps_customer.cus_id INNER JOIN ps_motor ON ps_transaction.trn_motor = ps_motor.mtr_id AND trn_instalment='ADIRA' AND cus_name LIKE '%"+name+"%' AND cus_contact LIKE '%"+contact+"%' AND trn_date BETWEEN ? AND ? ORDER BY trn_date DESC";
                    
                    break;
                case "CS":
                    sql = "SELECT trn_id, DATE(trn_created_at) as date, cus_name,mtr_name,mtr_purchase_price, mtr_resale_price, mtr_resale_price-mtr_purchase_price as revenue FROM ps_transaction INNER JOIN ps_customer ON ps_transaction.trn_customer = ps_customer.cus_id INNER JOIN ps_motor ON ps_transaction.trn_motor = ps_motor.mtr_id AND trn_instalment='CS' AND cus_name LIKE '%"+name+"%' AND cus_contact LIKE '%"+contact+"%' AND trn_date BETWEEN ? AND ? ORDER BY trn_date DESC";
                    
                    break; 
                }      
                psSelect = connection.prepareStatement(sql);
                statements.add(psSelect);
                psSelect.setString(1, from);
                psSelect.setString(2, to);
            }

            rs = psSelect.executeQuery();

            connection.commit();

            String[] colum = {"NO PK","Tanggal", "Customer", "Produk", "Hara Beli", "Harga Jual", "Margin"};
            return TableData.buildTableModel(rs, colum);
        } catch (SQLException sqle) {
            printSQLException(sqle);
        } finally {
            releaseMysqlResource();
        }
        return null;
    }

    public Object[] getRevenue(String from, String to, Object filter, String name, String contact) {
        try {
            mysqlConnect();

            String sql = "";
            if(from.equals("-1") && to.equals("-1")){
                sql = "SELECT SUM(mtr_purchase_price) as purchase, SUM(mtr_resale_price) as selling, SUM(mtr_resale_price-mtr_purchase_price) as revenue FROM ps_transaction INNER JOIN ps_customer ON ps_transaction.trn_customer = ps_customer.cus_id INNER JOIN ps_motor ON ps_transaction.trn_motor = ps_motor.mtr_id WHERE cus_name LIKE '%"+name+"%' AND cus_contact LIKE '%"+contact+"%'";
                psSelect = connection.prepareStatement(sql);
                statements.add(psSelect);
            }
            else{
                switch (filter.toString()) {
                case "ALL":
                    sql = "SELECT IFNULL(SUM(mtr_purchase_price),0) as purchase, IFNULL(SUM(mtr_resale_price),0) as selling, IFNULL(SUM(mtr_resale_price-mtr_purchase_price),0) as revenue FROM ps_transaction INNER JOIN ps_customer ON ps_transaction.trn_customer = ps_customer.cus_id INNER JOIN ps_motor ON ps_transaction.trn_motor = ps_motor.mtr_id AND cus_name LIKE '%"+name+"%' AND cus_contact LIKE '%"+contact+"%' AND trn_date between ? and ? ORDER BY trn_date DESC";
                    
                    break;
                case "ADIRA":
                    sql = "SELECT IFNULL(SUM(mtr_purchase_price),0) as purchase, IFNULL(SUM(mtr_resale_price),0) as selling, IFNULL(SUM(mtr_resale_price-mtr_purchase_price),0) as revenue FROM ps_transaction INNER JOIN ps_customer ON ps_transaction.trn_customer = ps_customer.cus_id INNER JOIN ps_motor ON ps_transaction.trn_motor = ps_motor.mtr_id AND trn_instalment='ADIRA' AND cus_name LIKE '%"+name+"%' AND cus_contact LIKE '%"+contact+"%' AND trn_date between ? and ? ORDER BY trn_date DESC";
                    
                    break;
                case "CS":
                    sql = "SELECT IFNULL(SUM(mtr_purchase_price),0) as purchase, IFNULL(SUM(mtr_resale_price),0) as selling, IFNULL(SUM(mtr_resale_price-mtr_purchase_price),0) as revenue FROM ps_transaction INNER JOIN ps_customer ON ps_transaction.trn_customer = ps_customer.cus_id INNER JOIN ps_motor ON ps_transaction.trn_motor = ps_motor.mtr_id AND trn_instalment='CS' AND cus_name LIKE '%"+name+"%' AND cus_contact LIKE '%"+contact+"%' AND trn_date between ? and ? ORDER BY trn_date DESC";
                    
                    break; 
                }      
                psSelect = connection.prepareStatement(sql);
                statements.add(psSelect);
                psSelect.setString(1, from);
                psSelect.setString(2, to);
            }

            rs = psSelect.executeQuery();
            
            Object[] data = null;
            if(rs.next()){
                data = new Object[3];
                data[0] = rs.getString("purchase");
                data[1] = rs.getString("selling");
                data[2] = rs.getString("revenue");
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
    
    public static String getTransactionTotal(){
        try {
            mysqlConnect();
            
            psSelect = connection.prepareStatement("SELECT IFNULL(COUNT(*),0) as sales FROM ps_transaction");
            statements.add(psSelect);
            rs = psSelect.executeQuery(); 
            
            String result = "0";
            if(rs.next()){
                result = rs.getString("sales");
            }
                        
            connection.commit();
            
            return result;
        } catch (SQLException sqle) {
            printSQLException(sqle);
        } finally {
            releaseMysqlResource();
        }
        return "0";        
    }
    
    public static String getTotalSales(){
        try {
            mysqlConnect();
            
            psSelect = connection.prepareStatement("SELECT IFNULL(SUM(mtr_resale_price),0) as selling FROM ps_transaction INNER JOIN ps_motor ON ps_transaction.trn_motor = ps_motor.mtr_id");
            statements.add(psSelect);
            rs = psSelect.executeQuery(); 
            
            String result = "0";
            if(rs.next()){
                result = rs.getString("selling");
            }
            
            
            connection.commit();
            
            return result;
        } catch (SQLException sqle) {
            printSQLException(sqle);
        } finally {
            releaseMysqlResource();
        }
        return "0";
    }
    
    public static String getTotalRevenue(){
        try {
            mysqlConnect();
            
            psSelect = connection.prepareStatement("SELECT IFNULL(SUM(mtr_resale_price-mtr_purchase_price),0) as revenue FROM ps_transaction INNER JOIN ps_motor ON ps_transaction.trn_motor = ps_motor.mtr_id");
            statements.add(psSelect);
            rs = psSelect.executeQuery(); 
            
            String result = "0";
            if(rs.next()){
                result = rs.getString("revenue");
            }
            
            
            connection.commit();
            
            return result;
        } catch (SQLException sqle) {
            printSQLException(sqle);
        } finally {
            releaseMysqlResource();
        }
        return "0";
    }
    
    public static String getTotalAssets(){
        try {
            mysqlConnect();
            
            psSelect = connection.prepareStatement("SELECT IFNULL(SUM(mtr_purchase_price),0) as assets FROM ps_motor WHERE mtr_status='STOCK'");
            statements.add(psSelect);
            rs = psSelect.executeQuery(); 
            
            String result = "0";
            if(rs.next()){
                result = rs.getString("assets");
            }
            
            connection.commit();
            
            return result;
        } catch (SQLException sqle) {
            printSQLException(sqle);
        } finally {
            releaseMysqlResource();
        }
        return "0";
    }
    
    public static String getTotalStock(){
        try {
            mysqlConnect();
            
            psSelect = connection.prepareStatement("SELECT IFNULL(COUNT(*),0) as stock FROM ps_motor WHERE mtr_status='STOCK'");
            statements.add(psSelect);
            rs = psSelect.executeQuery(); 
            
            String result = "0";
            if(rs.next()){
                result = rs.getString("stock");
            }
            
            
            
            connection.commit();
            
            return result;
        } catch (SQLException sqle) {
            printSQLException(sqle);
        } finally {
            releaseMysqlResource();
        }
        return "0";
    }
    
    public static String getTotalSold(){
        try {
            mysqlConnect();
            
            psSelect = connection.prepareStatement("SELECT IFNULL(COUNT(*),0) as sold FROM ps_motor WHERE mtr_status='SOLD'");
            statements.add(psSelect);
            rs = psSelect.executeQuery(); 
            
            String result="0";
            if(rs.next()){
                result = rs.getString("sold");
            }
            
            connection.commit();
            
            return result;
        } catch (SQLException sqle) {
            printSQLException(sqle);
        } finally {
            releaseMysqlResource();
        }
        return "0";
    }
    
    public static String getItemRevenueAvg(){
        try {
            mysqlConnect();
            
            psSelect = connection.prepareStatement("SELECT IFNULL(FLOOR(AVG(mtr_resale_price-mtr_purchase_price)),0) as itemavg FROM ps_motor");
            statements.add(psSelect);
            rs = psSelect.executeQuery(); 
            
            String result = "0";
            if(rs.next()){
                result = rs.getString("itemavg");
            }
            
            
            
            connection.commit();
            
            return result;
        } catch (SQLException sqle) {
            printSQLException(sqle);
        } finally {
            releaseMysqlResource();
        }
        return "0";
    }
        
    public static String getMonthRevenueAvg(){
        try {
            mysqlConnect();
            
            psSelect = connection.prepareStatement("SELECT IFNULL(SUM(mtr_resale_price-mtr_purchase_price),0) as monthavg FROM ps_transaction INNER JOIN ps_motor ON ps_transaction.trn_motor = ps_motor.mtr_id GROUP BY MONTH(trn_created_at)");
            statements.add(psSelect);
            rs = psSelect.executeQuery(); 
            
            String result = "0";
            if(rs.next()){
                result = rs.getString("monthavg");
            }
                        
            connection.commit();
            
            return result;
        } catch (SQLException sqle) {
            printSQLException(sqle);
        } finally {
            releaseMysqlResource();
        }
        return "0";
    }
    
    public static String getRevenueThisMonth(){
        try {
            mysqlConnect();
            
            psSelect = connection.prepareStatement("SELECT IFNULL(SUM(mtr_resale_price-mtr_purchase_price),0) as revenue FROM ps_transaction INNER JOIN ps_motor ON ps_transaction.trn_motor = ps_motor.mtr_id WHERE MONTH(trn_created_at) = MONTH(now())");
            statements.add(psSelect);
            rs = psSelect.executeQuery(); 
            
            String result = "0";
            if(rs.next()){
                result = rs.getString("revenue");
            }
            
            
            
            connection.commit();
            
            return result;
        } catch (SQLException sqle) {
            printSQLException(sqle);
        } finally {
            releaseMysqlResource();
        }
        return "0";
    }
}
