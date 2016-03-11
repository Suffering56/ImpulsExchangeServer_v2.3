package impulsexchangeserver.ftp;

import impulsexchangeserver.common.EraseEntity;
import java.util.List;
import org.apache.commons.net.ftp.FTPClient;

public class FTPUpdateLauncher extends Thread {

    public FTPUpdateLauncher(List<EraseEntity> eraseEntityList) {
        this.eraseEntityList = eraseEntityList;
    }

    @Override
    public void run() {
        ftpInstance = FTPConnector.getInstance();
        FTPClient ftpConnection = ftpInstance.connect();
        if (ftpConnection != null) {
            for (EraseEntity eraseEntity : eraseEntityList) {
                new FTPSingleUpater(ftpConnection, eraseEntity).start();
            }
            ftpInstance.disconnect();
        }
    }

    private FTPConnector ftpInstance;
    private final List<EraseEntity> eraseEntityList;
}
