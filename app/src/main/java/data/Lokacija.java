package data;

/**
 * Created by Kerman on 2.3.2016.
 */
public class Lokacija {
    String mesto;
    String regija;
    String drzava;

    //Setters & getters
    public String getMesto() {
        return mesto;
    }
    public void setMesto(String mesto) {
        this.mesto = mesto;
    }
    public String getRegija() {
        return regija;
    }
    public void setRegija(String regija) {
        this.regija = regija;
    }
    public String getDrzava() {
        return drzava;
    }
    public void setDrzava(String drzava) {
        this.drzava = drzava;
    }

    //Basic constructor
    public Lokacija(String mesto, String regija, String drzava) {
        this.mesto = mesto;
        this.regija = regija;
        this.drzava = drzava;
    }
    public  Lokacija(){
        this.mesto = "";
        this.regija = "";
        this.drzava = "";
    }

    @Override
    public String toString() {
        return "Lokacija{" +
                "mesto='" + mesto + '\'' +
                ", regija='" + regija + '\'' +
                ", drzava='" + drzava + '\'' +
                '}';
    }
}
