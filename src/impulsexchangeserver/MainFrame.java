package impulsexchangeserver;

import impulsexchangeserver.common.CurrentDepartment;
import impulsexchangeserver.ftp.FTPImportLauncher;
import impulsexchangeserver.history.*;
import impulsexchangeserver.monitor.FrameMonitorAssemblers;
import impulsexchangeserver.monitor.FrameMonitorMain;
import impulsexchangeserver.options.FrameOptionsCommon;
import impulsexchangeserver.options.FrameOptionsFTP;
import impulsexchangeserver.options.FrameOptionsMySQL;
import impulsexchangeserver.options.Options;
import java.awt.Desktop;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JToggleButton;
import javax.swing.Timer;

public class MainFrame extends javax.swing.JFrame {

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        exchangePanel = new javax.swing.JPanel();
        mainDownloadBtn = new javax.swing.JButton();
        doPrintBtn = new javax.swing.JButton();
        exitBtn = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        commonOptionsCallBtn = new javax.swing.JMenuItem();
        ftpOptionsCallBtn = new javax.swing.JMenuItem();
        mySqlOptionsCallBtn = new javax.swing.JMenuItem();
        exitCallBtn = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        previousExchangeCallBtn = new javax.swing.JMenuItem();
        searchCallBtn = new javax.swing.JMenuItem();
        last25CallBtn = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        monitorMainCallBtn = new javax.swing.JMenuItem();
        monitorAsssemblersCallBtn = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Impuls Exchange Server");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        exchangePanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout exchangePanelLayout = new javax.swing.GroupLayout(exchangePanel);
        exchangePanel.setLayout(exchangePanelLayout);
        exchangePanelLayout.setHorizontalGroup(
            exchangePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        exchangePanelLayout.setVerticalGroup(
            exchangePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 238, Short.MAX_VALUE)
        );

