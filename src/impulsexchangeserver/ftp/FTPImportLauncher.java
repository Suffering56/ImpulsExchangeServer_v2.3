package impulsexchangeserver.ftp;

import impulsexchangeserver.MainFrame;
import impulsexchangeserver.common.CurrentDepartment;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JToggleButton;
import javax.swing.Timer;
import org.apache.commons.net.ftp.FTPClient;

public class FTPImportLauncher extends Thread {

    public FTPImportLauncher(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        this.departmentsList = mainFrame.getDepartmentsList();
        this.progressBarArray = mainFrame.getProgressBarArray();
        this.doExchangeBtnArray = mainFrame.getDoExchangeBtnArray();
    }

    /**
     * Инициализация соединения с FTP и запуск дополнительных потоков для
     * загрузки файлов обмена и информации о новых заказах с FTP-сервера. А так
     * же запуск таймера для контроля за второстепенными потоками.
     */
    @Override
    public void run() {
        ftpInstance = FTPConnector.getInstance();
        FTPClient ftpConnection = ftpInstance.connect();
        if (ftpConnection != null) {
            for (int i = 0; i < departmentsList.size(); i++) {
                progressBarArray[i].setString(null);
                progressBarArray[i].setValue(0);
                doExchangeBtnArray[i].setEnabled(false);
                doExchangeBtnArray[i].setSelected(false);

                FTPSingleImportThread importThread = new FTPSingleImportThread(departmentsList.get(i), ftpConnection);
                Timer timer = createTimer(importThread, i);
                timer.start();
                importThread.start();
                waitNextTurn(importThread);
                // Пока поток жив, методы ниже не будут вызваны
                timer.stop();
                if (!importThread.isError()) {
                    if (!importThread.isEmptyData()) {
                        progressBarArray[i].setValue(100);
                        progressBarArray[i].setString("Завершено");
                        doExchangeBtnArray[i].setEnabled(true);

                        /**
                         * Запуск оповещения о новых заказах
                         */
                        mainFrame.startNotification();

                    } else {
                        progressBarArray[i].setValue(100);
                        progressBarArray[i].setString("Нет новых данных");
                    }
                } else {
                    progressBarArray[i].setString("Ошибка");
                }
            }
            ftpInstance.disconnect();
        }
    }

    /**
     * Реализация загрузки данных в порядке очереди. Ждем пока не загрузкится
     * информация об одном отделе, и только потом запускаем загрузку следующего.
     */
    private void waitNextTurn(FTPSingleImportThread importThread) {
        try {
            while (importThread.isAlive()) {
                sleep(0);
            }
        } catch (InterruptedException ex) {
            JOptionPane.showMessageDialog(null, "Ошибка загрузки данных с FTP. \r\n"
                    + ex, this.getClass().getName() + " : waitNextTurn()", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Инициализация таймера, который будет следить за прогрессоом загрузки
     * файлов обмена и менять значение progressBar.
     */
    private Timer createTimer(FTPSingleImportThread importThread, int i) {
        Timer timer = new Timer(10, (ActionEvent e) -> {
            if (importThread.isAlive()) {
                progressBarArray[i].setValue(importThread.getProgress());
            }
        });
        return timer;
    }

    private final MainFrame mainFrame;
    private final List<CurrentDepartment> departmentsList;
    private final JProgressBar[] progressBarArray;
    private final JToggleButton[] doExchangeBtnArray;
    private FTPConnector ftpInstance;
}
