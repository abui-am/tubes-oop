package ui;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import core.models.requests.MemberRequest;
import core.models.responses.BaseResponse;
import core.models.responses.MemberListResponse;
import helpers.DateHelper;
import helpers.HttpHelper;
import helpers.JdbcHelper;
import helpers.MapperHelper;
import helpers.MessageHelper;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonModel;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import ui.action.TableActionCellEditor;
import ui.action.TableActionCellRender;
import ui.action.TableActionEvent;
import ui.members.EditMemberPanel;

public class FormMember extends javax.swing.JFrame {
    
    private List<MemberListResponse> members;
    
    public FormMember() {
        initComponents();
        getMembers();
    }
    
    public void getMembers () {        
        tableMember.getColumnModel().getColumn(8).setCellRenderer(new TableActionCellRender());
        tableMember.getColumnModel().getColumn(8).setCellEditor(new TableActionCellEditor(getEvent()));
        
        DefaultTableModel model = (DefaultTableModel) tableMember.getModel();
        model.setRowCount(0);
        
        try {
            String token = JdbcHelper.getToken();
            String response = HttpHelper.get("members", token);
            
            ObjectMapper mapper = MapperHelper.getMapper();
            BaseResponse<List<MemberListResponse>> br = mapper.readValue(response, new TypeReference<BaseResponse<List<MemberListResponse>>>() {});
            
            List<MemberListResponse> members = br.getData();
            this.members = members;
            
            for (MemberListResponse member : members) {
                String joinDate = DateHelper.setFormat(member.getJoinDate());
                String data[] = {member.getMemberNumber(), joinDate, String.valueOf(member.getPoint()), member.getNik(), member.getName(), member.getBirthPlace(), member.getGender(), member.getAddress()};
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
    
    private TableActionEvent getEvent() {
        TableActionEvent event = new TableActionEvent() {
            @Override
            public void onEdit(int row) {
                int id = members.get(row).getId();
                String name = members.get(row).getName();
                String nik = members.get(row).getNik();
                String address = members.get(row).getAddress();
                String birthPlace = members.get(row).getBirthPlace();
                String gender = members.get(row).getGender();
                
                JDialog dialog = new JDialog();
                    
                EditMemberPanel panel = new EditMemberPanel(id, dialog, FormMember.this);
                panel.textFieldName.setText(name);
                panel.textFieldNIK.setText(nik);
                panel.textFieldAddress.setText(address);
                panel.textFieldBirthPlace.setText(birthPlace);
                
                if (gender.equals("L")) {
                    panel.rbLakiLaki.setSelected(true);
                } else {
                    panel.rbPerempuan.setSelected(true);
                }
                
                dialog.add(panel);
                dialog.setSize(400, 300);
                dialog.setVisible(true);
            }

            @Override
            public void onDelete(int row) {
                int answer = MessageHelper.Confirm("Confirm", "Apakah yakin ingin menghapus data tersebut?");
                
                if (answer == JOptionPane.YES_OPTION) {
                    int id = members.get(row).getId();
                    
                    try {
                        String token = JdbcHelper.getToken();
                        String response = HttpHelper.delete("members/" + id, token);
                        ObjectMapper mapper = MapperHelper.getMapper();
                        
                        BaseResponse br = mapper.readValue(response, BaseResponse.class);
                        
                        if (br.isSuccess()) {
                            MessageHelper.Success("Success", "Data deleted");
                            getMembers();
                        } else {
                            MessageHelper.Error("Error", br.getMessage());
                        }
                        
                        getMembers();
                    } catch (SQLException ex) {
                         Logger.getLogger(FormUser.class.getName()).log(Level.SEVERE, null, ex);
                    }catch (IOException ex) {
                        Logger.getLogger(FormUser.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(FormUser.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        };
        
        return event;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroupGender = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableMember = new javax.swing.JTable();
        tfNIK = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        tfName = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        tfBirthplace = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        tfAddress = new javax.swing.JTextField();
        btnAddMember = new javax.swing.JButton();
        backButton = new javax.swing.JButton();
        rbLakiLaki = new javax.swing.JRadioButton();
        rbPerempuan = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tableMember.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No. Member", "Tanggal Join", "Poin", "NIK", "Nama", "Tempat Lahir", "Gender", "Alamat", "Aksi"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableMember.setRowHeight(50);
        jScrollPane1.setViewportView(tableMember);
        if (tableMember.getColumnModel().getColumnCount() > 0) {
            tableMember.getColumnModel().getColumn(2).setPreferredWidth(20);
            tableMember.getColumnModel().getColumn(6).setPreferredWidth(20);
        }

        jLabel1.setText("NIK");

        jLabel3.setText("Nama");

        jLabel4.setText("Tempat Lahir");

        jLabel5.setText("Gender");

        jLabel6.setText("Alamat");

        btnAddMember.setText("TAMBAH MEMBER");
        btnAddMember.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddMemberActionPerformed(evt);
            }
        });

        backButton.setText("KEMBALI");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        buttonGroupGender.add(rbLakiLaki);
        rbLakiLaki.setText("Laki - Laki");
        rbLakiLaki.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbLakiLakiActionPerformed(evt);
            }
        });

        buttonGroupGender.add(rbPerempuan);
        rbPerempuan.setText("Perempuan");
        rbPerempuan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbPerempuanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 852, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnAddMember, javax.swing.GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addComponent(tfName)
                        .addComponent(jLabel4)
                        .addComponent(tfBirthplace)
                        .addComponent(jLabel5)
                        .addComponent(tfNIK, javax.swing.GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addComponent(backButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(rbLakiLaki)
                        .addGap(42, 42, 42)
                        .addComponent(rbPerempuan))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel6)
                        .addComponent(tfAddress, javax.swing.GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfNIK, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfName, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfBirthplace, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rbLakiLaki)
                            .addComponent(rbPerempuan))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(btnAddMember, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(backButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 441, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddMemberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddMemberActionPerformed
        String buttonLabel = btnAddMember.getLabel();
        btnAddMember.setEnabled(false);
        btnAddMember.setLabel("Loading...");

        ObjectMapper mapper = MapperHelper.getMapper();

        MemberRequest request
        = new MemberRequest(
            tfNIK.getText(),
            tfName.getText(),
            tfBirthplace.getText(),
            buttonGroupGender.getSelection() == rbLakiLaki.getModel() ? "L" : "P",
            tfAddress.getText());

        try {
            String body = mapper.writeValueAsString(request);
            String response = HttpHelper.post("members", body, JdbcHelper.getToken());
            BaseResponse br = mapper.readValue(response, BaseResponse.class);

            if (br.getCode().equals("0000")) {
                MessageHelper.Success("Success", br.getMessage());
                getMembers();
            } else {
                MessageHelper.Error("Error", br.getMessage());
            }
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(FormLoginRegister.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(FormUser.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            btnAddMember.setEnabled(true);
            btnAddMember.setLabel(buttonLabel);
        }
    }//GEN-LAST:event_btnAddMemberActionPerformed

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        MainMenu mm = new MainMenu();
        mm.setVisible(true);
        super.setVisible(false);
    }//GEN-LAST:event_backButtonActionPerformed

    private void rbLakiLakiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbLakiLakiActionPerformed
        ButtonModel m = this.rbLakiLaki.getModel();
        buttonGroupGender.setSelected(m, rootPaneCheckingEnabled);
    }//GEN-LAST:event_rbLakiLakiActionPerformed

    private void rbPerempuanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbPerempuanActionPerformed
        ButtonModel m = this.rbPerempuan.getModel();
        buttonGroupGender.setSelected(m, rootPaneCheckingEnabled);
    }//GEN-LAST:event_rbPerempuanActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormMember().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JButton btnAddMember;
    private javax.swing.ButtonGroup buttonGroupGender;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton rbLakiLaki;
    private javax.swing.JRadioButton rbPerempuan;
    private javax.swing.JTable tableMember;
    private javax.swing.JTextField tfAddress;
    private javax.swing.JTextField tfBirthplace;
    private javax.swing.JTextField tfNIK;
    private javax.swing.JTextField tfName;
    // End of variables declaration//GEN-END:variables
}