        mainDownloadBtn.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        mainDownloadBtn.setText("Загрузить данные");
        mainDownloadBtn.setFocusPainted(false);
        mainDownloadBtn.setMaximumSize(new java.awt.Dimension(161, 23));
        mainDownloadBtn.setMinimumSize(new java.awt.Dimension(161, 23));
        mainDownloadBtn.setPreferredSize(new java.awt.Dimension(170, 23));
        mainDownloadBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mainDownloadBtnActionPerformed(evt);
            }
        });

        doPrintBtn.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        doPrintBtn.setText("На печать");
        doPrintBtn.setFocusPainted(false);
        doPrintBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                doPrintBtnActionPerformed(evt);
            }
        });

        exitBtn.setText("Выход");
        exitBtn.setEnabled(false);
        exitBtn.setFocusPainted(false);
        exitBtn.setMaximumSize(new java.awt.Dimension(161, 23));
        exitBtn.setMinimumSize(new java.awt.Dimension(161, 23));
        exitBtn.setPreferredSize(new java.awt.Dimension(170, 23));
        exitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitBtnActionPerformed(evt);
            }
        });

        jMenu1.setText("Меню");

        commonOptionsCallBtn.setText("Общие настройки");
        commonOptionsCallBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                commonOptionsCallBtnActionPerformed(evt);
            }
        });
        jMenu1.add(commonOptionsCallBtn);

        ftpOptionsCallBtn.setText("Настройки FTP");
        ftpOptionsCallBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ftpOptionsCallBtnActionPerformed(evt);
            }
        });
        jMenu1.add(ftpOptionsCallBtn);

        mySqlOptionsCallBtn.setText("Настройки MySQL");
        mySqlOptionsCallBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mySqlOptionsCallBtnActionPerformed(evt);
            }
        });
        jMenu1.add(mySqlOptionsCallBtn);

        exitCallBtn.setText("Выход");
        exitCallBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitCallBtnActionPerformed(evt);
            }
        });
        jMenu1.add(exitCallBtn);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Архив");

        previousExchangeCallBtn.setText("Предыдущий обмен");
        previousExchangeCallBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                previousExchangeCallBtnActionPerformed(evt);
            }
        });
        jMenu2.add(previousExchangeCallBtn);

        searchCallBtn.setText("Поиск по всем заказам");
        searchCallBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchCallBtnActionPerformed(evt);
            }
        });
        jMenu2.add(searchCallBtn);

        last25CallBtn.setText("Последние 25 заказов отдела");
        last25CallBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                last25CallBtnActionPerformed(evt);
            }
        });
        jMenu2.add(last25CallBtn);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Отгрузка");

        monitorMainCallBtn.setText("Заказы");
        monitorMainCallBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                monitorMainCallBtnActionPerformed(evt);
            }
        });
        jMenu3.add(monitorMainCallBtn);

        monitorAsssemblersCallBtn.setText("Сборщики");
        monitorAsssemblersCallBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                monitorAsssemblersCallBtnActionPerformed(evt);
            }
        });
        jMenu3.add(monitorAsssemblersCallBtn);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(mainDownloadBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addComponent(doPrintBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(exitBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(exchangePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {exitBtn, mainDownloadBtn});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(exchangePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(doPrintBtn)
                    .addComponent(mainDownloadBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(exitBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {doPrintBtn, exitBtn, mainDownloadBtn});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public MainFrame() {
        departmentsList = new ArrayList<>();
        doPrintList = new ArrayList<>();
        initComponents();
        int count = Options.getDepartmentsList().size();
        initDynamicComponents(count);
        this.setSize(this.getWidth(), (26 + 7) * count + 20 + 65 + 7);
        this.setLocationRelativeTo(null);
        trayInit();
    }

    private void trayInit() {
        flashingTray = new FlashingTray(this);
        createUpdateCheckTimer();
        createFlashingTrayTimer();
        updateCheckTimer.start();
    }

    public void startNotification() {
        updateCheckTimer.stop();
        flashingTray.showTray();
        flashingTrayTimer.start();
    }

    public void endNotification() {
        flashingTrayTimer.stop();
        flashingTray.hideTray();
        updateCheckTimer.start();
    }

    private void createUpdateCheckTimer() {
        updateCheckTimer = new Timer(30 * 60 * 1000, (ActionEvent e) -> {   //60*1000 = минута
            mainDownloadBtn.doClick();
            System.out.println("checkUpdate");
        });
    }

    private void createFlashingTrayTimer() {
        flashingTrayTimer = new Timer(500, (ActionEvent e) -> {
            switcher = !switcher;
            if (switcher) {
                flashingTray.getTray().setImage(flashingTray.getEmptyIcon());
            } else {
                flashingTray.getTray().setImage(flashingTray.getNormalIcon());
            }
        });
    }

    private void initDynamicComponents(int count) {
        //устанавливаем компоновку (rows, cols, отступ, отступ)
        exchangePanel.setLayout(new GridLayout(0, 4, 7, 7));
        //инициализируем массивы динамических компонентов
        progressBarArray = new JProgressBar[count];
        departmentLabelArray = new JLabel[count];
        doExchangeBtnArray = new JToggleButton[count];
        openDirBtnArray = new JButton[count];

        //инициализируем сами копоненты
        for (int i = 0; i < count; i++) {
            departmentsList.add(new CurrentDepartment(Options.getDepartmentsList().get(i)));
            initializeRow(i);
        }
    }

    private void initializeRow(int i) {
        //инициализация ProgressBar
        progressBarArray[i] = new JProgressBar();
        progressBarArray[i].setStringPainted(true);
        exchangePanel.add(progressBarArray[i]);

        //инициализация DepartmentLabel (номер отдела)
        departmentLabelArray[i] = new JLabel("Отдел №" + departmentsList.get(i).getDepartmentName());
        departmentLabelArray[i].setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        exchangePanel.add(departmentLabelArray[i]);

        //инициализация кнопок "на обмен"
        doExchangeBtnArray[i] = new JToggleButton("На обмен");
        doExchangeBtnArray[i].setActionCommand(String.valueOf(i));                          //передаем в качестве параметра индекс отдела
        doExchangeBtnArray[i].addActionListener(this::doExchangeBtnActionPerformed);
        doExchangeBtnArray[i].setFocusPainted(false);
        doExchangeBtnArray[i].setEnabled(false);
        exchangePanel.add(doExchangeBtnArray[i]);

        //инициализация кнопок "..."
        openDirBtnArray[i] = new JButton("...");
        openDirBtnArray[i].setActionCommand(departmentsList.get(i).getDepartmentName());    //передаем в качестве параметра номер отдела
        openDirBtnArray[i].addActionListener(this::openDirActionPerformed);
        openDirBtnArray[i].setFocusPainted(false);
        exchangePanel.add(openDirBtnArray[i]);
    }

    private void doExchangeBtnActionPerformed(ActionEvent evt) {
        int i = Integer.valueOf(evt.getActionCommand());
        doExchangeBtnArray[i].setSelected(!doExchangeBtnArray[i].isSelected());
        File source = new File(System.getProperty("user.dir") + "\\"
                + departmentsList.get(i).getDepartmentName() + "\\" + Options.getSwndFileName());
        File destination = new File(Options.getExchangePlacePath() + "\\" + Options.getSwndFileName());
        try {
            if (!Files.exists(destination.toPath())) {
                throw new IOException("Указанной директории места обмена <" + Options.getExchangePlacePath() + "> не существует.");
            }
            Files.copy(source.toPath(), destination.toPath(), StandardCopyOption.REPLACE_EXISTING);
            if (!doExchangeBtnArray[i].isSelected()) {                          //Если - это первое нажатие на кнопку, то...
                doPrintList.add(departmentsList.get(i));                        //... и добавляем заказы в printList
                doExchangeBtnArray[i].setSelected(true);                        //... зажимаем кнопку ...
            }
        } catch (IOException ex) {
            String error = "";
            if (ex.toString().contains("SecurityException")) {
                error = "Недостаточно прав доступа. Пожалуйста измените путь к месту обмена, либо настройте права.\r\n";
            }
            doExchangeBtnArray[i].setSelected(false);
            JOptionPane.showMessageDialog(null, "Произошла ошибка при копировании файла обмена <" + Options.getSwndFileName() + ">\r\n"
                    + error + "ex: " + ex, this.getClass().getName() + ": doExchangeBtnActionPerformed()", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void openDirActionPerformed(ActionEvent evt) {
        String path = System.getProperty("user.dir") + "\\" + evt.getActionCommand();
        try {
            LocalFilesHandler.checkAndCreateLocalDirectory(path);
            Desktop.getDesktop().open(new File(path));
        } catch (IOException | RuntimeException ex) {
            JOptionPane.showMessageDialog(null, "Произошла ошибка при открытии директории <" + path + ">\r\n"
                    + "ex: " + ex, this.getClass().getName() + ": openDirActionPerformed()", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void mainDownloadBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mainDownloadBtnActionPerformed
        doPrintList.clear();
        launcher = new FTPImportLauncher(this);
        launcher.start();
    }//GEN-LAST:event_mainDownloadBtnActionPerformed

    private void doPrintBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_doPrintBtnActionPerformed
        new FramePrint(this, doPrintList).setVisible(true);
    }//GEN-LAST:event_doPrintBtnActionPerformed

    private void previousExchangeCallBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_previousExchangeCallBtnActionPerformed
        new FrameHistoryPrevious(this).setVisible(true);
    }//GEN-LAST:event_previousExchangeCallBtnActionPerformed

    private void searchCallBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchCallBtnActionPerformed
        new FrameHistorySearch(this).setVisible(true);
    }//GEN-LAST:event_searchCallBtnActionPerformed

    private void last25CallBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_last25CallBtnActionPerformed
        String param = JOptionPane.showInputDialog(null, "Введите номер отдела:", "Выбор отдела", JOptionPane.PLAIN_MESSAGE);
        if (param != null) {
            if (!Options.getDepartmentsList().contains(param)) {
                JOptionPane.showMessageDialog(null, "Программа не знает такого отдела!", "Отдел №" + param, JOptionPane.INFORMATION_MESSAGE);
            } else {
                new FrameHistoryLast25(this, param).setVisible(true);
            }
        }
    }//GEN-LAST:event_last25CallBtnActionPerformed

    public List<CurrentDepartment> getDepartmentsList() {
        return departmentsList;
    }

    public JProgressBar[] getProgressBarArray() {
        return progressBarArray;
    }

    public JToggleButton[] getDoExchangeBtnArray() {
        return doExchangeBtnArray;
    }

    private void commonOptionsCallBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_commonOptionsCallBtnActionPerformed
        new FrameOptionsCommon(this).setVisible(true);
    }//GEN-LAST:event_commonOptionsCallBtnActionPerformed

    private void ftpOptionsCallBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ftpOptionsCallBtnActionPerformed
        new FrameOptionsFTP(this).setVisible(true);
    }//GEN-LAST:event_ftpOptionsCallBtnActionPerformed

    private void mySqlOptionsCallBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mySqlOptionsCallBtnActionPerformed
        new FrameOptionsMySQL(this).setVisible(true);
    }//GEN-LAST:event_mySqlOptionsCallBtnActionPerformed

    private void exitCallBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitCallBtnActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitCallBtnActionPerformed

    private void monitorMainCallBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_monitorMainCallBtnActionPerformed
        new FrameMonitorMain(this).setVisible(true);
    }//GEN-LAST:event_monitorMainCallBtnActionPerformed

    private void monitorAsssemblersCallBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_monitorAsssemblersCallBtnActionPerformed
        new FrameMonitorAssemblers(this).setVisible(true);
    }//GEN-LAST:event_monitorAsssemblersCallBtnActionPerformed

    private void exitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitBtnActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitBtnActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        flashingTray.showTray();
        this.setVisible(false);
    }//GEN-LAST:event_formWindowClosing

    private final List<CurrentDepartment> departmentsList;
    private final List<CurrentDepartment> doPrintList;

    private JProgressBar[] progressBarArray;
    private JLabel[] departmentLabelArray;
    private JToggleButton[] doExchangeBtnArray;
    private JButton[] openDirBtnArray;

    private FTPImportLauncher launcher;

    private FlashingTray flashingTray;
    private boolean switcher = false;
    public Timer updateCheckTimer;
    public Timer flashingTrayTimer;


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem commonOptionsCallBtn;
    private javax.swing.JButton doPrintBtn;
    private javax.swing.JPanel exchangePanel;
    private javax.swing.JButton exitBtn;
    private javax.swing.JMenuItem exitCallBtn;
    private javax.swing.JMenuItem ftpOptionsCallBtn;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem last25CallBtn;
    private javax.swing.JButton mainDownloadBtn;
    private javax.swing.JMenuItem monitorAsssemblersCallBtn;
    private javax.swing.JMenuItem monitorMainCallBtn;
    private javax.swing.JMenuItem mySqlOptionsCallBtn;
    private javax.swing.JMenuItem previousExchangeCallBtn;
    private javax.swing.JMenuItem searchCallBtn;
    // End of variables declaration//GEN-END:variables
}
