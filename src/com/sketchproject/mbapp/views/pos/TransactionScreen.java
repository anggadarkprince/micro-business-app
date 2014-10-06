/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sketchproject.mbapp.views.pos;

import com.sketchproject.mbapp.utility.BoardTableCellRenderer;
import com.sketchproject.mbapp.utility.DateModifier;
import com.sketchproject.mbapp.utility.Item;
import com.sketchproject.mbapp.utility.ItemRenderer;
import java.awt.Color;
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
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Asus
 */
public class TransactionScreen extends javax.swing.JPanel {

    /**
     * Creates new form TransactionScreen
     */
    public TransactionScreen() {
        initComponents();
        
        groupAcc.add(yes);
        groupAcc.add(no);
        groupType.add(adira);
        groupType.add(cs);
        
        reset();
    }
    
    public void setTransactionData(DefaultTableModel model){
        tableTransaction.setModel(model);
        
    }

    public int getCategoryId(){
        if(comboCategory.getItemCount()<=0){
            return -1;
        }
        else{
            Item item = (Item)comboCategory.getSelectedItem();
            return item.getId();
        }        
    }
    
    public int getProductId(){
        if(comboProduct.getItemCount()<=0){
            return -1;
        }
        else{
            Item item = (Item)comboProduct.getSelectedItem();
            return item.getId();
        }        
    }
    
    public int getCustomerId(){
        if(comboCustomer.getItemCount()<=0){
            return -1;
        }
        else{
            Item item = (Item)comboCustomer.getSelectedItem();
            return item.getId(); 
        }               
    }
    
    public void reset(){ 
        id.setText(null);
        if(comboCategory.getItemCount()>0){
            comboCategory.setSelectedIndex(0);
        }
        stnk.setText("");
        chasis.setText("");
        color.setText("");
        year.setText("");
        contact.setText("");
        policenum.setText("");
        purchasePrice.setText("");
        resalePrice.setText("");
                
        if(comboCustomer.getItemCount()>0){
            comboCustomer.setSelectedIndex(0);  
        }
        address.setText("");
        contact.setText("");
        pid.setText("");
        mother.setText("");
    }
    
    public void setDataCustomer(Object[] data){
        address.setText(data[0].toString());
        contact.setText(data[1].toString());
        pid.setText(data[2].toString());
        mother.setText(data[3].toString());
    }
    
    public void setDataProduct(Object[] data){
        stnk.setText(data[0].toString());
        chasis.setText(data[1].toString());
        color.setText(data[2].toString());
        year.setText(data[3].toString());
        policenum.setText(data[4].toString());
        purchasePrice.setText(data[5].toString());
        resalePrice.setText(data[6].toString());
    }
    
    public void setCategory(Object[][] data){
        Vector model = new Vector();
        if(data.length <= 0){
            comboProduct.setModel(new DefaultComboBoxModel(model));
            comboProduct.setRenderer(new BasicComboBoxRenderer());
        }
        else{
            for (Object[] data1 : data) {
                model.addElement(new Item(Integer.parseInt(data1[0].toString()), data1[1].toString()));
            }                
            comboCategory.setModel(new DefaultComboBoxModel(model));
            comboCategory.setRenderer( new ItemRenderer() );
        }        
    }
    
    public void setProduct(Object[][] data){
        Vector model = new Vector();
        if(data.length <= 0){
            comboProduct.setModel(new DefaultComboBoxModel(model));
            comboProduct.setRenderer(new BasicComboBoxRenderer());
        }
        else{
            for (Object[] data1 : data) {
                model.addElement(new Item(Integer.parseInt(data1[0].toString()), data1[1].toString()));
            }                
            comboProduct.setModel(new DefaultComboBoxModel(model));
            comboProduct.setRenderer( new ItemRenderer() );
        }        
    }
    
    public void setCustomer(Object[][] data){
        Vector model = new Vector();
        if(data.length <= 0){
            comboCustomer.setModel(new DefaultComboBoxModel(model));
            comboCustomer.setRenderer(new BasicComboBoxRenderer());
        }
        else{
            for (Object[] data1 : data) {
                model.addElement(new Item(Integer.parseInt(data1[0].toString()), data1[1].toString()));
            }                
            comboCustomer.setModel(new DefaultComboBoxModel(model));
            comboCustomer.setRenderer( new ItemRenderer() );
        }
    }
    
