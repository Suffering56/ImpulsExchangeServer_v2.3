package impulsexchangeserver.common;

import javax.swing.JCheckBox;

public class ChildEntity {

    public ChildEntity() {
    }

    public ChildEntity(String orderLine) {
        this.orderLine = orderLine;
    }

    public String getOrderLine() {
        return orderLine;
    }

    public void setOrderLine(String orderLine) {
        this.orderLine = orderLine;
    }

    public JCheckBox getChildBox() {
        return childBox;
    }

    public void setChildBox(JCheckBox childBox) {
        this.childBox = childBox;
    }

    private String orderLine;
    private JCheckBox childBox;
}
