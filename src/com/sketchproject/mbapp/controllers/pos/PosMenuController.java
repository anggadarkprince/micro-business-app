/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sketchproject.mbapp.controllers.pos;

import com.sketchproject.mbapp.controllers.AppController;
import com.sketchproject.mbapp.core.Config;
import com.sketchproject.mbapp.utility.BrowserControl;
import com.sketchproject.mbapp.views.Lockscreen;
import com.sketchproject.mbapp.views.pos.AboutScreen;
import com.sketchproject.mbapp.views.pos.CustomerScreen;
import com.sketchproject.mbapp.views.pos.HelpScreen;
import com.sketchproject.mbapp.views.pos.HomeScreen;
import com.sketchproject.mbapp.views.pos.InventoryScreen;
import com.sketchproject.mbapp.views.pos.PosMenuScreen;
import com.sketchproject.mbapp.views.pos.ReportScreen;
import com.sketchproject.mbapp.views.pos.SettingScreen;
import com.sketchproject.mbapp.views.pos.TransactionScreen;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;

/**
 *
 * @author Asus
 */
public class PosMenuController {

    private final PosMenuScreen menu;
    
    private final HomeScreen home;
    private final SettingScreen setting;
    private final CustomerScreen customer;
    private final InventoryScreen inventory;
    private final TransactionScreen transaction;
    private final ReportScreen report;
    
    private final AboutScreen about;
    private final HelpScreen help;
    
    private final HomeController cHome;
    private final SettingController cSetting;
    private final CustomerController cCustomer;
    private final InventoryController cInventory;
    private final TransactionController cTransaction;
    private final ReportController cReport;

    public PosMenuController() {
        // menu
        menu = new PosMenuScreen();
        menu.setUserName(Config.userName);
        menu.setVisible(true);
        
        // panel
        home = new HomeScreen();
        setting = new SettingScreen();
        customer = new CustomerScreen();
        inventory = new InventoryScreen();
        transaction = new TransactionScreen();
        report = new ReportScreen();
        
        about = new AboutScreen();
        help = new HelpScreen();
        
        // controller
        cHome = new HomeController(menu, home);
        cSetting = new SettingController(menu, setting);
        cCustomer = new CustomerController(menu, customer);
        cInventory = new InventoryController(menu, inventory);
        cTransaction = new TransactionController(menu, transaction);
        cReport = new ReportController(menu, report);
        
        // listener
        menu.setListenerLabelAccountSetting(new listenerSettingAccount());
        menu.setListenerNavigateHome(new listenerNavigateHome());
        menu.setListenerNavigateSetting(new listenerNavigateSetting());
        menu.setListenerNavigateCustomer(new listenerNavigateCustomer());
        menu.setListenerNavigateInventory(new listenerNavigateInventory());
        menu.setListenerNavigateTransaction(new listenerNavigateTransaction());
        menu.setListenerNavigateReport(new listenerNavigateReport());
        
        menu.setListenerNavigateAboutUs(new listenerNavigateAboutUs());
        menu.setListenerNavigateHelpSupport(new listenerNavigateHelpSupport());
        
        menu.setListenerNavigateFacebook(new listenerNavigateFacebook());
        menu.setListenerNavigateTwitter(new listenerNavigateTwitter());
        
        menu.setListenerLock(new listenerLock());
        menu.setListenerLogout(new listenerLogout());
        menu.setListenerBack(new listenerBack());
        
        menu.setScreenPage(home);
        
        menu.updateStatistic();
    }

    private class listenerSettingAccount extends MouseAdapter {

        @Override
        public void mousePressed(MouseEvent e) {
            menu.switchPage(menu.getButtonSetting());
            menu.setScreenPage(setting);
        }
    }
    
    private class listenerNavigateHome implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            menu.switchPage((JButton) e.getSource());
            menu.setScreenPage(home);
            cHome.update();
        }
    }

    private class listenerNavigateSetting implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            menu.switchPage((JButton) e.getSource());
            menu.setScreenPage(setting);
        }
    }

    private class listenerNavigateCustomer implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            menu.switchPage((JButton) e.getSource());
            menu.setScreenPage(customer);
        }
    }

    private class listenerNavigateInventory implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            menu.switchPage((JButton) e.getSource());
            menu.setScreenPage(inventory);
            cInventory.update();
        }
    }

    private class listenerNavigateTransaction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            menu.switchPage((JButton) e.getSource());
            menu.setScreenPage(transaction);
            cTransaction.update();
        }
    }

    private class listenerNavigateReport implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            menu.switchPage((JButton) e.getSource());
            menu.setScreenPage(report);
        }
    }

    private class listenerNavigateAboutUs implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            menu.switchPage((JButton) e.getSource());
            menu.setScreenPage(about);
        }
    }

    private class listenerNavigateHelpSupport implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            menu.switchPage((JButton) e.getSource());
            menu.setScreenPage(help);
        }
    }

    private class listenerNavigateFacebook implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
           BrowserControl.displayURL("http://www.facebook.com");
        }
    }

    private class listenerNavigateTwitter implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            BrowserControl.displayURL("http://www.twitter.com");
        }
    }
    
    private class listenerLock implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            menu.showOverlay();
            Lockscreen lock = new Lockscreen(menu);
            menu.hideOverlay();
        }
    }
    
    private class listenerLogout implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Config.idSession = null;
            Config.userId = null;
            Config.userName = null;
            Config.hashPassword = null;
            AppController app = new AppController(true);
            menu.dispose();
        }
    }
    
    private class listenerBack implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            AppController app = new AppController(false);
            menu.dispose();
        }
    }
}
