package impulsexchangeserver;

import impulsexchangeserver.common.ChildEntity;
import impulsexchangeserver.common.CurrentDepartment;
import impulsexchangeserver.common.EraseEntity;
import impulsexchangeserver.common.ParentEntity;
import impulsexchangeserver.ftp.FTPUpdateLauncher;
import impulsexchangeserver.history.LocalFilesHandler;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;

public class FramePrint extends JFrame {

    public FramePrint(MainFrame mainFrame, List<CurrentDepartment> doPrintList) {
        this.eraseEntityList = new ArrayList<>();
        this.parentEntityList = new ArrayList<>();
        this.doPrintList = doPrintList;
        this.mainFrame = mainFrame;
        initComponents();
        setLocationRelativeTo(null);
        mainFrame.setEnabled(false);
    }

    private void initComponents() {
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent evt) {
                mainFrame.setEnabled(true);
            }
        });
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setTitle("Подтверждение обмена");
        this.setResizable(false);
        this.setLayout(null);
        
        globalPanel = new JPanel();
        globalPanel.setPreferredSize(new Dimension(WINDOW_WIDTH, 10));
        globalPanel.setLayout(null);
        this.add(globalPanel);

        yGlobal = 0;
        parentEntityList.clear();
        //инциализация элементов parentBox и childBox
        for (int i = 0; i < doPrintList.size(); i++) {
            initializeDepartmentPanel(i);
        }
        //инициализация остальных элементов: scrollPane, exitBtn, completeBtn
        initOtherComponents(yGlobal);
        this.add(exitBtn);
        this.add(completeBtn);
        this.repaint();
        mainFrame.setEnabled(false);
    }

    private void initializeDepartmentPanel(int i) {
        JPanel localPanel = new JPanel();
        localPanel.setLayout(null);
        localPanel.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
        int yLocal = ELEMENT_PADDING;

        //====================добавляем элемент parentBox====================
        JCheckBox parentBox = new JCheckBox(doPrintList.get(i).getDepartmentName());
        parentBox.setFocusPainted(false);
        parentBox.setSize(85, ELEMENT_HEIGHT);
        parentBox.setLocation(5, yLocal);
        parentBox.setActionCommand(String.valueOf(i));
        parentBox.addActionListener(this::parentBoxActionPerformed);
        yLocal += ELEMENT_HEIGHT + ELEMENT_PADDING;
        localPanel.add(parentBox);

        ParentEntity parentEntity = new ParentEntity();
        parentEntity.setParentBox(parentBox);
        //====================добавляем элементы childBox====================
        for (int j = 0; j < doPrintList.get(i).getOrdersList().size(); j++) {
            JCheckBox childBox = new JCheckBox(extractOrderName(doPrintList.get(i).getOrdersList().get(j)));
            childBox.setFocusPainted(false);
            childBox.setSize(260, ELEMENT_HEIGHT);
            childBox.setLocation(25, yLocal);
            yLocal += ELEMENT_HEIGHT + ELEMENT_PADDING;
            localPanel.add(childBox);

            ChildEntity childEntity = new ChildEntity();
            childEntity.setOrderLine(doPrintList.get(i).getOrdersList().get(j));
            childEntity.setChildBox(childBox);
            parentEntity.getChildBoxList().add(childEntity);
        }
        parentEntityList.add(parentEntity);
        //===================================================================

        localPanel.setSize(WINDOW_WIDTH, yLocal);
        localPanel.setLocation(0, yGlobal);
        yGlobal += yLocal + ELEMENT_PADDING;
        globalPanel.add(localPanel);
    }

    private void initOtherComponents(int yGlobal) {
        exitBtn = new JButton("Отмена");
        exitBtn.setFocusPainted(false);
        exitBtn.setFont(new Font("Times New Roman", 0, 14));
        exitBtn.setSize(115, ELEMENT_HEIGHT);
        exitBtn.addActionListener(this::exitBtnActionPerformed);

        completeBtn = new JButton("Завершить обмен");
        completeBtn.setFocusPainted(false);
        completeBtn.setFont(new Font("Times New Roman", 1, 14));
        completeBtn.setSize(155, ELEMENT_HEIGHT);
        completeBtn.addActionListener(this::completeBtnActionPerformed);

        if (yGlobal > WINDOW_MAX_HEIGHT) {
            scrollPane = new JScrollPane(globalPanel);
            scrollPane.setVerticalScrollBar(new JScrollBar() {
                @Override
                public int getUnitIncrement(int direction) {
                    return 25;
                }
            });
            this.add(scrollPane, null);
            scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

            globalPanel.setSize(new Dimension(WINDOW_WIDTH, yGlobal - ELEMENT_PADDING));
            globalPanel.setPreferredSize(new Dimension(globalPanel.getWidth(), globalPanel.getHeight()));
            scrollPane.setSize(globalPanel.getWidth() + 19, WINDOW_MAX_HEIGHT);
            this.setSize(scrollPane.getWidth() + 16, scrollPane.getHeight() + 60);

            exitBtn.setLocation(BUTTONS_PADDING, WINDOW_MAX_HEIGHT + ELEMENT_PADDING);
            completeBtn.setLocation(BUTTONS_PADDING * 2 + exitBtn.getWidth(), WINDOW_MAX_HEIGHT + ELEMENT_PADDING);
        } else {
            if (yGlobal == 0) {
                yGlobal = ELEMENT_PADDING;
            }
            globalPanel.setSize(new Dimension(WINDOW_WIDTH, yGlobal - ELEMENT_PADDING));
            this.setSize(globalPanel.getWidth() + 16, globalPanel.getHeight() + 60);
            exitBtn.setLocation(BUTTONS_PADDING, yGlobal);
            completeBtn.setLocation(BUTTONS_PADDING * 2 + exitBtn.getWidth(), yGlobal);
        }
    }

    private void completeBtnActionPerformed(ActionEvent evt) {
        EraseEntityListInitialize();
        new FTPUpdateLauncher(eraseEntityList).start();
        printListModify();
        if (!eraseEntityList.isEmpty()) {
            LocalFilesHandler.updatePreviousExchangeArchive(eraseEntityList);
            /**
             * Убираем оповещение о новых заказах
             */
            mainFrame.endNotification();
        }
        mainFrame.setEnabled(true);
        this.dispose();
    }

    private void EraseEntityListInitialize() {
        for (ParentEntity parent : parentEntityList) {
            EraseEntity eraseEntity = selectionChildCheck(parent);
            if (!eraseEntity.getDeletionLinesList().isEmpty()) {
                eraseEntityList.add(eraseEntity);
            }
        }
    }

    private EraseEntity selectionChildCheck(ParentEntity parent) {
        EraseEntity eraseEntity = new EraseEntity(parent.getParentBox().getText());
        for (ChildEntity child : parent.getChildBoxList()) {
            if (child.getChildBox().isSelected()) {
                eraseEntity.getDeletionLinesList().add(child.getOrderLine());
            } else {
                eraseEntity.getKeepLinesList().add(child.getOrderLine());
            }
        }
        return eraseEntity;
    }

    private void printListModify() {
        List<CurrentDepartment> tempRemovalList = new ArrayList<>();
        for (CurrentDepartment currentDep : doPrintList) {
            for (EraseEntity eraseEntity : eraseEntityList) {
                if (currentDep.getDepartmentName().equals(eraseEntity.getDepartmentName())) {
                    if (eraseEntity.getKeepLinesList().isEmpty()) {
                        tempRemovalList.add(currentDep);
                    } else {
                        currentDep.getOrdersList().removeAll(eraseEntity.getDeletionLinesList());
                    }
                }
            }
        }
        doPrintList.removeAll(tempRemovalList);
    }

    /**
     * Событие вызываемое нажатием на родительский элемент parentBox.
     * Устанавливает во все дочерние элементы (childBox) значения равные
     * значению родителя.
     *
     * @param evt
     */
    private void parentBoxActionPerformed(ActionEvent evt) {
        int i = Integer.valueOf(evt.getActionCommand());
        for (ChildEntity childEntity : parentEntityList.get(i).getChildBoxList()) {
            childEntity.getChildBox().setSelected(parentEntityList.get(i).getParentBox().isSelected());
        }
    }

    private String extractOrderName(String str) {
        Matcher m = EXTRACT_ORDER_NAME_PATTERN.matcher(str);
        if (m.matches()) {
            return m.group(1);
        } else {
            return "Error: <" + str + ">";
        }
    }

    private void exitBtnActionPerformed(ActionEvent evt) {
        mainFrame.setEnabled(true);
        this.dispose();
    }

    private final MainFrame mainFrame;
    private JButton completeBtn, exitBtn;
    private JScrollPane scrollPane;
    private JPanel globalPanel;
    private int yGlobal;
    // Объявление констант    
    private static final int WINDOW_MAX_HEIGHT = 600;                           //Максимальная высота окна PrintFrame (на случай если заказов будет слишком много)
    private static final int WINDOW_WIDTH = 290;                                //Стандартная ширина окна PrintFrame
    private static final int ELEMENT_HEIGHT = 23;                               //Стандартная высота элементов (JButton, JCheckBox)
    private static final int ELEMENT_PADDING = 3;                               //Стандартный отступ между элементами
    private static final int BUTTONS_PADDING = 10;
    private static final Pattern EXTRACT_ORDER_NAME_PATTERN = Pattern.compile(
            "(\\d+/\\d+)\\p{Space}+(\\d+.\\d+.\\d+)\\p{Space}(\\d+:\\d+:\\d+)");
    // Объявление остальных переменных
    private final List<CurrentDepartment> doPrintList;
    private final List<ParentEntity> parentEntityList;
    private final List<EraseEntity> eraseEntityList;
}
