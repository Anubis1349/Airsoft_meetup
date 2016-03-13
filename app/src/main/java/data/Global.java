package data;


import java.util.ArrayList;

/**
 * Created by Kerman on 2.3.2016.
 */
public class Global {
    public ArrayList<Oseba> osebe;
    public ArrayList<Objava> objave;
    public ArrayList<Lokacija> lokacije;

    //
    public Global() {
        this.osebe = new ArrayList<>();
        this.objave = new ArrayList<>();
        this.lokacije = new ArrayList<>();
    }
}
