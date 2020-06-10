package sample.model;


import java.sql.Date;

public class worker_model {
    public int idWorker;
    public String name;
    public String surname;
    public String ID_number;
    public String phone_number;
    public Date driving_license;
    public Date sanitary_book;
    public Date driver_card;
    public Boolean OHS;
    public String registration_number_ST;
    public String registration_number_TU;

    public worker_model(int idWorker, String name, String surname, String ID_number, String phone_number, Date driving_license, Date sanitary_book, Date driver_card, Boolean OHS, String registration_number_ST, String registration_number_TU){
        this.idWorker = idWorker;
        this.name = name;
        this.surname = surname;
        this.ID_number = ID_number;
        this.phone_number = phone_number;
        this.driving_license = driving_license;
        this.sanitary_book = sanitary_book;
        this.driver_card = driver_card;
        this.OHS = OHS;
        this.registration_number_ST = registration_number_ST;
        this.registration_number_TU = registration_number_TU;

    }

    public int getIdWorker() {
        return idWorker;
    }

    public void setIdWorker(int idWorker) {
        this.idWorker = idWorker;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getID_number() {
        return ID_number;
    }

    public void setID_number(String ID_number) {
        this.ID_number = ID_number;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public Date getDriving_license() {
        return driving_license;
    }

    public void setDriving_license(Date driving_license) {
        this.driving_license = driving_license;
    }

    public Date getSanitary_book() {
        return sanitary_book;
    }

    public void setSanitary_book(Date sanitary_book) {
        this.sanitary_book = sanitary_book;
    }

    public Date getDriver_card() {
        return driver_card;
    }

    public void setDriver_card(Date driver_card) {
        this.driver_card = driver_card;
    }

    public Boolean getOHS() {
        return OHS;
    }

    public void setOHS(Boolean OHS) {
        this.OHS = OHS;
    }

    public String getRegistration_number_ST() {
        return registration_number_ST;
    }

    public void setRegistration_number_ST(String registration_number_ST) {
        this.registration_number_ST = registration_number_ST;
    }

    public String getRegistration_number_TU() {
        return registration_number_TU;
    }

    public void setRegistration_number_TU(String registration_number_TU) {
        this.registration_number_TU = registration_number_TU;
    }
}


