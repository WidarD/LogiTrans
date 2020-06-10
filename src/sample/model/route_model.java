package sample.model;

public class route_model {
    public int idRoute;
    public String city_start;
    public String city_stop;
    public int km;




    public route_model(int idRoute, String city_start, String city_stop, int km) {
        this.idRoute = idRoute;
        this.city_start = city_start;
        this.city_stop = city_stop;
        this.km = km;

    }

    public int getIdRoute() {
        return idRoute;
    }

    public void setIdRoute(int idRoute) {
        this.idRoute = idRoute;
    }

    public String getCity_start() {
        return city_start;
    }

    public void setCity_start(String city_start) {
        this.city_start = city_start;
    }

    public String getCity_stop() {
        return city_stop;
    }

    public void setCity_stop(String city_stop) {
        this.city_stop = city_stop;
    }

    public int getKm() {
        return km;
    }

    public void setKm(int km) {
        this.km = km;
    }
}
