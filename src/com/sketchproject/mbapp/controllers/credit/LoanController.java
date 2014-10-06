/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sketchproject.mbapp.controllers.credit;

import com.sketchproject.mbapp.models.credit.CustomerModel;
import com.sketchproject.mbapp.models.credit.LoanModel;
import com.sketchproject.mbapp.views.NativeDialog;
import com.sketchproject.mbapp.views.credit.CreditMenuScreen;
import com.sketchproject.mbapp.views.credit.LoanList;
import com.sketchproject.mbapp.views.credit.LoanScreen;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 *
 * @author Asus
 */
public class LoanController {
    CreditMenuScreen menu;
    LoanScreen loan;
    LoanList list;
    LoanModel mLoan;
    CustomerModel mCustomer;
    
    public LoanController(CreditMenuScreen menu, LoanScreen loan){
        this.menu = menu;
        this.loan = loan;   
        
        mCustomer = new CustomerModel();
        
        mLoan = new LoanModel();
        loan.setTableData(mLoan.getLoanData());
        loan.setComboLoan(mLoan.getTypeLoan());
        
        list = new LoanList(menu, true);
        list.setTableData(mLoan.getDataLoanType());
        
        list.setListenerAdd(new HandlerAddLoanType());
        list.setListenerDelete(new HandlerDeleteLoanType());
        
        loan.setListenerTextIdCustomer(new HandlerIdCustomer());
        loan.setListenerComboLoanType(new HandlerComboLoanType());
        loan.setListenerComboLoanMonth(new HandlerComboLoanMonth());
        
        loan.setListenerAdd(new HandlerAdd());
        loan.setListenerEdit(new HandlerEdit());
        loan.setListenerUpdate(new HandlerUpdate());
        loan.setListenerDelete(new HandlerDelete());
        loan.setListenerReset(new HandlerReset());
        loan.setListenerAddLoanType(new HandlerNewLoanType());
        
        loan.setComboLoanMonth(mLoan.getLoanMonth(loan.getLoanTypeId())); 
        loan.setValueInstalment(String.valueOf(mLoan.getInstalmentValue(loan.getLoanTypeId(), loan.getLoanMonthId()))); 
    }
    
    public void update(){
        loan.setTableData(mLoan.getLoanData());
    }
    
