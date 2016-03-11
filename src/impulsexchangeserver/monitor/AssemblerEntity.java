package impulsexchangeserver.monitor;

public class AssemblerEntity {

    public AssemblerEntity() {
    }

    public AssemblerEntity(String name, int idBrig) {
        this.assemblerName = name;
        this.idBrig = idBrig;
    }

    public String getAssemblerName() {
        return assemblerName;
    }

    public void setAssemblerName(String assemblerName) {
        this.assemblerName = assemblerName;
    }

    public int getIdBrig() {
        return idBrig;
    }

    public void setIdBrig(int idBrig) {
        this.idBrig = idBrig;
    }

    private String assemblerName;
    private int idBrig;
}
