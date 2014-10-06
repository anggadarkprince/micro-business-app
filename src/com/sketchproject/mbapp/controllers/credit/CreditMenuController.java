/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sketchproject.mbapp.controllers.credit;

import com.sketchproject.mbapp.controllers.AppController;
import com.sketchproject.mbapp.core.Config;
import com.sketchproject.mbapp.utility.BrowserControl;
import com.sketchproject.mbapp.views.Lockscreen;
import com.sketchproject.mbapp.views.NativeDialog;
import com.sketchproject.mbapp.views.credit.AboutScreen;
import com.sketchproject.mbapp.views.credit.CustomerScreen;
import com.sketchproject.mbapp.views.credit.HomeScreen;
import com.sketchproject.mbapp.views.credit.CreditMenuScreen;
import com.sketchproject.mbapp.views.credit.HelpScreen;
import com.sketchproject.mbapp.views.credit.InstalmentScreen;
import com.sketchproject.mbapp.views.credit.LoanScreen;
import com.sketchproject.mbapp.views.credit.ReportScreen;
import com.sketchproject.mbapp.views.credit.SettingScreen;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;

/**
 *
 * @author Asus
 */
public class CreditMenuController {

    private final CreditMenuScreen menu;
    
    private final HomeScreen home;
    private final SettingScreen setting;
    private final CustomerScreen customer;
    private final LoanScreen loan;
    private final InstalmentScreen instalment;
    private final ReportScreen report;
    
    private final AboutScreen about;
    private final HelpScreen help;
    
    private final HomeController cHome;
    private final SettingController cSetting;
    private final CustomerController cCustomer;
    private final LoanController cLoan;
    private final InstalmentController cInstalment;
    private final ReportController cReport;

    public CreditMenuController() {
        // menu
        menu = new CreditMenuScreen();
        menu.setVisible(true);
        
        // panel
        home = new HomeScreen();
        setting = new SettingScreen();
        customer = new CustomerScreen();
        loan = new LoanScreen();
        instalment = new InstalmentScreen();
        report = new ReportScreen();
        
        about = new AboutScreen();
        help = new HelpScreen();
        
        // controller
        cHome = new HomeController(menu, home);
        cSetting = new SettingController(menu, setting);
        cCustomer = new CustomerController(menu, customer);
        cLoan = new LoanController(menu, loan);
        cInstalment = new InstalmentController(menu, instalment);
        cReport = new ReportController(menu, report);
        
        // listener
        menu.setListenerLabelAccountSetting(new ListenerSettingAccount());
        menu.setListenerNavigateHome(new ListenerNavigateHome());
        menu.setListenerNavigateSetting(new ListenerNavigateSetting());
        menu.setListenerNavigateCustomer(new ListenerNavigateCustomer());
        menu.setListenerNavigateLoan(new ListenerNavigateLoan());
        menu.setListenerNavigateInstalment(new ListenerNavigateInstalment());
        menu.setListenerNavigateReport(new ListenerNavigateReport());
        
        menu.setListenerNavigateAboutUs(new ListenerNavigateAboutUs());
        menu.setListenerNavigateHelpSupport(new ListenerNavigateHelpSupport());
        
        menu.setListenerNavigateFacebook(new ListenerNavigateFacebook());
        menu.setListenerNavigateTwitter(new ListenerNavigateTwitter());
        
        menu.setListenerLock(new ListenerLock());
        menu.setListenerLogout(new ListenerLogout());
        menu.setListenerBack(new ListenerBack());
        
        menu.setScreenPage(home);
        
        menu.updateStatistic();
    }

    private class ListenerSettingAccount extends MouseAdapter {

        @Override
        public void mousePressed(MouseEvent e) {
            menu.switchPage(menu.getButtonSetting());
            menu.setScreenPage(setting);
        }
    }
    
    private class ListenerNavigateHome implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            menu.switchPage((JButton) e.getSource());
            menu.setScreenPage(home);
            cHome.update();
        }
    }

    private class ListenerNavigateSetting implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            menu.switchPage((JButton) e.getSource());
            menu.setScreenPage(setting);
        }
    }

    private class ListenerNavigateCustomer implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            menu.switchPage((JButton) e.getSource());
            menu.setScreenPage(customer);
        }
    }

    private class ListenerNavigateLoan implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            menu.switchPage((JButton) e.getSource());
            menu.setScreenPage(loan);
            cLoan.update();
        }
    }

    private class ListenerNavigateInstalment implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            menu.switchPage((JButton) e.getSource());
            menu.setScreenPage(instalment);
            cInstalment.update();
        }
    }

    private class ListenerNavigateReport implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            menu.switchPage((JButton) e.getSource());
            menu.setScreenPage(report);
        }
    }

    private class ListenerNavigateAboutUs implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            menu.switchPage((JButton) e.getSource());
            menu.setScreenPage(about);
        }
    }

    private class ListenerNavigateHelpSupport implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            menu.switchPage((JButton) e.getSource());
            menu.setScreenPage(help);
        }
    }

    private class ListenerNavigateFacebook implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
           BrowserControl.displayURL("http://www.facebook.com");
        }
    }

    private class ListenerNavigateTwitter implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            BrowserControl.displayURL("http://www.twitter.com");
        }
    }
    
    private class ListenerLock implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            menu.showOverlay();
            Lockscreen lock = new Lockscreen(menu);
            menu.hideOverlay();
        }
    }
    
    private class ListenerLogout implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            menu.showOverlay();
            NativeDialog dialog = new NativeDialog(menu, "Logout Confirm", "Are you sure want to Logout?", NativeDialog.DIALOG_QUESTION);
            if(dialog.showDialog() == NativeDialog.PRIMARY_SELECT){
                Config.idSession = null;
                Config.userId = null;
                Config.userName = null;
                Config.hashPassword = null;
                AppController app = new AppController(true);
                menu.dispose();
            }
            menu.hideOverlay();
        }
    }
    
    private class ListenerBack implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            AppController app = new AppController(false);
            menu.dispose();
        }
    }
}