    private class HandlerComboLoanType implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            loan.setComboLoanMonth(mLoan.getLoanMonth(loan.getLoanTypeId()));
            loan.setValueInstalment(String.valueOf(mLoan.getInstalmentValue(loan.getLoanTypeId(), loan.getLoanMonthId()))); 
        }        
    }
    
    private class HandlerComboLoanMonth implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            loan.setValueInstalment(String.valueOf(mLoan.getInstalmentValue(loan.getLoanTypeId(), loan.getLoanMonthId()))); 
        }        
    }
    
    private class HandlerIdCustomer extends KeyAdapter{
        @Override
        public void keyReleased(KeyEvent e) {
            try{
                if(!loan.getIdCustomer().trim().isEmpty()){
                    loan.setCustomerData(mCustomer.getCustomerData(Integer.parseInt(loan.getIdCustomer())));
                }                
            }
            catch(NumberFormatException ex){
                loan.reset();
                menu.showOverlay();
                NativeDialog dialog = new NativeDialog(menu, "Add Loan", "ID pelanggan harus angka", NativeDialog.DIALOG_INFORMATION);
                dialog.showDialog();
                menu.hideOverlay();
            }
        }
        
    }
    
    private class HandlerAdd implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            Object[] data = loan.getDataLoan();
            boolean validation = true;
            
            for (Object value : data) {
                if (value.toString().trim().isEmpty()) {
                    validation = false;
                }
            }
            
            if(validation){
                if(mLoan.addLoan(data)){
                    menu.showOverlay();
                    NativeDialog dialog = new NativeDialog(menu, "Add Loan", "Tambah pinjaman berhasil", NativeDialog.DIALOG_INFORMATION);
                    dialog.showDialog();
                    menu.hideOverlay();
                    
                    loan.reset();
                    loan.setTableData(mLoan.getLoanData());
                }
                else{
                    menu.showOverlay();
                    NativeDialog dialog = new NativeDialog(menu, "Add Loan", "Tambah pinjaman gagal!", NativeDialog.DIALOG_INFORMATION);
                    dialog.showDialog();
                    menu.hideOverlay();
                }
            }
            else{
                menu.showOverlay();
                NativeDialog dialog = new NativeDialog(menu, "Add Loan", "Lengkapi semua isian", NativeDialog.DIALOG_INFORMATION);
                dialog.showDialog();
                menu.hideOverlay();
            }
        }        
    }
    
    private class HandlerEdit implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            int customerId = Integer.parseInt(loan.getId().toString());            
            if(customerId != -1){
                loan.setEdit(mCustomer.getCustomerData(Integer.parseInt(loan.getIdCustomerEdit().toString())),mLoan.getLoan(Integer.parseInt(loan.getId().toString())));
                
                loan.editState();
            }
            else{
                menu.showOverlay();
                NativeDialog dialog = new NativeDialog(menu, "Edit Loan", "Silakan pilih baris tabel!", NativeDialog.DIALOG_INFORMATION);
                dialog.showDialog();
                menu.hideOverlay();
            }            
        }        
    }
    
    private class HandlerUpdate implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            Object[] data = loan.getDataLoan();
            boolean validation = true;
            
            for (Object value : data) {
                if (value.toString().trim().isEmpty()) {
                    validation = false;
                }
            }
            
            if(validation){
                int customerId = Integer.parseInt(loan.getId().toString());
                if(mLoan.updateLoan(data,customerId)){
                    menu.showOverlay();
                    NativeDialog dialog = new NativeDialog(menu, "Update Loan", "Update pinjaman berhasil", NativeDialog.DIALOG_INFORMATION);
                    dialog.showDialog();
                    menu.hideOverlay();
                    
                    loan.reset();
                    loan.normalState();
                    loan.setTableData(mLoan.getLoanData());
                }
                else{
                    menu.showOverlay();
                    NativeDialog dialog = new NativeDialog(menu, "Update Loan", "Update pinjaman gagal!", NativeDialog.DIALOG_INFORMATION);
                    dialog.showDialog();
                    menu.hideOverlay();
                }
            }
            else{
                menu.showOverlay();
                NativeDialog dialog = new NativeDialog(menu, "Update Loan", "Lengkapi semua isian", NativeDialog.DIALOG_INFORMATION);
                dialog.showDialog();
                menu.hideOverlay();
            }
        }        
    }
    
    private class HandlerDelete implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            int inventoryId = Integer.parseInt(loan.getId().toString());
            
            if(inventoryId != -1){
                menu.showOverlay();
                NativeDialog dialog = new NativeDialog(menu, "Delete Confirmation", "Anda yakin ingin menghapus ID "+inventoryId+"?", NativeDialog.DIALOG_QUESTION);
                if(dialog.showDialog() == NativeDialog.PRIMARY_SELECT){
                    menu.hideOverlay();
                    if(mLoan.deleteLoan(inventoryId)){
                        menu.showOverlay();
                        dialog = new NativeDialog(menu, "Delete Loan", "Pinjaman ID "+inventoryId+" telah di hapus!", NativeDialog.DIALOG_INFORMATION);
                        dialog.showDialog();
                        menu.hideOverlay();

                        loan.setTableData(mLoan.getLoanData());
                    }
                    else{
                        menu.showOverlay();
                        dialog = new NativeDialog(menu, "Delete Loan", "Hapus pinjaman gagal, coba lagi!", NativeDialog.DIALOG_INFORMATION);
                        dialog.showDialog();
                        menu.hideOverlay(); 
                    }
                }
                menu.hideOverlay();
                
            }
            else{                
                menu.showOverlay();
                NativeDialog dialog = new NativeDialog(menu, "Delete Loan", "Silakan pilih baris tabel!", NativeDialog.DIALOG_INFORMATION);
                dialog.showDialog();
                menu.hideOverlay();                                
            }
        }        
    }    
    
    private class HandlerReset implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            loan.reset();
            loan.normalState();
        }        
    }
    
    private class HandlerNewLoanType implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            menu.showOverlay();
            list.setVisible(true);
            menu.hideOverlay();
        }        
    }
    
    private class HandlerAddLoanType implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            Object[] data = list.getLoanType();
            boolean validation = true;
            
            for (Object value : data) {
                if (value.toString().trim().isEmpty()) {
                    validation = false;
                }
            }
            
            if(validation){
                mLoan.addLoanType(list.getLoanType());
                list.setTableData(mLoan.getDataLoanType());
                list.reset();
                loan.setComboLoan(mLoan.getTypeLoan());
            }
            else{
                list.showOverlay();
                NativeDialog dialog = new NativeDialog(menu, "Add Loan Type", "Lengkapi semua isian", NativeDialog.DIALOG_INFORMATION);
                dialog.showDialog();
                list.hideOverlay();
            }
            
        }        
    }
    
    private class HandlerDeleteLoanType implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            int loanId = Integer.parseInt(list.getId().toString());
            
            if(loanId != -1){
                list.showOverlay();
                NativeDialog dialog = new NativeDialog(menu, "Delete Confirmation", "Anda yakin ingin menghapus jenis pinjaman?", NativeDialog.DIALOG_QUESTION);
                if(dialog.showDialog() == NativeDialog.PRIMARY_SELECT){
                    list.hideOverlay();
                    if(mLoan.deleteLoanType(loanId)){
                        list.showOverlay();
                        dialog = new NativeDialog(menu, "Delete Loan Type", "Jenis Pinjaman ID "+loanId+" telah di hapus!", NativeDialog.DIALOG_INFORMATION);
                        dialog.showDialog();
                        list.hideOverlay();
                        
                        list.setTableData(mLoan.getDataLoanType()); 
                        loan.setComboLoan(mLoan.getTypeLoan());
                        loan.setTableData(mLoan.getLoanData());
                    }
                    else{
                        list.showOverlay();
                        dialog = new NativeDialog(menu, "Delete Loan Type", "Hapus Jenis pinjaman gagal!", NativeDialog.DIALOG_INFORMATION);
                        dialog.showDialog();
                        list.hideOverlay(); 
                    }
                }
                list.hideOverlay();
            }
            else{
                list.showOverlay();
                NativeDialog dialog = new NativeDialog(menu, "Delete Loan", "Silakan pilih baris tabel!", NativeDialog.DIALOG_INFORMATION);
                dialog.showDialog();
                list.hideOverlay();
            }
        }
    }
    
}
