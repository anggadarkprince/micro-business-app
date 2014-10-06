/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sketchproject.mbapp.controllers;

import com.sketchproject.mbapp.controllers.credit.CreditMenuController;
import com.sketchproject.mbapp.controllers.pos.PosMenuController;
import com.sketchproject.mbapp.core.Config;
import com.sketchproject.mbapp.models.AppModel;
import com.sketchproject.mbapp.views.LoginScreen;
import com.sketchproject.mbapp.views.MenuScreen;
import com.sketchproject.mbapp.views.NativeDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 *
 * @author Asus
 */
public class AppController {
    private final LoginScreen authenticateScreen;
    private final MenuScreen menuScreen;
    
    private final AppModel mAuthenticate;
    
    public AppController(boolean startup)
    {
        authenticateScreen = new LoginScreen();
        menuScreen = new MenuScreen();
        
        mAuthenticate = new AppModel();
        
        if(startup){
            authenticateScreen.setVisible(true);
        }
        else{
            menuScreen.setVisible(true);
        }
               
        authenticateScreen.setListenerCloseApp(new listenerExit());
        authenticateScreen.setListenerLoginApp(new listenerLogin());
        authenticateScreen.setListenerKeyTrigger(new ListenerKeyTriggrer());
        
        menuScreen.setListenerPos(new listenerNavigatePos());
        menuScreen.setListenerCredit(new listenerNavigateCredit());
    }
    
    private class listenerExit implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }        
    }
    
    private class ListenerKeyTriggrer extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e) {
            if(e.getKeyCode() == 10){
                listenerLogin login = new listenerLogin();
                login.actionPerformed(null);
            }            
        }        
    }
    
    private class listenerLogin implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            authenticateScreen.showOverlay();
            if(mAuthenticate.checkAuthorize(authenticateScreen.getUserID(), authenticateScreen.getUserPassword())){
                authenticateScreen.dispose();
                menuScreen.setUser(Config.userName);                        
                menuScreen.setVisible(true);
                try {
                    for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                        if ("Windows".equals(info.getName())) {
                            javax.swing.UIManager.setLookAndFeel(info.getClassName());
                            break;
                        }
                    }
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {

                }
            }
            else{
                NativeDialog dialog = new NativeDialog(authenticateScreen, "Login Failed", "User ID atau Password tidak cocok!", NativeDialog.DIALOG_INFORMATION);
                dialog.showDialog();
            }
            authenticateScreen.hideOverlay();
        }        
    }
    
    private class listenerNavigatePos implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            menuScreen.dispose();
            PosMenuController pos = new PosMenuController();
        }        
    }
    
    private class listenerNavigateCredit implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            menuScreen.dispose();
            CreditMenuController credit = new CreditMenuController();
        }        
    }
}
