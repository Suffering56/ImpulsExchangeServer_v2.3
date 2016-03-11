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

public class FrameHistoryPrevious extends javax.swing.JFrame {

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jArchiveList = new javax.swing.JList();
        jScrollPane2 = new javax.swing.JScrollPane();
        jDateList = new javax.swing.JList();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Архив (предыдущий обмен)");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jArchiveList.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                generalKeyPressed(evt);
            }
        });
        jArchiveList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jArchiveListValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(jArchiveList);

        jDateList.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                generalKeyPressed(evt);
            }
        });
        jDateList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jDateListValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(jDateList);

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

    public FrameHistoryPrevious(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        initComponents();
        setLocationRelativeTo(null);
        String path = System.getProperty("user.dir") + "\\PreviousExchange.bin";
        try {
            extractArchive(path);
            jArchiveList.setModel(archiveList);
            jDateList.setModel(dateList);
            mainFrame.setEnabled(false);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Произошла ошибка при чтении файла архива <" + path + ">\r\n"
                    + "ex: " + ex, this.getClass().getName() + ": extractArchive()", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void extractArchive(String path) throws IOException {
        File archive = new File(path);
        archiveList.clear();
        dateList.clear();
        if (LocalFilesHandler.checkAndCreateLocalFile(path)) {
            BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(archive)));
            String line;
            while ((line = in.readLine()) != null) {
                line = line.trim();
                if (!line.equals("")) {
                    archiveList.addElement(extractData(line, "order"));
                    dateList.addElement(extractData(line, "date"));
                }
            }
            Service.streamClose(in);
        }
    }

    private String extractData(String line, String param) {
        Matcher m = p.matcher(line);
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

    private void jDateListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jDateListValueChanged
        jArchiveList.setSelectedIndex(jDateList.getSelectedIndex());
    }//GEN-LAST:event_jDateListValueChanged

    private void jArchiveListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jArchiveListValueChanged
        jDateList.setSelectedIndex(jArchiveList.getSelectedIndex());
    }//GEN-LAST:event_jArchiveListValueChanged

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
    private final Pattern p = Pattern.compile("(\\d+/\\d+)\\p{Space}+(\\d+.\\d+.\\d+)\\p{Space}(\\d+:\\d+:\\d+)");
    private final DefaultListModel archiveList = new DefaultListModel();
    private final DefaultListModel dateList = new DefaultListModel();
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList jArchiveList;
    private javax.swing.JList jDateList;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
