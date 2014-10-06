/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sketchproject.mbapp.views.pos;

import com.sketchproject.mbapp.utility.DateModifier;
import com.sketchproject.mbapp.utility.ItemRenderer;
import com.sketchproject.mbapp.utility.Item;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
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
public class InventoryScreen extends javax.swing.JPanel {

    /**
     * Creates new form MasterScreen
     */
    public InventoryScreen() {
        initComponents();
        
        normalState();
    }

    public void setProduct(Object[][] data){
        Vector model = new Vector();
        if(data.length <= 0){
            comboMerk.setModel(new DefaultComboBoxModel(model));
            comboMerk.setRenderer(new BasicComboBoxRenderer());
        }
        else{
            for (Object[] data1 : data) {
                model.addElement(new Item(Integer.parseInt(data1[0].toString()), data1[1].toString()));
            }                
            comboMerk.setModel(new DefaultComboBoxModel(model));
            comboMerk.setRenderer( new ItemRenderer() );
        }
    }
    
    public void normalState(){
        buttonUpdate.setEnabled(false);
        
        buttonAdd.setEnabled(true);
        buttonDelete.setEnabled(true);
        buttonEdit.setEnabled(true);   
        
        tableInventory.setEnabled(true);
    }
    
    public void editState(){
        buttonAdd.setEnabled(false);
        buttonDelete.setEnabled(false);
        buttonEdit.setEnabled(false);  
        
        buttonUpdate.setEnabled(true);
        
        tableInventory.setEnabled(false);
    }
    
    public void reset(){
        comboMerk.setSelectedIndex(0);
        product.setText(null);
        stnk.setText(null);
        type.setText(null);
        chasis.setText(null);
        color.setText(null);
        year.setYear(Integer.parseInt(DateModifier.getYear()));
        policeNumber.setText(null);
        purchasePrice.setText(null);
        resalePrice.setText(null);
    }
    
    public Object[] getDataInventory(){
        Object[] data = new Object[10];
        if(comboMerk.getItemCount()<=0){
            data[0] = "";
        }
        else{
            Item item = (Item)comboMerk.getSelectedItem();            
            data[0] = item.getId();
        }
        data[1] = type.getText();
        data[2] = product.getText();
        data[3] = stnk.getText();
        data[4] = chasis.getText();
        data[5] = color.getText();
        data[6] = year.getYear();
        data[7] = policeNumber.getText();
        data[8] = purchasePrice.getText();
        data[9] = resalePrice.getText();        
        
        return data;
    }
    
    public void setTableData(DefaultTableModel model){
        tableInventory.setModel(model);
        
    }
    
    public Object getId(){
        int row = tableInventory.getSelectedRow();
        if(row == -1){
            return -1;
        }
        return tableInventory.getValueAt(row, 0);
    }
    
    public void setEdit(){
        int row = tableInventory.getSelectedRow();
        
        Item item = new Item(2, tableInventory.getValueAt(row, 0).toString()); 
        comboMerk.setSelectedItem(item);
        
        product.setText(tableInventory.getValueAt(row, 2).toString());
        type.setText(tableInventory.getValueAt(row, 3).toString());
        stnk.setText(tableInventory.getValueAt(row, 4).toString());
        chasis.setText(tableInventory.getValueAt(row, 5).toString());
        color.setText(tableInventory.getValueAt(row, 6).toString());
        year.setYear(Integer.parseInt(tableInventory.getValueAt(row, 7).toString()));
        policeNumber.setText(tableInventory.getValueAt(row, 8).toString());
        purchasePrice.setText(tableInventory.getValueAt(row, 9).toString());
        resalePrice.setText(tableInventory.getValueAt(row, 10).toString());
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
    
    public void setListenerAddMerk(ActionListener listener){
        buttonAddMerk.addActionListener(listener);
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
        spTable = new javax.swing.JScrollPane();
        tableInventory = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        comboMerk = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        product = new javax.swing.JTextField();
        stnk = new javax.swing.JTextField();
        chasis = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        purchasePrice = new javax.swing.JTextField();
        resalePrice = new javax.swing.JTextField();
        color = new javax.swing.JTextField();
        year = new com.toedter.calendar.JYearChooser();
        buttonAddMerk = new javax.swing.JButton();
        policeNumber = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        buttonUpdate = new javax.swing.JButton();
        buttonEdit = new javax.swing.JButton();
        buttonDelete = new javax.swing.JButton();
        buttonAdd = new javax.swing.JButton();
        buttonReset = new javax.swing.JButton();
        type = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(214, 214, 214));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(83, 84, 87));
        jLabel1.setText("Inventory");

