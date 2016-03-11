package impulsexchangeserver.monitor;

import javax.swing.table.DefaultTableModel;

public class ExtendedDefaultTableModel extends DefaultTableModel {

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}
