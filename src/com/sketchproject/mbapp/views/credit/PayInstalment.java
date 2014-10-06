/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sketchproject.mbapp.views.credit;

import com.sketchproject.mbapp.utility.DateModifier;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeListener;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
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
public class PayInstalment extends javax.swing.JDialog {

    /** Creates new form PayInstalment */
    public PayInstalment(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        setLocationRelativeTo(parent);
        
        getRootPane().setGlassPane(new JComponent(){
            @Override
            public void paintComponent(Graphics g) 
            {
                g.setColor(new Color(0, 0, 0, 100));
                g.fillRect(0, 0, getWidth(), getHeight());
                super.paintComponent(g);
            }
        });
        
        instalmentDate.setDate(DateModifier.stringToDate(DateModifier.getToday("yyyy-MM-dd")));
    }
    
    public void showOverlay(){
        getRootPane().getGlassPane().setVisible(true);
    }
    
    public void hideOverlay(){
        getRootPane().getGlassPane().setVisible(false);
    }
    
    public void setPayed(){
        if(status.getText().equals("LUNAS")){
            buttonAdd.setEnabled(false);
        }
        else{
            buttonAdd.setEnabled(true);
        }
    }
    
    public int getLoanId(){
        return Integer.parseInt(loanId.getText());
    }
    
    public int getCustomerId(){
        return Integer.parseInt(customerId.getText());
    }
    
    public void setLoanData(Object[] data, int nextMonth){
        if(data != null){
            customerId.setText(data[0].toString());
            loanId.setText(data[1].toString());
            name.setText(data[2].toString());
            loan.setText(data[3].toString());
            instalment.setText(data[4].toString());
            month.setText(data[5].toString());
            status.setText(data[6].toString());
            loanDate.setText(data[7].toString());
            
            instalmentFor.setText(String.valueOf(nextMonth));
        }
        else{
            customerId.setText(null);
            loanId.setText(null);
            name.setText(null);
            loan.setText(null);
            instalment.setText(null);
            month.setText(null);
            status.setText(null);
            loanDate.setText(null);
        }
       
    }
    
    public Object[] getInstalmentData(){
        Object[] data = new Object[6];
        data[0] = loanId.getText();
        if(instalmentDate.getDate() != null){
            data[1] = DateModifier.dateToString(instalmentDate.getDate());
        }
        else{
            data[1] = "";
        }
        data[2] = instalmentFor.getText();
        data[3] = overdue.getText().split(" ")[0];
        data[4] = penalty.getText();
        data[5] = payment.getText();        
        return data;
    }
    
    public void setInstalmentData(DefaultTableModel data){
        if(data != null){
            tableInstalment.setModel(data);
            tableInstalment.getColumnModel().getColumn(0).setMaxWidth(80);
                        
        }
        else{
            tableInstalment.setModel(new DefaultTableModel());
        }        
    }
    
    public Object getId(){
        int row = tableInstalment.getSelectedRow();
        if(row == -1){
            return row;
        }
        return tableInstalment.getValueAt(row, 0);
    }
    
    public Object[] getCalculateOverdueData(){
        Object[] data = new Object[4];        
        data[0] = instalmentFor.getText();
        data[1] = loanDate.getText();
        if(instalmentDate.getDate() != null){
            data[2] = DateModifier.dateToString(instalmentDate.getDate());
        }
        else{
            data[2] = "";
        }
        data[3] = instalment.getText();
        return data;
    }

    public void setCalculateOverdueData(Object[] data){
        overdue.setText(data[0].toString()+" Hari");
        penalty.setText(data[1].toString());
        payment.setText(data[2].toString());
    }
    
    public void reset(){
        overdue.setText("-");
        penalty.setText("-");
        payment.setText("-");
    }
    
    public void setListenerInstalmentCount(KeyListener listener){
        instalmentFor.addKeyListener(listener);
    }
    
    public void setListenerSetDate(PropertyChangeListener listener){
        instalmentDate.addPropertyChangeListener(listener);
    }
    
    public void setListenerDelete(ActionListener listener){
        buttonDelete.addActionListener(listener);
    }
    
