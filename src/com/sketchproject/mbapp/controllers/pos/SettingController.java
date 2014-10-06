/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sketchproject.mbapp.controllers.pos;

import com.sketchproject.mbapp.core.Config;
import com.sketchproject.mbapp.models.AppModel;
import com.sketchproject.mbapp.utility.BCrypt;
import com.sketchproject.mbapp.views.NativeDialog;
import com.sketchproject.mbapp.views.pos.PosMenuScreen;
import com.sketchproject.mbapp.views.pos.SettingScreen;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Asus
 */
public class SettingController {
    PosMenuScreen menu;
    SettingScreen setting;
    AppModel mSetting;
    
    public SettingController(PosMenuScreen menu, SettingScreen setting){
        this.menu = menu;
        this.setting = setting;  
        
        mSetting = new AppModel();
        Object data[] = mSetting.getSetting("APPPS");        
        
        setting.setSetting(data);
        
        setting.setListenerUpdateSetting(new HandlerUpdateSetting());
    }
    
    private class HandlerUpdateSetting implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            Object[][] data = setting.getSetting();
            
            if(!data[1][2].toString().trim().isEmpty()){
                if(BCrypt.checkpw(data[1][2].toString(), Config.hashPassword)){
                    if((!data[1][3].toString().trim().isEmpty() || !data[1][4].toString().trim().isEmpty()) && !(data[1][3].toString().equals(data[1][4].toString()))){
                        menu.showOverlay();
                        NativeDialog dialog = new NativeDialog(menu, "Update Failed", "New Password dan Confirm password harus sama", NativeDialog.DIALOG_INFORMATION);
                        dialog.showDialog();
                        menu.hideOverlay();
                    }
                    else{
                        if(mSetting.updateSetting(data,"APPPS")){
                            menu.showOverlay();
                            NativeDialog dialog = new NativeDialog(menu, "Update Success", "Update Setting dan User berhasil", NativeDialog.DIALOG_INFORMATION);
                            dialog.showDialog();
                            menu.hideOverlay();
                            
                            setting.reset();
                            menu.setUserName(Config.userName);
                        }
                        else{
                            menu.showOverlay();
                            NativeDialog dialog = new NativeDialog(menu, "Update Failed", "Update Setting dan User gagal, coba lagi", NativeDialog.DIALOG_INFORMATION);
                            dialog.showDialog();
                            menu.hideOverlay();
                        }
                    }  
                }
                else{
                    menu.showOverlay();
                    NativeDialog dialog = new NativeDialog(menu, "Update Failed", "password tidak cocok dengan user aktif!", NativeDialog.DIALOG_INFORMATION);
                    dialog.showDialog();
                    menu.hideOverlay();
                }
            }
            else{
                menu.showOverlay();
                NativeDialog dialog = new NativeDialog(menu, "Update Failed", "Tulis password sekarang untuk melakukan update!", NativeDialog.DIALOG_INFORMATION);
                dialog.showDialog();
                menu.hideOverlay();
            }            
            
        }        
    }
    
    
    
    
}