    public Object getId(){
        int row = tableTransaction.getSelectedRow();
        if(row == -1){
            return -1;
        }
        return tableTransaction.getValueAt(row, 0);
    }
    
    public Object[] checkTransaction(){
        Object[] data = new Object[4];
        data[0] = stnk.getText();
        data[1] = address.getText();
        data[2] = id.getText();
        if(date.getDate() == null){
            data[3] = "";
        }
        else{
            data[3] = DateModifier.dateToString(date.getDate());
        }
        return data;
    }
    
    public Object[] getTransactionData(){
        Object[] data= new Object[6];
        
        Item item;
        
        item = (Item)comboProduct.getSelectedItem();
        data[0] = item.getId(); 
        
        item = (Item)comboCustomer.getSelectedItem();
        data[1] = item.getId();
              
        if(yes.isSelected()){
            data[2] = "YES";
        }
        else{
            data[2] = "NO";
        }
        
        if(adira.isSelected()){
            data[3] = "ADIRA";
        }
        else{
            data[3] = "CS";
        }
        
        data[4] = id.getText();
        
        if(date.getDate() == null){
            data[5] = "";
        }
        else{
            data[5] = DateModifier.dateToString(date.getDate());
        }
        
        return data;
    }
    
    public String getKeyword(){
        return keyword.getText();
    }
    
    public void setListenerCategory(ActionListener listener){
        comboCategory.addActionListener(listener);
    }
    
    public void setListenerProduct(ActionListener listener){
        comboProduct.addActionListener(listener);
    }
    
    public void setListenerCustomer(ActionListener listener){
        comboCustomer.addActionListener(listener);
    }
    
    public void setListenerSave(ActionListener listener){
        buttonSave.addActionListener(listener);
    }
    
    public void setListenerReset(ActionListener listener){
        buttonReset.addActionListener(listener);
    }
    
