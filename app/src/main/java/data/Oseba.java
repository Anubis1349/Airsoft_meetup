package data;

/**
 * Created by Kerman on 2.3.2016.
 */
public class Oseba {
    String ime; //will change to usr_name
    String priimek; //will change to usr_pass

    //Setters & getters
    public String getIme() {
        return ime;
    }
    public void setIme(String ime) {
        this.ime = ime;
    }
    public String getPriimek() {
        return priimek;
    }
    public void setPriimek(String priimek) {
        this.priimek = priimek;
    }

    //Basic constructors
    public Oseba(String ime, String priimek) {
        this.ime = ime;
        this.priimek = priimek;
    }
    public Oseba(){
        this.ime = "";
        this.priimek = "";
    }

    @Override
    public String toString() {
        return "Oseba{" +
                "ime='" + ime + '\'' +
                ", priimek='" + priimek + '\'' +
                '}';
    }
}
