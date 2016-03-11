package impulsexchangeserver.history;

import impulsexchangeserver.common.EraseEntity;
import impulsexchangeserver.common.Service;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import javax.swing.JOptionPane;

public class LocalFilesHandler {

    public static boolean checkAndCreateLocalDirectory(String path) {
        try {
            File dir = new File(path);
            if (!Files.isDirectory(dir.toPath())) {
                Files.createDirectory(dir.toPath());
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Произошла ошибка при создании директории <" + path + ">\r\n"
                    + "ex: " + ex, "LocalFilesHandler : checkAndCreateLocalDirectory()", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    public static boolean checkAndCreateLocalFile(String path) {
        try {
            File file = new File(path);
            if (!Files.exists(file.toPath())) {
                Files.createFile(file.toPath());
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Произошла ошибка при создании файла <" + path + ">\r\n"
                    + "ex: " + ex, "LocalFilesHandler : checkAndCreateLocalFile()", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    public static void updateMainArchive(String departmentName, List<String> deletionLinesList) {
        String path = System.getProperty("user.dir") + "\\" + departmentName + "\\archive.bin";
        BufferedOutputStream out = null;
        if (checkAndCreateLocalFile(path)) {
            try {
                File archive = new File(path);
                out = new BufferedOutputStream(new FileOutputStream(archive));
                for (String tempList : deletionLinesList) {
                    out.write((tempList + "\r\n").getBytes());
                }
                Service.streamClose(out);
                deletionLinesList.clear();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Произошла ошибка при записи данных в архив <" + path + ">\r\n"
                        + "ex: " + ex, "LocalFileHandler : updateMainArchive()", JOptionPane.ERROR_MESSAGE);
            } finally {
                Service.streamClose(out);
            }
        }
    }

    public static void updatePreviousExchangeArchive(List<EraseEntity> eraseEntityList) {
        BufferedOutputStream out = null;
        String path = System.getProperty("user.dir") + File.separator + "PreviousExchange.bin";
        try {
            File archive = new File(path);
            if (checkAndCreateLocalFile(path)) {
                out = new BufferedOutputStream(new FileOutputStream(archive));
                for (EraseEntity eraseEntity : eraseEntityList) {
                    for (String deletedLine : eraseEntity.getDeletionLinesList()) {
                        out.write((deletedLine + "\r\n").getBytes());
                    }
                }
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Произошла ошибка при записи данных в архив <" + path + ">\r\n"
                    + "ex: " + ex, "LocalFileHandler : updatePreviousExchangeArchive()", JOptionPane.ERROR_MESSAGE);
        } finally {
            Service.streamClose(out);
        }
    }
}
