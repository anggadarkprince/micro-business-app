/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sketchproject.mbapp.controllers.pos;

import com.sketchproject.mbapp.models.pos.InventoryModel;
import com.sketchproject.mbapp.views.NativeDialog;
import com.sketchproject.mbapp.views.pos.AddCategory;
import com.sketchproject.mbapp.views.pos.InventoryScreen;
import com.sketchproject.mbapp.views.pos.PosMenuScreen;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Asus
 */
public class InventoryController {
    PosMenuScreen menu;
    InventoryScreen inventory;
    AddCategory category;
    InventoryModel mInventory;
    
    public InventoryController(PosMenuScreen menu, InventoryScreen inventory){
        this.menu = menu;
        this.inventory = inventory;
        
        
        
        mInventory = new InventoryModel();
        inventory.setTableData(mInventory.getInventoryData());
        inventory.setProduct(mInventory.getCategory());
        
        category = new AddCategory(menu, true);
        category.setTableData(mInventory.getCategory());
                
        category.setListenerAdd(new HandlerAddCategory());
        category.setListenerDelete(new HandlerDeleteCategory());
        
        inventory.setListenerAdd(new HandlerAdd());
        inventory.setListenerEdit(new HandlerEdit());
        inventory.setListenerUpdate(new HandlerUpdate());
        inventory.setListenerDelete(new HandlerDelete());
        inventory.setListenerReset(new HandlerReset());
        inventory.setListenerAddMerk(new HandlerNewCategory());
    }
    
    public void update(){
        inventory.setTableData(mInventory.getInventoryData());
    }
    
    private class HandlerAdd implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            Object[] data = inventory.getDataInventory();
            boolean validation = true;
            
            for (Object value : data) {
                if (value.toString().trim().isEmpty()) {
                    validation = false;
                }
            }
            
