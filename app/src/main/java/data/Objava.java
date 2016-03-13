package data;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Kerman on 2.3.2016.
 */
public class Objava {
    public SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
    //some strings are placeholders for classes
    Lokacija kje;
    Date kdaj;
    Oseba kdo;
    ArrayList<Oseba> vsi;
    String naslov;
    String opis;

    //Setters & getters
    public Lokacija getKje() {
        return kje;
    }
    public void setKje(Lokacija kje) {
        this.kje = kje;
    }
    public Date getKdaj() {
        return kdaj;
    }
    public void setKdaj(Date kdaj) {
        this.kdaj = kdaj;
    }
    public Oseba getKdo() {
        return kdo;
    }
    public void setKdo(Oseba kdo) {
        this.kdo = kdo;
    }
    public ArrayList<Oseba> getVsi() {
        return vsi;
    }
    public void setVsi(ArrayList<Oseba> vsi) {
        this.vsi = vsi;
    }
    public String getNaslov() {
        return naslov;
    }
    public void setNaslov(String naslov) {
        this.naslov = naslov;
    }
    public String getOpis() {
        return opis;
    }
    public void setOpis(String opis) {
        this.opis = opis;
    }
    public void addVsi(Oseba oseba){
        vsi.add(oseba);
    }

    //Basic constructor
    public Objava(Lokacija kje, Date kdaj, Oseba kdo, String naslov, String opis) {
        this.kje = kje;
        this.kdaj = kdaj;
        this.kdo = kdo;
        this.vsi = new ArrayList<Oseba>();
        this.naslov = naslov;
        this.opis = opis;
    }
    public Objava(){
        this.kje = new Lokacija();
        this.kdaj = new Date();
        this.kdo = new Oseba();
        this.vsi = new ArrayList<Oseba>();
        this.naslov = "";
        this.opis = "";
    }

    @Override
    public String toString() {
        return "Objava:" +
                "\nkje=" + kje +
                "\nkdaj='" + dateFormat.format(kdaj) + '\'' +
                "\nkdo=" + kdo +
                "\nvsi=" + vsi +
                "\nnaslov='" + naslov + '\'' +
                "\nopis='" + opis + '\'';
    }

    public void DodajOsebo(Oseba os){
        vsi.add(os);
    }
}
