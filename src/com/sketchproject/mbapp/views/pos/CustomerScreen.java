/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sketchproject.mbapp.views.pos;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Asus
 */
public class CustomerScreen extends javax.swing.JPanel {

    /**
     * Creates new form CustomerScreen
     */
    public CustomerScreen() {
        initComponents();
        
        normalState();
    }
    
    public void normalState(){
        buttonUpdate.setEnabled(false);
        
        buttonAdd.setEnabled(true);
        buttonDelete.setEnabled(true);
        buttonEdit.setEnabled(true);   
        
        tableCustomer.setEnabled(true);
    }
    
    public void editState(){
        buttonAdd.setEnabled(false);
        buttonDelete.setEnabled(false);
        buttonEdit.setEnabled(false);  
        
        buttonUpdate.setEnabled(true);
        
        tableCustomer.setEnabled(false);
    }
    
    public void reset(){
        customerName.setText(null);
        address.setText(null);
        contact.setText(null);
        pid.setText(null);
        motherName.setText(null);
    }
    
    public Object[] getDataCustomer(){
        Object[] data = new Object[5];
        
        data[0] = customerName.getText();
        data[1] = address.getText();
        data[2] = contact.getText();
        data[3] = pid.getText();
        data[4] = motherName.getText();
        
        return data;
    }
    
    public String getKeyword(){
        return keyword.getText();
    }
    
    public void setTableData(DefaultTableModel model){
        tableCustomer.setModel(model);
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(JLabel.CENTER);   
        tableCustomer.getColumnModel().getColumn(0).setCellRenderer( renderer );
        //tableCustomer.setDefaultRenderer(String.class, centerRenderer);
        tableCustomer.getTableHeader().setReorderingAllowed(false);
        tableCustomer.getColumnModel().getColumn(0).setMaxWidth(140);
        //tableCustomer.getColumnModel().getColumn(10).setPreferredWidth(400);
        //tableCustomer.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        
//        spTable.setBorder(BorderFactory.createEmptyBorder());
//        spTable.setColumnHeader(new JViewport(){
//            @Override 
//            public Dimension getPreferredSize() {
//                Dimension d = super.getPreferredSize();
//                d.height = 30;
//                return d;
//            }
//        });
//        
//        tableCustomer.getTableHeader().setBackground(new Color(240,240,240));
//        
//        final TableCellRenderer tcrOs = tableCustomer.getTableHeader().getDefaultRenderer();
//        tableCustomer.getTableHeader().setDefaultRenderer((JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) -> {
//            JLabel lbl = (JLabel) tcrOs.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
//            lbl.setFont (lbl.getFont().deriveFont(14));
//            lbl.setBorder(BorderFactory.createEmptyBorder());
//            return lbl;            
//        }); 
//        spTable.getViewport().setBackground(Color.WHITE);
//        tableCustomer.getTableHeader().setReorderingAllowed(false); 
    }
    
    public Object getId(){
        int row = tableCustomer.getSelectedRow();
        if(row == -1){
            return -1;
        }
        return tableCustomer.getValueAt(row, 0);
    }
    
    public void setEdit(){
        int row = tableCustomer.getSelectedRow();
        customerName.setText(tableCustomer.getValueAt(row, 1).toString());
        address.setText(tableCustomer.getValueAt(row, 2).toString());
        contact.setText(tableCustomer.getValueAt(row, 3).toString());
        pid.setText(tableCustomer.getValueAt(row, 4).toString());
        motherName.setText(tableCustomer.getValueAt(row, 5).toString());
    }
    
    public void setListenerAdd(ActionListener listener){
        buttonAdd.addActionListener(listener);
    }
    
    public void setListenerEdit(ActionListener listener){
        buttonEdit.addActionListener(listener);
    }
    
    public void setListenerUpdate(ActionListener listener){
        buttonUpdate.addActionListener(listener);
    }
    
    public void setListenerDelete(ActionListener listener){
        buttonDelete.addActionListener(listener);
    }
    
    public void setListenerReset(ActionListener listener){
        buttonReset.addActionListener(listener);
    }
    
