/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sketchproject.mbapp.views.credit;

import com.sketchproject.mbapp.models.AppModel;
import com.sketchproject.mbapp.utility.DateModifier;
import com.sketchproject.mbapp.utility.Item;
import com.sketchproject.mbapp.utility.ItemRenderer;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.plaf.basic.BasicComboBoxRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Asus
 */
public class LoanScreen extends javax.swing.JPanel {

    /** Creates new form LoanScreen */
    public LoanScreen() {
        initComponents();
        
        
        
        
        reset();
        normalState();
    }
    
    public String getIdCustomer(){
        return idCustomer.getText();
    }
    
    public Object[] getDataLoan(){
        Object[] data = new Object[7];
        if(name.getText().trim().isEmpty()){
            data[0] = "";
        }
        else{
            data[0] = idCustomer.getText();
        }
        
        if(comboLoanMonth.getItemCount()<=0){
            data[1] = "";
        }
        else{
            Item item = (Item)comboLoanMonth.getSelectedItem();            
            data[1] = item.getId();
        }
        
        if(date.getDate() == null){
            data[2] = "";
        }
        else{
            data[2] = DateModifier.dateToString(date.getDate());
        }
        
        data[3] = fine.getText();
        data[4] = policenum.getText();
        data[5] = year.getText();
        data[6] = merk.getText();
        
        return data;
    }
    
    public int getLoanTypeId(){
        if(comboLoan.getItemCount()<=0){
            return -1;
        }
        else{
            Item item = (Item)comboLoan.getSelectedItem();
            return Integer.parseInt(item.getDescription());
        }        
    }
    
    public int getLoanMonthId(){
        if(comboLoanMonth.getItemCount()<=0){
            return -1;
        }
        else{
            Item item = (Item)comboLoanMonth.getSelectedItem();
            return Integer.parseInt(item.getDescription());
        }        
    }
    
    public void setTableData(DefaultTableModel model){
        tableLoan.setModel(model);
        tableLoan.getColumnModel().getColumn(0).setMaxWidth(100);
    }
    
    public void normalState(){
        buttonUpdate.setEnabled(false);
        
        buttonAdd.setEnabled(true);
        buttonDelete.setEnabled(true);
        buttonEdit.setEnabled(true);   
        
        tableLoan.setEnabled(true);
    }
    
    public void editState(){
        buttonAdd.setEnabled(false);
        buttonDelete.setEnabled(false);
        buttonEdit.setEnabled(false);  
        
        buttonUpdate.setEnabled(true);
        
        tableLoan.setEnabled(false);
    }
    
    public void setCustomerData(Object[] data){
        name.setText(data[0].toString());
        address.setText(data[1].toString());
        contact.setText(data[2].toString());
        pid.setText(data[3].toString());
        job.setText(data[4].toString());
    }
    
    public void setComboLoan(Object[][] data){
        Vector model = new Vector();
        if(data.length <= 0){
            comboLoan.setModel(new DefaultComboBoxModel(model));
            comboLoan.setRenderer(new BasicComboBoxRenderer());
        }
        else{
            for (Object[] data1 : data) {
                model.addElement(new Item(Integer.parseInt(data1[0].toString()), data1[1].toString()));
            }                
            comboLoan.setModel(new DefaultComboBoxModel(model));
            comboLoan.setRenderer( new ItemRenderer() );
        }
    }
    
    public void setComboLoanMonth(Object[][] data){
        Vector model = new Vector();
        if(data.length <= 0){
            comboLoanMonth.setModel(new DefaultComboBoxModel(model));
            comboLoanMonth.setRenderer(new BasicComboBoxRenderer());
        }
        else{
            for (Object[] data1 : data) {
                model.addElement(new Item(Integer.parseInt(data1[0].toString()), data1[1].toString()));
            }                
            comboLoanMonth.setModel(new DefaultComboBoxModel(model));
            comboLoanMonth.setRenderer( new ItemRenderer() );
        }
    }
    
    public void setValueInstalment(String value){
        instalmentPerMonth.setText(value);
    }
    
    public Object getId(){
        int row = tableLoan.getSelectedRow();
        if(row == -1){
            return -1;
        }
        return tableLoan.getValueAt(row, 0);
    }
    
    public Object getIdCustomerEdit(){
        int row = tableLoan.getSelectedRow();
        if(row == -1){
            return -1;
        }
        return tableLoan.getValueAt(row, 1);
    }
    
    
    public void setEdit(Object[] data, Object[] loan){
        name.setText(data[0].toString());
        address.setText(data[1].toString());
        contact.setText(data[2].toString());
        pid.setText(data[3].toString());
        job.setText(data[4].toString());        
        idCustomer.setText(data[5].toString());
        
        fine.setText(loan[2].toString());
        policenum.setText(loan[3].toString());
        year.setText(loan[4].toString());
        merk.setText(loan[5].toString());
        date.setDate(DateModifier.stringToDate(loan[1].toString()));
    }
    