    public void setListenerDelete(ActionListener listener){
        buttonDelete.addActionListener(listener);
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

        groupType = new javax.swing.ButtonGroup();
        groupAcc = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        keyword = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        spTable = new javax.swing.JScrollPane();
        tableTransaction = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        policenum = new javax.swing.JLabel();
        purchasePrice = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        comboCustomer = new javax.swing.JComboBox();
        jLabel10 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        yes = new javax.swing.JRadioButton();
        no = new javax.swing.JRadioButton();
        jLabel26 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        buttonSave = new javax.swing.JButton();
        comboCategory = new javax.swing.JComboBox();
        adira = new javax.swing.JRadioButton();
        comboProduct = new javax.swing.JComboBox();
        cs = new javax.swing.JRadioButton();
        jLabel3 = new javax.swing.JLabel();
        stnk = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        chasis = new javax.swing.JLabel();
        color = new javax.swing.JLabel();
        resalePrice = new javax.swing.JLabel();
        year = new javax.swing.JLabel();
        address = new javax.swing.JLabel();
        contact = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        pid = new javax.swing.JLabel();
        mother = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        id = new javax.swing.JTextField();
        buttonDelete = new javax.swing.JButton();
        buttonReset = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        date = new com.toedter.calendar.JDateChooser();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(214, 214, 214));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(83, 84, 87));
        jLabel1.setText("Transaction");

        jLabel2.setForeground(new java.awt.Color(83, 84, 87));
        jLabel2.setText("Sell and Buy Transaction Data");

        keyword.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        keyword.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(221, 221, 221), 2));

        jLabel15.setText("Cari");

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
                .addComponent(jLabel15)
                .addGap(18, 18, 18)
                .addComponent(keyword, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(0, 0, 0)
                .addComponent(jLabel2)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(keyword, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        tableTransaction.setAutoCreateRowSorter(true);
        tableTransaction.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tableTransaction.setGridColor(new java.awt.Color(204, 204, 204));
        tableTransaction.setRowHeight(24);
        tableTransaction.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tableTransaction.setShowVerticalLines(false);
        spTable.setViewportView(tableTransaction);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(spTable, javax.swing.GroupLayout.DEFAULT_SIZE, 607, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(spTable)
                .addGap(25, 25, 25))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        policenum.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        policenum.setText("-");

        purchasePrice.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        purchasePrice.setText("-");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setText("No Rangka");

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel19.setText("Customer");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("No STNK");

        comboCustomer.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(221, 221, 221), 2));

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel10.setText("Harga Beli");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel9.setText("Alamat");

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel21.setText("Harga Jual");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel8.setText("Tahun");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel7.setText("Warna");

        yes.setSelected(true);
        yes.setText("YA");
        yes.setOpaque(false);

        no.setText("TIDAK");
        no.setOpaque(false);

        jLabel26.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel26.setText("No Polisi");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel11.setText("ACC");

        jLabel27.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel27.setText("Telepon");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel12.setText("Type");

        buttonSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sketchproject/mbapp/assets/AssetsCreditApp/ButtonSave.png"))); // NOI18N
        buttonSave.setBorderPainted(false);
        buttonSave.setContentAreaFilled(false);
        buttonSave.setFocusPainted(false);
        buttonSave.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sketchproject/mbapp/assets/AssetsCreditApp/ButtonSaveHover.png"))); // NOI18N

        comboCategory.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(221, 221, 221), 2));

        adira.setSelected(true);
        adira.setText("ADIRA");
        adira.setOpaque(false);

        comboProduct.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(221, 221, 221), 2));

        cs.setText("CS");
        cs.setOpaque(false);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Merk");

        stnk.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        stnk.setText("-");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Motor");

        chasis.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        chasis.setText("-");

        color.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        color.setText("-");

        resalePrice.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        resalePrice.setText("-");

        year.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        year.setText("-");

        address.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        address.setText("-");

        contact.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        contact.setText("-");

        jLabel30.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel30.setText("No KTP");

        jLabel31.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel31.setText("Nama Ibu");

        pid.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        pid.setText("-");

        mother.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        mother.setText("-");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel13.setText("No PK");

        id.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(221, 221, 221), 2));

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

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel14.setText("Tanggal");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(46, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel4)
                                                .addComponent(jLabel3))
                                            .addGap(1, 1, 1))
                                        .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel26, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING))
                                    .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel27, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel30, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel31, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(adira)
                                    .addComponent(yes))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(no)
                                    .addComponent(cs)))
                            .addComponent(comboProduct, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(comboCategory, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(comboCustomer, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(address, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(contact, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(pid, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(mother, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(resalePrice, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(purchasePrice, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(policenum, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(year, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(color, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(stnk, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(chasis, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(id, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
                            .addComponent(date, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(buttonReset, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(buttonSave, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(buttonDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(id, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(stnk))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(chasis))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(color)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(year))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(policenum)
                    .addComponent(jLabel26))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(purchasePrice))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(resalePrice))
                .addGap(15, 15, 15)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(address))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(contact))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(pid))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31)
                    .addComponent(mother))
                .addGap(6, 6, 6)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(adira, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(cs, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(yes, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(no, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(jLabel11))
                            .addComponent(jLabel12))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(buttonDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(buttonSave, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonReset, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel address;
    private javax.swing.JRadioButton adira;
    private javax.swing.JButton buttonDelete;
    private javax.swing.JButton buttonReset;
    private javax.swing.JButton buttonSave;
    private javax.swing.JLabel chasis;
    private javax.swing.JLabel color;
    private javax.swing.JComboBox comboCategory;
    private javax.swing.JComboBox comboCustomer;
    private javax.swing.JComboBox comboProduct;
    private javax.swing.JLabel contact;
    private javax.swing.JRadioButton cs;
    private com.toedter.calendar.JDateChooser date;
    private javax.swing.ButtonGroup groupAcc;
    private javax.swing.ButtonGroup groupType;
    private javax.swing.JTextField id;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField keyword;
    private javax.swing.JLabel mother;
    private javax.swing.JRadioButton no;
    private javax.swing.JLabel pid;
    private javax.swing.JLabel policenum;
    private javax.swing.JLabel purchasePrice;
    private javax.swing.JLabel resalePrice;
    private javax.swing.JScrollPane spTable;
    private javax.swing.JLabel stnk;
    private javax.swing.JTable tableTransaction;
    private javax.swing.JLabel year;
    private javax.swing.JRadioButton yes;
    // End of variables declaration//GEN-END:variables
}
