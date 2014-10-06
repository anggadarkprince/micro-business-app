/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sketchproject.mbapp.views.pos;

import com.sketchproject.mbapp.models.pos.ReportModel;
import com.sketchproject.mbapp.utility.CurrencyModfier;
import com.sketchproject.mbapp.utility.DateModifier;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Asus
 */
public class PosMenuScreen extends javax.swing.JFrame {

    private Point initialClick;
    
    /**
     * Creates new form Mainmenu
     */
    public PosMenuScreen() {
        initComponents();
        buttonAboutUs.setVisible(false);
        buttonHelp.setVisible(false);
        buttonHome.setSelected(true);
        labelContactUs.setVisible(false);
        buttonFacebook.setVisible(false);
        buttonTwitter.setVisible(false);
        setLocationRelativeTo(null);
        
        getRootPane().setGlassPane(new JComponent() {
            @Override
            public void paintComponent(Graphics g) {
                g.setColor(new Color(0, 0, 0, 100));
                g.fillRect(0, 0, getWidth(), getHeight());
                super.paintComponent(g);
            }
        });
        
        Timer timer = new Timer(1000, (ActionEvent e) -> {
            updateDateTime();
        });
        timer.start();
        
        panelTop.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                initialClick = e.getPoint();
                getComponentAt(initialClick);
            }
        });
        
        panelTop.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {

                // get location of Window
                int thisX = getLocation().x;
                int thisY = getLocation().y;

                // Determine how much the mouse moved since the initial click
                int xMoved = (thisX + e.getX()) - (thisX + initialClick.x);
                int yMoved = (thisY + e.getY()) - (thisY + initialClick.y);

                // Move window to this position
                int X = thisX + xMoved;
                int Y = thisY + yMoved;
                setLocation(X, Y);
            }
        });
        
    }
    
    public void updateStatistic(){
        totalTransaction.setText(CurrencyModfier.format(ReportModel.getTransactionTotal(),"")+ " Item");
        totalSales.setText(CurrencyModfier.format(ReportModel.getTotalSales(), "IDR "));
        businessProfit.setText(CurrencyModfier.format(ReportModel.getTotalRevenue()," IDR "));
    }
    
    public void setUserName(String userName){
        name.setText(userName);
    }
    
    public void showOverlay(){
        getRootPane().getGlassPane().setVisible(true);
    }
    
    public void hideOverlay(){
        getRootPane().getGlassPane().setVisible(false);
    }
        
    public void switchPage(JButton button)
    {
        buttonHome.setSelected(false);
        buttonSetting.setSelected(false);
        buttonCustomer.setSelected(false);
        buttonInventory.setSelected(false);
        buttonTransaction.setSelected(false);
        buttonReport.setSelected(false);
        
        buttonAboutUs.setSelected(false);
        buttonHelp.setSelected(false);
        
        button.setSelected(true);
    }
    
    private void updateDateTime(){
        labelDate.setText(DateModifier.getToday());
        labelClock.setText(DateModifier.getTime());
    }
    
    public void setScreenPage(JPanel page)
    {
        panelContent.removeAll();
        panelContent.add(page);
        panelContent.invalidate();
        panelContent.validate();
        panelContent.revalidate();
        panelContent.repaint();
    }
    
    public JButton getButtonSetting(){
        return buttonSetting;
    }
    
    public void setTotalTransaction(int transaction){
        totalTransaction.setText("IDR "+String.valueOf(transaction));
    }
    
    public void setProfit(int profit){
        businessProfit.setText("IDR "+String.valueOf(profit));
    }
    
    public void setTotalSales(int sales){
        totalSales.setText(String.valueOf(sales));
    }
    
    public void setListenerLabelAccountSetting(MouseAdapter listener){
        labelAccountSetting.addMouseListener(listener);
    }
    
    public void setListenerNavigateHome(ActionListener listener){
        buttonHome.addActionListener(listener);
    }
    
    public void setListenerNavigateSetting(ActionListener listener){
        buttonSetting.addActionListener(listener);
    }
        
    public void setListenerNavigateCustomer(ActionListener listener){
        buttonCustomer.addActionListener(listener);
    }
    
    public void setListenerNavigateInventory(ActionListener listener){
        buttonInventory.addActionListener(listener);
    }
    
    public void setListenerNavigateTransaction(ActionListener listener){
        buttonTransaction.addActionListener(listener);
    }
    
    public void setListenerNavigateReport(ActionListener listener){
        buttonReport.addActionListener(listener);
    }
    
    public void setListenerNavigateAboutUs(ActionListener listener){
        buttonAboutUs.addActionListener(listener);
    }
    
    public void setListenerNavigateHelpSupport(ActionListener listener){
        buttonHelp.addActionListener(listener);
    }
    
    public void setListenerNavigateFacebook(ActionListener listener){
        buttonFacebook.addActionListener(listener);
    }
    
    public void setListenerNavigateTwitter(ActionListener listener){
        buttonTwitter.addActionListener(listener);
    }
    
    public void setListenerLock(ActionListener listener){
        buttonLockApp.addActionListener(listener);
    }
    
    public void setListenerLogout(ActionListener listener){
        buttonLogout.addActionListener(listener);
    }
    
    public void setListenerBack(ActionListener listener){
        buttonBack.addActionListener(listener);
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelHeader = new javax.swing.JPanel();
        logo = new javax.swing.JLabel();
        buttonBack = new javax.swing.JButton();
        buttonLockApp = new javax.swing.JButton();
        buttonLogout = new javax.swing.JButton();
        panelSidebar = new javax.swing.JPanel();
        buttonHome = new javax.swing.JButton();
        buttonSetting = new javax.swing.JButton();
        buttonCustomer = new javax.swing.JButton();
        buttonInventory = new javax.swing.JButton();
        buttonTransaction = new javax.swing.JButton();
        buttonReport = new javax.swing.JButton();
        buttonAboutUs = new javax.swing.JButton();
        buttonHelp = new javax.swing.JButton();
        labelApplication = new javax.swing.JLabel();
        labelAdministrator = new javax.swing.JLabel();
        labelAccountSetting = new javax.swing.JLabel();
        avatar = new javax.swing.JLabel();
        labelContactUs = new javax.swing.JLabel();
        buttonTwitter = new javax.swing.JButton();
        buttonFacebook = new javax.swing.JButton();
        panelSummaries = new javax.swing.JPanel();
        name = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        totalTransaction = new javax.swing.JLabel();
        businessProfit = new javax.swing.JLabel();
        totalSales = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        panelFooter = new javax.swing.JPanel();
        labelDate = new javax.swing.JLabel();
        labelClock = new javax.swing.JLabel();
        panelContent = new javax.swing.JPanel();
        panelTop = new javax.swing.JPanel();
        buttonClose = new javax.swing.JButton();
        buttonChange = new javax.swing.JButton();
        buttonMinimize = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Point of Sale");
        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(1100, 695));
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(1000, 650));

        panelHeader.setBackground(new java.awt.Color(53, 93, 156));

        logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sketchproject/mbapp/assets/AssetsPosApp/logo.png"))); // NOI18N

        buttonBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sketchproject/mbapp/assets/AssetsPosApp/ButtonBackDashboard.png"))); // NOI18N
        buttonBack.setBorderPainted(false);
        buttonBack.setContentAreaFilled(false);
        buttonBack.setFocusPainted(false);
        buttonBack.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sketchproject/mbapp/assets/AssetsPosApp/ButtonBackDashboardHover.png"))); // NOI18N

        buttonLockApp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sketchproject/mbapp/assets/AssetsPosApp/ButtonLockApp.png"))); // NOI18N
        buttonLockApp.setBorderPainted(false);
        buttonLockApp.setContentAreaFilled(false);
        buttonLockApp.setFocusPainted(false);
        buttonLockApp.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sketchproject/mbapp/assets/AssetsPosApp/ButtonLockAppHover.png"))); // NOI18N

        buttonLogout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sketchproject/mbapp/assets/AssetsPosApp/ButtonLogout.png"))); // NOI18N
        buttonLogout.setBorderPainted(false);
        buttonLogout.setContentAreaFilled(false);
        buttonLogout.setFocusPainted(false);
        buttonLogout.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sketchproject/mbapp/assets/AssetsPosApp/ButtonLogoutHover.png"))); // NOI18N

        javax.swing.GroupLayout panelHeaderLayout = new javax.swing.GroupLayout(panelHeader);
        panelHeader.setLayout(panelHeaderLayout);
        panelHeaderLayout.setHorizontalGroup(
            panelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelHeaderLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(logo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(buttonLockApp, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(buttonLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(buttonBack, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panelHeaderLayout.setVerticalGroup(
            panelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(buttonBack, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addComponent(buttonLockApp, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelHeaderLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(logo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(buttonLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        panelSidebar.setBackground(new java.awt.Color(28, 29, 34));

        buttonHome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sketchproject/mbapp/assets/AssetsPosApp/ButtonMenuHome.png"))); // NOI18N
        buttonHome.setBorderPainted(false);
        buttonHome.setContentAreaFilled(false);
        buttonHome.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        buttonHome.setFocusPainted(false);
        buttonHome.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sketchproject/mbapp/assets/AssetsPosApp/ButtonMenuHomeHover.png"))); // NOI18N
        buttonHome.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sketchproject/mbapp/assets/AssetsPosApp/ButtonMenuHomeHover.png"))); // NOI18N
        buttonHome.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sketchproject/mbapp/assets/AssetsPosApp/ButtonMenuHomeHover.png"))); // NOI18N

        buttonSetting.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sketchproject/mbapp/assets/AssetsPosApp/ButtonMenuSetting.png"))); // NOI18N
        buttonSetting.setBorderPainted(false);
        buttonSetting.setContentAreaFilled(false);
        buttonSetting.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        buttonSetting.setFocusPainted(false);
        buttonSetting.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sketchproject/mbapp/assets/AssetsPosApp/ButtonMenuSettingHover.png"))); // NOI18N
        buttonSetting.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sketchproject/mbapp/assets/AssetsPosApp/ButtonMenuSettingHover.png"))); // NOI18N
        buttonSetting.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sketchproject/mbapp/assets/AssetsPosApp/ButtonMenuSettingHover.png"))); // NOI18N

        buttonCustomer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sketchproject/mbapp/assets/AssetsPosApp/ButtonMenuCustomer.png"))); // NOI18N
        buttonCustomer.setBorderPainted(false);
        buttonCustomer.setContentAreaFilled(false);
        buttonCustomer.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        buttonCustomer.setFocusPainted(false);
        buttonCustomer.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sketchproject/mbapp/assets/AssetsPosApp/ButtonMenuCustomerHover.png"))); // NOI18N
        buttonCustomer.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sketchproject/mbapp/assets/AssetsPosApp/ButtonMenuCustomerHover.png"))); // NOI18N
        buttonCustomer.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sketchproject/mbapp/assets/AssetsPosApp/ButtonMenuCustomerHover.png"))); // NOI18N

        buttonInventory.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sketchproject/mbapp/assets/AssetsPosApp/ButtonMenuInventory.png"))); // NOI18N
        buttonInventory.setBorderPainted(false);
        buttonInventory.setContentAreaFilled(false);
        buttonInventory.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        buttonInventory.setFocusPainted(false);
        buttonInventory.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sketchproject/mbapp/assets/AssetsPosApp/ButtonMenuInventoryHover.png"))); // NOI18N
        buttonInventory.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sketchproject/mbapp/assets/AssetsPosApp/ButtonMenuInventoryHover.png"))); // NOI18N
        buttonInventory.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sketchproject/mbapp/assets/AssetsPosApp/ButtonMenuInventoryHover.png"))); // NOI18N

        buttonTransaction.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sketchproject/mbapp/assets/AssetsPosApp/ButtonMenuTransaction.png"))); // NOI18N
        buttonTransaction.setBorderPainted(false);
        buttonTransaction.setContentAreaFilled(false);
        buttonTransaction.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        buttonTransaction.setFocusPainted(false);
        buttonTransaction.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sketchproject/mbapp/assets/AssetsPosApp/ButtonMenuTransactionHover.png"))); // NOI18N
        buttonTransaction.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sketchproject/mbapp/assets/AssetsPosApp/ButtonMenuTransactionHover.png"))); // NOI18N
        buttonTransaction.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sketchproject/mbapp/assets/AssetsPosApp/ButtonMenuTransactionHover.png"))); // NOI18N

        buttonReport.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sketchproject/mbapp/assets/AssetsPosApp/ButtonMenuReport.png"))); // NOI18N
        buttonReport.setBorderPainted(false);
        buttonReport.setContentAreaFilled(false);
        buttonReport.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        buttonReport.setFocusPainted(false);
        buttonReport.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sketchproject/mbapp/assets/AssetsPosApp/ButtonMenuReportHover.png"))); // NOI18N
        buttonReport.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sketchproject/mbapp/assets/AssetsPosApp/ButtonMenuReportHover.png"))); // NOI18N
        buttonReport.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sketchproject/mbapp/assets/AssetsPosApp/ButtonMenuReportHover.png"))); // NOI18N

        buttonAboutUs.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sketchproject/mbapp/assets/AssetsPosApp/ButtonMenuAboutUs.png"))); // NOI18N
        buttonAboutUs.setBorderPainted(false);
        buttonAboutUs.setContentAreaFilled(false);
        buttonAboutUs.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        buttonAboutUs.setFocusPainted(false);
        buttonAboutUs.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sketchproject/mbapp/assets/AssetsPosApp/ButtonMenuAboutUsHover.png"))); // NOI18N
        buttonAboutUs.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sketchproject/mbapp/assets/AssetsPosApp/ButtonMenuAboutUsHover.png"))); // NOI18N
        buttonAboutUs.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sketchproject/mbapp/assets/AssetsPosApp/ButtonMenuAboutUsHover.png"))); // NOI18N

        buttonHelp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sketchproject/mbapp/assets/AssetsPosApp/ButtonMenuHelp.png"))); // NOI18N
        buttonHelp.setBorderPainted(false);
        buttonHelp.setContentAreaFilled(false);
        buttonHelp.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        buttonHelp.setFocusPainted(false);
        buttonHelp.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sketchproject/mbapp/assets/AssetsPosApp/ButtonMenuHelpHover.png"))); // NOI18N
        buttonHelp.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sketchproject/mbapp/assets/AssetsPosApp/ButtonMenuHelpHover.png"))); // NOI18N
        buttonHelp.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sketchproject/mbapp/assets/AssetsPosApp/ButtonMenuHelpHover.png"))); // NOI18N

        labelApplication.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        labelApplication.setForeground(new java.awt.Color(97, 98, 101));
        labelApplication.setText("Application");

        labelAdministrator.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        labelAdministrator.setForeground(new java.awt.Color(97, 98, 101));
        labelAdministrator.setText("Administrator");

        labelAccountSetting.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        labelAccountSetting.setForeground(new java.awt.Color(53, 105, 159));
        labelAccountSetting.setText("Account Setting");
        labelAccountSetting.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        avatar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sketchproject/mbapp/assets/AssetsMenu/avatar.png"))); // NOI18N

        labelContactUs.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        labelContactUs.setForeground(new java.awt.Color(97, 98, 101));
        labelContactUs.setText("Contact Us");

        buttonTwitter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sketchproject/mbapp/assets/AssetsMenu/ButtonTwitter.png"))); // NOI18N
        buttonTwitter.setBorderPainted(false);
        buttonTwitter.setContentAreaFilled(false);
        buttonTwitter.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buttonTwitter.setFocusPainted(false);

        buttonFacebook.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sketchproject/mbapp/assets/AssetsMenu/ButtonFacebook.png"))); // NOI18N
        buttonFacebook.setBorderPainted(false);
        buttonFacebook.setContentAreaFilled(false);
        buttonFacebook.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buttonFacebook.setFocusPainted(false);

        javax.swing.GroupLayout panelSidebarLayout = new javax.swing.GroupLayout(panelSidebar);
        panelSidebar.setLayout(panelSidebarLayout);
        panelSidebarLayout.setHorizontalGroup(
            panelSidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelSidebarLayout.createSequentialGroup()
                .addGap(0, 15, Short.MAX_VALUE)
                .addGroup(panelSidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelApplication)
                    .addComponent(labelContactUs)
                    .addGroup(panelSidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(buttonHome, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(buttonSetting, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(buttonCustomer, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(buttonInventory, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(buttonTransaction, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(buttonReport, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(buttonAboutUs, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(buttonHelp, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelSidebarLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelSidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelSidebarLayout.createSequentialGroup()
                        .addComponent(avatar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelSidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelAdministrator)
                            .addComponent(labelAccountSetting))
                        .addGap(25, 25, 25))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelSidebarLayout.createSequentialGroup()
                        .addComponent(buttonFacebook, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonTwitter, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(63, 63, 63))))
        );
        panelSidebarLayout.setVerticalGroup(
            panelSidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelSidebarLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelSidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelSidebarLayout.createSequentialGroup()
                        .addComponent(labelAdministrator)
                        .addGap(2, 2, 2)
                        .addComponent(labelAccountSetting))
                    .addComponent(avatar))
                .addGap(18, 18, 18)
                .addComponent(labelApplication)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonHome, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonSetting, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonInventory, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonTransaction, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonReport, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addComponent(labelContactUs)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonAboutUs, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonHelp, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelSidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonTwitter, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonFacebook, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        panelSummaries.setBackground(new java.awt.Color(28, 29, 34));

        name.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        name.setForeground(new java.awt.Color(255, 255, 255));
        name.setText("Hi, Angga Ari Wijaya");

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sketchproject/mbapp/assets/AssetsPosApp/IconStaticCart.png"))); // NOI18N

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sketchproject/mbapp/assets/AssetsPosApp/IconStaticDollar.png"))); // NOI18N

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sketchproject/mbapp/assets/AssetsPosApp/IconStaticCircle.png"))); // NOI18N

        jLabel12.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(97, 98, 101));
        jLabel12.setText("Total Transaction");

        jLabel13.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(97, 98, 101));
        jLabel13.setText("Business Profits");

        jLabel14.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(97, 98, 101));
        jLabel14.setText("Total Sales");

        totalTransaction.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        totalTransaction.setForeground(new java.awt.Color(255, 255, 255));
        totalTransaction.setText("IDR 5400 K");

        businessProfit.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        businessProfit.setForeground(new java.awt.Color(255, 255, 255));
        businessProfit.setText("IDR 5400 K");

        totalSales.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        totalSales.setForeground(new java.awt.Color(255, 255, 255));
        totalSales.setText("5400 K");

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sketchproject/mbapp/assets/AssetsPosApp/IconUser.png"))); // NOI18N

        javax.swing.GroupLayout panelSummariesLayout = new javax.swing.GroupLayout(panelSummaries);
        panelSummaries.setLayout(panelSummariesLayout);
        panelSummariesLayout.setHorizontalGroup(
            panelSummariesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSummariesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelSummariesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(totalTransaction))
                .addGap(36, 36, 36)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelSummariesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addComponent(businessProfit))
                .addGap(45, 45, 45)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelSummariesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addComponent(totalSales))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(name)
                .addGap(19, 19, 19))
        );
        panelSummariesLayout.setVerticalGroup(
            panelSummariesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSummariesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelSummariesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelSummariesLayout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addGap(0, 0, 0)
                        .addComponent(totalSales))
                    .addComponent(jLabel11)
                    .addComponent(jLabel9)
                    .addGroup(panelSummariesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel8))
                    .addGroup(panelSummariesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(panelSummariesLayout.createSequentialGroup()
                            .addComponent(jLabel13)
                            .addGap(0, 0, Short.MAX_VALUE)
                            .addComponent(businessProfit))
                        .addGroup(panelSummariesLayout.createSequentialGroup()
                            .addComponent(jLabel12)
                            .addGap(0, 0, Short.MAX_VALUE)
                            .addComponent(totalTransaction))
                        .addComponent(jLabel10)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelFooter.setBackground(new java.awt.Color(15, 16, 21));

        labelDate.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        labelDate.setForeground(new java.awt.Color(97, 98, 101));
        labelDate.setText("25 January 2014");

        labelClock.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        labelClock.setForeground(new java.awt.Color(97, 98, 101));
        labelClock.setText("23:15 AM");

        javax.swing.GroupLayout panelFooterLayout = new javax.swing.GroupLayout(panelFooter);
        panelFooter.setLayout(panelFooterLayout);
        panelFooterLayout.setHorizontalGroup(
            panelFooterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFooterLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(labelDate)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(labelClock)
                .addGap(13, 13, 13))
        );
        panelFooterLayout.setVerticalGroup(
            panelFooterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelFooterLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelFooterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelDate)
                    .addComponent(labelClock))
                .addContainerGap())
        );

        panelContent.setBackground(new java.awt.Color(255, 255, 255));
        panelContent.setLayout(new java.awt.CardLayout());

        panelTop.setBackground(new java.awt.Color(28, 29, 34));

        buttonClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sketchproject/mbapp/assets/AssetsMenu/buttonWindowClose.png"))); // NOI18N
        buttonClose.setBorderPainted(false);
        buttonClose.setContentAreaFilled(false);
        buttonClose.setFocusPainted(false);
        buttonClose.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sketchproject/mbapp/assets/AssetsMenu/buttonWindowCloseHover.png"))); // NOI18N
        buttonClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCloseActionPerformed(evt);
            }
        });

        buttonChange.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sketchproject/mbapp/assets/AssetsMenu/buttonWindowChange.png"))); // NOI18N
        buttonChange.setBorderPainted(false);
        buttonChange.setContentAreaFilled(false);
        buttonChange.setFocusPainted(false);
        buttonChange.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sketchproject/mbapp/assets/AssetsMenu/buttonWindowChangeHover.png"))); // NOI18N
        buttonChange.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonChangeActionPerformed(evt);
            }
        });

        buttonMinimize.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sketchproject/mbapp/assets/AssetsMenu/buttonWindowMinimize.png"))); // NOI18N
        buttonMinimize.setBorderPainted(false);
        buttonMinimize.setContentAreaFilled(false);
        buttonMinimize.setFocusPainted(false);
        buttonMinimize.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sketchproject/mbapp/assets/AssetsMenu/buttonWindowMinimizeHover.png"))); // NOI18N
        buttonMinimize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonMinimizeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelTopLayout = new javax.swing.GroupLayout(panelTop);
        panelTop.setLayout(panelTopLayout);
        panelTopLayout.setHorizontalGroup(
            panelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTopLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(buttonMinimize, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonChange, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonClose, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelTopLayout.setVerticalGroup(
            panelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(buttonClose, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
            .addComponent(buttonChange, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(buttonMinimize, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelHeader, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelSidebar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelFooter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelSummaries, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelContent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addComponent(panelTop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelTop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panelHeader, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panelSidebar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(0, 0, 0)
                        .addComponent(panelFooter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panelSummaries, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(panelContent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCloseActionPerformed
        System.exit(0);
    }//GEN-LAST:event_buttonCloseActionPerformed

    private void buttonChangeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonChangeActionPerformed
        if(getExtendedState() == JFrame.NORMAL){
            setExtendedState(JFrame.MAXIMIZED_BOTH);
            //panelContainer.setBorder(null);
        }
        else{
            setExtendedState(JFrame.NORMAL);
            //panelContainer.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        }
    }//GEN-LAST:event_buttonChangeActionPerformed

    private void buttonMinimizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonMinimizeActionPerformed
        setState(JFrame.ICONIFIED);
    }//GEN-LAST:event_buttonMinimizeActionPerformed

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel avatar;
    private javax.swing.JLabel businessProfit;
    private javax.swing.JButton buttonAboutUs;
    private javax.swing.JButton buttonBack;
    private javax.swing.JButton buttonChange;
    private javax.swing.JButton buttonClose;
    private javax.swing.JButton buttonCustomer;
    private javax.swing.JButton buttonFacebook;
    private javax.swing.JButton buttonHelp;
    private javax.swing.JButton buttonHome;
    private javax.swing.JButton buttonInventory;
    private javax.swing.JButton buttonLockApp;
    private javax.swing.JButton buttonLogout;
    private javax.swing.JButton buttonMinimize;
    private javax.swing.JButton buttonReport;
    private javax.swing.JButton buttonSetting;
    private javax.swing.JButton buttonTransaction;
    private javax.swing.JButton buttonTwitter;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel labelAccountSetting;
    private javax.swing.JLabel labelAdministrator;
    private javax.swing.JLabel labelApplication;
    private javax.swing.JLabel labelClock;
    private javax.swing.JLabel labelContactUs;
    private javax.swing.JLabel labelDate;
    private javax.swing.JLabel logo;
    private javax.swing.JLabel name;
    private javax.swing.JPanel panelContent;
    private javax.swing.JPanel panelFooter;
    private javax.swing.JPanel panelHeader;
    private javax.swing.JPanel panelSidebar;
    private javax.swing.JPanel panelSummaries;
    private javax.swing.JPanel panelTop;
    private javax.swing.JLabel totalSales;
    private javax.swing.JLabel totalTransaction;
    // End of variables declaration//GEN-END:variables
}
