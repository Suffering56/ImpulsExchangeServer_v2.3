package impulsexchangeserver.options;

import impulsexchangeserver.common.Service;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class OptionsHandler {

    /**
     * Инициализация всех объектов необходимых, для дальнейшей работы с XML.
     * Должна быть выполнена при запуске программы. В противном случае не будут
     * работать все остальные функции класса OptionsHandler
     */
    public static void init() throws SAXException, IOException, ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        XPathFactory xpfactory = XPathFactory.newInstance();
        xPath = xpfactory.newXPath();
        doc = builder.parse(xmlFile);
    }

    /**
     * Сохраняет измененния настроек в XML-файле "options.xml"
     *
     * @param switchParam Параметр, указывающий на то, какой именно раздел опций
     * нужно сохранить в XML файле. Значения: Common, FTP, MySQL
     */
    public static void saveOptions(String switchParam) {
        try {
            switch (switchParam) {
                case "Common":
                    saveCommonOptions();
                    saveDepartmentsList();
                    break;
                case "FTP":
                    saveFtpOptions();
                    break;
                case "MySQL":
                    saveMySqlOptions();
                    break;
            }

            FileOutputStream out = new FileOutputStream(xmlFile);
            TransformerFactory.newInstance().newTransformer().transform(new DOMSource(doc), new StreamResult(out));
            Service.streamClose(out);
        } catch (DOMException | IOException | TransformerException | XPathExpressionException ex) {
            JOptionPane.showMessageDialog(null, "Ошибка при сохранении настроек программы: \r\n"
                    + "ex: " + ex, "OptionsHandler : saveOptions()", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void replaceNode(Element section, String nodePath, String value) throws XPathExpressionException {
        Node oldNode = (Node) xPath.evaluate(nodePath, doc, XPathConstants.NODE);
        Node newNode = oldNode.cloneNode(true);
        newNode.setTextContent(value);
        section.replaceChild(newNode, oldNode);
    }

    private static void saveCommonOptions() throws XPathExpressionException {
        Element section = (Element) xPath.evaluate("Options/Common", doc, XPathConstants.NODE);
        String nodePath = "Options/Common/ExchangePlacePath";   //not encode
        replaceNode(section, nodePath, Options.getExchangePlacePath());
        nodePath = "Options/Common/SwndFileName";
        replaceNode(section, nodePath, Options.getSwndFileName());
    }

    private static void extractDepartmentsList() throws XPathExpressionException {
        Element section = (Element) xPath.evaluate("Options/DepartmentsList", doc, XPathConstants.NODE);
        NodeList nodeList = section.getChildNodes();
        Options.getDepartmentsList().clear();
        int index = 0;
        for (int i = 0; i < nodeList.getLength(); i++) {
            if (nodeList.item(i) instanceof Element) {
                Element item = (Element) nodeList.item(i);
                if (item.getTextContent().trim().length() != 0) {
                    Options.getDepartmentsList().add(index, item.getTextContent());
                    index++;
                }
            }
        }
    }

    private static void saveFtpOptions() throws XPathExpressionException {
        Element section = (Element) xPath.evaluate("Options/FTP", doc, XPathConstants.NODE);
        String nodePath = "Options/FTP/Address";    //encode
        replaceNode(section, nodePath, Service.encode(Options.getFtpAddress()));
        nodePath = "Options/FTP/Login";
        replaceNode(section, nodePath, Service.encode(Options.getFtpLogin()));
        nodePath = "Options/FTP/Password";
        replaceNode(section, nodePath, Service.encode(Options.getFtpPassword()));
    }

    private static void saveMySqlOptions() throws XPathExpressionException {
        Element section = (Element) xPath.evaluate("Options/MySQL", doc, XPathConstants.NODE);
        String nodePath = "Options/MySQL/Address";  //encode
        replaceNode(section, nodePath, Service.encode(Options.getMySqlAddress()));
        nodePath = "Options/MySQL/Port";
        replaceNode(section, nodePath, Service.encode(Options.getMySqlPort()));
        nodePath = "Options/MySQL/DatabaseName";
        replaceNode(section, nodePath, Service.encode(Options.getMySqlDatabaseName()));
        nodePath = "Options/MySQL/User";
        replaceNode(section, nodePath, Service.encode(Options.getMySqlUser()));
        nodePath = "Options/MySQL/Password";
        replaceNode(section, nodePath, Service.encode(Options.getMySqlPassword()));
    }

    private static void saveDepartmentsList() throws XPathExpressionException {
        Element oldChild = (Element) xPath.evaluate("Options/DepartmentsList", doc, XPathConstants.NODE);
        Element newChild = doc.createElement("DepartmentsList");
        for (int i = 0; i < Options.getDepartmentsList().size(); i++) {
            Element item = doc.createElement("item" + i);
            item.setTextContent(Options.getDepartmentsList().get(i));
            newChild.appendChild(item);
        }
        doc.getDocumentElement().replaceChild(newChild, oldChild);
    }

    /**
     * Чтение настроек, хранящихся в файле "options.xml" и их дальнейшая
     * установка в качестве значений полей класса "Options".
     */
    public static void readOptions() {
        try {
            init();
            //Чтение общих настроек (not decode)
            Node currentNode = (Node) xPath.evaluate("Options/Common/ExchangePlacePath", doc, XPathConstants.NODE);
            Options.setExchangePlacePath(currentNode.getTextContent());
            currentNode = (Node) xPath.evaluate("Options/Common/SwndFileName", doc, XPathConstants.NODE);
            Options.setSwndFileName(currentNode.getTextContent());

            //Чтение настроек FTP (decode)
            currentNode = (Node) xPath.evaluate("Options/FTP/Address", doc, XPathConstants.NODE);
            Options.setFtpAddress(Service.decode(currentNode.getTextContent()));
            currentNode = (Node) xPath.evaluate("Options/FTP/Login", doc, XPathConstants.NODE);
            Options.setFtpLogin(Service.decode(currentNode.getTextContent()));
            currentNode = (Node) xPath.evaluate("Options/FTP/Password", doc, XPathConstants.NODE);
            Options.setFtpPassword(Service.decode(currentNode.getTextContent()));

            //Чтение настроек MySQL (decode)
            currentNode = (Node) xPath.evaluate("Options/MySQL/Address", doc, XPathConstants.NODE);
            Options.setMySqlAddress(Service.decode(currentNode.getTextContent()));
            currentNode = (Node) xPath.evaluate("Options/MySQL/Port", doc, XPathConstants.NODE);
            Options.setMySqlPort(Service.decode(currentNode.getTextContent()));
            currentNode = (Node) xPath.evaluate("Options/MySQL/DatabaseName", doc, XPathConstants.NODE);
            Options.setMySqlDatabaseName(Service.decode(currentNode.getTextContent()));
            currentNode = (Node) xPath.evaluate("Options/MySQL/User", doc, XPathConstants.NODE);
            Options.setMySqlUser(Service.decode(currentNode.getTextContent()));
            currentNode = (Node) xPath.evaluate("Options/MySQL/Password", doc, XPathConstants.NODE);
            Options.setMySqlPassword(Service.decode(currentNode.getTextContent()));

            //Чтение списка отделов
            extractDepartmentsList();

        } catch (XPathExpressionException | ParserConfigurationException | SAXException | IOException | DOMException | NullPointerException ex) {
            JOptionPane.showMessageDialog(null, "Ошибка чтения настроек программы: \r\n"
                    + "ex: " + ex, "OptionsHandler : readOptions()", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static XPath xPath;
    private static Document doc;
    private static final File xmlFile = new File(System.getProperty("user.dir") + File.separator + "options.xml");
}
