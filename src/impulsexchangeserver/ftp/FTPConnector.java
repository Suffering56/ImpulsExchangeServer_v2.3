package impulsexchangeserver.ftp;

import impulsexchangeserver.options.Options;
import java.io.IOException;
import javax.swing.JOptionPane;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

public class FTPConnector {

    private FTPConnector() {
        //private!
    }

    /**
     * SingleTone.
     *
     * @return Новый экземпляр FTPConnector при первом вызове данного метода.
     * При повторном вызове будет вовзращен уже существующий экземпляр.
     */
    public static FTPConnector getInstance() {
        if (instance == null) {
            instance = new FTPConnector();
        }
        return instance;
    }

    /**
     * Попытка установления соединения с FTP-сервером. В случае ошибок на экране
     * появится информационное окно, с сообщением об ошибке и дополнительных
     * рекомендациях.
     *
     * @return объект FTPCleint в случае успешного установления соединения. null
     * - в случае возникновения каких-либо ошибок.
     */
    public FTPClient connect() {
        try {
            ftpConnection = new FTPClient();
            ftpConnection.connect(Options.getFtpAddress());
            if (!ftpConnection.login(Options.getFtpLogin(), Options.getFtpPassword())) {
                throw new IOException("FtpLoginException");
            }
            
            /**
             * Древняя магия. Не трогать. Без нее не обойтись.
             */
            ftpConnection.enterLocalPassiveMode();
            ftpConnection.setFileType(FTP.BINARY_FILE_TYPE);

        } catch (IOException ex) {
            disconnect();
            String errorMsg = "Неизвестная ошибка.";
            if (ex.toString().contains("UnknownHostException")) {
                errorMsg = "Указан неверный <адрес> FTP-сервера.";
            } else if (ex.toString().contains("FtpLoginException")) {
                errorMsg = "Указан неверный <логин> и(или) <пароль>. \r\n"
                        + "Либо вы указали <адрес> 'чужого' FTP-сервера.";
            } else if (ex.toString().contains("NoRouteToHostException")) {
                errorMsg = "Отсутствует подключение к интернету. Проверьте соединение.";
            }
            JOptionPane.showMessageDialog(null, "Ошибка при установлении соединения с FTP-сервером. \r\n"
                    + errorMsg + "\r\n" + "ex: " + ex, this.getClass().getName() + " : connect()", JOptionPane.ERROR_MESSAGE);
        }
        return ftpConnection;
    }

    /**
     * Попытка корректного закрытия соединения с FTP-сервером. В случае ошибки
     * будет отображено соответствующее сообщение. В любом случае переменной
     * ftpConnection будет присвоено значение null.
     *
     * @return
     */
    public boolean disconnect() {
        boolean result = false;
        try {
            if (ftpConnection != null) {
                ftpConnection.disconnect();
            }
            result = true;
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Ошибка при закрытии соединения: <FTP.disconnect()>. \r\n"
                    + "ex: " + ex, this.getClass().getName() + " : disconnect()", JOptionPane.ERROR_MESSAGE);
        } finally {
            ftpConnection = null;
        }
        return result;
    }

    private static FTPConnector instance = null;
    private FTPClient ftpConnection;
}
