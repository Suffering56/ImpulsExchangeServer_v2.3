package impulsexchangeserver;

import java.awt.AWTException;
import static java.awt.Frame.ICONIFIED;
import static java.awt.Frame.MAXIMIZED_BOTH;
import static java.awt.Frame.NORMAL;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class FlashingTray {

    public FlashingTray(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        mainFrame.setIconImage(normalIcon);
        init();
    }

    private void init() {
        if (SystemTray.isSupported()) {
            MenuItem openItem = new MenuItem("Развернуть");
            openItem.addActionListener((ActionEvent e) -> {
                mainFrame.setVisible(true);
                mainFrame.setExtendedState(JFrame.NORMAL);
            });
            MenuItem exitItem = new MenuItem("Выход");
            exitItem.addActionListener((ActionEvent e) -> {
                System.exit(0);
            });

            PopupMenu popup = new PopupMenu();
            popup.add(openItem);
            popup.add(exitItem);

            tray = new TrayIcon(normalIcon, tooltip, popup);
            tray.setImageAutoSize(true);
            tray.addActionListener(this::trayActionListener);
        } else {
            JOptionPane.showMessageDialog(null, "System tray not supported");
        }

        mainFrame.addWindowStateListener((WindowEvent e) -> {
            if ((e.getNewState() == ICONIFIED) || (e.getNewState() == 7)) {
                showTray();
                mainFrame.setVisible(false);
            }
            if ((e.getNewState() == MAXIMIZED_BOTH) || (e.getNewState() == NORMAL)) {
                hideTray();
            }
        });
    }

    public final void showTray() {
        try {
            boolean adding = true;
            tray.setImage(normalIcon);
            TrayIcon[] icons = SystemTray.getSystemTray().getTrayIcons();
            for (TrayIcon icon : icons) {
                if (icon.getToolTip().equals(tooltip)) {
                    adding = false;
                }
            }
            if (adding) {
                SystemTray.getSystemTray().add(tray);
            }
        } catch (AWTException ex) {
            JOptionPane.showMessageDialog(null, "Unable to add to tray\r\n"
                    + "ex: " + ex);
        }
    }

    public final void hideTray() {
        SystemTray.getSystemTray().remove(tray);
        mainFrame.setVisible(true);
    }

    private void trayActionListener(ActionEvent evt) {
        hideTray();
        mainFrame.setExtendedState(JFrame.NORMAL);
        mainFrame.updateCheckTimer.start();
    }

    public TrayIcon getTray() {
        return tray;
    }

    public Image getNormalIcon() {
        return normalIcon;
    }

    public Image getEmptyIcon() {
        return emptyIcon;
    }

    private final MainFrame mainFrame;
    private TrayIcon tray;
    private final Image normalIcon = Toolkit.getDefaultToolkit().getImage("icon.png");
    private final Image emptyIcon = Toolkit.getDefaultToolkit().getImage("");
    private static final String tooltip = "Impuls Exchange Server";
}
