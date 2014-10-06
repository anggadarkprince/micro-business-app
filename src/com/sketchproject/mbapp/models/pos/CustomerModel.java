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
public class CustomerModel extends MysqlDatabase {

    public DefaultTableModel getCustomerData(String keyword) {        
        try {
            mysqlConnect();
            
            psSelect = connection.prepareStatement(""
                    + "SELECT "
                    + "cus_id AS 'ID Pelanggan',"
                    + "cus_name AS Nama,"
                    + "cus_address AS Alamat,"
                    + "cus_contact AS 'No Telp',"
                    + "cus_PID AS 'No KTP',"
                    + "cus_mother AS 'Nama Ibu' "
                    + "FROM ps_customer WHERE cus_id LIKE '%"+keyword+"%' OR cus_name LIKE '%"+keyword+"%' OR cus_address LIKE '%"+keyword+"%' OR cus_contact LIKE '%"+keyword+"%' OR cus_PID LIKE '%"+keyword+"%' OR cus_mother LIKE '%"+keyword+"%'");
            statements.add(psSelect);
            rs = psSelect.executeQuery();   
            
            connection.commit();
            
            String[] colum = {"ID Pelanggan","Nama","Alamat","No Telp","No KTP","Nama Ibu"};
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
            
            psSelect = connection.prepareStatement("SELECT * FROM ps_customer WHERE cus_id=?");
            statements.add(psSelect);
            
            psSelect.setInt(1, id);
            rs = psSelect.executeQuery(); 
                        
            rs.next();
            
            Object[] data = new Object[4];
            data[0] = rs.getString("cus_address");
            data[1] = rs.getString("cus_contact");
            data[2] = rs.getString("cus_pid");
            data[3] = rs.getString("cus_mother");
            
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
            
            psInsert = connection.prepareStatement("INSERT into ps_customer(cus_name,cus_address,cus_contact,cus_pid,cus_mother) VALUES (?, ?, ?, ?, ?)");
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
            
            psUpdate = connection.prepareStatement("UPDATE ps_customer SET cus_name=?, cus_address=?, cus_contact=?, cus_pid=?, cus_mother=? where cus_id=?");
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
            
            psDelete = connection.prepareStatement("DELETE from ps_customer where cus_id=?");
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
