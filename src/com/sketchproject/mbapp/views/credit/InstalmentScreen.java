/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sketchproject.mbapp.views.credit;

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
public class InstalmentScreen extends javax.swing.JPanel {

    /** Creates new form HomeScreen */
    public InstalmentScreen() {
        initComponents();
        
//        spTable.setBorder(BorderFactory.createEmptyBorder());
//        spTable.setColumnHeader(new JViewport() {
//            @Override 
//            public Dimension getPreferredSize() {
//                Dimension d = super.getPreferredSize();
//                d.height = 30;
//                return d;
//            }
//        });
//        
//        tableLoan.getTableHeader().setBackground(new Color(240,240,240));
//        
//        final TableCellRenderer tcrOs = tableLoan.getTableHeader().getDefaultRenderer();
//        tableLoan.getTableHeader().setDefaultRenderer((JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) -> {
//            JLabel lbl = (JLabel) tcrOs.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
//            lbl.setFont (lbl.getFont().deriveFont(14));
//            lbl.setBorder(BorderFactory.createEmptyBorder());
//            return lbl;            
//        }); 
//        spTable.getViewport().setBackground(Color.WHITE);
//        tableLoan.getTableHeader().setReorderingAllowed(false);  
    }
    
    public Object getId(){
        int row = tableLoan.getSelectedRow();
        if(row == -1){
            return row;
        }
        return tableLoan.getValueAt(row, 0);
    }
            
    public Object getKeyword(){
        if(keyword.getText().trim().isEmpty())
        {
            return "";
        }
        return keyword.getText();
    }
    
    public void setLoanInstalmentData(DefaultTableModel data){
        if(data != null){
            tableLoan.setModel(data);
            tableLoan.getColumnModel().getColumn(0).setMaxWidth(80);
            DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
            renderer.setHorizontalAlignment(JLabel.CENTER); 
            tableLoan.setDefaultRenderer(String.class, renderer);
        }
        else{
            tableLoan.setModel(new DefaultTableModel());
        }
    }
    
    public void setListenerKeyword(KeyListener listener){
        keyword.addKeyListener(listener);
    }
    
    public void setListenerDetail(ActionListener listener){
        detail.addActionListener(listener);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        keyword = new javax.swing.JTextField();
        spTable = new javax.swing.JScrollPane();
        tableLoan = new javax.swing.JTable();
        detail = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 102, 102));
        jLabel2.setText("Managing Instalment Transaction");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 102));
        jLabel1.setText("Cicilan");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setText("Customer");

        keyword.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(220, 220, 220), 3));

        tableLoan.setAutoCreateRowSorter(true);
        tableLoan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tableLoan.setGridColor(new java.awt.Color(220, 220, 220));
        tableLoan.setRowHeight(24);
        tableLoan.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tableLoan.setShowVerticalLines(false);
        spTable.setViewportView(tableLoan);

        detail.setBackground(new java.awt.Color(102, 204, 255));
        detail.setText("DETAIL");
        detail.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204), 2));
        detail.setBorderPainted(false);

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(spTable, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 644, Short.MAX_VALUE)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(jLabel3)
                        .add(30, 30, 30)
                        .add(keyword, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 199, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(detail, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 92, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(jLabel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 28, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(keyword, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 28, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(detail, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .add(18, 18, 18)
                .add(spTable, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 365, Short.MAX_VALUE)
                .addContainerGap())
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel2)
                    .add(jLabel1))
                .addContainerGap(474, Short.MAX_VALUE))
            .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jLabel1)
                .add(0, 0, 0)
                .add(jLabel2)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton detail;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField keyword;
    private javax.swing.JScrollPane spTable;
    private javax.swing.JTable tableLoan;
    // End of variables declaration//GEN-END:variables

}