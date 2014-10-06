/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sketchproject.mbapp.controllers.pos;

import com.sketchproject.mbapp.models.pos.CustomerModel;
import com.sketchproject.mbapp.models.pos.InventoryModel;
import com.sketchproject.mbapp.models.pos.TransactionModel;
import com.sketchproject.mbapp.views.NativeDialog;
import com.sketchproject.mbapp.views.pos.PosMenuScreen;
import com.sketchproject.mbapp.views.pos.TransactionScreen;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 *
 * @author Asus
 */
public class TransactionController {
    PosMenuScreen menu;
    TransactionScreen transaction;
    
    TransactionModel mTransaction;
    CustomerModel mCustomer;
    InventoryModel mInventory;
    
    public TransactionController(PosMenuScreen menu, TransactionScreen transaction){
        this.menu = menu;
        this.transaction = transaction;
        
        mTransaction = new TransactionModel();
        mCustomer = new CustomerModel();
        mInventory = new InventoryModel();
                
        transaction.setListenerCategory(new HandlerCategory());
        transaction.setListenerProduct(new HandlerProduct());
        transaction.setListenerCustomer(new HandlerCustomer());
        transaction.setListenerSave(new HandlerSave());
        transaction.setListenerReset(new HandlerReset());
        transaction.setListenerDelete(new HandlerDelete());
        transaction.setListenerSearch(new HandlerSearch());
        
        update();
    }
    
    public void update(){
        transaction.setCategory(mInventory.getCategory()); 
        transaction.setProduct(mInventory.getProduct(transaction.getCategoryId())); 
        transaction.setCustomer(mCustomer.getCustomer()); 
        transaction.setTransactionData(mTransaction.getTransactionData(transaction.getKeyword()));
    }
    
    private class HandlerSearch extends KeyAdapter{

        @Override
        public void keyReleased(KeyEvent e) {
            transaction.setTransactionData(mTransaction.getTransactionData(transaction.getKeyword()));
        }
        
    }
    
    private class HandlerCategory implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            transaction.setProduct(mInventory.getProduct(transaction.getCategoryId())); 
        }        
    }
    
    private class HandlerProduct implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            transaction.setDataProduct(mInventory.getProductData(transaction.getProductId()));
        }        
    }
    
    private class HandlerCustomer implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            transaction.setDataCustomer(mCustomer.getCustomerData(transaction.getCustomerId()));
        }        
    }
    
    private class HandlerSave implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            Object[] data = transaction.checkTransaction();
            
            if(data[2].toString().trim().isEmpty()){
                menu.showOverlay();
                NativeDialog dialog = new NativeDialog(menu, "Transaction", "Silakan masukkan no PK", NativeDialog.DIALOG_INFORMATION);
                dialog.showDialog();
                menu.hideOverlay();
            }
            else{
                if(data[0].toString().trim().isEmpty()){
                    menu.showOverlay();
                    NativeDialog dialog = new NativeDialog(menu, "Transaction", "Silakan pilih produk", NativeDialog.DIALOG_INFORMATION);
                    dialog.showDialog();
                    menu.hideOverlay();
                }
                else{
                    if(data[1].toString().trim().isEmpty()){
                        menu.showOverlay();
                        NativeDialog dialog = new NativeDialog(menu, "Transaction", "Silakan pilih pelanggan", NativeDialog.DIALOG_INFORMATION);
                        dialog.showDialog();
                        menu.hideOverlay();
                    }
                    else{
                        if(data[3].toString().trim().isEmpty()){
                            menu.showOverlay();
                            NativeDialog dialog = new NativeDialog(menu, "Transaction", "Silakan pilih Tanggal", NativeDialog.DIALOG_INFORMATION);
                            dialog.showDialog();
                            menu.hideOverlay();
                        }
                        else{
                            if(mTransaction.insertTransaction(transaction.getTransactionData())){
                                menu.showOverlay();
                                NativeDialog dialog = new NativeDialog(menu, "Transaction", "Tambah transaksi berhasil", NativeDialog.DIALOG_INFORMATION);
                                dialog.showDialog();
                                menu.hideOverlay();

                                update();                            
                                transaction.reset();
                                menu.updateStatistic();
                            }
                            else{
                                menu.showOverlay();
                                NativeDialog dialog = new NativeDialog(menu, "Transaction", "Tambah transaksi gagal!", NativeDialog.DIALOG_INFORMATION);
                                dialog.showDialog();
                                menu.hideOverlay();
                            }
                        }                        
                    }
                }
            }
            
            
        }        
    }
    
    private class HandlerReset implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            transaction.reset();
        }        
    }
    
    private class HandlerDelete implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            String transactionId = transaction.getId().toString();
            
            if(!transactionId.equals("-1")){
                menu.showOverlay();
                NativeDialog dialog = new NativeDialog(menu, "Delete Confirmation", "Anda yakin ingin menghapus transaksi ID "+transactionId+"?", NativeDialog.DIALOG_QUESTION);
                if(dialog.showDialog() == NativeDialog.PRIMARY_SELECT){
                    menu.hideOverlay();
                    if(mTransaction.deleteTransaction(transactionId)){
                        menu.showOverlay();
                        dialog = new NativeDialog(menu, "Delete Transaction", "Transaksi ID "+transactionId+" telah dihapus!", NativeDialog.DIALOG_INFORMATION);
                        dialog.showDialog();
                        menu.hideOverlay();

                        update();
                        menu.updateStatistic();
                    }
                    else{
                        menu.showOverlay();
                        dialog = new NativeDialog(menu, "Delete Transaction", "hapus transaksi gagal!", NativeDialog.DIALOG_INFORMATION);
                        dialog.showDialog();
                        menu.hideOverlay(); 
                    }
                }
                menu.hideOverlay();
                
            }
            else{                
                menu.showOverlay();
                NativeDialog dialog = new NativeDialog(menu, "Delete Transaction", "Silakan pilih baris tabel terlebih dahulu!", NativeDialog.DIALOG_INFORMATION);
                dialog.showDialog();
                menu.hideOverlay();                                
            }
        }        
    }  
}
