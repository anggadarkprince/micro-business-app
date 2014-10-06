/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sketchproject.mbapp.models.pos;

import com.sketchproject.mbapp.core.Config;
import com.sketchproject.mbapp.core.MysqlDatabase;
import com.sketchproject.mbapp.utility.TableData;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Asus
 */
public class TransactionModel extends MysqlDatabase{
    
    public DefaultTableModel getTransactionData(String keyword) {        
        try {
            mysqlConnect();
            
            psSelect = connection.prepareStatement("SELECT trn_id,cus_name,mtr_name,mtr_resale_price, trn_acc, trn_instalment, DATE(trn_date) FROM ps_transaction INNER JOIN ps_customer ON ps_transaction.trn_customer = ps_customer.cus_id INNER JOIN ps_motor ON ps_transaction.trn_motor = ps_motor.mtr_id WHERE cus_name LIKE '%"+keyword+"%' OR trn_id LIKE '%"+keyword+"%' ORDER BY trn_date DESC");
            statements.add(psSelect);
            rs = psSelect.executeQuery();   
            
            connection.commit();
            
            String[] colum = {"No PK","Customer","Produk","Harga","ACC","Instalment","Tanggal"};
            return TableData.buildTableModel(rs,colum);
        } catch (SQLException sqle) {
            printSQLException(sqle);
        } finally {
            releaseMysqlResource();
        }
        return null;
    }
    
    public boolean insertTransaction(Object[] data){
        try{
            mysqlConnect();
            
            psInsert = connection.prepareStatement("INSERT into ps_transaction(trn_motor, trn_customer,trn_acc,trn_instalment,trn_id,trn_date,trn_user) VALUES (?, ?, ?, ?, ?, ?, ?)");
            statements.add(psInsert);

            psInsert.setString(1, data[0].toString());
            psInsert.setString(2, data[1].toString());
            psInsert.setString(3, data[2].toString());
            psInsert.setString(4, data[3].toString());
            psInsert.setString(5, data[4].toString());
            psInsert.setString(6, data[5].toString());
            psInsert.setString(7, Config.userId);
            psInsert.executeUpdate();
            
            psUpdate = connection.prepareStatement("UPDATE ps_motor SET mtr_status='SOLD' where mtr_id=?");
            statements.add(psUpdate);

            psUpdate.setString(1, data[0].toString());
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
    
    
    public boolean deleteTransaction(String id){
        try{
            mysqlConnect();
            
            s = connection.createStatement();
            statements.add(s);
            
            psSelect = connection.prepareStatement("SELECT trn_motor FROM ps_transaction WHERE trn_id=?");
            statements.add(psSelect);
            psSelect.setString(1, id);   
            rs = psSelect.executeQuery();
            rs.next();
            
            
            psUpdate = connection.prepareStatement("UPDATE ps_motor SET mtr_status='STOCK' WHERE mtr_id=?");
            statements.add(psUpdate);
            psUpdate.setInt(1, rs.getInt("trn_motor"));
            psUpdate.executeUpdate();
            
            psDelete = connection.prepareStatement("DELETE from ps_transaction WHERE trn_id=?");
            psDelete.setString(1, id);            
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
