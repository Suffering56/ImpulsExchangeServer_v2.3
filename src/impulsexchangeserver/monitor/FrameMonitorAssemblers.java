package impulsexchangeserver.monitor;

import impulsexchangeserver.MainFrame;
import java.awt.Point;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;

public class FrameMonitorAssemblers extends javax.swing.JFrame {

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTablePopup = new javax.swing.JPopupMenu();
        deleteMenuBtn = new javax.swing.JMenuItem();
        closeBtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable = new javax.swing.JTable();
        addBtn = new javax.swing.JButton();
        nameField = new javax.swing.JTextField();
        idBrigField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        deleteBtn = new javax.swing.JButton();

        deleteMenuBtn.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        deleteMenuBtn.setText("Удалить");
        deleteMenuBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteMenuBtnActionPerformed(evt);
            }
        });
        jTablePopup.add(deleteMenuBtn);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        closeBtn.setText("Закрыть окно");
        closeBtn.setFocusable(false);
        closeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeBtnActionPerformed(evt);
            }
        });

        jTable.setModel(tableModel);
        jTable.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTable.setFocusable(false);
        jTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable.getTableHeader().setResizingAllowed(false);
        jTable.getTableHeader().setReorderingAllowed(false);
        jTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable);

        addBtn.setText("Добавить сборщика");
        addBtn.setFocusable(false);
        addBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBtnActionPerformed(evt);
            }
        });

        nameField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nameFieldKeyPressed(evt);
            }
        });

        idBrigField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                idBrigFieldKeyPressed(evt);
            }
        });

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Фамилия И.");
        jLabel1.setFocusable(false);

        jLabel2.setText("№ бригады");
        jLabel2.setFocusable(false);

        deleteBtn.setText("Удалить сборщика");
        deleteBtn.setFocusable(false);
        deleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(addBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(closeBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
                            .addComponent(nameField))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(idBrigField, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)))
                    .addComponent(deleteBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {idBrigField, jLabel2});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(2, 2, 2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(idBrigField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addComponent(addBtn)
                .addGap(7, 7, 7)
                .addComponent(deleteBtn)
                .addGap(7, 7, 7)
                .addComponent(closeBtn)
                .addGap(7, 7, 7))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {idBrigField, nameField});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public FrameMonitorAssemblers(MainFrame mainFrame) {
        this.tableModel = new ExtendedDefaultTableModel();
        this.mainFrame = mainFrame;
        initComponents();
        tableModel.addColumn("Фамилия Имя");
        tableModel.addColumn("Номер бригады");
        setLocationRelativeTo(null);
        handler = new MySqlHandler();
        updateTable();
        mainFrame.setEnabled(false);
    }

    private void updateTable() {
        tableModel.setRowCount(0);
        showResult(handler.getAssemblersQueryResult());
    }

    private void closeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeBtnActionPerformed
        handler.closeConnection();
        mainFrame.setEnabled(true);
        this.dispose();
    }//GEN-LAST:event_closeBtnActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        handler.closeConnection();
        mainFrame.setEnabled(true);
    }//GEN-LAST:event_formWindowClosing

    private void jTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableMouseClicked
        if (!SwingUtilities.isLeftMouseButton(evt)) {
            if (SwingUtilities.isRightMouseButton(evt)) {
                Point p = evt.getPoint();
                int rowNumber = jTable.rowAtPoint(p);
                ListSelectionModel model = jTable.getSelectionModel();
                model.setSelectionInterval(rowNumber, rowNumber);
                jTablePopup.show(jTable, evt.getX() + 10, evt.getY() + 10);
            }
        }
    }//GEN-LAST:event_jTableMouseClicked

    private void deleteMenuBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteMenuBtnActionPerformed
        deleteBtn.doClick();
    }//GEN-LAST:event_deleteMenuBtnActionPerformed

    private void deleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBtnActionPerformed
        if (jTable.getSelectedRow() != -1) {
            String assemblerName = (String) tableModel.getValueAt(jTable.getSelectedRow(), 0);
            int result = JOptionPane.showOptionDialog(null, "Вы уверены что хотите удалить сборщика <" + assemblerName + ">?",
                    "Подтвердите действие", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE,
                    null, new Object[]{"Удалить", "Отмена"}, "Отмена");
            if (result == 0) {
                if (handler.deleteAssembler(assemblerName)) {
                    updateTable();
                }
            }
        }
    }//GEN-LAST:event_deleteBtnActionPerformed

    private void addBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBtnActionPerformed
        String name = nameField.getText().trim();
        String id = idBrigField.getText().trim();
        if (!name.equals("") && !id.equals("")) {
            Matcher m = PATTERN.matcher(id);
            if (m.matches()) {
                if (handler.addAssembler(name, Integer.valueOf(id))) {
                    updateTable();
                    nameField.setText(null);
                    idBrigField.setText(null);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Некорректный номер бригады (должно быть число)");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Введите информацию о сборщике");
        }
    }//GEN-LAST:event_addBtnActionPerformed

    private void nameFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nameFieldKeyPressed
        if (evt.getKeyCode() == 27) {
            closeBtn.doClick();
        } else if (evt.getKeyCode() == 10) {
            idBrigField.requestFocusInWindow();
        } else if ((evt.getKeyCode() == 127) || (evt.getKeyCode() == 110)) {
            deleteBtn.doClick();
        }
    }//GEN-LAST:event_nameFieldKeyPressed

    private void idBrigFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_idBrigFieldKeyPressed
        if (evt.getKeyCode() == 27) {
            closeBtn.doClick();
        } else if (evt.getKeyCode() == 10) {
            addBtn.doClick();
        } else if ((evt.getKeyCode() == 127) || (evt.getKeyCode() == 110)) {
            deleteBtn.doClick();
        }
    }//GEN-LAST:event_idBrigFieldKeyPressed

    private int showResult(List<AssemblerEntity> assemblersList) {
        tableModel.setRowCount(0);  //Очищаем предыдущие результаты
        int size = 0;
        if (assemblersList != null) {
            size = assemblersList.size();
            for (int i = 0; i < size; i++) {
                tableModel.insertRow(0, new Object[]{
                    assemblersList.get(i).getAssemblerName(),
                    assemblersList.get(i).getIdBrig()
                });
            }
        }
        return size;
    }

    private final MainFrame mainFrame;
    private final MySqlHandler handler;
    private final ExtendedDefaultTableModel tableModel;
    private final Pattern PATTERN = Pattern.compile("\\d+");
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBtn;
    private javax.swing.JButton closeBtn;
    private javax.swing.JButton deleteBtn;
    private javax.swing.JMenuItem deleteMenuBtn;
    private javax.swing.JTextField idBrigField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable;
    private javax.swing.JPopupMenu jTablePopup;
    private javax.swing.JTextField nameField;
    // End of variables declaration//GEN-END:variables
}
