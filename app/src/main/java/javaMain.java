import data.*;
import com.google.gson.*;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 * Created by Kerman on 2.3.2016.
 */
public class javaMain {

    public static Global glob = new Global();
    public static  Scanner scan = new Scanner(System.in);
    public static int n = 0;
    public static Oseba logged = new Oseba();
    //test
    public static boolean login = false;
    public static SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

    public static final String IME_DAT="data.airsoft";

    public static void Shrani(Global glob){
        File file = new File(IME_DAT);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        PrintWriter pw = null;
        try
        {
            pw = new PrintWriter(file);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

        pw.println(gson.toJson(glob));
        pw.close();
        System.out.println("Save complete");
    }
    public static Global Naloži(){
        Global glob = new Global();
        try {
            File file2 = new File(IME_DAT);
            FileInputStream fstream = new FileInputStream(file2);
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            StringBuffer sb = new StringBuffer();
            String strLine;

            while ((strLine = br.readLine()) != null)
                sb.append(strLine).append('\n');

            Gson gson2 = new GsonBuilder().setPrettyPrinting().create();
            glob = gson2.fromJson(sb.toString(), Global.class);

            if (glob == null)
                System.out.println("Error: fromJson Format error");
            else {
                System.out.println("Load complete");
//                System.out.println(glob.osebe + "\n\n" + glob.objave + "\n\n" + glob.lokacije);
            }

        }
        catch (IOException e) {
            System.out.println("Error load Team");
        }
        return glob;
    }
    public static void New_Oseba(){
        System.out.println("Enter user name: \n");
        String usr_name = scan.nextLine();

        System.out.println("Enter user password: ");
        String usr_pass = scan.nextLine();

        Oseba os = new Oseba(usr_name, usr_pass);
        try {
            glob.osebe.add(os);
        }
        catch (NullPointerException ex){
            System.out.println(ex.getMessage());
        }
    }
    public static void New_Lokacija(){
        System.out.println("Enter City: ");
        String mesto = scan.nextLine();
        System.out.println("Enter Region/State: ");
        String regija = scan.nextLine();
        System.out.println("Enter Country: ");
        String drzava = scan.nextLine();

        Lokacija lok = new Lokacija(mesto, regija, drzava);
        glob.lokacije.add(lok);
    }
    public static void New_Objava(){
        System.out.flush();
        System.out.println("Location");
        System.out.println("Use existing location?");
        System.out.println("1: Yes");
        System.out.println("2: No (create new)");
        Lokacija temp_lok = new Lokacija();

        if(Integer.parseInt(scan.nextLine()) == 1)
            temp_lok = Search_Location();
        else {
            New_Lokacija();
            temp_lok = glob.lokacije.get(glob.lokacije.size()-1);
        }

        System.out.flush();
        System.out.println("Date");
        System.out.println("Enter date (dd.mm.yyyy): ");
        String temp_date = scan.nextLine();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyy");
        Date temp_date2 = new Date();
        try {
            temp_date2 = dateFormat.parse(temp_date);
        }
        catch (ParseException e){
            System.out.println(e.getMessage());
        }


        System.out.flush();
        System.out.println("Title");
        System.out.println("Enter the title: ");
        String temp_nas = scan.nextLine();

        System.out.flush();
        System.out.println("Description");
        System.out.println("Enter the description: ");
        String temp_opis = scan.nextLine();

        Objava objava = new Objava(temp_lok, temp_date2, logged, temp_nas, temp_opis);
        glob.objave.add(objava);
    }
    public static int Search_name(String name){


        for(int i = 0; i < glob.osebe.size(); i++){
            if(glob.osebe.get(i).getIme().equals(name)) {
                return i;
            }
        }
        return -1;

    }
    public static Lokacija Search_Location(){
        System.out.println("Enter country: ");
        String temp_drz = scan.nextLine();
        System.out.println("Enter region/state");
        String temp_reg = scan.nextLine();

        ArrayList<Lokacija> temp_list = new ArrayList<Lokacija>();

        System.out.flush();
        int index = 1;
        for(int i = 0; i < glob.lokacije.size(); i++){
            if(glob.lokacije.get(i).getDrzava().equals(temp_drz) && glob.lokacije.get(i).getRegija().equals(temp_reg)) {
                temp_list.add(glob.lokacije.get(i));
                System.out.println("ID: " + index + "; " + glob.lokacije.get(i).getDrzava() + ", " + glob.lokacije.get(i).getRegija() + ", " + glob.lokacije.get(i).getMesto() + "\n");
                index++;
            }
        }
        System.out.println("Select location ID");

        return temp_list.get(Integer.parseInt(scan.nextLine())-1);
    }
    public static void Search_Objava(){
        System.out.println("Enter date (dd.mm.yyyy): ");
        String temp_dat = scan.nextLine();
        Date temp_dat2 = new Date();

        try {
            temp_dat2 = dateFormat.parse(temp_dat);
        }
        catch (ParseException e){
            System.out.println(e.getMessage());
        }

        ArrayList<Objava> temp_list = new ArrayList<>();

        System.out.flush();
        int index = 1;
        for(int i = 0; i < glob.objave.size(); i++){
            if(glob.objave.get(i).getKdaj().equals(temp_dat2)) {
                temp_list.add(glob.objave.get(i));
                System.out.println("ID: " + index + "\nDate: " + dateFormat.format(glob.objave.get(i).getKdaj()) + "\nTitle: " + glob.objave.get(i).getNaslov() + "\n");
                index++;
            }
        }
        System.out.println("\n");
        System.out.println("Press Return/Enter to go back to Main Menu");
        scan.nextLine();
    }
    public static void Search_Objava_ByDate(){
        System.out.println("From (dd.mm.yyyy): ");
        String temp1 = scan.nextLine();
        Date temp_dat = new Date();
        try {
            temp_dat = dateFormat.parse(temp1);
        }
        catch (ParseException e){
            System.out.println(e.getMessage());
        }

        System.out.println("To (dd.mm.yyyy): ");
        String temp2 = scan.nextLine();
        Date temp_dat2 = new Date();
        try {
            temp_dat2 = dateFormat.parse(temp2);
        }
        catch (ParseException e){
            System.out.println(e.getMessage());
        }

        ArrayList<Objava> temp_list = new ArrayList<>();

        System.out.flush();
        int index = 1;
        for(int i = 0; i < glob.objave.size(); i++){
            if((glob.objave.get(i).getKdaj().equals(temp_dat) || glob.objave.get(i).getKdaj().after(temp_dat)) && (glob.objave.get(i).getKdaj().equals(temp_dat2) || glob.objave.get(i).getKdaj().before(temp_dat2))) {
                temp_list.add(glob.objave.get(i));
                System.out.println("ID: " + index + "\nDate: " + glob.objave.get(i).getKdaj() + "\nTitle: " + glob.objave.get(i).getNaslov() + "\n");
                index++;
            }
        }
        System.out.println("\n");
        System.out.println("Press Return/Enter to go back to Main Menu");
        scan.nextLine();
    }
    public static void Show_Events(){
        int choice = 0;
        while (choice !=10) {
            for (int i = 0; i < glob.objave.size(); i++) {
                System.out.println("ID: " + i + "\nDate: " + dateFormat.format(glob.objave.get(i).getKdaj()) + "\nTitle: " + glob.objave.get(i).getNaslov() + "\n");
//            System.out.println("ID: " + i + "\n" + glob.objave.get(i).toString() + "\n");
            }
            System.out.println("1-10: Select Event");
            System.out.println("0: Back");
            System.out.println("-->");
            choice = Integer.parseInt(scan.nextLine());

            if(choice > 0 && choice < glob.objave.size()){
                int tmp = choice - 1;
                System.out.println(glob.objave.get(choice-1).toString() + "\n");
                System.out.println("Go to Event?");
                System.out.println("1: Yes");
                System.out.println("2: No");
                System.out.println("-->");
                choice = Integer.parseInt(scan.nextLine());

                if(choice == 1)
                    glob.objave.get(tmp).addVsi(logged);
                else
                    choice = -1;
            }


        }
    }


    public static void Login_Menu(){
        System.out.flush();
        System.out.println("---Airsoft Meetup---");
        System.out.println("1: Log in");
        System.out.println("2: New user");
        System.out.println("3: Exit");
        System.out.println("-->");
        n = Integer.parseInt(scan.nextLine());

        if(n == 1){
            System.out.flush();

            System.out.println("Enter user name: ");
            int id = Search_name(scan.nextLine());

            if(id == -1)
                System.out.println("User does not exist");
            else{
                System.out.println("Enter password: ");
                if(glob.osebe.get(id).getPriimek().equals(scan.nextLine())) {
                    logged = glob.osebe.get(id);
                    login=true;
                }
                else
                    System.out.println("Invalid password");
            }
        }
        if(n == 2){
            New_Oseba();
            logged = glob.osebe.get(glob.osebe.size()-1);
            login=true;
        }
    }
    public static void Main_Menu(){
        System.out.flush();
        System.out.println("---COMMAND LINE INTERFACE---");
        System.out.println("Logged in as: " + logged.getIme());
        System.out.println("1: Add User");
        System.out.println("2: Add Location");
        System.out.println("3: Add Event");
        System.out.println("4: Show Events");
        System.out.println("5: Search Events by date");
        System.out.println("6: Log out");
        System.out.println("->");
        int choice = Integer.parseInt(scan.nextLine());

        switch (choice){
            case 1:
                New_Oseba();
                break;
            case 2:
                New_Lokacija();
                break;
            case 3:
                New_Objava();
                break;
            case 4:
                Show_Events();
                break;
            case 5:
                Search_Objava_ByDate();
                break;
            case 6:
                login=false;
                break;
        }

    }

    public static void main(String []arg){
//        glob = new Global();

//        Oseba os1 = new Oseba("kerman", "test");
//        glob.osebe.add(os1);

        glob = Naloži();
        while(n != 3) {
            System.out.flush();
            while (!login) {
                Login_Menu();
                if(n==3)
                    break;
            }
            System.out.flush();
            while (login) {
                Main_Menu();
            }
        }
        Shrani(glob);

    }
}
