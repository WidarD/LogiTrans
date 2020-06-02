package sample.model;

import java.sql.Date;
import java.sql.Time;

public class route_model {
    public int idRoute;
    public Date loading_date;
    public Time loading_hour;
    public Date unloading_date;
    public Time unloading_hour;



    public route_model(int idRoute, Date loading_date, Time loading_hour, Date unloading_date, Time unloading_hour) {
        this.idRoute = idRoute;
        this.loading_date = loading_date;
        this.loading_hour = loading_hour;
        this.unloading_date = unloading_date;
        this.unloading_hour = unloading_hour;
    }

    public int getIdRoute() {
        return idRoute;
    }

    public void setIdRoute(int idRoute) {
        this.idRoute = idRoute;
    }

    public Date getLoading_date() {
        return loading_date;
    }

    public void setLoading_date(Date loading_date) {
        this.loading_date = loading_date;
    }

    public Time getLoading_hour() {
        return loading_hour;
    }

    public void setLoading_hour(Time loading_hour) {
        this.loading_hour = loading_hour;
    }

    public Date getUnloading_date() {
        return unloading_date;
    }

    public void setUnloading_date(Date unloading_date) {
        this.unloading_date = unloading_date;
    }

    public Time getUnloading_hour() {
        return unloading_hour;
    }

    public void setUnloading_hour(Time unloading_hour) {
        this.unloading_hour = unloading_hour;
    }

}
