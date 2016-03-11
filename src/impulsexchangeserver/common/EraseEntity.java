package impulsexchangeserver.common;

import java.util.ArrayList;
import java.util.List;

public class EraseEntity {

    public EraseEntity() {
        this.deletionLinesList = new ArrayList<>();
        this.keepLinesList = new ArrayList<>();
    }

    public EraseEntity(String departmentName) {
        this.deletionLinesList = new ArrayList<>();
        this.keepLinesList = new ArrayList<>();
        this.departmentName = departmentName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public List<String> getKeepLinesList() {
        return keepLinesList;
    }

    public void setKeepLinesList(List<String> keepOrdersList) {
        this.keepLinesList = keepOrdersList;
    }

    public List<String> getDeletionLinesList() {
        return deletionLinesList;
    }

    public void setDeletionLinesList(List<String> deletionLinesList) {
        this.deletionLinesList = deletionLinesList;
    }

    private String departmentName;
    private List<String> keepLinesList;
    private List<String> deletionLinesList;
}
