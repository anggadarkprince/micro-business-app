/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sketchproject.mbapp.views.credit;

import com.sketchproject.mbapp.models.credit.ReportModel;
import com.sketchproject.mbapp.utility.CurrencyModfier;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Asus
 */
public class CreditMenuScreen extends javax.swing.JFrame {
    private Point initialClick;
    
    /** Creates new form MenuCreditScreen */
    public CreditMenuScreen() {
        initComponents();
        buttonAboutUs.setVisible(false);
        buttonHelp.setVisible(false);
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
        
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                initialClick = e.getPoint();
                getComponentAt(initialClick);
            }
        });
        
        addMouseMotionListener(new MouseMotionAdapter() {
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
        buttonLoan.setSelected(false);
        buttonInstalment.setSelected(false);
        buttonReport.setSelected(false);
        
        buttonAboutUs.setSelected(false);
        buttonHelp.setSelected(false);
        
        button.setSelected(true);
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
    
    public void setTotalCustomer(int customer){
        totalCustomer.setText(CurrencyModfier.format(customer,"")+" Member");
    }
    
    public void setLoan(int loan){
        totalLoan.setText(CurrencyModfier.format(loan,"IDR "));
    }
    
    public void setTotalInstalment(int instalment){
        totalInstalment.setText(CurrencyModfier.format(instalment,"IDR "));
    }
    
    public void setTotalRevenue(int revenue){
        totalRevenue.setText(CurrencyModfier.format(revenue,"IDR "));
    }
    
    public void updateStatistic(){
        setTotalCustomer(ReportModel.getTotalCutomer());
        setLoan(ReportModel.getTotalLoan());
        setTotalInstalment(ReportModel.getTotalInstalment());
        setTotalRevenue(ReportModel.getTotalRevenue());
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
    
    public void setListenerNavigateLoan(ActionListener listener){
        buttonLoan.addActionListener(listener);
    }
    public void setListenerNavigateInstalment(ActionListener listener){
        buttonInstalment.addActionListener(listener);
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
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelContainer = new javax.swing.JPanel();
        panelHeader = new javax.swing.JPanel();
        buttonLockApp = new javax.swing.JButton();
        buttonBack = new javax.swing.JButton();
        buttonLogout = new javax.swing.JButton();
        panelFooter = new javax.swing.JPanel();
        panelNavigation = new javax.swing.JPanel();
        buttonReport = new javax.swing.JButton();
        buttonInstalment = new javax.swing.JButton();
        buttonLoan = new javax.swing.JButton();
        buttonCustomer = new javax.swing.JButton();
        buttonSetting = new javax.swing.JButton();
        buttonHome = new javax.swing.JButton();
        panelSidebar = new javax.swing.JPanel();
        labelAccountSetting = new javax.swing.JLabel();
        labelAdministrator = new javax.swing.JLabel();
        avatar = new javax.swing.JLabel();
        logo = new javax.swing.JLabel();
        buttonFacebook = new javax.swing.JButton();
        buttonTwitter = new javax.swing.JButton();
        buttonHelp = new javax.swing.JButton();
        buttonAboutUs = new javax.swing.JButton();
        panelInfoCustomer = new javax.swing.JPanel();
        totalCustomer = new javax.swing.JLabel();
        labelCustomer = new javax.swing.JLabel();
        iconUser = new javax.swing.JLabel();
        panelInfoLoan = new javax.swing.JPanel();
        labelLoan = new javax.swing.JLabel();
        totalLoan = new javax.swing.JLabel();
        iconCircle = new javax.swing.JLabel();
        panelInfoInstalment = new javax.swing.JPanel();
        totalInstalment = new javax.swing.JLabel();
        labelInstalment = new javax.swing.JLabel();
        iconPie = new javax.swing.JLabel();
        panelRevenue = new javax.swing.JPanel();
        labelRevenue = new javax.swing.JLabel();
        totalRevenue = new javax.swing.JLabel();
        iconChart = new javax.swing.JLabel();
        panelContent = new javax.swing.JPanel();
        buttonClose = new javax.swing.JButton();
        buttonChange = new javax.swing.JButton();
        buttonMinimize = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Credits");
        setMinimumSize(new java.awt.Dimension(1000, 650));
        setUndecorated(true);

        panelContainer.setBackground(new java.awt.Color(255, 255, 255));
        panelContainer.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        panelHeader.setBackground(new java.awt.Color(0, 191, 243));

        buttonLockApp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sketchproject/mbapp/assets/AssetsCreditApp/ButtonLockApp.png"))); // NOI18N
        buttonLockApp.setBorderPainted(false);
        buttonLockApp.setContentAreaFilled(false);
        buttonLockApp.setFocusPainted(false);
        buttonLockApp.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sketchproject/mbapp/assets/AssetsCreditApp/ButtonLockAppHover.png"))); // NOI18N

        buttonBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sketchproject/mbapp/assets/AssetsCreditApp/ButtonBackDashboard.png"))); // NOI18N
        buttonBack.setBorderPainted(false);
        buttonBack.setContentAreaFilled(false);
        buttonBack.setFocusPainted(false);
        buttonBack.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sketchproject/mbapp/assets/AssetsCreditApp/ButtonBackDashboardHover.png"))); // NOI18N
        buttonBack.setSelected(true);
        buttonBack.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sketchproject/mbapp/assets/AssetsCreditApp/ButtonBackDashboardHover.png"))); // NOI18N

        buttonLogout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sketchproject/mbapp/assets/AssetsCreditApp/ButtonLogout.png"))); // NOI18N
        buttonLogout.setBorderPainted(false);
        buttonLogout.setContentAreaFilled(false);
        buttonLogout.setFocusPainted(false);
        buttonLogout.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sketchproject/mbapp/assets/AssetsCreditApp/ButtonLogoutHover.png"))); // NOI18N

        org.jdesktop.layout.GroupLayout panelHeaderLayout = new org.jdesktop.layout.GroupLayout(panelHeader);
        panelHeader.setLayout(panelHeaderLayout);
        panelHeaderLayout.setHorizontalGroup(
            panelHeaderLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, panelHeaderLayout.createSequentialGroup()
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(buttonLockApp, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 118, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(0, 0, 0)
                .add(buttonLogout, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 101, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(0, 0, 0)
                .add(buttonBack, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 177, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(0, 0, 0))
        );
        panelHeaderLayout.setVerticalGroup(
            panelHeaderLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, buttonBack, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 43, Short.MAX_VALUE)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, buttonLockApp, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .add(buttonLogout, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        panelFooter.setBackground(new java.awt.Color(28, 29, 34));
        panelFooter.setLayout(new javax.swing.BoxLayout(panelFooter, javax.swing.BoxLayout.Y_AXIS));

        panelNavigation.setBackground(new java.awt.Color(28, 29, 34));
        panelNavigation.setMaximumSize(new java.awt.Dimension(650, 100));

        buttonReport.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sketchproject/mbapp/assets/AssetsCreditApp/ButtonMenuReport.png"))); // NOI18N
        buttonReport.setBorderPainted(false);
        buttonReport.setContentAreaFilled(false);
        buttonReport.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        buttonReport.setFocusPainted(false);
        buttonReport.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sketchproject/mbapp/assets/AssetsCreditApp/ButtonMenuReportHover.png"))); // NOI18N
        buttonReport.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sketchproject/mbapp/assets/AssetsCreditApp/ButtonMenuReportHover.png"))); // NOI18N
        buttonReport.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sketchproject/mbapp/assets/AssetsCreditApp/ButtonMenuReportHover.png"))); // NOI18N

        buttonInstalment.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sketchproject/mbapp/assets/AssetsCreditApp/ButtonMenuInstalment.png"))); // NOI18N
        buttonInstalment.setBorderPainted(false);
        buttonInstalment.setContentAreaFilled(false);
        buttonInstalment.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        buttonInstalment.setFocusPainted(false);
        buttonInstalment.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sketchproject/mbapp/assets/AssetsCreditApp/ButtonMenuInstalmentHover.png"))); // NOI18N
        buttonInstalment.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sketchproject/mbapp/assets/AssetsCreditApp/ButtonMenuInstalmentHover.png"))); // NOI18N
        buttonInstalment.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sketchproject/mbapp/assets/AssetsCreditApp/ButtonMenuInstalmentHover.png"))); // NOI18N

        buttonLoan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sketchproject/mbapp/assets/AssetsCreditApp/ButtonMenuLoan.png"))); // NOI18N
        buttonLoan.setBorderPainted(false);
        buttonLoan.setContentAreaFilled(false);
        buttonLoan.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        buttonLoan.setFocusPainted(false);
        buttonLoan.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sketchproject/mbapp/assets/AssetsCreditApp/ButtonMenuLoanHover.png"))); // NOI18N
        buttonLoan.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sketchproject/mbapp/assets/AssetsCreditApp/ButtonMenuLoanHover.png"))); // NOI18N
        buttonLoan.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sketchproject/mbapp/assets/AssetsCreditApp/ButtonMenuLoanHover.png"))); // NOI18N

        buttonCustomer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sketchproject/mbapp/assets/AssetsCreditApp/ButtonMenuCustomer.png"))); // NOI18N
        buttonCustomer.setBorderPainted(false);
        buttonCustomer.setContentAreaFilled(false);
        buttonCustomer.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        buttonCustomer.setFocusPainted(false);
        buttonCustomer.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sketchproject/mbapp/assets/AssetsCreditApp/ButtonMenuCustomerHover.png"))); // NOI18N
        buttonCustomer.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sketchproject/mbapp/assets/AssetsCreditApp/ButtonMenuCustomerHover.png"))); // NOI18N
        buttonCustomer.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sketchproject/mbapp/assets/AssetsCreditApp/ButtonMenuCustomerHover.png"))); // NOI18N

        buttonSetting.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sketchproject/mbapp/assets/AssetsCreditApp/ButtonMenuSetting.png"))); // NOI18N
        buttonSetting.setBorderPainted(false);
        buttonSetting.setContentAreaFilled(false);
        buttonSetting.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        buttonSetting.setFocusPainted(false);
        buttonSetting.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sketchproject/mbapp/assets/AssetsCreditApp/ButtonMenuSettingHover.png"))); // NOI18N
        buttonSetting.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sketchproject/mbapp/assets/AssetsCreditApp/ButtonMenuSettingHover.png"))); // NOI18N
        buttonSetting.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sketchproject/mbapp/assets/AssetsCreditApp/ButtonMenuSettingHover.png"))); // NOI18N

        buttonHome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sketchproject/mbapp/assets/AssetsCreditApp/ButtonMenuHome.png"))); // NOI18N
        buttonHome.setBorderPainted(false);
        buttonHome.setContentAreaFilled(false);
        buttonHome.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        buttonHome.setFocusPainted(false);
        buttonHome.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sketchproject/mbapp/assets/AssetsCreditApp/ButtonMenuHomeHover.png"))); // NOI18N
        buttonHome.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sketchproject/mbapp/assets/AssetsCreditApp/ButtonMenuHomeHover.png"))); // NOI18N
        buttonHome.setSelected(true);
        buttonHome.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sketchproject/mbapp/assets/AssetsCreditApp/ButtonMenuHomeHover.png"))); // NOI18N

        org.jdesktop.layout.GroupLayout panelNavigationLayout = new org.jdesktop.layout.GroupLayout(panelNavigation);
        panelNavigation.setLayout(panelNavigationLayout);
        panelNavigationLayout.setHorizontalGroup(
            panelNavigationLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(panelNavigationLayout.createSequentialGroup()
                .add(buttonHome, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 108, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(0, 0, 0)
                .add(buttonSetting, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 108, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(0, 0, 0)
                .add(buttonCustomer, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 108, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(0, 0, 0)
                .add(buttonLoan, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 108, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(0, 0, 0)
                .add(buttonInstalment, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 108, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(0, 0, 0)
                .add(buttonReport, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 108, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(0, 0, Short.MAX_VALUE))
        );
        panelNavigationLayout.setVerticalGroup(
            panelNavigationLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, buttonHome, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 80, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
            .add(buttonSetting, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 80, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
            .add(buttonCustomer, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 80, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
            .add(buttonLoan, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 80, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
            .add(buttonInstalment, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 80, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
            .add(buttonReport, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 80, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
        );

        panelFooter.add(panelNavigation);

        panelSidebar.setBackground(new java.awt.Color(255, 255, 255));

        labelAccountSetting.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        labelAccountSetting.setForeground(new java.awt.Color(53, 105, 159));
        labelAccountSetting.setText("Account Setting");
        labelAccountSetting.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        labelAdministrator.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        labelAdministrator.setForeground(new java.awt.Color(97, 98, 101));
        labelAdministrator.setText("Administrator");

        avatar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sketchproject/mbapp/assets/AssetsMenu/avatar.png"))); // NOI18N

        logo.setFont(new java.awt.Font("Verdana", 0, 26)); // NOI18N
        logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sketchproject/mbapp/assets/AssetsCreditApp/logo.png"))); // NOI18N

        buttonFacebook.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sketchproject/mbapp/assets/AssetsMenu/ButtonFacebook.png"))); // NOI18N
        buttonFacebook.setBorderPainted(false);
        buttonFacebook.setContentAreaFilled(false);
        buttonFacebook.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buttonFacebook.setFocusPainted(false);

        buttonTwitter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sketchproject/mbapp/assets/AssetsMenu/ButtonTwitter.png"))); // NOI18N
        buttonTwitter.setBorderPainted(false);
        buttonTwitter.setContentAreaFilled(false);
        buttonTwitter.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buttonTwitter.setFocusPainted(false);

        buttonHelp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sketchproject/mbapp/assets/AssetsCreditApp/ButtonHelpSupport.png"))); // NOI18N
        buttonHelp.setBorderPainted(false);
        buttonHelp.setContentAreaFilled(false);
        buttonHelp.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        buttonHelp.setFocusPainted(false);

        buttonAboutUs.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sketchproject/mbapp/assets/AssetsCreditApp/ButtonAboutUs.png"))); // NOI18N
        buttonAboutUs.setBorderPainted(false);
        buttonAboutUs.setContentAreaFilled(false);
        buttonAboutUs.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        buttonAboutUs.setFocusPainted(false);

        panelInfoCustomer.setBackground(new java.awt.Color(255, 255, 255));

        totalCustomer.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        totalCustomer.setForeground(new java.awt.Color(160, 211, 80));
        totalCustomer.setText("54");

        labelCustomer.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        labelCustomer.setForeground(new java.awt.Color(97, 98, 101));
        labelCustomer.setText("Total Customer");

        iconUser.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        iconUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sketchproject/mbapp/assets/AssetsCreditApp/IconStaticUser.png"))); // NOI18N

        org.jdesktop.layout.GroupLayout panelInfoCustomerLayout = new org.jdesktop.layout.GroupLayout(panelInfoCustomer);
        panelInfoCustomer.setLayout(panelInfoCustomerLayout);
        panelInfoCustomerLayout.setHorizontalGroup(
            panelInfoCustomerLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(panelInfoCustomerLayout.createSequentialGroup()
                .add(24, 24, 24)
                .add(iconUser, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 35, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(panelInfoCustomerLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(labelCustomer)
                    .add(totalCustomer))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelInfoCustomerLayout.setVerticalGroup(
            panelInfoCustomerLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(panelInfoCustomerLayout.createSequentialGroup()
                .add(labelCustomer)
                .add(0, 0, Short.MAX_VALUE)
                .add(totalCustomer))
            .add(iconUser)
        );

        panelInfoLoan.setBackground(new java.awt.Color(255, 255, 255));

        labelLoan.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        labelLoan.setForeground(new java.awt.Color(97, 98, 101));
        labelLoan.setText("Total Pinjaman");

        totalLoan.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        totalLoan.setForeground(new java.awt.Color(138, 163, 255));
        totalLoan.setText("IDR 5400 K");

        iconCircle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sketchproject/mbapp/assets/AssetsCreditApp/IconStaticCircle.png"))); // NOI18N

        org.jdesktop.layout.GroupLayout panelInfoLoanLayout = new org.jdesktop.layout.GroupLayout(panelInfoLoan);
        panelInfoLoan.setLayout(panelInfoLoanLayout);
        panelInfoLoanLayout.setHorizontalGroup(
            panelInfoLoanLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(panelInfoLoanLayout.createSequentialGroup()
                .add(25, 25, 25)
                .add(iconCircle)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(panelInfoLoanLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(labelLoan)
                    .add(totalLoan))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelInfoLoanLayout.setVerticalGroup(
            panelInfoLoanLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(iconCircle)
            .add(panelInfoLoanLayout.createSequentialGroup()
                .add(labelLoan)
                .add(0, 0, Short.MAX_VALUE)
                .add(totalLoan))
        );

        panelInfoInstalment.setBackground(new java.awt.Color(255, 255, 255));

        totalInstalment.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        totalInstalment.setForeground(new java.awt.Color(255, 112, 112));
        totalInstalment.setText("IDR 5400 K");

        labelInstalment.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        labelInstalment.setForeground(new java.awt.Color(97, 98, 101));
        labelInstalment.setText("Total Cicilan");

        iconPie.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sketchproject/mbapp/assets/AssetsCreditApp/IconStaticPie.png"))); // NOI18N

        org.jdesktop.layout.GroupLayout panelInfoInstalmentLayout = new org.jdesktop.layout.GroupLayout(panelInfoInstalment);
        panelInfoInstalment.setLayout(panelInfoInstalmentLayout);
        panelInfoInstalmentLayout.setHorizontalGroup(
            panelInfoInstalmentLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(panelInfoInstalmentLayout.createSequentialGroup()
                .add(27, 27, 27)
                .add(iconPie)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(panelInfoInstalmentLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(labelInstalment)
                    .add(totalInstalment))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelInfoInstalmentLayout.setVerticalGroup(
            panelInfoInstalmentLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(iconPie)
            .add(panelInfoInstalmentLayout.createSequentialGroup()
                .add(labelInstalment)
                .add(0, 0, Short.MAX_VALUE)
                .add(totalInstalment))
        );

        panelRevenue.setBackground(new java.awt.Color(255, 255, 255));

        labelRevenue.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        labelRevenue.setForeground(new java.awt.Color(97, 98, 101));
        labelRevenue.setText("Revenue");

        totalRevenue.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        totalRevenue.setForeground(new java.awt.Color(255, 180, 48));
        totalRevenue.setText("IDR 5400 K");

        iconChart.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sketchproject/mbapp/assets/AssetsCreditApp/IconStaticChart.png"))); // NOI18N

        org.jdesktop.layout.GroupLayout panelRevenueLayout = new org.jdesktop.layout.GroupLayout(panelRevenue);
        panelRevenue.setLayout(panelRevenueLayout);
        panelRevenueLayout.setHorizontalGroup(
            panelRevenueLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(panelRevenueLayout.createSequentialGroup()
                .add(27, 27, 27)
                .add(iconChart)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(panelRevenueLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(labelRevenue)
                    .add(totalRevenue))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelRevenueLayout.setVerticalGroup(
            panelRevenueLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(iconChart)
            .add(panelRevenueLayout.createSequentialGroup()
                .add(labelRevenue)
                .add(0, 0, Short.MAX_VALUE)
                .add(totalRevenue))
        );

        org.jdesktop.layout.GroupLayout panelSidebarLayout = new org.jdesktop.layout.GroupLayout(panelSidebar);
        panelSidebar.setLayout(panelSidebarLayout);
        panelSidebarLayout.setHorizontalGroup(
            panelSidebarLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, panelSidebarLayout.createSequentialGroup()
                .add(panelSidebarLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, panelInfoCustomer, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(panelInfoLoan, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(panelSidebarLayout.createSequentialGroup()
                        .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(panelSidebarLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, buttonAboutUs, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 185, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, buttonHelp, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 185, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, panelSidebarLayout.createSequentialGroup()
                                .add(buttonFacebook, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 38, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(buttonTwitter, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 36, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .add(63, 63, 63))))
                    .add(org.jdesktop.layout.GroupLayout.LEADING, panelSidebarLayout.createSequentialGroup()
                        .add(20, 20, 20)
                        .add(panelSidebarLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(panelSidebarLayout.createSequentialGroup()
                                .add(avatar)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                                .add(panelSidebarLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(labelAdministrator)
                                    .add(labelAccountSetting)))
                            .add(logo))
                        .add(0, 0, Short.MAX_VALUE))
                    .add(panelInfoInstalment, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(panelRevenue, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelSidebarLayout.setVerticalGroup(
            panelSidebarLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(panelSidebarLayout.createSequentialGroup()
                .add(25, 25, 25)
                .add(logo)
                .add(35, 35, 35)
                .add(panelSidebarLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(panelSidebarLayout.createSequentialGroup()
                        .add(labelAdministrator)
                        .add(2, 2, 2)
                        .add(labelAccountSetting))
                    .add(avatar))
                .add(32, 32, 32)
                .add(panelInfoCustomer, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(18, 18, 18)
                .add(panelInfoLoan, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(18, 18, 18)
                .add(panelInfoInstalment, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(18, 18, 18)
                .add(panelRevenue, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 80, Short.MAX_VALUE)
                .add(buttonAboutUs, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 25, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(buttonHelp, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 25, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(panelSidebarLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(buttonTwitter, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 35, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(buttonFacebook, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 35, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(23, 23, 23))
        );

        panelContent.setBackground(new java.awt.Color(255, 255, 255));
        panelContent.setLayout(new java.awt.CardLayout());

        buttonClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sketchproject/mbapp/assets/AssetsMenu/buttonWindowClose.png"))); // NOI18N
        buttonClose.setBorderPainted(false);
        buttonClose.setContentAreaFilled(false);
        buttonClose.setFocusPainted(false);
        buttonClose.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
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
        buttonChange.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
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
        buttonMinimize.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        buttonMinimize.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sketchproject/mbapp/assets/AssetsMenu/buttonWindowMinimizeHover.png"))); // NOI18N
        buttonMinimize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonMinimizeActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout panelContainerLayout = new org.jdesktop.layout.GroupLayout(panelContainer);
        panelContainer.setLayout(panelContainerLayout);
        panelContainerLayout.setHorizontalGroup(
            panelContainerLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(panelContainerLayout.createSequentialGroup()
                .add(panelSidebar, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(panelContainerLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(panelContent, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(panelHeader, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, panelContainerLayout.createSequentialGroup()
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(buttonMinimize, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 15, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(buttonChange, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 15, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(buttonClose, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 16, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
            .add(org.jdesktop.layout.GroupLayout.TRAILING, panelFooter, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 998, Short.MAX_VALUE)
        );
        panelContainerLayout.setVerticalGroup(
            panelContainerLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(panelContainerLayout.createSequentialGroup()
                .add(panelContainerLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(panelContainerLayout.createSequentialGroup()
                        .add(panelContainerLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(buttonMinimize)
                            .add(buttonChange)
                            .add(buttonClose))
                        .add(3, 3, 3)
                        .add(panelHeader, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(0, 0, 0)
                        .add(panelContent, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .add(panelSidebar, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .add(0, 0, 0)
                .add(panelFooter, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(0, 0, 0))
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(panelContainer, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(panelContainer, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCloseActionPerformed
        System.exit(0);
    }//GEN-LAST:event_buttonCloseActionPerformed

    private void buttonMinimizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonMinimizeActionPerformed
        setState(JFrame.ICONIFIED);
    }//GEN-LAST:event_buttonMinimizeActionPerformed

    private void buttonChangeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonChangeActionPerformed
        if(getExtendedState() == JFrame.NORMAL){
            setExtendedState(JFrame.MAXIMIZED_BOTH);
            panelContainer.setBorder(null);
        }
        else{
            setExtendedState(JFrame.NORMAL);
            panelContainer.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        }
    }//GEN-LAST:event_buttonChangeActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel avatar;
    private javax.swing.JButton buttonAboutUs;
    private javax.swing.JButton buttonBack;
    private javax.swing.JButton buttonChange;
    private javax.swing.JButton buttonClose;
    private javax.swing.JButton buttonCustomer;
    private javax.swing.JButton buttonFacebook;
    private javax.swing.JButton buttonHelp;
    private javax.swing.JButton buttonHome;
    private javax.swing.JButton buttonInstalment;
    private javax.swing.JButton buttonLoan;
    private javax.swing.JButton buttonLockApp;
    private javax.swing.JButton buttonLogout;
    private javax.swing.JButton buttonMinimize;
    private javax.swing.JButton buttonReport;
    private javax.swing.JButton buttonSetting;
    private javax.swing.JButton buttonTwitter;
    private javax.swing.JLabel iconChart;
    private javax.swing.JLabel iconCircle;
    private javax.swing.JLabel iconPie;
    private javax.swing.JLabel iconUser;
    private javax.swing.JLabel labelAccountSetting;
    private javax.swing.JLabel labelAdministrator;
    private javax.swing.JLabel labelCustomer;
    private javax.swing.JLabel labelInstalment;
    private javax.swing.JLabel labelLoan;
    private javax.swing.JLabel labelRevenue;
    private javax.swing.JLabel logo;
    private javax.swing.JPanel panelContainer;
    private javax.swing.JPanel panelContent;
    private javax.swing.JPanel panelFooter;
    private javax.swing.JPanel panelHeader;
    private javax.swing.JPanel panelInfoCustomer;
    private javax.swing.JPanel panelInfoInstalment;
    private javax.swing.JPanel panelInfoLoan;
    private javax.swing.JPanel panelNavigation;
    private javax.swing.JPanel panelRevenue;
    private javax.swing.JPanel panelSidebar;
    private javax.swing.JLabel totalCustomer;
    private javax.swing.JLabel totalInstalment;
    private javax.swing.JLabel totalLoan;
    private javax.swing.JLabel totalRevenue;
    // End of variables declaration//GEN-END:variables

}
