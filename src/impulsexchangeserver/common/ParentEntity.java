package impulsexchangeserver.common;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JCheckBox;

public class ParentEntity {

    public ParentEntity() {
        this.childBoxList = new ArrayList<>();
    }

    public ParentEntity(JCheckBox parentBox) {
        this.childBoxList = new ArrayList<>();
        this.parentBox = parentBox;
    }

    public JCheckBox getParentBox() {
        return parentBox;
    }

    public void setParentBox(JCheckBox parentBox) {
        this.parentBox = parentBox;
    }

    public List<ChildEntity> getChildBoxList() {
        return childBoxList;
    }

    public void setChildBoxList(List<ChildEntity> childBoxList) {
        this.childBoxList = childBoxList;
    }

    private JCheckBox parentBox;
    private List<ChildEntity> childBoxList;
}
