import java.sql.Timestamp;

public class Zadanie {
    private String user;
    private String projekt;
    private Timestamp data;
    private String zadanie;
    private double czas;

    public String getUser() {return user;}
    public void setUser(String user) {
        this.user = user;
    }

    public String getProjekt() {
        return projekt;
    }
    public void setProjekt(String projekt) {
        this.projekt = projekt;
    }

    public Timestamp getData() {
        return data;
    }
    public void setData(Timestamp data) {
        this.data = data;
    }

    public String getZadanie() {
        return zadanie;
    }
    public void setZadanie(String zadanie) {
        this.zadanie = zadanie;
    }

    public double getCzas() {
        return czas;
    }
    public void setCzas(double czas) {
        this.czas = czas;
    }


}