    public void reset(){
        idLoanTransaction.setText(AppModel.getNextId("cr_loan_creditor"));
        name.setText("");
        address.setText("");
        contact.setText("");
        pid.setText("");
        job.setText("");
        fine.setText(null);
        policenum.setText(null);
        year.setText(null);
        merk.setText(null);
        idCustomer.setText(null);
        date.setDate(DateModifier.stringToDate(DateModifier.getToday()));        
    }
    
    public void setListenerTextIdCustomer(KeyListener listener){
        idCustomer.addKeyListener(listener);
    }
    
    public void setListenerComboLoanType(ActionListener listener){
        comboLoan.addActionListener(listener);
    }
    
    public void setListenerComboLoanMonth(ActionListener listener){
        comboLoanMonth.addActionListener(listener);
    }
    
    public void setListenerAddLoanType(ActionListener listener){
        buttonAddLoanType.addActionListener(listener);
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
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        idCustomer = new javax.swing.JTextField();
        name = new javax.swing.JLabel();
        address = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        contact = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        pid = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        job = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        fine = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        policenum = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        year = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        merk = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        comboLoan = new javax.swing.JComboBox();
        jLabel15 = new javax.swing.JLabel();
        comboLoanMonth = new javax.swing.JComboBox();
        jLabel16 = new javax.swing.JLabel();
        instalmentPerMonth = new javax.swing.JLabel();
        buttonAddLoanType = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        idLoanTransaction = new javax.swing.JLabel();
        date = new com.toedter.calendar.JDateChooser();
        jLabel20 = new javax.swing.JLabel();
        buttonAdd = new javax.swing.JButton();
        buttonEdit = new javax.swing.JButton();
        buttonUpdate = new javax.swing.JButton();
        buttonDelete = new javax.swing.JButton();
        buttonReset = new javax.swing.JButton();
        spTable = new javax.swing.JScrollPane();
        tableLoan = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 102, 102));
        jLabel2.setText("Managing Loan Data");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 102));
        jLabel1.setText("Pinjaman");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new java.awt.GridLayout(1, 2));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setText("Alamat");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("Nama ");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("ID Customer");

        idCustomer.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        idCustomer.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(220, 220, 220), 3));

        name.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        name.setText("-");

        address.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        address.setText("-");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel7.setText("No Telephone");

        contact.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        contact.setText("-");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel8.setText("No KTP");

        pid.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        pid.setText("-");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel9.setText("Pekerjaan");

        job.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        job.setText("-");

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel21.setText("Jaminan");

        fine.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(220, 220, 220), 3));

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel22.setText("No Polisi");

        policenum.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(220, 220, 220), 3));

        jLabel23.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel23.setText("Tahun");

        year.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(220, 220, 220), 3));

        jLabel24.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel24.setText("Merk");

        merk.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(220, 220, 220), 3));

        org.jdesktop.layout.GroupLayout jPanel6Layout = new org.jdesktop.layout.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(idCustomer)
                    .add(jPanel6Layout.createSequentialGroup()
                        .add(jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jLabel6)
                            .add(jLabel5)
                            .add(jLabel7)
                            .add(jLabel8)
                            .add(jLabel9))
                        .add(18, 18, 18)
                        .add(jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(name, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(address, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(contact, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(pid, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(job, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE)))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel6Layout.createSequentialGroup()
                        .add(jLabel21)
                        .add(28, 28, 28)
                        .add(fine))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel6Layout.createSequentialGroup()
                        .add(jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jLabel22)
                            .add(jLabel23))
                        .add(28, 28, 28)
                        .add(jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(policenum)
                            .add(year)))
                    .add(jPanel6Layout.createSequentialGroup()
                        .add(jLabel3)
                        .add(0, 0, Short.MAX_VALUE))
                    .add(jPanel6Layout.createSequentialGroup()
                        .add(jLabel24)
                        .add(46, 46, 46)
                        .add(merk)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .add(jLabel3)
                .add(0, 0, 0)
                .add(idCustomer, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 26, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel5)
                    .add(name))
                .add(0, 0, 0)
                .add(jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel6)
                    .add(address))
                .add(0, 0, 0)
                .add(jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel7)
                    .add(contact))
                .add(0, 0, 0)
                .add(jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel8)
                    .add(pid))
                .add(0, 0, 0)
                .add(jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel9)
                    .add(job))
                .add(0, 0, 0)
                .add(jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(fine, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 28, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel21))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(policenum, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 28, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel22))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(year, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 28, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel23))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel24)
                    .add(merk, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 28, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        jPanel4.add(jPanel6);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Pinjaman");

        comboLoan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboLoan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(220, 220, 220), 3));

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel15.setText("Lama Angsuran");

        comboLoanMonth.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboLoanMonth.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(220, 220, 220), 3));

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel16.setText("Jumlah Cicilan");

        instalmentPerMonth.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        instalmentPerMonth.setText("Rp. 350.000");

        buttonAddLoanType.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sketchproject/mbapp/assets/AssetsCreditApp/ButtonPlus.png"))); // NOI18N
        buttonAddLoanType.setBorderPainted(false);
        buttonAddLoanType.setContentAreaFilled(false);
        buttonAddLoanType.setFocusPainted(false);
        buttonAddLoanType.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sketchproject/mbapp/assets/AssetsCreditApp/ButtonPlusHover.png"))); // NOI18N
        buttonAddLoanType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAddLoanTypeActionPerformed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel18.setText("ID Transaksi");

        idLoanTransaction.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        idLoanTransaction.setText("45");

        date.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 240, 240), 3));

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel20.setText("Tanggal Peminjaman");

        org.jdesktop.layout.GroupLayout jPanel5Layout = new org.jdesktop.layout.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel5Layout.createSequentialGroup()
                .add(24, 24, 24)
                .add(jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel5Layout.createSequentialGroup()
                        .add(jLabel15)
                        .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .add(jPanel5Layout.createSequentialGroup()
                        .add(jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jPanel5Layout.createSequentialGroup()
                                .add(instalmentPerMonth)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .add(buttonAddLoanType, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 32, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                            .add(jPanel5Layout.createSequentialGroup()
                                .add(jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(idLoanTransaction)
                                    .add(jLabel18))
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .add(jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel5Layout.createSequentialGroup()
                                        .add(jLabel20)
                                        .add(36, 36, 36))
                                    .add(date, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 148, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                            .add(comboLoan, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, comboLoanMonth, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(jPanel5Layout.createSequentialGroup()
                                .add(jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(jLabel16)
                                    .add(jLabel4))
                                .add(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel18)
                    .add(jLabel20))
                .add(5, 5, 5)
                .add(jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(date, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(idLoanTransaction, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jLabel4)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(comboLoan, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 23, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(19, 19, 19)
                .add(jLabel15)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(comboLoanMonth, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(9, 9, 9)
                .add(jLabel16)
                .add(5, 5, 5)
                .add(jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(instalmentPerMonth)
                    .add(buttonAddLoanType, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 33, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(52, Short.MAX_VALUE))
        );

        jPanel4.add(jPanel5);

        buttonAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sketchproject/mbapp/assets/AssetsCreditApp/ButtonInsert.png"))); // NOI18N
        buttonAdd.setBorderPainted(false);
        buttonAdd.setContentAreaFilled(false);
        buttonAdd.setFocusPainted(false);
        buttonAdd.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sketchproject/mbapp/assets/AssetsCreditApp/ButtonInsertHover.png"))); // NOI18N

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

        buttonDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sketchproject/mbapp/assets/AssetsCreditApp/ButtonDelete.png"))); // NOI18N
        buttonDelete.setBorderPainted(false);
        buttonDelete.setContentAreaFilled(false);
        buttonDelete.setFocusPainted(false);
        buttonDelete.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sketchproject/mbapp/assets/AssetsCreditApp/ButtonDeleteHover.png"))); // NOI18N

        buttonReset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sketchproject/mbapp/assets/AssetsCreditApp/ButtonReset.png"))); // NOI18N
        buttonReset.setBorderPainted(false);
        buttonReset.setContentAreaFilled(false);
        buttonReset.setFocusPainted(false);
        buttonReset.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sketchproject/mbapp/assets/AssetsCreditApp/ButtonResetHover.png"))); // NOI18N

        org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(buttonAdd, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 118, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(buttonEdit, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 102, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(buttonUpdate, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 118, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(buttonDelete, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 113, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(buttonReset, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 115, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .add(jPanel4, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel2Layout.createSequentialGroup()
                .add(jPanel4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(buttonDelete, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(buttonUpdate, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(buttonEdit, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(buttonAdd, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, buttonReset)))
        );

        spTable.setBackground(new java.awt.Color(255, 255, 255));
        spTable.setBorder(null);

        tableLoan.setAutoCreateRowSorter(true);
        tableLoan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tableLoan.setGridColor(new java.awt.Color(220, 220, 220));
        tableLoan.setRowHeight(24);
        tableLoan.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tableLoan.setShowVerticalLines(false);
        spTable.setViewportView(tableLoan);

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(spTable)
                .addContainerGap())
            .add(jPanel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(spTable, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
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
                .addContainerGap(865, Short.MAX_VALUE))
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

    private void buttonAddLoanTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAddLoanTypeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonAddLoanTypeActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel address;
    private javax.swing.JButton buttonAdd;
    private javax.swing.JButton buttonAddLoanType;
    private javax.swing.JButton buttonDelete;
    private javax.swing.JButton buttonEdit;
    private javax.swing.JButton buttonReset;
    private javax.swing.JButton buttonUpdate;
    private javax.swing.JComboBox comboLoan;
    private javax.swing.JComboBox comboLoanMonth;
    private javax.swing.JLabel contact;
    private com.toedter.calendar.JDateChooser date;
    private javax.swing.JTextField fine;
    private javax.swing.JTextField idCustomer;
    private javax.swing.JLabel idLoanTransaction;
    private javax.swing.JLabel instalmentPerMonth;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JLabel job;
    private javax.swing.JTextField merk;
    private javax.swing.JLabel name;
    private javax.swing.JLabel pid;
    private javax.swing.JTextField policenum;
    private javax.swing.JScrollPane spTable;
    private javax.swing.JTable tableLoan;
    private javax.swing.JTextField year;
    // End of variables declaration//GEN-END:variables

}
