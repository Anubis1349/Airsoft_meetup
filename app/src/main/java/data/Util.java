package data;

import android.os.Environment;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * Created by Kerman on 2.3.2016.
 */
public class Util {
    public static final String IME_DAT="data.airsoft";

    public static void Shrani(Global glob){
        try {
            File file = new File(Environment.getExternalStorageDirectory() + "/" + IME_DAT);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            PrintWriter pw = new PrintWriter(file);
            pw.println(gson.toJson(glob)); //a is my object
            pw.close();
        } catch (IOException e) {
            System.out.println("Error save team!");
        }
    }
    public static Global Naloži(){
        Global glob = new Global();
        try {
            Gson gson = new Gson();
            File myFile = new File(Environment.getExternalStorageDirectory() + "/" + IME_DAT);
            FileInputStream fIn = new FileInputStream(myFile);
            BufferedReader myReader = new BufferedReader(new InputStreamReader(fIn));
            String aDataRow = "";
            String aBuffer = ""; //Holds the text
            while ((aDataRow = myReader.readLine()) != null) {
                aBuffer += aDataRow;
            }
            myReader.close();
            glob = gson.fromJson(aBuffer, Global.class);
            return glob;
            //Toast.makeText(this, user.toString(), Toast.LENGTH_LONG).show();
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
        return glob;
    }

    public static int Search_name(Global glob, String name){


        for(int i = 0; i < glob.osebe.size(); i++){
            if(glob.osebe.get(i).getIme().equals(name)) {
                return i;
            }
        }
        return -1;

    }

    public static boolean Login(Global glob, String usr, String pass){
        int id = Search_name(glob, usr);

        if(id == -1)
            System.out.println("User does not exist");
        else{
            System.out.println("Enter password: ");
            if(glob.osebe.get(id).getPriimek().equals(pass)) {
                return true;
            }
            else
                return false;
        }
        return false;
    }
}
