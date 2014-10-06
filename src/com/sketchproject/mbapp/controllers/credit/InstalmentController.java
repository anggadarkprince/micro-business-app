/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sketchproject.mbapp.controllers.credit;

import com.sketchproject.mbapp.models.credit.InstalmentModel;
import com.sketchproject.mbapp.views.NativeDialog;
import com.sketchproject.mbapp.views.credit.CreditMenuScreen;
import com.sketchproject.mbapp.views.credit.InstalmentScreen;
import com.sketchproject.mbapp.views.credit.PayInstalment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Date;

/**
 *
 * @author Asus
 */
public class InstalmentController {
    CreditMenuScreen menu;
    InstalmentScreen instalment;
    PayInstalment pay;
    
    InstalmentModel mInstalment;
    
    public InstalmentController(CreditMenuScreen menu, InstalmentScreen instalment){
        this.menu = menu;
        this.instalment = instalment; 
        
        pay = new PayInstalment(menu, true);
        
        mInstalment = new InstalmentModel();
        
        instalment.setListenerDetail(new HandlerDetail());
        instalment.setListenerKeyword(new HandlerKeyword());
        
        pay.setListenerSetDate(new HandlerChangeDate());
        pay.setListenerInstalmentCount(new HandlerInstalmentMonth());
        pay.setListenerAdd(new HandlerAddInstalment());
        pay.setListenerDelete(new HandlerDeleteInstalment());
        
        instalment.setLoanInstalmentData(mInstalment.getInstalmentData(instalment.getKeyword()));
    }
    
    public void update(){
        instalment.setLoanInstalmentData(mInstalment.getInstalmentData(instalment.getKeyword()));
    }
    
    private class HandlerDetail implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            int loanId = Integer.parseInt(instalment.getId().toString());
            
            if(loanId != -1){
                menu.showOverlay();
                pay.setLoanData(mInstalment.getInstalmentStatus(loanId), mInstalment.getCurrentMonthInstalment(loanId));
                pay.setInstalmentData(mInstalment.getLoanInstalment(loanId));
                Object[] data = pay.getCalculateOverdueData();
                if(data[0].toString().isEmpty() || data[2] == null){
                    pay.reset();
                }
                else{
                    try{
                        pay.setCalculateOverdueData(mInstalment.checkPenalty(data)); 
                    }
                    catch(NumberFormatException ex){
                        pay.reset();
                    }        
                }
                pay.setPayed();
                pay.setVisible(true);
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
    
    private class HandlerKeyword extends KeyAdapter{

        @Override
        public void keyReleased(KeyEvent e) {
            instalment.setLoanInstalmentData(mInstalment.getInstalmentData(instalment.getKeyword()));
        }
        
    }
    
    private class HandlerInstalmentMonth extends KeyAdapter{

        @Override
        public void keyReleased(KeyEvent e) {
            Object[] data = pay.getCalculateOverdueData();
            if(data[0].toString().isEmpty() || data[2] == null){
                pay.reset();
            }
            else{
                try{
                    if(Integer.parseInt(data[0].toString()) < 1){
                        pay.reset();
                    }
                    else{
                        pay.setCalculateOverdueData(mInstalment.checkPenalty(data));
                    } 
                }
                catch(NumberFormatException ex){
                    pay.reset();
                }           
            }
        }
        
    }
    
    private class HandlerAddInstalment implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            Object[] data = pay.getInstalmentData();
            boolean validation = true;
            
            for (Object value : data) {
                if (value.toString().trim().isEmpty() || value.toString().trim().equals("-")) {
                    validation = false;
                }
            }
            
            if(validation){
                if(mInstalment.addInstalment(data)){
                    pay.showOverlay();
                    NativeDialog dialog = new NativeDialog(menu, "Add Instalment", "Tambah cicilan berhasil", NativeDialog.DIALOG_INFORMATION);
                    dialog.showDialog();
                    pay.hideOverlay();
                    
                    pay.setLoanData(mInstalment.getInstalmentStatus(pay.getLoanId()), mInstalment.getCurrentMonthInstalment(pay.getLoanId()));
                    pay.setInstalmentData(mInstalment.getLoanInstalment(pay.getLoanId()));
                    instalment.setLoanInstalmentData(mInstalment.getInstalmentData(instalment.getKeyword()));
                    pay.setPayed();
                }
                else{
                    pay.showOverlay();
                    NativeDialog dialog = new NativeDialog(menu, "Add Instalment", "Tambah cicilan gagal, Try Again!", NativeDialog.DIALOG_INFORMATION);
                    dialog.showDialog();
                    pay.hideOverlay();
                }
            }
            else{
                pay.showOverlay();
                NativeDialog dialog = new NativeDialog(menu, "Add Instalment", "Lengkapi semua isian", NativeDialog.DIALOG_INFORMATION);
                dialog.showDialog();
                pay.hideOverlay();
            }
        }
        
    }
    
    private class HandlerDeleteInstalment implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            int id = Integer.parseInt(pay.getId().toString());
            
            if(id != -1){
                pay.showOverlay();
                NativeDialog dialog = new NativeDialog(menu, "Delete Confirmation", "Anda yakin ingin menghapus ID "+id+"?", NativeDialog.DIALOG_QUESTION);
                if(dialog.showDialog() == NativeDialog.PRIMARY_SELECT){
                    pay.hideOverlay();
                    if(mInstalment.deleteInstalment(id)){
                        pay.showOverlay();
                        dialog = new NativeDialog(menu, "Delete Instalment", "Cicilan ID "+id+" telah dihapus!", NativeDialog.DIALOG_INFORMATION);
                        dialog.showDialog();
                        pay.hideOverlay();

                        pay.setLoanData(mInstalment.getInstalmentStatus(pay.getLoanId()), mInstalment.getCurrentMonthInstalment(pay.getLoanId()));
                        pay.setInstalmentData(mInstalment.getLoanInstalment(pay.getLoanId()));
                        instalment.setLoanInstalmentData(mInstalment.getInstalmentData(instalment.getKeyword()));
                        pay.setPayed();
                    }
                    else{
                        pay.showOverlay();
                        dialog = new NativeDialog(menu, "Delete Instalment", "Hapus pinjaman gagal!", NativeDialog.DIALOG_INFORMATION);
                        dialog.showDialog();
                        pay.hideOverlay(); 
                    }
                }
                pay.hideOverlay();
                
            }
            else{                
                pay.showOverlay();
                NativeDialog dialog = new NativeDialog(menu, "Delete Instalment", "Silakan pilih baris tabel!", NativeDialog.DIALOG_INFORMATION);
                dialog.showDialog();
                pay.hideOverlay();                                
            }
        }
        
    }
    
    private class HandlerChangeDate implements PropertyChangeListener{

        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            Object[] data = pay.getCalculateOverdueData();
            if(data[0].toString().isEmpty() || data[2] == null){
                pay.reset();
            }
            else{
                try{                    
                    pay.setCalculateOverdueData(mInstalment.checkPenalty(data)); 
                }
                catch(NumberFormatException e){
                    pay.reset();
                }
            }
        }
        
    }
    
}
