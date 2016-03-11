package impulsexchangeserver.ftp;

import impulsexchangeserver.common.CurrentDepartment;
import impulsexchangeserver.common.Service;
import impulsexchangeserver.history.LocalFilesHandler;
import impulsexchangeserver.options.Options;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.parser.ParserInitializationException;
import org.apache.commons.net.io.CopyStreamAdapter;

public class FTPSingleImportThread extends Thread {

    public FTPSingleImportThread(CurrentDepartment activeDepartment, FTPClient ftpConnection) {
        this.activeDepartment = activeDepartment;   //передаем информацию отдела (номер, список заказов)
        this.ftpConnection = ftpConnection;
        departmentName = activeDepartment.getDepartmentName();
    }

    @Override
    public void run() {
        try {
            String path = System.getProperty("user.dir") + "\\" + departmentName;
            if (LocalFilesHandler.checkAndCreateLocalDirectory(path)) {
                runImportStages();                                              //начинаем загрузку данных
            } else {
                error = true;
            }
        } catch (IOException ex) {
            error = true;
            JOptionPane.showMessageDialog(null, "Произошла ошибка при загрузке данных о заказах. \r\n"
                    + "Отдел №" + departmentName + "\r\n" + "ex: " + ex,
                    this.getClass().getName() + " : RunImportStages", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Последовательный запуск всех необходимых для корректного импорта
     * процессов. В случае возникновения ошибок, все действия будут прекращены,
     * а переменной error будет присвоено значение true.
     */
    private void runImportStages() throws IOException {
        ftpConnection.changeToParentDirectory();
        if (ftpConnection.changeWorkingDirectory(departmentName)) {
            if (getRemoteFileSize("orders.txt") > 0) {
                if (downloadOrders()) {
                    if (downloadSwndFile()) {
                        activeDepartment.setOrdersList(ordersList);
                        return;
                    }
                }
            }
        }
        emptyData = true;   //нет новых данных
    }

    /**
     * Процесс загрузки информации о поступивших заказах.
     *
     * @return Возвращает true, если процесс был выполнен без ошибок, false - в
     * противном случае.
     */
    private boolean downloadOrders() throws IOException {
        boolean result = false;
        BufferedReader reader = null;
        try {
            InputStream in = ftpConnection.retrieveFileStream("orders.txt");
            if (in != null) {
                reader = new BufferedReader(new InputStreamReader(in));
                String line;
                ordersList = new ArrayList<>();
                while ((line = reader.readLine()) != null) {
                    ordersList.add(line);
                }
                result = true;
            } else {
                throw new IOException("InputStream: retrieveFileStream(\"orders.txt\") is null.");
            }
        } catch (IOException ex) {
            error = true;
            JOptionPane.showMessageDialog(null, "Ошибка чтения списка заказов <FTP:/.../orders.txt>. \r\n"
                    + "ex: " + ex, this.getClass().getName() + ": DownloadOrders", JOptionPane.ERROR_MESSAGE);
        } finally {
            Service.streamClose(reader);
            ftpConnection.completePendingCommand();
        }
        return result;
    }

    /**
     * Процесс загрузки файла обмена.
     *
     * @return Возвращает true, если процесс был выполнен без ошибок, false - в
     * противном случае.
     */
    private boolean downloadSwndFile() {
        boolean result = false;
        long size = getRemoteFileSize(Options.getSwndFileName());
        if (size > 0) {
            setProgressListener(size);
            BufferedOutputStream out = null;
            try {
                File localFile = new File(System.getProperty("user.dir") + "\\"
                        + departmentName + "\\" + Options.getSwndFileName());
                out = new BufferedOutputStream(new FileOutputStream(localFile));
                boolean isSuccess = ftpConnection.retrieveFile(Options.getSwndFileName(), out);
                if (isSuccess) {
                    result = true;
                }
            } catch (IOException ex) {
                error = true;
                JOptionPane.showMessageDialog(null, "Произошла ошибка при загрузке файла обмена. \r\n"
                        + "ex: " + ex, this.getClass().getName() + ": downloadSwndFile", JOptionPane.ERROR_MESSAGE);
            } finally {
                Service.streamClose(out);
            }
        }
        return result;
    }

    /**
     * Узнает размер файла на FTP-сервере.
     *
     * @param fileName имя удаленного файла.
     * @return Возвращает '-1', если файл не существует, 0 - если файл пуст,
     * другие значения (>0) - соответствуют размеру файла в байтах
     */
    private long getRemoteFileSize(String fileName) {
        long size = 0;
        try {
            FTPFile[] files = ftpConnection.listFiles(fileName);
            for (FTPFile file : files) {
                if (file.getName().equals(fileName)) {
                    size = file.getSize();
                }
            }
        } catch (IOException | ParserInitializationException ex) {
            error = true;
            JOptionPane.showMessageDialog(null, "Произошла ошибка при определении размера файла <" + fileName + ">. \r\n"
                    + "ex: " + ex, this.getClass().getName() + ": GetRemoteFileSize", JOptionPane.ERROR_MESSAGE);
        }
        return size;
    }

    /**
     * Установка слушателя событий, который будет следить за процессом загрузки
     * файла обмена с FTP-сервера, и вычислять текущий прогресс загрузки.
     *
     * @param size размер загружаемого файла в байтах.
     */
    private void setProgressListener(long size) {
        CopyStreamAdapter streamListener = new CopyStreamAdapter() {
            private int oldProgress = 0;

            @Override
            public void bytesTransferred(long totalBytesTransferred, int bytesTransferred, long streamSize) {
                progress = (int) (totalBytesTransferred * 100 / size);
                if ((progress != oldProgress) && (progress < 100)) {
                    oldProgress = progress;
                }
            }
        };
        ftpConnection.setCopyStreamListener(streamListener);
    }

    public int getProgress() {
        return progress;
    }

    public boolean isError() {
        return error;
    }

    public boolean isEmptyData() {
        return emptyData;
    }

    private final CurrentDepartment activeDepartment;
    private final FTPClient ftpConnection;

    private List<String> ordersList;
    private final String departmentName;

    private int progress = 0;
    private boolean error = false;
    private boolean emptyData = false;
}
