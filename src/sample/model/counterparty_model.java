package sample.model;

public class counterparty_model {
    public int idCounterparty;
    public String name;
    public String address;
    public String tax_number;
    public String phone_number;

    public counterparty_model(int idCounterparty, String name, String address, String tax_number, String phone_number) {
        this.idCounterparty = idCounterparty;
        this.name = name;
        this.address = address;
        this.tax_number = tax_number;
        this.phone_number = phone_number;
    }


    public int getIdCounterparty() {
        return idCounterparty;
    }

    public void setIdCounterparty(int idCounterparty) {
        this.idCounterparty = idCounterparty;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTax_number() {
        return tax_number;
    }

    public void setTax_number(String tax_number) {
        this.tax_number = tax_number;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }
}
