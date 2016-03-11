package impulsexchangeserver.common;

import impulsexchangeserver.ImpulsExchangeServer;
import impulsexchangeserver.MainFrame;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Formatter;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

public class Service {

    public static void restartApplication(MainFrame mainFrame) {
        mainFrame.dispose();
        ImpulsExchangeServer.main(new String[0]);
    }

    public static DefaultListModel convertToModel(List<String> list) {
        DefaultListModel result = new DefaultListModel();
        if (!list.isEmpty()) {
            for (String str : list) {
                result.addElement(str);
            }
        }
        return result;
    }

    public static List<String> convertToList(DefaultListModel dm) {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < dm.getSize(); i++) {
            result.add(dm.get(i).toString());
        }
        return result;
    }

    public static String encode(String normalText) {
        char[] encodedChars = new char[normalText.length()];
        for (int i = 0; i < normalText.length(); i++) {
            encodedChars[i] = (char) ((byte) normalText.charAt(i) + 5);
        }
        return String.valueOf(encodedChars);
    }

    public static String decode(String xmlText) {
        char[] decodedChars = new char[xmlText.length()];
        for (int i = 0; i < xmlText.length(); i++) {
            decodedChars[i] = (char) ((byte) xmlText.charAt(i) - 5);
        }
        return String.valueOf(decodedChars);
    }

    public static void streamClose(InputStream in) {
        try {
            if (in != null) {
                in.close();
            }
        } catch (IOException ex) {
            showExceptionDlg(ex);
        }
    }

    public static void streamClose(OutputStream out) {
        try {
            if (out != null) {
                out.close();
            }
        } catch (IOException ex) {
            showExceptionDlg(ex);
        }
    }

    public static void streamClose(BufferedReader reader) {
        try {
            if (reader != null) {
                reader.close();
            }
        } catch (IOException ex) {
            showExceptionDlg(ex);
        }
    }

    private static void showExceptionDlg(IOException ex) {
        JOptionPane.showMessageDialog(null, "Ошибка освобождения ресурсов (stream.close()). \r\n"
                + "ex: " + ex, "Service : StreamClose", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Преобразуем дату (calendar) в формат необходимый для SQL-запросов.
     *
     * @param c Дата, представленная, как объект Calendar.
     * @return String - строка вида: "гггг-мм-дд". Например, для даты '23 ноября
     * 2015 года' будет возвращена строка: "2015-11-23".
     */
    public static String convertToSqlDate(Calendar c) {
        return new Formatter().format("%1$tY-%1$tm-%1$td", c).toString();
    }
}
