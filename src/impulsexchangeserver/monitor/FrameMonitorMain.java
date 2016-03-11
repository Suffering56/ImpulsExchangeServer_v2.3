package impulsexchangeserver.monitor;

import impulsexchangeserver.MainFrame;
import impulsexchangeserver.common.Service;
import java.awt.Point;
import java.beans.PropertyChangeEvent;
import java.util.Calendar;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;

public class FrameMonitorMain extends javax.swing.JFrame {

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTablePopup = new javax.swing.JPopupMenu();
        deleteMenuBtn = new javax.swing.JMenuItem();
        closeBtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable = new javax.swing.JTable();
        searchBtn = new javax.swing.JButton();
        searchField = new javax.swing.JTextField();
        jCalendar = new com.toedter.calendar.JCalendar();
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
        closeBtn.setFocusPainted(false);
        closeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeBtnActionPerformed(evt);
            }
        });
        closeBtn.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                generalKeyPressed(evt);
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

        searchBtn.setText("Поиск заказа");
        searchBtn.setFocusPainted(false);
        searchBtn.setFocusable(false);
        searchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBtnActionPerformed(evt);
            }
        });
        searchBtn.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                generalKeyPressed(evt);
            }
        });

        searchField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                searchFieldKeyPressed(evt);
            }
        });

        jCalendar.setFocusable(false);
        jCalendar.setWeekOfYearVisible(false);

        deleteBtn.setText("Удалить выделенный заказ");
        deleteBtn.setFocusPainted(false);
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
                    .addComponent(jCalendar, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(closeBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 109, Short.MAX_VALUE)
                        .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(searchBtn)
                        .addGap(101, 101, 101))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(deleteBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jCalendar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(52, 52, 52))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(searchBtn)
                            .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(deleteBtn)
                            .addComponent(closeBtn))
                        .addGap(7, 7, 7))))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {closeBtn, deleteBtn});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public FrameMonitorMain(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        initComponents();
        setLocationRelativeTo(null);
        handler = new MySqlHandler();
        mainFrame.setEnabled(false);
        otherInit();
    }

    private void searchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBtnActionPerformed
        List<MainEntity> resultList = handler.getMainQueryResult("SEARCH", searchField.getText().trim());
        int size = showResult(resultList);
        this.setTitle("Поиск по заказу: <" + searchField.getText() + ">. Найдено " + size + " совпадений");
        searchField.setText("");
    }//GEN-LAST:event_searchBtnActionPerformed

    private void jCalendarListener(PropertyChangeEvent evt) {
        if (isUserAction) {
            cal.setTime(jCalendar.getDate());
            String mDate = Service.convertToSqlDate(cal);
            List<MainEntity> resultList = handler.getMainQueryResult("CALENDAR", mDate);
            int size = showResult(resultList);
            this.setTitle("Поиск по дате монтажа: <" + mDate + ">. Найдено " + size + " совпадений");
        }
    }

    private void searchFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchFieldKeyPressed
        if (evt.getKeyCode() == 10) {
            searchBtn.doClick();
        } else if (evt.getKeyCode() == 27) {
            closeBtn.doClick();
        } else if ((evt.getKeyCode() == 127) || (evt.getKeyCode() == 110)) {
            deleteBtn.doClick();
        }
    }//GEN-LAST:event_searchFieldKeyPressed

    private void generalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_generalKeyPressed
        if (evt.getKeyCode() == 27) {
            closeBtn.doClick();
        }
    }//GEN-LAST:event_generalKeyPressed

    private void closeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeBtnActionPerformed
        isUserAction = false;
        handler.closeConnection();
        mainFrame.setEnabled(true);
        this.dispose();
    }//GEN-LAST:event_closeBtnActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        isUserAction = false;
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
            String order = (String) tableModel.getValueAt(jTable.getSelectedRow(), 0);
            int result = JOptionPane.showOptionDialog(null, "Вы уверены что хотите удалить заказ №" + order + ">?",
                    "Подтвердите действие", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE,
                    null, new Object[]{"Удалить", "Отмена"}, "Отмена");
            if (result == 0) {
                if (handler.deleteOrder(order)) {
                    tableModel.removeRow(jTable.getSelectedRow());
                }
            }
        }
    }//GEN-LAST:event_deleteBtnActionPerformed

    private int showResult(List<MainEntity> resultList) {
        tableModel.setRowCount(0);  //Очищаем предыдущие результаты
        int size = 0;
        if (resultList != null) {
            size = resultList.size();
            for (int i = 0; i < size; i++) {
                tableModel.insertRow(0, new Object[]{
                    resultList.get(i).getOrderName(),
                    resultList.get(i).getMountingDate(),
                    resultList.get(i).getAccessoriesStatus(),
                    resultList.get(i).getProductionStatus()
                });
            }
        }
        return size;
    }

    private void otherInit() {
        tableModel.addColumn("№ заказа");
        tableModel.addColumn("Дата монтажа");
        tableModel.addColumn("Комплектация");
        tableModel.addColumn("Производство");
        tableModel.setRowCount(0);
        searchField.requestFocusInWindow();
        cal = Calendar.getInstance();
        isUserAction = true;
        jCalendar.getDayChooser().addPropertyChangeListener(this::jCalendarListener);
    }

    private Calendar cal;
    private final MainFrame mainFrame;
    private final MySqlHandler handler;
    private final ExtendedDefaultTableModel tableModel = new ExtendedDefaultTableModel();

    /**
     * Нужна для избежания ошибки, которая возникала при закрытии фрейма.
     * Почему-то после всех событий formWindowClosing вызывалось событие
     * JCalendar.PropertyChange, и программа пыталась выполнить запрос к БД,
     * после вызова disconnect(), что приводило к ошибке SQL.
     */
    private boolean isUserAction;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton closeBtn;
    private javax.swing.JButton deleteBtn;
    private javax.swing.JMenuItem deleteMenuBtn;
    private com.toedter.calendar.JCalendar jCalendar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable;
    private javax.swing.JPopupMenu jTablePopup;
    private javax.swing.JButton searchBtn;
    private javax.swing.JTextField searchField;
    // End of variables declaration//GEN-END:variables
}
