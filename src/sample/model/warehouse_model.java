package sample.model;

public class warehouse_model {
    public int idWarehouse;
    public String street;
    public String postal_code;
    public String location;
    public int id_counterparty;

    public warehouse_model(int idWarehouse, String street, String postal_code, String location, int id_counterparty) {
        this.idWarehouse = idWarehouse;
        this.street = street;
        this.postal_code = postal_code;
        this.location = location;
        this.id_counterparty = id_counterparty;
    }

    public int getIdWarehouse() {
        return idWarehouse;
    }

    public void setIdWarehouse(int idWarehouse) {
        this.idWarehouse = idWarehouse;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getId_counterparty() {
        return id_counterparty;
    }

    public void setId_counterparty(int id_counterparty) {
        this.id_counterparty = id_counterparty;
    }
}
