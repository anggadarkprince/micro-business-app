/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sketchproject.mbapp.controllers.credit;

import com.sketchproject.mbapp.models.credit.CustomerModel;
import com.sketchproject.mbapp.views.NativeDialog;
import com.sketchproject.mbapp.views.credit.CreditMenuScreen;
import com.sketchproject.mbapp.views.credit.CustomerScreen;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 *
 * @author Asus
 */
public class CustomerController {
    CreditMenuScreen menu;
    CustomerScreen customer;
    CustomerModel mCustomer;
    
    public CustomerController(CreditMenuScreen menu, CustomerScreen customer){
        this.menu = menu;
        this.customer = customer;    
        
        mCustomer = new CustomerModel();
        customer.setTableData(mCustomer.getCustomerData(customer.getKeyword()));
        
        customer.setListenerAdd(new HandlerAdd());
        customer.setListenerEdit(new HandlerEdit());
        customer.setListenerUpdate(new HandlerUpdate());
        customer.setListenerDelete(new HandlerDelete());
        customer.setListenerReset(new HandlerReset());
        customer.setListenerSearch(new HandlerSearch());
        customer.reset();
    }
    
    private class HandlerSearch extends KeyAdapter{

        @Override
        public void keyReleased(KeyEvent e) {
            customer.setTableData(mCustomer.getCustomerData(customer.getKeyword()));
        }
        
    }
    
    private class HandlerAdd implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            Object[] data = customer.getDataCustomer();
            boolean validation = true;
            
            for (Object value : data) {
                if (value.toString().trim().isEmpty()) {
                    validation = false;
                }
            }
            
            if(validation){
                if(mCustomer.addCustomer(data)){
                    menu.showOverlay();
                    NativeDialog dialog = new NativeDialog(menu, "Add Customer", "Tambah pelanggan berhasil", NativeDialog.DIALOG_INFORMATION);
                    dialog.showDialog();
                    menu.hideOverlay();
                    
                    customer.reset();
                    customer.setTableData(mCustomer.getCustomerData(customer.getKeyword()));
                }
                else{
                    menu.showOverlay();
                    NativeDialog dialog = new NativeDialog(menu, "Add Customer", "Tambah pelanggan gagal!", NativeDialog.DIALOG_INFORMATION);
                    dialog.showDialog();
                    menu.hideOverlay();
                }
            }
            else{
                menu.showOverlay();
                NativeDialog dialog = new NativeDialog(menu, "Add Customer", "Lengkapi semua isian", NativeDialog.DIALOG_INFORMATION);
                dialog.showDialog();
                menu.hideOverlay();
            }
        }        
    }
    
    private class HandlerEdit implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            int customerId = Integer.parseInt(customer.getId().toString());            
            if(customerId != -1){
                customer.setEdit();
                customer.editState();
            }
            else{
                menu.showOverlay();
                NativeDialog dialog = new NativeDialog(menu, "Edit Customer", "Silakan pilih baris tabel terlebih dahulu!", NativeDialog.DIALOG_INFORMATION);
                dialog.showDialog();
                menu.hideOverlay();
            }            
        }        
    }
    
    private class HandlerUpdate implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            Object[] data = customer.getDataCustomer();
            boolean validation = true;
            
            for (Object value : data) {
                if (value.toString().trim().isEmpty()) {
                    validation = false;
                }
            }
            
            if(validation){
                int customerId = Integer.parseInt(customer.getId().toString());
                if(mCustomer.updateCustomer(data,customerId)){
                    menu.showOverlay();
                    NativeDialog dialog = new NativeDialog(menu, "Update Customer", "Update customer berhasil", NativeDialog.DIALOG_INFORMATION);
                    dialog.showDialog();
                    menu.hideOverlay();
                    
                    customer.reset();
                    customer.normalState();
                    customer.setTableData(mCustomer.getCustomerData(customer.getKeyword()));
                }
                else{
                    menu.showOverlay();
                    NativeDialog dialog = new NativeDialog(menu, "Update Customer", "Tambah pelanggan gagal", NativeDialog.DIALOG_INFORMATION);
                    dialog.showDialog();
                    menu.hideOverlay();
                }
            }
            else{
                menu.showOverlay();
                NativeDialog dialog = new NativeDialog(menu, "Update Customer", "Lengkapi semua isian", NativeDialog.DIALOG_INFORMATION);
                dialog.showDialog();
                menu.hideOverlay();
            }
        }        
    }
    
    private class HandlerDelete implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            int customerId = Integer.parseInt(customer.getId().toString());
            
            if(customerId != -1){
                menu.showOverlay();
                NativeDialog dialog = new NativeDialog(menu, "Delete Confirmation", "Anda yakin akan menghapus ID "+customerId+"?", NativeDialog.DIALOG_QUESTION);
                if(dialog.showDialog() == NativeDialog.PRIMARY_SELECT){
                    menu.hideOverlay();
                    if(mCustomer.deleteCustomer(customerId)){
                        menu.showOverlay();
                        dialog = new NativeDialog(menu, "Delete Customer", "Pelanggan ID "+customerId+" telah di hapus!", NativeDialog.DIALOG_INFORMATION);
                        dialog.showDialog();
                        menu.hideOverlay();

                        customer.setTableData(mCustomer.getCustomerData(customer.getKeyword()));
                    }
                    else{
                        menu.showOverlay();
                        dialog = new NativeDialog(menu, "Delete Customer", "Hapus pelanggan gagal, coba lagi!", NativeDialog.DIALOG_INFORMATION);
                        dialog.showDialog();
                        menu.hideOverlay(); 
                    }
                }
                menu.hideOverlay();
                
            }
            else{                
                menu.showOverlay();
                NativeDialog dialog = new NativeDialog(menu, "Delete Customer", "Silakan pilih baris tabel!", NativeDialog.DIALOG_INFORMATION);
                dialog.showDialog();
                menu.hideOverlay();                                
            }
        }        
    }    
    
    private class HandlerReset implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            customer.reset();
            customer.normalState();
        }        
    }
}
