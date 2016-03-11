package impulsexchangeserver.options;

import java.util.ArrayList;
import java.util.List;

public class Options {

    public static String getExchangePlacePath() {
        return exchangePlacePath;
    }

    public static void setExchangePlacePath(String exchangePlacePath) {
        Options.exchangePlacePath = exchangePlacePath;
    }

    public static String getSwndFileName() {
        return swndFileName;
    }

    public static void setSwndFileName(String swndFileName) {
        Options.swndFileName = swndFileName;
    }

    public static String getFtpAddress() {
        return ftpAddress;
    }

    public static void setFtpAddress(String ftpAddress) {
        Options.ftpAddress = ftpAddress;
    }

    public static String getFtpLogin() {
        return ftpLogin;
    }

    public static void setFtpLogin(String ftpLogin) {
        Options.ftpLogin = ftpLogin;
    }

    public static String getFtpPassword() {
        return ftpPassword;
    }

    public static void setFtpPassword(String ftpPassword) {
        Options.ftpPassword = ftpPassword;
    }

    public static String getMySqlAddress() {
        return mySqlAddress;
    }

    public static void setMySqlAddress(String mySqlAddress) {
        Options.mySqlAddress = mySqlAddress;
    }

    public static String getMySqlPort() {
        return mySqlPort;
    }

    public static void setMySqlPort(String mySqlPort) {
        Options.mySqlPort = mySqlPort;
    }

    public static String getMySqlDatabaseName() {
        return mySqlDatabaseName;
    }

    public static void setMySqlDatabaseName(String mySqlDatabaseName) {
        Options.mySqlDatabaseName = mySqlDatabaseName;
    }

    public static String getMySqlUser() {
        return mySqlUser;
    }

    public static void setMySqlUser(String mySqlUser) {
        Options.mySqlUser = mySqlUser;
    }

    public static String getMySqlPassword() {
        return mySqlPassword;
    }

    public static void setMySqlPassword(String mySqlPassword) {
        Options.mySqlPassword = mySqlPassword;
    }

    public static List<String> getDepartmentsList() {
        return departmentsList;
    }

    public static void setDepartmentsList(List<String> departmentsList) {
        Options.departmentsList = departmentsList;
    }

    //общие настройки
    private static String exchangePlacePath;
    private static String swndFileName;
    //список отделов
    private static List<String> departmentsList = new ArrayList<>();
    //настройки FTP
    private static String ftpAddress;
    private static String ftpLogin;
    private static String ftpPassword;
    //настройки MySql
    private static String mySqlAddress;
    private static String mySqlPort;
    private static String mySqlDatabaseName;
    private static String mySqlUser;
    private static String mySqlPassword;
}
