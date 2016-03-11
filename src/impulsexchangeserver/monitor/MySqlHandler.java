package impulsexchangeserver.monitor;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class MySqlHandler {

    public MySqlHandler() {
        mySqlInstance = MySqlConnector.getInstance();
        createStatement();
    }

    /**
     *
     * @param from переключатель. Определяет на основе каких данных выполнять
     * запрос. Если параметр = "SEARCH", то будет возвращен список заказов, чьи
     * <b>наименования (nz)</b> совпадают c именем указанном в параметре
     * <b>value</b>. Если параметр = "CALENDAR", то будет возвращен список
     * заказов, чья <b>дата монтажа (datam)</b> соответствует значению
     * <b>value</b>.
     * @param value параметр выборки для запросов к БД.
     * @return Список заказов, соответствующих указанным пользователем
     * параметрам.
     */
    public List<MainEntity> getMainQueryResult(String from, String value) {
        List<MainEntity> resultList = new ArrayList<>();
        try {
            ResultSet rs = null;
            switch (from) {
                case "SEARCH":
                    rs = statement.executeQuery("SELECT * FROM `jos_zakazi` WHERE "
                            + "`nz` LIKE '%" + value + "%'");
                    break;
                case "CALENDAR":
                    rs = statement.executeQuery("SELECT * FROM `jos_zakazi` WHERE "
                            + "`datam` = '" + value + "'");
                    break;
            }
            if (rs != null) {
                while (rs.next()) {
                    String nz = rs.getString("nz");
                    String datam = rs.getString("datam");
                    String accessoriesStatus = convertStatus(rs.getInt("gk"));
                    String productionStatus = convertStatus(rs.getInt("g"));
                    resultList.add(new MainEntity(nz, datam, accessoriesStatus, productionStatus));
                }
            }
        } catch (SQLException ex) {
            resultList = null;
            JOptionPane.showMessageDialog(null, "Ошибка при работе с базой данных MySQL. \r\n"
                    + "ex: " + ex, this.getClass().getName() + " : getQueryResult()", JOptionPane.ERROR_MESSAGE);
        }
        return resultList;
    }

    public List<AssemblerEntity> getAssemblersQueryResult() {
        List<AssemblerEntity> resultList = new ArrayList<>();
        try {
            ResultSet rs = statement.executeQuery("SELECT * FROM `jos_assemblers` ORDER BY `jos_assemblers`.`num_brig` DESC");
            while (rs.next()) {
                String name = rs.getString("assembler_name");
                int id = rs.getInt("num_brig");
                resultList.add(new AssemblerEntity(name, id));
            }
        } catch (SQLException ex) {
            resultList = null;
            JOptionPane.showMessageDialog(null, "Ошибка при работе с базой данных MySQL. \r\n"
                    + "ex: " + ex, this.getClass().getName() + " : getAssemblers()", JOptionPane.ERROR_MESSAGE);
        }
        return resultList;
    }

    public boolean deleteOrder(String nz) {
        try {
            int result = statement.executeUpdate("DELETE FROM `jos_zakazi` "
                    + "WHERE `nz` LIKE '" + nz + "'");
            return result > 0;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ошибка при работе с базой данных MySQL. \r\n"
                    + "Не удалось удалить заказ <" + nz + ">"
                    + "ex: " + ex, this.getClass().getName() + " : deleteOrder()", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public boolean deleteAssembler(String assemblerName) {
        try {
            int result = statement.executeUpdate("DELETE FROM `jos_assemblers` "
                    + "WHERE `assembler_name` LIKE '" + assemblerName + "'");
            return result > 0;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ошибка при работе с базой данных MySQL. \r\n"
                    + "Не удалось удалить сборщика <" + assemblerName + ">"
                    + "ex: " + ex, this.getClass().getName() + " : deleteAssembler()", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public boolean addAssembler(String assemblerName, int id) {
        try {
            int result = statement.executeUpdate("INSERT INTO `jos_assemblers` "
                    + "(`num_brig`, `assembler_name`) VALUES "
                    + "('" + id + "', '" + assemblerName + "')");
            return result > 0;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ошибка при работе с базой данных MySQL. \r\n"
                    + "Не удалось добавить сборщика <" + assemblerName + ">"
                    + "ex: " + ex, this.getClass().getName() + " : deleteAssembler()", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    private void createStatement() {
        Connection connection = mySqlInstance.connect();
        statement = null;
        if (connection != null) {
            try {
                statement = connection.createStatement();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Ошибка при работе с базой данных MySQL. \r\n"
                        + "ex.toString(): " + ex, "MySqlViewHandler.createStatement()", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void closeConnection() {
        mySqlInstance.disconnect();
    }

    private String convertStatus(int value) {
        String newValue;
        if (value == 0) {
            newValue = "Нет";
        } else {
            newValue = "Да";
        }
        return newValue;
    }

    private final MySqlConnector mySqlInstance;
    private Statement statement;
}
