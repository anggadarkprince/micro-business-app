/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sketchproject.mbapp.views.pos;

import com.sketchproject.mbapp.core.Config;
import java.awt.event.ActionListener;

/**
 *
 * @author Asus
 */
public class SettingScreen extends javax.swing.JPanel {

    /**
     * Creates new form SettingScreen
     */
    public SettingScreen() {
        initComponents();
    }

    public void setSetting(Object[] setting){
        shopName.setText(setting[0].toString());
        address.setText(setting[1].toString());
        contact.setText(setting[2].toString());
        owner.setText(setting[3].toString());
        
        username.setText(Config.userId);
        name.setText(Config.userName);
        password.setText(null);
        newPassword.setText(null);
        confirmPassword.setText(null);
    }
    
    public Object[][] getSetting(){
        Object[][] data = new Object[2][];
        data[0] = new Object[4];
        data[1]= new Object[5];
        
        data[0][0] = shopName.getText();
        data[0][1] = address.getText();
        data[0][2] = contact.getText();
        data[0][3] = owner.getText();
        
        data[1][0] = username.getText();
        data[1][1] = name.getText();
        data[1][2] = new String(password.getPassword());
        data[1][3] = new String(newPassword.getPassword());
        data[1][4] = new String(confirmPassword.getPassword());
        
        return data;
    }
    
