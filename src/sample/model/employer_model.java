package sample.model;

public class employer_model {
    public int idEmployer;
    public String login;
    public String password;

    public employer_model(int idEmployer, String login, String password) {
        this.idEmployer = idEmployer;
        this.login = login;
        this.password = password;
    }

    public int getIdEmployer() {
        return idEmployer;
    }

    public void setIdEmployer(int idEmployer) {
        this.idEmployer = idEmployer;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