            if(validation){
                if(mInventory.addInventory(data)){
                    menu.showOverlay();
                    NativeDialog dialog = new NativeDialog(menu, "Add Inventory", "Tambah barang berhasil", NativeDialog.DIALOG_INFORMATION);
                    dialog.showDialog();
                    menu.hideOverlay();
                    
                    inventory.reset();
                    inventory.setTableData(mInventory.getInventoryData());
                }
                else{
                    menu.showOverlay();
                    NativeDialog dialog = new NativeDialog(menu, "Add Inventory", "Tambah barang gagal, coba lagi!", NativeDialog.DIALOG_INFORMATION);
                    dialog.showDialog();
                    menu.hideOverlay();
                }
            }
            else{
                menu.showOverlay();
                NativeDialog dialog = new NativeDialog(menu, "Add Inventory", "Lengkapi semua isian!", NativeDialog.DIALOG_INFORMATION);
                dialog.showDialog();
                menu.hideOverlay();
            }
        }        
    }
    
    private class HandlerEdit implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            int customerId = Integer.parseInt(inventory.getId().toString());            
            if(customerId != -1){
                inventory.setEdit();
                inventory.editState();
            }
            else{
                menu.showOverlay();
                NativeDialog dialog = new NativeDialog(menu, "Edit Inventory", "Silakan pilih baris tabel terlebih dahulu", NativeDialog.DIALOG_INFORMATION);
                dialog.showDialog();
                menu.hideOverlay();
            }            
        }        
    }
    
    private class HandlerUpdate implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            Object[] data = inventory.getDataInventory();
            boolean validation = true;
            
            for (Object value : data) {
                if (value.toString().trim().isEmpty()) {
                    validation = false;
                }
            }
            
            if(validation){
                int customerId = Integer.parseInt(inventory.getId().toString());
                if(mInventory.updateInventory(data,customerId)){
                    menu.showOverlay();
                    NativeDialog dialog = new NativeDialog(menu, "Update Inventory", "Update barang berhasil", NativeDialog.DIALOG_INFORMATION);
                    dialog.showDialog();
                    menu.hideOverlay();
                    
                    inventory.reset();
                    inventory.normalState();
                    inventory.setTableData(mInventory.getInventoryData());
                }
                else{
                    menu.showOverlay();
                    NativeDialog dialog = new NativeDialog(menu, "Update Inventory", "Gagal update barang, coba lagi!", NativeDialog.DIALOG_INFORMATION);
                    dialog.showDialog();
                    menu.hideOverlay();
                }
            }
            else{
                menu.showOverlay();
                NativeDialog dialog = new NativeDialog(menu, "Update Inventory", "Lengkapi semua isian!", NativeDialog.DIALOG_INFORMATION);
                dialog.showDialog();
                menu.hideOverlay();
            }
        }        
    }
    
    private class HandlerDelete implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            int inventoryId = Integer.parseInt(inventory.getId().toString());
            
            if(inventoryId != -1){
                menu.showOverlay();
                NativeDialog dialog = new NativeDialog(menu, "Delete Confirmation", "Anda yakin akan menghapus barIng D "+inventoryId+"?", NativeDialog.DIALOG_QUESTION);
                if(dialog.showDialog() == NativeDialog.PRIMARY_SELECT){
                    menu.hideOverlay();
                    if(mInventory.deleteInventory(inventoryId)){
                        menu.showOverlay();
                        dialog = new NativeDialog(menu, "Delete Inventory", "Barang ID "+inventoryId+" telah di hapus!", NativeDialog.DIALOG_INFORMATION);
                        dialog.showDialog();
                        menu.hideOverlay();

                        inventory.setTableData(mInventory.getInventoryData());
                    }
                    else{
                        menu.showOverlay();
                        dialog = new NativeDialog(menu, "Delete Inventory", "Hapus barang gagal, Try Again!", NativeDialog.DIALOG_INFORMATION);
                        dialog.showDialog();
                        menu.hideOverlay(); 
                    }
                }
                menu.hideOverlay();
                
            }
            else{                
                menu.showOverlay();
                NativeDialog dialog = new NativeDialog(menu, "Delete Inventory", "Silakan pilih baris tabel terlebih dahulu!", NativeDialog.DIALOG_INFORMATION);
                dialog.showDialog();
                menu.hideOverlay();                                
            }
        }        
    }    
    
    private class HandlerReset implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            inventory.reset();
            inventory.normalState();
        }        
    }
    
    private class HandlerNewCategory implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            menu.showOverlay();
            category.setVisible(true);
            menu.hideOverlay();
        }        
    }
    
    private class HandlerAddCategory implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(!category.getCategory().trim().isEmpty()){
                mInventory.addCategory(category.getCategory());
                category.setTableData(mInventory.getCategory());
                category.reset();
                inventory.setProduct(mInventory.getCategory());
            }
            else{
                category.showOverlay();
                NativeDialog dialog = new NativeDialog(menu, "Add Category", "Lengkapi semua isian!", NativeDialog.DIALOG_INFORMATION);
                dialog.showDialog();
                category.hideOverlay();
            }
            
        }        
    }
    
    private class HandlerDeleteCategory implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            int merkId = Integer.parseInt(category.getId().toString());
            
            if(merkId != -1){
                category.showOverlay();
                NativeDialog dialog = new NativeDialog(menu, "Delete Confirmation", "Menghapus kategori juga akan menghapus barang yang bersangkutan!", NativeDialog.DIALOG_QUESTION);
                if(dialog.showDialog() == NativeDialog.PRIMARY_SELECT){
                    category.hideOverlay();
                    if(mInventory.deleteCategory(merkId)){
                        category.showOverlay();
                        dialog = new NativeDialog(menu, "Delete Category", "Kategori ID "+merkId+" telah di hapus!", NativeDialog.DIALOG_INFORMATION);
                        dialog.showDialog();
                        category.hideOverlay();
                        
                        category.setTableData(mInventory.getCategory()); 
                        inventory.setProduct(mInventory.getCategory());
                        inventory.setTableData(mInventory.getInventoryData());
                    }
                    else{
                        category.showOverlay();
                        dialog = new NativeDialog(menu, "Delete Category", "Hapus kategori gagal, coba lagi!", NativeDialog.DIALOG_INFORMATION);
                        dialog.showDialog();
                        category.hideOverlay(); 
                    }
                }
                category.hideOverlay();
            }
            else{
                category.showOverlay();
                NativeDialog dialog = new NativeDialog(menu, "Delete Inventory", "Silakan pilih baris tabel terlebih dahulu!", NativeDialog.DIALOG_INFORMATION);
                dialog.showDialog();
                category.hideOverlay();
            }
        }
    }
}