        jLabel2.setForeground(new java.awt.Color(83, 84, 87));
        jLabel2.setText("Managing Material and Stock");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        tableInventory.setAutoCreateRowSorter(true);
        tableInventory.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tableInventory.setGridColor(new java.awt.Color(204, 204, 204));
        tableInventory.setRowHeight(24);
        tableInventory.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tableInventory.setShowVerticalLines(false);
        spTable.setViewportView(tableInventory);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        comboMerk.setOpaque(false);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Merk");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Jenis");

        product.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(221, 221, 221), 2));

        stnk.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(221, 221, 221), 2));

        chasis.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(221, 221, 221), 2));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setText("No Rangka");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("No STNK");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel10.setText("Harga Jual");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel9.setText("Harga Beli");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel8.setText("Tahun");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel7.setText("Warna");

        purchasePrice.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(221, 221, 221), 2));

        resalePrice.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(221, 221, 221), 2));

        color.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(221, 221, 221), 2));

        year.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(221, 221, 221), 2));
        year.setOpaque(false);

        buttonAddMerk.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sketchproject/mbapp/assets/AssetsCreditApp/ButtonPlus.png"))); // NOI18N
        buttonAddMerk.setBorderPainted(false);
        buttonAddMerk.setContentAreaFilled(false);
        buttonAddMerk.setFocusPainted(false);
        buttonAddMerk.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sketchproject/mbapp/assets/AssetsCreditApp/ButtonPlusHover.png"))); // NOI18N

        policeNumber.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(221, 221, 221), 2));

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel11.setText("No Polisi");

        buttonUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sketchproject/mbapp/assets/AssetsCreditApp/ButtonUpdate.png"))); // NOI18N
        buttonUpdate.setBorderPainted(false);
        buttonUpdate.setContentAreaFilled(false);
        buttonUpdate.setFocusPainted(false);
        buttonUpdate.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sketchproject/mbapp/assets/AssetsCreditApp/ButtonUpdateHover.png"))); // NOI18N

        buttonEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sketchproject/mbapp/assets/AssetsCreditApp/ButtonEdit.png"))); // NOI18N
        buttonEdit.setBorderPainted(false);
        buttonEdit.setContentAreaFilled(false);
        buttonEdit.setFocusPainted(false);
        buttonEdit.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sketchproject/mbapp/assets/AssetsCreditApp/ButtonEditHover.png"))); // NOI18N

        buttonDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sketchproject/mbapp/assets/AssetsCreditApp/ButtonDelete.png"))); // NOI18N
        buttonDelete.setBorderPainted(false);
        buttonDelete.setContentAreaFilled(false);
        buttonDelete.setFocusPainted(false);
        buttonDelete.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sketchproject/mbapp/assets/AssetsCreditApp/ButtonDeleteHover.png"))); // NOI18N

        buttonAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sketchproject/mbapp/assets/AssetsCreditApp/ButtonInsert.png"))); // NOI18N
        buttonAdd.setBorderPainted(false);
        buttonAdd.setContentAreaFilled(false);
        buttonAdd.setFocusPainted(false);
        buttonAdd.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sketchproject/mbapp/assets/AssetsCreditApp/ButtonInsertHover.png"))); // NOI18N

        buttonReset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sketchproject/mbapp/assets/AssetsCreditApp/ButtonReset.png"))); // NOI18N
        buttonReset.setBorderPainted(false);
        buttonReset.setContentAreaFilled(false);
        buttonReset.setFocusPainted(false);
        buttonReset.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sketchproject/mbapp/assets/AssetsCreditApp/ButtonResetHover.png"))); // NOI18N

        type.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(221, 221, 221), 2));

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel12.setText("Type");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(buttonAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonReset, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(18, 18, 18)
                                .addComponent(chasis, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(9, 9, 9)
                                        .addComponent(jLabel5))
                                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(comboMerk, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(buttonAddMerk, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(product, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(stnk, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addGap(18, 18, 18)
                                .addComponent(type, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(91, 91, 91)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel7)))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel9)
                                .addComponent(jLabel10))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addComponent(jLabel11)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(purchasePrice, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(policeNumber, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(year, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)
                            .addComponent(color, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(resalePrice))))
                .addContainerGap(123, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(comboMerk, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(buttonAddMerk, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(13, 13, 13)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(product, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(type, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12))
                        .addGap(11, 11, 11)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(stnk, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(chasis, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel8)
                                .addGap(20, 20, 20)
                                .addComponent(jLabel11)
                                .addGap(17, 17, 17)
                                .addComponent(jLabel9)
                                .addGap(19, 19, 19)
                                .addComponent(jLabel10))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(color, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(year, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(policeNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(4, 4, 4)
                                .addComponent(purchasePrice, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(resalePrice, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(28, 28, 28)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(buttonDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(buttonEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(buttonAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(buttonUpdate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(buttonReset, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(28, 28, 28))
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
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spTable, javax.swing.GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonAdd;
    private javax.swing.JButton buttonAddMerk;
    private javax.swing.JButton buttonDelete;
    private javax.swing.JButton buttonEdit;
    private javax.swing.JButton buttonReset;
    private javax.swing.JButton buttonUpdate;
    private javax.swing.JTextField chasis;
    private javax.swing.JTextField color;
    private javax.swing.JComboBox comboMerk;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField policeNumber;
    private javax.swing.JTextField product;
    private javax.swing.JTextField purchasePrice;
    private javax.swing.JTextField resalePrice;
    private javax.swing.JScrollPane spTable;
    private javax.swing.JTextField stnk;
    private javax.swing.JTable tableInventory;
    private javax.swing.JTextField type;
    private com.toedter.calendar.JYearChooser year;
    // End of variables declaration//GEN-END:variables
}
