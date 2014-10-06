/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sketchproject.mbapp.models;

import com.sketchproject.mbapp.core.Config;
import com.sketchproject.mbapp.core.MysqlDatabase;
import com.sketchproject.mbapp.utility.BCrypt;
import java.sql.SQLException;

/**
 *
 * @author Asus
 */
public class AppModel extends MysqlDatabase{
    
    public AppModel(){
        
    }
    
    public static String getNextId(String table){
        try {
            mysqlConnect();
            //SELECT Auto_increment FROM information_schema.tables WHERE table_name='the_table_you_want';
            psSelect = connection.prepareStatement("SHOW TABLE STATUS LIKE '"+table+"'");
            statements.add(psSelect);
            rs = psSelect.executeQuery(); 
            rs.next();
            
            String id = rs.getString("Auto_increment");                    
            
            connection.commit();
            
            return id;
        } catch (SQLException sqle) {
            printSQLException(sqle);
        } finally {
            releaseMysqlResource();
        }
        return "1";
    }
    
    public boolean checkAuthorize(String userID, String password){
        try{
            mysqlConnect();        
                    
            psSelect = connection.prepareStatement("SELECT * FROM ap_user WHERE usr_id=?");
            statements.add(psSelect);

            psSelect.setString(1,userID);
            rs = psSelect.executeQuery();
            
            if(rs.next()){
                if(BCrypt.checkpw(password, rs.getString("usr_password"))){
                    Config.idSession = String.valueOf((int) (Math.random() * 1000));
                    Config.userId = userID;
                    Config.hashPassword = rs.getString("usr_password");
                    Config.userName = rs.getString("usr_name");
                    System.out.println(Config.hashPassword);
                    connection.commit();
                    
                    return true;
                }
                return false;
            }
            return false;            
        }
        catch (SQLException sqle) {
            printSQLException(sqle);
        }
        finally{
            releaseMysqlResource();
        }
        
        return false;
    }
    
    public Object[] getSetting(String app){
        Object[] data = null;
        try{
            mysqlConnect();        
                    
            psSelect = connection.prepareStatement("SELECT * FROM ap_setting WHERE stg_application=?");
            statements.add(psSelect);
            
            psSelect.setString(1,app);
            rs = psSelect.executeQuery();
            
            rs.last();
            int row = rs.getRow();
            rs.beforeFirst();
            
            data = new Object[row];
            int i = 0;
            while(rs.next()){
                data[i] = rs.getString("stg_value");
                i++;
            }
            
            connection.commit();
        }
        catch (SQLException sqle) {
            printSQLException(sqle);
        }
        finally{
            releaseMysqlResource();
        }
        
        return data;
    }
    
    public boolean updateSetting(Object[][] data, String app){
        try{
            mysqlConnect();
            
            for(int i=0;i<data[0].length;i++){
                psUpdate = connection.prepareStatement("UPDATE ap_setting set stg_value=? where stg_id=?");
                statements.add(psUpdate);           
                psUpdate.setString(1, data[0][i].toString());
                psUpdate.setString(2, app+String.valueOf(i+1));
                psUpdate.executeUpdate();
            }
                        
            psUpdate = connection.prepareStatement("UPDATE ap_user set usr_id=?, usr_name=?, usr_password=? where usr_id=?");
            statements.add(psUpdate);            
            psUpdate.setString(1, data[1][0].toString());
            psUpdate.setString(2, data[1][1].toString());
            
            if(data[1][3].toString().trim().isEmpty() && data[1][4].toString().trim().isEmpty()){
                psUpdate.setString(3, Config.hashPassword);
            }
            else{
                String newPassword = BCrypt.hashpw(data[1][3].toString(),BCrypt.gensalt());
                psUpdate.setString(3, newPassword);
            }
            
            psUpdate.setString(4, Config.userId);
            psUpdate.executeUpdate();
            
            // recall
            psSelect = connection.prepareStatement("SELECT * FROM ap_user WHERE usr_id=?");
            statements.add(psSelect);

            psSelect.setString(1,data[1][0].toString());
            rs = psSelect.executeQuery();
            
            if(rs.next()){
                Config.userId = data[1][0].toString();
                Config.hashPassword = rs.getString("usr_password");
                Config.userName = rs.getString("usr_name");
            }
            
            connection.commit();
            
            return true;
        
        }
        catch (SQLException sqle) {
            printSQLException(sqle);
        }
        finally{
            releaseMysqlResource();
        }
        
        return false;
    }
    
}
