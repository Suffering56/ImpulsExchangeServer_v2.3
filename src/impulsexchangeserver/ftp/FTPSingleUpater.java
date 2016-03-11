package impulsexchangeserver.ftp;

import impulsexchangeserver.common.EraseEntity;
import impulsexchangeserver.common.Service;
import impulsexchangeserver.history.LocalFilesHandler;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import javax.swing.JOptionPane;
import org.apache.commons.net.ftp.FTPClient;

public class FTPSingleUpater {

    public FTPSingleUpater(FTPClient ftpConnection, EraseEntity eraseEntity) {
        this.ftpConnection = ftpConnection;
        departmentName = eraseEntity.getDepartmentName();
        keepLinesList = eraseEntity.getKeepLinesList();
        deletionLinesList = eraseEntity.getDeletionLinesList();
    }

    public void start() {
        try {
            if (changeFTPDirectory()) {
                if (updateRemoteFile()) {
                    LocalFilesHandler.updateMainArchive(departmentName, deletionLinesList);
                }
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Произошла ошибка при удалении выбранных заказов с FTP-сервера.\r\n"
                    + "Отдел №" + departmentName + "\r\n" + "ex: " + ex,
                    this.getClass().getName() + " : Start", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean changeFTPDirectory() throws IOException {
        try {
            if (ftpConnection.changeToParentDirectory()) {
                return ftpConnection.changeWorkingDirectory(departmentName);
            } else {
                throw new IOException();
            }
        } catch (IOException ex) {
            throw new IOException("Произошла ошибка при вызове метода <changeFTPDirectory()>.\r\n"
                    + "ex: " + ex);
        }
    }

    private boolean updateRemoteFile() throws IOException {
        OutputStream stream = ftpConnection.storeFileStream("orders.txt");
        if (stream != null) {
            BufferedOutputStream out = new BufferedOutputStream(stream);
            if (keepLinesList.isEmpty()) {
                out.write(("").getBytes());
            } else {
                for (String keepLine : keepLinesList) {
                    out.write((keepLine + "\r\n").getBytes());
                }
            }
            Service.streamClose(out);
        } else {
            throw new IOException("FtpConnection.storeFileStream(\"orders.txt\") is null.");
        }
        return ftpConnection.completePendingCommand();
    }

    private final FTPClient ftpConnection;
    private final List<String> keepLinesList;
    private final List<String> deletionLinesList;
    private final String departmentName;
}
