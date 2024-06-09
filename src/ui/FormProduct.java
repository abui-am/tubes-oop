package ui;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import core.models.requests.ProductRequest;
import core.models.responses.BaseResponse;
import core.models.responses.ProductListResponse;
import helpers.HttpHelper;
import helpers.JdbcHelper;
import helpers.MapperHelper;
import helpers.MessageHelper;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import ui.action.TableActionCellEditor;
import ui.action.TableActionCellRender;
import ui.action.TableActionEvent;
import ui.products.ProductEdit;

public class FormProduct extends javax.swing.JFrame {
    
    private List<ProductListResponse> products;
    
    public FormProduct() {
        initComponents();
        getProducts();
    }
    
    public void getProducts() {
        tableProduct.getColumnModel().getColumn(3).setCellRenderer(new TableActionCellRender());
        tableProduct.getColumnModel().getColumn(3).setCellEditor(new TableActionCellEditor(getEvent()));
        
        DefaultTableModel model = (DefaultTableModel) tableProduct.getModel();
        model.setRowCount(0);
        
        try {
            String token = JdbcHelper.getToken();
            String response = HttpHelper.get("products", token);
            
            ObjectMapper mapper = MapperHelper.getMapper();
            BaseResponse<List<ProductListResponse>> br = mapper.readValue(response, new TypeReference<BaseResponse<List<ProductListResponse>>>() {});
            
            List<ProductListResponse> products = br.getData();
            this.products = products;
            
            for (ProductListResponse product : products) {
                String data[] = {product.getName(), String.valueOf(product.getStock()), String.valueOf(product.getPrice()) };
                model.addRow(data);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(FormUser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FormUser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(FormUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tableProduct = new javax.swing.JTable();
        tfNama = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        tfStock = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        tfHarga = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        btnAddProduct = new javax.swing.JButton();
        backButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tableProduct.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nama", "Stok", "Harga", "Aksi"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableProduct.setRowHeight(50);
        jScrollPane1.setViewportView(tableProduct);
        if (tableProduct.getColumnModel().getColumnCount() > 0) {
            tableProduct.getColumnModel().getColumn(2).setResizable(false);
            tableProduct.getColumnModel().getColumn(3).setResizable(false);
        }

        jLabel1.setText("Nama");

        jLabel2.setText("Stok");

        jLabel3.setText("Harga");

        btnAddProduct.setText("TAMBAH PRODUCT");
        btnAddProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddProductActionPerformed(evt);
            }
        });

        backButton.setText("KEMBALI");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 635, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel1)
                        .addComponent(tfNama, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel2)
                        .addComponent(tfStock, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel3)
                        .addComponent(tfHarga, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnAddProduct, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(backButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfNama, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfStock, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfHarga, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnAddProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 383, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 9, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private TableActionEvent getEvent() {
        TableActionEvent event = new TableActionEvent() {
            @Override
            public void onEdit(int row) {
                int id = products.get(row).getId();
                String name = products.get(row).getName();
                String price = String.valueOf(products.get(row).getPrice());
                String stock = String.valueOf(products.get(row).getStock());
                
                JDialog dialog = new JDialog();
                    
                ProductEdit panel = new ProductEdit(id, FormProduct.this, dialog);
                panel.tfName.setText(name);
                panel.tfHarga.setText(price);
                panel.tfStok.setText(stock);
                                
                dialog.add(panel);
                dialog.setSize(400, 300);
                dialog.setVisible(true);
            }

            @Override
            public void onDelete(int row) {
                int answer = MessageHelper.Confirm("Confirm", "Apakah yakin ingin menghapus data tersebut?");
                
                if (answer == JOptionPane.YES_OPTION) {
                    int id = products.get(row).getId();
                    
                    try {
                        String token = JdbcHelper.getToken();
                        String response = HttpHelper.delete("products/" + id, token);
                        ObjectMapper mapper = MapperHelper.getMapper();
                        
                        BaseResponse br = mapper.readValue(response, BaseResponse.class);
                        
                        if (br.isSuccess()) {
                            MessageHelper.Success("Success", "Data deleted");
                            getProducts();
                        } else {
                            MessageHelper.Error("Error", br.getMessage());
                        }
                        
                    } catch (SQLException ex) {
                         Logger.getLogger(FormProduct.class.getName()).log(Level.SEVERE, null, ex);
                    }catch (IOException ex) {
                        Logger.getLogger(FormProduct.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(FormProduct.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        };
        
        return event;
    }
    
    private void btnAddProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddProductActionPerformed
        String buttonLabel = btnAddProduct.getLabel();
        btnAddProduct.setEnabled(false);
        btnAddProduct.setLabel("Loading...");

        ObjectMapper mapper = MapperHelper.getMapper();
        
        ProductRequest request
        = new ProductRequest(
            tfNama.getText(),
            Integer.parseInt(tfStock.getText()),
            Integer.parseInt(tfHarga.getText()));

        try {
            String body = mapper.writeValueAsString(request);
            String response = HttpHelper.post("products", body, JdbcHelper.getToken());
            BaseResponse br = mapper.readValue(response, BaseResponse.class);

            if (br.getCode().equals("0000")) {
                MessageHelper.Success("Success", br.getMessage());
                getProducts();
            } else {
                MessageHelper.Error("Error", br.getMessage());
            }
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(FormLoginRegister.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(FormUser.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            btnAddProduct.setEnabled(true);
            btnAddProduct.setLabel(buttonLabel);
        }
    }//GEN-LAST:event_btnAddProductActionPerformed

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        MainMenu mm = new MainMenu();
        mm.setVisible(true);
        super.setVisible(false);
    }//GEN-LAST:event_backButtonActionPerformed

    public static void main(String args[]) {
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FormProduct.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormProduct.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormProduct.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormProduct.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormProduct().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JButton btnAddProduct;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableProduct;
    private javax.swing.JTextField tfHarga;
    private javax.swing.JTextField tfNama;
    private javax.swing.JTextField tfStock;
    // End of variables declaration//GEN-END:variables
}
