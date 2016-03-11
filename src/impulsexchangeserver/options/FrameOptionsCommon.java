package impulsexchangeserver.options;

import impulsexchangeserver.MainFrame;
import impulsexchangeserver.common.Service;
import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class FrameOptionsCommon extends javax.swing.JFrame {

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        chooseExchangePathBtn = new javax.swing.JButton();
        newDepartmentField = new javax.swing.JTextField();
        cancelBtn = new javax.swing.JButton();
        saveBtn = new javax.swing.JButton();
        exchangePlacePathField = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        departmentsList = new javax.swing.JList();
        addDepartmentBtn = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        removeDepartmentBtn = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        swndFileNameField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Настройки");
        setName("optionsFrame"); // NOI18N
        setResizable(false);
        setType(java.awt.Window.Type.POPUP);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Отделы:");
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel1.setFocusable(false);
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Путь к месту обмена:");
        jLabel2.setToolTipText("Тот, который указывается в СуперОкна (обычно C:\\)");
        jLabel2.setFocusable(false);

        chooseExchangePathBtn.setText(" ...");
        chooseExchangePathBtn.setFocusPainted(false);
        chooseExchangePathBtn.setFocusable(false);
        chooseExchangePathBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chooseExchangePathBtnActionPerformed(evt);
            }
        });

        newDepartmentField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                newDepartmentFieldKeyPressed(evt);
            }
        });

        cancelBtn.setText("Отмена");
        cancelBtn.setFocusPainted(false);
        cancelBtn.setFocusable(false);
        cancelBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelBtnActionPerformed(evt);
            }
        });

        saveBtn.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        saveBtn.setText("Сохранить и выйти");
        saveBtn.setFocusPainted(false);
        saveBtn.setFocusable(false);
        saveBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveBtnActionPerformed(evt);
            }
        });

        exchangePlacePathField.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        exchangePlacePathField.setToolTipText("Тот, который указывается в СуперОкна (обычно C:\\)");
        exchangePlacePathField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                generalKeyPressed(evt);
            }
        });

        departmentsList.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        departmentsList.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                departmentsListKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(departmentsList);

        addDepartmentBtn.setText("Добавить отдел");
        addDepartmentBtn.setFocusPainted(false);
        addDepartmentBtn.setFocusable(false);
        addDepartmentBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addDepartmentBtnActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Новый отдел:");
        jLabel3.setFocusable(false);

        removeDepartmentBtn.setText("Удалить выделенный отдел");
        removeDepartmentBtn.setActionCommand("<html>Удалить<br>выделенный<br>отдел</html>");
        removeDepartmentBtn.setFocusPainted(false);
        removeDepartmentBtn.setFocusable(false);
        removeDepartmentBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeDepartmentBtnActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Имя файла обмена:");
        jLabel10.setToolTipText("Расширение указывать обязательно");
        jLabel10.setFocusable(false);

        swndFileNameField.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        swndFileNameField.setToolTipText("Расширение указывать обязательно");
        swndFileNameField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                generalKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(swndFileNameField)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(cancelBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(saveBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(newDepartmentField, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(addDepartmentBtn))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(exchangePlacePathField, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(chooseExchangePathBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(removeDepartmentBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(10, 10, 10))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel1, jScrollPane1});

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {exchangePlacePathField, jLabel2, newDepartmentField});

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {addDepartmentBtn, chooseExchangePathBtn});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(newDepartmentField)
                            .addComponent(addDepartmentBtn))
                        .addGap(7, 7, 7)
                        .addComponent(removeDepartmentBtn)
                        .addGap(7, 7, 7)
                        .addComponent(jLabel2)
                        .addGap(0, 0, 0)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(exchangePlacePathField, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(chooseExchangePathBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(7, 7, 7)
                        .addComponent(jLabel10)
                        .addGap(0, 0, 0)
                        .addComponent(swndFileNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cancelBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(saveBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1))
                .addGap(12, 12, 12))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {addDepartmentBtn, chooseExchangePathBtn, exchangePlacePathField, jLabel1, jLabel10, jLabel2, jLabel3, newDepartmentField, removeDepartmentBtn, swndFileNameField});

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {cancelBtn, saveBtn});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public FrameOptionsCommon(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        initComponents();
        setLocationRelativeTo(null);
        exchangePlaceChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        exchangePlacePathField.setText(Options.getExchangePlacePath());
        exchangePlacePathField.setCaretPosition(0);
        swndFileNameField.setText(Options.getSwndFileName());
        tempDepartmentsList = Service.convertToModel(Options.getDepartmentsList());
        departmentsList.setModel(tempDepartmentsList);

        newDepartmentField.requestFocusInWindow();
        mainFrame.setEnabled(false);
    }

    private void saveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveBtnActionPerformed
        Options.setDepartmentsList(Service.convertToList(tempDepartmentsList));
        Options.setExchangePlacePath(exchangePlacePathField.getText());
        Options.setSwndFileName(swndFileNameField.getText());

        OptionsHandler.saveOptions("Common");
        if (switcher) {
            Service.restartApplication(mainFrame);
        }
        mainFrame.setEnabled(true);
        this.dispose();
    }//GEN-LAST:event_saveBtnActionPerformed

    private void chooseExchangePathBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chooseExchangePathBtnActionPerformed
        exchangePlaceChooser.setCurrentDirectory(new File(Options.getExchangePlacePath()));
        if (exchangePlaceChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            exchangePlacePathField.setText(exchangePlaceChooser.getSelectedFile().getPath());
            exchangePlacePathField.setCaretPosition(0);
        }
    }//GEN-LAST:event_chooseExchangePathBtnActionPerformed

    private void addDepartmentBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addDepartmentBtnActionPerformed
        String newDep = newDepartmentField.getText().trim();
        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher(newDep);
        if (m.matches()) {
            if (!tempDepartmentsList.contains(newDep)) {
                tempDepartmentsList.addElement(newDep);
                switcher = true;
            } else {
                JOptionPane.showMessageDialog(null, "Отдел №" + newDep + " уже есть в списке!");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Некорректный номер отдела!");
        }
        newDepartmentField.setText(null);
    }//GEN-LAST:event_addDepartmentBtnActionPerformed

    private void removeDepartmentBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeDepartmentBtnActionPerformed
        if (departmentsList.getSelectedIndex() != -1) {                              //Если отдел выбран
            tempDepartmentsList.remove(departmentsList.getSelectedIndex());          //Удалить из списка
            switcher = true;
        }
    }//GEN-LAST:event_removeDepartmentBtnActionPerformed

    private void newDepartmentFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_newDepartmentFieldKeyPressed
        if (evt.getKeyCode() == 10) {
            addDepartmentBtn.doClick();
        } else if (evt.getKeyCode() == 27) {
            cancelBtn.doClick();
        }
    }//GEN-LAST:event_newDepartmentFieldKeyPressed

    private void departmentsListKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_departmentsListKeyPressed
        if ((evt.getKeyCode() == 127) || (evt.getKeyCode() == 110)) {
            removeDepartmentBtn.doClick();
        } else if (evt.getKeyCode() == 27) {
            cancelBtn.doClick();
        }
    }//GEN-LAST:event_departmentsListKeyPressed

    private void generalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_generalKeyPressed
        if (evt.getKeyCode() == 27) {
            cancelBtn.doClick();
        }
    }//GEN-LAST:event_generalKeyPressed

    private void cancelBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelBtnActionPerformed
        mainFrame.setEnabled(true);
        this.dispose();
    }//GEN-LAST:event_cancelBtnActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        mainFrame.setEnabled(true);
    }//GEN-LAST:event_formWindowClosing

    private final MainFrame mainFrame;
    private final DefaultListModel tempDepartmentsList;
    private final JFileChooser exchangePlaceChooser = new JFileChooser();
    private boolean switcher = false;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addDepartmentBtn;
    private javax.swing.JButton cancelBtn;
    private javax.swing.JButton chooseExchangePathBtn;
    private javax.swing.JList departmentsList;
    private javax.swing.JTextField exchangePlacePathField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField newDepartmentField;
    private javax.swing.JButton removeDepartmentBtn;
    private javax.swing.JButton saveBtn;
    private javax.swing.JTextField swndFileNameField;
    // End of variables declaration//GEN-END:variables
}
