package impulsexchangeserver.history;

import impulsexchangeserver.MainFrame;
import impulsexchangeserver.common.Service;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

public class FrameHistoryLast25 extends javax.swing.JFrame {

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jOrdersList = new javax.swing.JList();
        jScrollPane2 = new javax.swing.JScrollPane();
        jDatesList = new javax.swing.JList();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Архив (последние 25 заказов)");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jOrdersList.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                generalKeyPressed(evt);
            }
        });
        jOrdersList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jOrdersListValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(jOrdersList);

        jDatesList.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                generalKeyPressed(evt);
            }
        });
        jDatesList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jDatesListValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(jDatesList);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Номер заказа");
        jLabel1.setFocusable(false);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Дата");
        jLabel2.setFocusable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(3, 3, 3))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel1, jLabel2, jScrollPane1});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 467, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addGap(3, 3, 3))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public FrameHistoryLast25(MainFrame mainFrame, String departmentName) {
        this.mainFrame = mainFrame;
        initComponents();
        setLocationRelativeTo(null);
        String path = System.getProperty("user.dir") + "\\" + departmentName + "\\archive.bin";
        try {
            extractArchive(path);
            jOrdersList.setModel(viewOrdersList);
            jDatesList.setModel(viewDatesList);
            mainFrame.setEnabled(false);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Произошла ошибка при чтении файла архива <" + path + ">\r\n"
                    + "ex: " + ex, this.getClass().getName() + ": extractArchive()", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void extractArchive(String path) throws IOException {
        ordersList.clear();
        datesList.clear();
        viewOrdersList.clear();
        viewDatesList.clear();
        if (LocalFilesHandler.checkAndCreateLocalFile(path)) {
            String line;
            BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(new File(path))));
            while ((line = in.readLine()) != null) {
                line = line.trim();
                if (!line.equals("")) {
                    ordersList.addElement(extractData(line, "order"));
                    datesList.addElement(extractData(line, "date"));
                }
            }
            Service.streamClose(in);

            //реализация чтения архива с конца.
            int max = 25;
            if (ordersList.size() < 25) {
                max = ordersList.size();
            }
            for (int i = 0; i < max; i++) {
                int index = ordersList.getSize() - 1 - i;
                viewOrdersList.addElement(ordersList.get(index));
                viewDatesList.addElement(datesList.get(index));
            }
        }
    }

    private String extractData(String line, String param) {
        Matcher m = PATTERN.matcher(line);
        if (m.matches()) {
            switch (param) {
                case "order":
                    return m.group(1);                          //номер заказа!
                case "date":
                    return m.group(2) + "     " + m.group(3);   //дата + время
                default:
                    return "Неверный параметр функции <extractData>";
            }
        } else {
            return "Ошибка чтения строки: <" + line + ">";
        }
    }

    private void jDatesListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jDatesListValueChanged
        jOrdersList.setSelectedIndex(jDatesList.getSelectedIndex());
    }//GEN-LAST:event_jDatesListValueChanged

    private void jOrdersListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jOrdersListValueChanged
        jDatesList.setSelectedIndex(jOrdersList.getSelectedIndex());
    }//GEN-LAST:event_jOrdersListValueChanged

    private void generalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_generalKeyPressed
        if (evt.getKeyCode() == 27) {
            mainFrame.setEnabled(true);
            this.dispose();
        }
    }//GEN-LAST:event_generalKeyPressed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        mainFrame.setEnabled(true);
    }//GEN-LAST:event_formWindowClosing

    private final MainFrame mainFrame;
    private final Pattern PATTERN = Pattern.compile("(\\d+/\\d+)\\p{Space}+(\\d+.\\d+.\\d+)\\p{Space}(\\d+:\\d+:\\d+)");
    private final DefaultListModel ordersList = new DefaultListModel();
    private final DefaultListModel datesList = new DefaultListModel();
    private final DefaultListModel viewOrdersList = new DefaultListModel();
    private final DefaultListModel viewDatesList = new DefaultListModel();
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList jDatesList;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JList jOrdersList;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
