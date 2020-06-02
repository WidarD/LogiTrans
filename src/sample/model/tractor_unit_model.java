package sample.model;

import java.util.Date;

public class tractor_unit_model {
    public int idTractor_unit;
    public String registration_number;
    public Date status;
    public Date technical_inspection;
    public Date insurance;
    public Date tachograph_legalization;

    public tractor_unit_model(int idTractor_unit, String registration_number, Date status, Date technical_inspection, Date insurance, Date tachograph_legalization) {
        this.idTractor_unit = idTractor_unit;
        this.registration_number = registration_number;
        this.status = status;
        this.technical_inspection = technical_inspection;
        this.insurance = insurance;
        this.tachograph_legalization = tachograph_legalization;
    }

    public int getIdTractor_unit() {
        return idTractor_unit;
    }

    public void setIdTractor_unit(int idTractor_unit) {
        this.idTractor_unit = idTractor_unit;
    }

    public String getRegistration_number() {
        return registration_number;
    }

    public void setRegistration_number(String registration_number) {
        this.registration_number = registration_number;
    }

    public Date getStatus() {
        return status;
    }

    public void setStatus(Date status) {
        this.status = status;
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

    public Date getTachograph_legalization() {
        return tachograph_legalization;
    }

    public void setTachograph_legalization(Date tachograph_legalization) {
        this.tachograph_legalization = tachograph_legalization;
    }
}