    public void setListenerAdd(ActionListener listener){
        buttonAdd.addActionListener(listener);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        spTable = new javax.swing.JScrollPane();
        tableInstalment = new javax.swing.JTable();
        buttonAdd = new javax.swing.JButton();
        buttonDelete = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        buttonClose = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        customerId = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        name = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        loan = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        instalment = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        month = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        loanId = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        instalmentDate = new com.toedter.calendar.JDateChooser();
        jLabel18 = new javax.swing.JLabel();
        penalty = new javax.swing.JLabel();
        payment = new javax.swing.JLabel();
        overdue = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        status = new javax.swing.JLabel();
        instalmentFor = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        loanDate = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        tableInstalment.setAutoCreateRowSorter(true);
        tableInstalment.setModel(new javax.swing.table.DefaultTableModel(
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
        tableInstalment.setRowHeight(24);
        tableInstalment.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tableInstalment.setShowVerticalLines(false);
        spTable.setViewportView(tableInstalment);

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

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("Instalment Detail");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(153, 153, 153));
        jLabel3.setText("Manage customer instalment detail");

        buttonClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sketchproject/mbapp/assets/AssetsMenu/ButtonCrossBlue.png"))); // NOI18N
        buttonClose.setBorderPainted(false);
        buttonClose.setContentAreaFilled(false);
        buttonClose.setFocusPainted(false);
        buttonClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCloseActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("ID Pelanggan");

        customerId.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        customerId.setText("34");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Nama");

        name.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        name.setText("Angga Ari Wijaya");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Pinjaman");

        loan.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        loan.setText("2000000");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("Cicilan");

        instalment.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        instalment.setText("350000");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setText("Jumlah Cicilan");

        month.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        month.setText("5 / 24");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel13.setText("ID Pinjaman");

        loanId.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        loanId.setText("34");

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel15.setText("Tanggal Pinjam");

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel16.setText("Tanggal Bayar");

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel17.setText("Denda");

        instalmentDate.setDateFormatString("yyyy-MM-dd");

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel18.setText("Bayar + Denda");

        penalty.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        penalty.setText("2500");

        payment.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        payment.setText("350000");

        overdue.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        overdue.setText("3 Hari");

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel22.setText("Telat");

        jLabel23.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel23.setText("Status");

        status.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        status.setText("BELUM LUNAS");

        instalmentFor.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204), 3));

        jLabel25.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel25.setText("Cicilan Ke");

        loanDate.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        loanDate.setText("24 January 2014");

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(25, 25, 25)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(spTable, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 657, Short.MAX_VALUE)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(jLabel2)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(buttonClose, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(jLabel3)
                        .add(0, 0, Short.MAX_VALUE))
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jLabel23)
                            .add(jLabel5)
                            .add(jLabel1)
                            .add(jLabel11)
                            .add(jLabel9)
                            .add(jLabel7)
                            .add(jLabel25))
                        .add(40, 40, 40)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                                .add(name, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .add(customerId, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 170, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                            .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                                .add(status, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 170, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(instalment, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 170, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                    .add(month, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 170, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                .add(org.jdesktop.layout.GroupLayout.TRAILING, loan, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 170, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                            .add(instalmentFor, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 100, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jPanel1Layout.createSequentialGroup()
                                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(jLabel17)
                                    .add(jLabel18))
                                .add(36, 36, 36)
                                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(payment, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 156, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                    .add(penalty, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 158, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                            .add(jPanel1Layout.createSequentialGroup()
                                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(jLabel13)
                                    .add(jLabel15)
                                    .add(jLabel16)
                                    .add(jLabel22))
                                .add(36, 36, 36)
                                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                                    .add(instalmentDate, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
                                    .add(overdue, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .add(loanId, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .add(loanDate, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                                .add(buttonAdd, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 97, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(buttonDelete, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 94, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .add(2, 2, 2)))))
                .add(25, 25, 25))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(25, 25, 25)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(buttonClose, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel2))
                .add(0, 0, 0)
                .add(jLabel3)
                .add(30, 30, 30)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(1, 1, 1)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jPanel1Layout.createSequentialGroup()
                                .add(107, 107, 107)
                                .add(month)
                                .add(10, 10, 10)
                                .add(status))
                            .add(jPanel1Layout.createSequentialGroup()
                                .add(customerId)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                                .add(name)
                                .add(10, 10, 10)
                                .add(loan)
                                .add(10, 10, 10)
                                .add(instalment)))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(instalmentFor, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE)
                        .add(310, 310, 310))
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                            .add(jPanel1Layout.createSequentialGroup()
                                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                                    .add(jLabel13)
                                    .add(loanId))
                                .add(26, 26, 26))
                            .add(jPanel1Layout.createSequentialGroup()
                                .add(jLabel1)
                                .add(8, 8, 8)
                                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                                    .add(jLabel5)
                                    .add(jLabel15)
                                    .add(loanDate))))
                        .add(10, 10, 10)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jLabel16)
                            .add(jLabel7)
                            .add(instalmentDate, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(10, 10, 10)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jPanel1Layout.createSequentialGroup()
                                .add(jLabel9)
                                .add(10, 10, 10)
                                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(jPanel1Layout.createSequentialGroup()
                                        .add(jLabel11)
                                        .add(10, 10, 10)
                                        .add(jLabel23))
                                    .add(jPanel1Layout.createSequentialGroup()
                                        .add(3, 3, 3)
                                        .add(penalty)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                        .add(payment))
                                    .add(jPanel1Layout.createSequentialGroup()
                                        .add(jLabel17)
                                        .add(10, 10, 10)
                                        .add(jLabel18))))
                            .add(jLabel22)
                            .add(jPanel1Layout.createSequentialGroup()
                                .add(3, 3, 3)
                                .add(overdue)))
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jLabel25, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 25, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .add(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(buttonAdd, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 31, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                    .add(buttonDelete, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 31, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .add(spTable, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 243, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(25, 25, 25))))
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCloseActionPerformed
        dispose();
    }//GEN-LAST:event_buttonCloseActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonAdd;
    private javax.swing.JButton buttonClose;
    private javax.swing.JButton buttonDelete;
    private javax.swing.JLabel customerId;
    private javax.swing.JLabel instalment;
    private com.toedter.calendar.JDateChooser instalmentDate;
    private javax.swing.JTextField instalmentFor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel loan;
    private javax.swing.JLabel loanDate;
    private javax.swing.JLabel loanId;
    private javax.swing.JLabel month;
    private javax.swing.JLabel name;
    private javax.swing.JLabel overdue;
    private javax.swing.JLabel payment;
    private javax.swing.JLabel penalty;
    private javax.swing.JScrollPane spTable;
    private javax.swing.JLabel status;
    private javax.swing.JTable tableInstalment;
    // End of variables declaration//GEN-END:variables

}
