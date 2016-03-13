package data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Kerman on 2.3.2016.
 */
public class Util {
    public static final String IME_DAT="data.airsoft";

    public void Shrani(Global glob){
        try {
            File file = new File(IME_DAT);
            file.getParentFile().mkdirs();
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            PrintWriter pw = new PrintWriter(file);
            pw.println(gson.toJson(glob)); //a is my object
            pw.close();
        } catch (IOException e) {
            System.out.println("Error save team!");
        }
    }
}