    public void reset(){
        password.setText(null);
        newPassword.setText(null);
        confirmPassword.setText(null);
    }
    
    
    public void setListenerUpdateSetting(ActionListener listener){
        buttonUpdateSetting.addActionListener(listener);
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        shopName = new javax.swing.JTextField();
        username = new javax.swing.JTextField();
        name = new javax.swing.JTextField();
        address = new javax.swing.JTextField();
        contact = new javax.swing.JTextField();
        owner = new javax.swing.JTextField();
        password = new javax.swing.JPasswordField();
        confirmPassword = new javax.swing.JPasswordField();
        newPassword = new javax.swing.JPasswordField();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        buttonUpdateSetting = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(214, 214, 214));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(83, 84, 87));
        jLabel1.setText("Setting");

        jLabel2.setForeground(new java.awt.Color(83, 84, 87));
        jLabel2.setText("Configure application and user");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addContainerGap(563, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(0, 0, 0)
                .addComponent(jLabel2)
                .addContainerGap())
        );

        shopName.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        shopName.setText("Django Shop");
        shopName.setBorder(null);
        shopName.setOpaque(false);
        jLayeredPane1.add(shopName);
        shopName.setBounds(40, 30, 250, 30);

        username.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        username.setText("anggadakprince");
        username.setBorder(null);
        username.setOpaque(false);
        jLayeredPane1.add(username);
        username.setBounds(380, 30, 250, 30);

        name.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        name.setText("Angga Ari Wijaya");
        name.setBorder(null);
        name.setOpaque(false);
        jLayeredPane1.add(name);
        name.setBounds(380, 90, 250, 30);

        address.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        address.setText("Java Street 56");
        address.setBorder(null);
        address.setOpaque(false);
        jLayeredPane1.add(address);
        address.setBounds(40, 150, 250, 30);

        contact.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        contact.setText("+6285655479868");
        contact.setBorder(null);
        contact.setOpaque(false);
        jLayeredPane1.add(contact);
        contact.setBounds(40, 210, 250, 30);

        owner.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        owner.setText("Yuka Ryuki");
        owner.setBorder(null);
        owner.setOpaque(false);
        jLayeredPane1.add(owner);
        owner.setBounds(40, 90, 250, 30);

        password.setText("jPasswordField1");
        password.setBorder(null);
        password.setOpaque(false);
        jLayeredPane1.add(password);
        password.setBounds(380, 150, 250, 30);

        confirmPassword.setText("jPasswordField1");
        confirmPassword.setBorder(null);
        confirmPassword.setOpaque(false);
        jLayeredPane1.add(confirmPassword);
        confirmPassword.setBounds(380, 270, 250, 30);

        newPassword.setText("jPasswordField1");
        newPassword.setBorder(null);
        newPassword.setOpaque(false);
        jLayeredPane1.add(newPassword);
        newPassword.setBounds(380, 210, 250, 30);

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sketchproject/mbapp/assets/AssetsMenu/TextfieldBase.png"))); // NOI18N
        jLayeredPane1.add(jLabel7);
        jLabel7.setBounds(31, 31, 270, 32);

        jLabel6.setText("NamaToko");
        jLayeredPane1.add(jLabel6);
        jLabel6.setBounds(31, 11, 140, 14);

        jLabel4.setText("Pemilik");
        jLayeredPane1.add(jLabel4);
        jLabel4.setBounds(31, 74, 130, 14);

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sketchproject/mbapp/assets/AssetsMenu/TextfieldBase.png"))); // NOI18N
        jLayeredPane1.add(jLabel8);
        jLabel8.setBounds(30, 90, 270, 32);

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sketchproject/mbapp/assets/AssetsMenu/TextfieldBase.png"))); // NOI18N
        jLayeredPane1.add(jLabel9);
        jLabel9.setBounds(30, 150, 270, 32);

        jLabel3.setText("Alamat");
        jLayeredPane1.add(jLabel3);
        jLabel3.setBounds(30, 130, 140, 14);

        jLabel5.setText("Telepon");
        jLayeredPane1.add(jLabel5);
        jLabel5.setBounds(30, 190, 130, 14);

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sketchproject/mbapp/assets/AssetsMenu/TextfieldBase.png"))); // NOI18N
        jLayeredPane1.add(jLabel10);
        jLabel10.setBounds(30, 210, 270, 32);

        jLabel11.setText("Username");
        jLayeredPane1.add(jLabel11);
        jLabel11.setBounds(370, 10, 140, 14);

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sketchproject/mbapp/assets/AssetsMenu/TextfieldBase.png"))); // NOI18N
        jLayeredPane1.add(jLabel12);
        jLabel12.setBounds(370, 30, 270, 32);

        jLabel13.setText("Nama");
        jLayeredPane1.add(jLabel13);
        jLabel13.setBounds(370, 70, 130, 14);

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sketchproject/mbapp/assets/AssetsMenu/TextfieldBase.png"))); // NOI18N
        jLayeredPane1.add(jLabel14);
        jLabel14.setBounds(370, 90, 270, 32);

        jLabel15.setText("Password");
        jLayeredPane1.add(jLabel15);
        jLabel15.setBounds(370, 130, 160, 14);

        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sketchproject/mbapp/assets/AssetsMenu/TextfieldBase.png"))); // NOI18N
        jLayeredPane1.add(jLabel16);
        jLabel16.setBounds(370, 150, 270, 32);

        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sketchproject/mbapp/assets/AssetsMenu/TextfieldBase.png"))); // NOI18N
        jLayeredPane1.add(jLabel17);
        jLabel17.setBounds(370, 210, 270, 32);

        jLabel18.setText("Password Baru");
        jLayeredPane1.add(jLabel18);
        jLabel18.setBounds(370, 190, 160, 14);

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sketchproject/mbapp/assets/AssetsMenu/TextfieldBase.png"))); // NOI18N
        jLayeredPane1.add(jLabel19);
        jLabel19.setBounds(370, 270, 270, 32);

        jLabel20.setText("Ulangi Password Baru");
        jLayeredPane1.add(jLabel20);
        jLabel20.setBounds(370, 250, 170, 14);

        buttonUpdateSetting.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sketchproject/mbapp/assets/AssetsCreditApp/ButtonUpdate.png"))); // NOI18N
        buttonUpdateSetting.setBorderPainted(false);
        buttonUpdateSetting.setContentAreaFilled(false);
        buttonUpdateSetting.setFocusPainted(false);
        buttonUpdateSetting.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sketchproject/mbapp/assets/AssetsCreditApp/ButtonUpdateHover.png"))); // NOI18N
        jLayeredPane1.add(buttonUpdateSetting);
        buttonUpdateSetting.setBounds(520, 320, 120, 40);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLayeredPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField address;
    private javax.swing.JButton buttonUpdateSetting;
    private javax.swing.JPasswordField confirmPassword;
    private javax.swing.JTextField contact;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField name;
    private javax.swing.JPasswordField newPassword;
    private javax.swing.JTextField owner;
    private javax.swing.JPasswordField password;
    private javax.swing.JTextField shopName;
    private javax.swing.JTextField username;
    // End of variables declaration//GEN-END:variables
}
