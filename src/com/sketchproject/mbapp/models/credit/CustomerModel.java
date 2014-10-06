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
public class CustomerModel extends MysqlDatabase {
    
    public DefaultTableModel getCustomerData(String keyword) {        
        try {
            mysqlConnect();
            
            psSelect = connection.prepareStatement(""
                    + "SELECT "
                    + "cdr_id,"
                    + "cdr_name,"
                    + "cdr_address,"
                    + "cdr_contact,"
                    + "cdr_PID,"
                    + "cdr_job "
                    + "FROM cr_creditor WHERE cdr_id LIKE '%"+keyword+"%' OR cdr_name LIKE '%"+keyword+"%' OR cdr_address LIKE '%"+keyword+"%' OR cdr_contact LIKE '%"+keyword+"%' OR cdr_PID LIKE '%"+keyword+"%' OR cdr_job LIKE '%"+keyword+"%'");
            statements.add(psSelect);
            rs = psSelect.executeQuery();   
            
            connection.commit();
            
            String[] colum = {"ID","Nama","Alamat","No Telp","No KTP","Pekerjaan"};
            return TableData.buildTableModel(rs,colum);
        } catch (SQLException sqle) {
            printSQLException(sqle);
        } finally {
            releaseMysqlResource();
        }
        return null;
    }
    
    public Object[][] getCustomer(){
        try {
            mysqlConnect();
            
            psSelect = connection.prepareStatement("SELECT cus_id, cus_name FROM ps_customer");
            statements.add(psSelect);
            rs = psSelect.executeQuery(); 
            
            rs.last();
            int row = rs.getRow();
            rs.beforeFirst();
            
            Object[][] data = new Object[row][2];
            
            int i = 0;
            while(rs.next()){
                data[i][0] = rs.getInt("cus_id");
                data[i][1] = rs.getString("cus_name");
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
    
    public Object[] getCustomerData(int id){
        try {
            mysqlConnect();
            
            psSelect = connection.prepareStatement("SELECT * FROM cr_creditor WHERE cdr_id=?");
            statements.add(psSelect);
            
            psSelect.setInt(1, id);
            rs = psSelect.executeQuery(); 
            
            Object[] data = new Object[6];
            if(rs.next()){
                data[0] = rs.getString("cdr_name");
                data[1] = rs.getString("cdr_address");
                data[2] = rs.getString("cdr_contact");
                data[3] = rs.getString("cdr_pid");
                data[4] = rs.getString("cdr_job");
                data[5] = id;
            }
            else{
                data[0] = "-";
                data[1] = "-";
                data[2] = "-";
                data[3] = "-";
                data[4] = "-";
                data[5] = "-";
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
    
    public boolean addCustomer(Object[] data){
        try{
            mysqlConnect();
            
            psInsert = connection.prepareStatement("INSERT into cr_creditor(cdr_name,cdr_address,cdr_contact,cdr_pid,cdr_job) VALUES (?, ?, ?, ?, ?)");
            statements.add(psInsert);

            psInsert.setString(1, data[0].toString());
            psInsert.setString(2, data[1].toString());
            psInsert.setString(3, data[2].toString());
            psInsert.setString(4, data[3].toString());
            psInsert.setString(5, data[4].toString());
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
    
    public boolean updateCustomer(Object[] data, int id){
        try{
            mysqlConnect();
            
            psUpdate = connection.prepareStatement("UPDATE cr_creditor SET cdr_name=?, cdr_address=?, cdr_contact=?, cdr_pid=?, cdr_job=? where cdr_id=?");
            statements.add(psUpdate);

            psUpdate.setString(1, data[0].toString());
            psUpdate.setString(2, data[1].toString());
            psUpdate.setString(3, data[2].toString());
            psUpdate.setString(4, data[3].toString());
            psUpdate.setString(5, data[4].toString());
            psUpdate.setInt(6, id);
            
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
    
    public boolean deleteCustomer(int customerId){
        try{
            mysqlConnect();
            
            s = connection.createStatement();
            statements.add(s);
            
            psDelete = connection.prepareStatement("DELETE from cr_creditor where cdr_id=?");
            psDelete.setInt(1, customerId);
            
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
