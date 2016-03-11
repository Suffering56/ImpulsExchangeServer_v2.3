package impulsexchangeserver.monitor;

public class MainEntity {

    /**
     *
     * @param orderName номер заказа (nz)
     * @param mountingDate дата монтажа (datam)
     * @param accessoriesStatus готовность комплектующих (gk)
     * @param productionStatus готовность производства (g)
     */
    public MainEntity(String orderName, String mountingDate, String accessoriesStatus, String productionStatus) {
        this.orderName = orderName;
        this.mountingDate = mountingDate;
        this.accessoriesStatus = accessoriesStatus;
        this.productionStatus = productionStatus;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getMountingDate() {
        return mountingDate;
    }

    public void setMountingDate(String mountingDate) {
        this.mountingDate = mountingDate;
    }

    public String getAccessoriesStatus() {
        return accessoriesStatus;
    }

    public void setAccessoriesStatus(String accessoriesStatus) {
        this.accessoriesStatus = accessoriesStatus;
    }

    public String getProductionStatus() {
        return productionStatus;
    }

    public void setProductionStatus(String productionStatus) {
        this.productionStatus = productionStatus;
    }

    private String orderName;
    private String mountingDate;
    private String accessoriesStatus;
    private String productionStatus;
}