    public void setListenerSearch(KeyListener listener){
        keyword.addKeyListener(listener);
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
        keyword = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        spTable = new javax.swing.JScrollPane();
        tableCustomer = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        contact = new javax.swing.JTextField();
        address = new javax.swing.JTextField();
        customerName = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        pid = new javax.swing.JTextField();
        motherName = new javax.swing.JTextField();
        buttonAdd = new javax.swing.JButton();
        buttonDelete = new javax.swing.JButton();
        buttonEdit = new javax.swing.JButton();
        buttonUpdate = new javax.swing.JButton();
        buttonReset = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(214, 214, 214));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(83, 84, 87));
        jLabel1.setText("Customer");

        jLabel2.setForeground(new java.awt.Color(83, 84, 87));
        jLabel2.setText("Managing Customer Data");

        keyword.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        keyword.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(221, 221, 221), 2));

        jLabel8.setText("Cari");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addGap(18, 18, 18)
                .addComponent(keyword, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8)
                        .addComponent(keyword, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel2)))
                .addContainerGap())
        );

        tableCustomer.setAutoCreateRowSorter(true);
        tableCustomer.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tableCustomer.setGridColor(new java.awt.Color(204, 204, 204));
        tableCustomer.setRowHeight(24);
        tableCustomer.setRowMargin(3);
        tableCustomer.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tableCustomer.setShowVerticalLines(false);
        spTable.setViewportView(tableCustomer);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Nama Pelanggan");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Alamat");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("No Telephone");

        contact.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        contact.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(221, 221, 221), 2));

        address.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        address.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(221, 221, 221), 2));

        customerName.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        customerName.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(221, 221, 221), 2));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel7.setText("Nama Ibu");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setText("No KTP");

        pid.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        pid.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(221, 221, 221), 2));

        motherName.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        motherName.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(221, 221, 221), 2));

        buttonAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sketchproject/mbapp/assets/AssetsCreditApp/ButtonInsert.png"))); // NOI18N
        buttonAdd.setBorderPainted(false);
        buttonAdd.setContentAreaFilled(false);
        buttonAdd.setFocusPainted(false);
        buttonAdd.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sketchproject/mbapp/assets/AssetsCreditApp/ButtonInsertHover.png"))); // NOI18N

        buttonDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sketchproject/mbapp/assets/AssetsCreditApp/ButtonDelete.png"))); // NOI18N
        buttonDelete.setBorderPainted(false);
        buttonDelete.setContentAreaFilled(false);
        buttonDelete.setFocusPainted(false);
        buttonDelete.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sketchproject/mbapp/assets/AssetsCreditApp/ButtonDeleteHover.png"))); // NOI18N

        buttonEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sketchproject/mbapp/assets/AssetsCreditApp/ButtonEdit.png"))); // NOI18N
        buttonEdit.setBorderPainted(false);
        buttonEdit.setContentAreaFilled(false);
        buttonEdit.setFocusPainted(false);
        buttonEdit.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sketchproject/mbapp/assets/AssetsCreditApp/ButtonEditHover.png"))); // NOI18N

        buttonUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sketchproject/mbapp/assets/AssetsCreditApp/ButtonUpdate.png"))); // NOI18N
        buttonUpdate.setBorderPainted(false);
        buttonUpdate.setContentAreaFilled(false);
        buttonUpdate.setFocusPainted(false);
        buttonUpdate.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sketchproject/mbapp/assets/AssetsCreditApp/ButtonUpdateHover.png"))); // NOI18N

        buttonReset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sketchproject/mbapp/assets/AssetsCreditApp/ButtonReset.png"))); // NOI18N
        buttonReset.setBorderPainted(false);
        buttonReset.setContentAreaFilled(false);
        buttonReset.setFocusPainted(false);
        buttonReset.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sketchproject/mbapp/assets/AssetsCreditApp/ButtonResetHover.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addGap(34, 34, 34)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(contact, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(address, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(customerName, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(51, 51, 51)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(33, 33, 33)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(motherName, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(pid, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(buttonAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(buttonUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonReset, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(47, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(customerName, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(pid, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6))
                                .addGap(14, 14, 14)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel7)
                                    .addComponent(motherName, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(16, 16, 16)
                        .addComponent(contact, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel3)
                        .addGap(22, 22, 22)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(address, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addComponent(jLabel5)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(buttonDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(buttonAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(buttonUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(buttonReset, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(spTable)
                .addContainerGap())
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spTable, javax.swing.GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField address;
    private javax.swing.JButton buttonAdd;
    private javax.swing.JButton buttonDelete;
    private javax.swing.JButton buttonEdit;
    private javax.swing.JButton buttonReset;
    private javax.swing.JButton buttonUpdate;
    private javax.swing.JTextField contact;
    private javax.swing.JTextField customerName;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField keyword;
    private javax.swing.JTextField motherName;
    private javax.swing.JTextField pid;
    private javax.swing.JScrollPane spTable;
    private javax.swing.JTable tableCustomer;
    // End of variables declaration//GEN-END:variables
}
