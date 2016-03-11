package impulsexchangeserver.common;

import java.util.ArrayList;
import java.util.List;

public class CurrentDepartment {

    public CurrentDepartment() {
        this.ordersList = new ArrayList<>();
    }

    public CurrentDepartment(String departmentName) {
        this.ordersList = new ArrayList<>();
        this.departmentName = departmentName;
    }

    public List<String> getOrdersList() {
        return ordersList;
    }

    public void setOrdersList(List<String> ordersList) {
        this.ordersList = ordersList;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    private String departmentName;
    private List<String> ordersList;
}
