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
public class InventoryModel extends MysqlDatabase{
            
    public Object[][] getCategory(){
        try {
            mysqlConnect();
            
            psSelect = connection.prepareStatement("SELECT typ_id, typ_name FROM ps_type");
            statements.add(psSelect);
            rs = psSelect.executeQuery(); 
            
            rs.last();
            int row = rs.getRow();
            rs.beforeFirst();
            
            Object[][] data = new Object[row][2];
            
            int i = 0;
            while(rs.next()){
                data[i][0] = rs.getInt("typ_id");
                data[i][1] = rs.getString("typ_name");
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
    
    public Object[][] getProduct(int id){
        try {
            mysqlConnect();
            
            psSelect = connection.prepareStatement("SELECT mtr_id, mtr_name, mtr_policenum FROM ps_motor WHERE mtr_type=? AND mtr_status='STOCK'");
            statements.add(psSelect);
            
            psSelect.setInt(1, id); 
            rs = psSelect.executeQuery(); 
            
            rs.last();
            int row = rs.getRow();
            rs.beforeFirst();
            
            Object[][] data = new Object[row][3];
            
            int i = 0;
            while(rs.next()){
                data[i][0] = rs.getInt("mtr_id");
                data[i][1] = rs.getString("mtr_name");
                data[i][2] = rs.getString("mtr_policenum");
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
    
    public Object[] getProductData(int id){
        try {
            mysqlConnect();
            
            psSelect = connection.prepareStatement("SELECT * FROM ps_motor WHERE mtr_id=? AND mtr_status = 'STOCK'");
            statements.add(psSelect);
            
            psSelect.setInt(1, id);
            rs = psSelect.executeQuery(); 
                        
            rs.next();
            
            Object[] data = new Object[7];
            data[0] = rs.getString("mtr_stnk");
            data[1] = rs.getString("mtr_chasis");
            data[2] = rs.getString("mtr_color");
            data[3] = rs.getString("mtr_year");
            data[4] = rs.getString("mtr_policenum");
            data[5] = rs.getString("mtr_purchase_price");
            data[6] = rs.getString("mtr_resale_price");
            
            connection.commit();
            
            return data;
        } catch (SQLException sqle) {
            printSQLException(sqle);
        } finally {
            releaseMysqlResource();
        }
        return null;
    }
    
    public DefaultTableModel getInventoryData() {        
        try {
            mysqlConnect();
            
            psSelect = connection.prepareStatement("SELECT mtr_id, typ_name, mtr_name, category, mtr_stnk, mtr_chasis, mtr_color, mtr_year, mtr_policenum, mtr_purchase_price,mtr_resale_price, mtr_status FROM ps_motor INNER JOIN ps_type ON ps_motor.mtr_type = ps_type.typ_id");
            statements.add(psSelect);
            rs = psSelect.executeQuery();   
            
            connection.commit();
            
            String[] colum = {"ID Motor","Merk","Jenis", "Type","STNK","Rangka","Warna","Tahun","No Polisi","Harga Beli","Harga Jual","Status"};
            return TableData.buildTableModel(rs,colum);
        } catch (SQLException sqle) {
            printSQLException(sqle);
        } finally {
            releaseMysqlResource();
        }
        return null;
    }
    
    public boolean addInventory(Object[] data){
        try{
            mysqlConnect();
            
            psInsert = connection.prepareStatement("INSERT into ps_motor(mtr_type,category,mtr_name,mtr_stnk,mtr_chasis,mtr_color,mtr_year, mtr_policenum, mtr_purchase_price, mtr_resale_price) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            statements.add(psInsert);

            psInsert.setString(1, data[0].toString());
            psInsert.setString(2, data[1].toString());
            psInsert.setString(3, data[2].toString());
            psInsert.setString(4, data[3].toString());
            psInsert.setString(5, data[4].toString());
            psInsert.setString(6, data[5].toString());
            psInsert.setString(7, data[6].toString());
            psInsert.setString(8, data[7].toString());
            psInsert.setString(9, data[8].toString());
            psInsert.setString(10, data[9].toString());
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
    
    public boolean addCategory(String data){
        try{
            mysqlConnect();
            
            psInsert = connection.prepareStatement("INSERT into ps_type(typ_name) VALUES (?)");
            statements.add(psInsert);

            psInsert.setString(1, data);
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
    
    public boolean updateInventory(Object[] data, int id){
        try{
            mysqlConnect();
            
            psUpdate = connection.prepareStatement("UPDATE ps_motor SET mtr_type=?, mtr_name=?, mtr_stnk=?, mtr_chasis=?, mtr_color=?, mtr_year=?, mtr_policenum=?, mtr_purchase_price=?, mtr_resale_price=? where mtr_id=?");
            statements.add(psUpdate);

            psUpdate.setString(1, data[0].toString());
            psUpdate.setString(2, data[1].toString());
            psUpdate.setString(3, data[2].toString());
            psUpdate.setString(4, data[3].toString());
            psUpdate.setString(5, data[4].toString());
            psUpdate.setString(6, data[5].toString());
            psUpdate.setString(7, data[6].toString());
            psUpdate.setString(8, data[7].toString());
            psUpdate.setString(9, data[8].toString());
            psUpdate.setInt(10, id);
            
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
    
    public boolean deleteInventory(int id){
        try{
            mysqlConnect();
            
            s = connection.createStatement();
            statements.add(s);
            
            psDelete = connection.prepareStatement("DELETE from ps_motor where mtr_id=?");
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
    
    public boolean deleteCategory(int id){
        try{
            mysqlConnect();
                        
            psDelete = connection.prepareStatement("DELETE from ps_motor where mtr_type=?");
            statements.add(psDelete);
            
            psDelete.setInt(1, id);            
            psDelete.executeUpdate();
            
            psDelete = connection.prepareStatement("DELETE from ps_type where typ_id=?");
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
