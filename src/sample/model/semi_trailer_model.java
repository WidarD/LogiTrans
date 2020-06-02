package sample.model;

import java.util.Date;

public class semi_trailer_model {
    public int idSemi_trailer;
    public String registration_number;
    public Date technical_inspection;
    public Date insurance;
    public Date veterinary_approval;
    public Date status;

    public semi_trailer_model(int idSemi_trailer, String registration_number, Date technical_inspection, Date insurance, Date veterinary_approval, Date status) {
        this.idSemi_trailer = idSemi_trailer;
        this.registration_number = registration_number;
        this.technical_inspection = technical_inspection;
        this.insurance = insurance;
        this.veterinary_approval = veterinary_approval;
        this.status = status;
    }

    public int getIdSemi_trailer() {
        return idSemi_trailer;
    }

    public void setIdSemi_trailer(int idSemi_trailer) {
        this.idSemi_trailer = idSemi_trailer;
    }

    public String getRegistration_number() {
        return registration_number;
    }

    public void setRegistration_number(String registration_number) {
        this.registration_number = registration_number;
    }

    public Date getTechnical_inspection() {
        return technical_inspection;
    }

    public void setTechnical_inspection(Date technical_inspection) {
        this.technical_inspection = technical_inspection;
    }

    public Date getInsurance() {
        return insurance;
    }

    public void setInsurance(Date insurance) {
        this.insurance = insurance;
    }

    public Date getVeterinary_approval() {
        return veterinary_approval;
    }

    public void setVeterinary_approval(Date veterinary_approval) {
        this.veterinary_approval = veterinary_approval;
    }

    public Date getStatus() {
        return status;
    }

    public void setStatus(Date status) {
        this.status = status;
    }
}
