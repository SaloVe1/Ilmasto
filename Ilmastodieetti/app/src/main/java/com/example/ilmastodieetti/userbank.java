package com.example.ilmastodieetti;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class userbank {

 /* Userbank is utilized to create new userprofile objects and city(Maakunta) objects
 Userbank contains and handles methods related to these objects and is used mainly in profile creation phase and login phase */


    private String name = "userbank";
    private final ArrayList<userprofile> users;
    private final ArrayList<CitiesAndIndexes> cities;
    private static userbank instance = new userbank();
    Context context;





    private userbank() {

        users = new ArrayList<>();
        cities = new ArrayList<>();

    }

    public static userbank getInstance(){

       return instance;
    }

    public void setContext(Context c){

    context = c;


    }

    //Create new user: Name, username, password, age, height, gender, city

    public void newUser(String n, String un, String p, int a, int h, String g, String c) {

        users.add(new userprofile(n, un, p, a, h, g, c));

    }

    //Create new city object. City, in this case Maakunta is taken from THL database. The corresponding ID is taken also from THL database

    public void newCity(String c, String i) {

        cities.add(new CitiesAndIndexes(c,i));

    }



    //Find profile index with username

    public int Findprofilebyname(String un) {

        for (int i = 0; i < users.size(); i++) {

            userprofile profile = users.get(i);
            if (profile.getUsername().equals(un)) {
                return i;
            }
        }
        return -1;

    }

    //Return true if profilename already exists

    public boolean Findprofiletruefalse (String un) {

        for (int i = 0; i < users.size(); i++) {

            userprofile profile = users.get(i);
            if (profile.getUsername().equals(un)) {
                return true;
            }
        }
        return false;

    }

    //Testing if the user input username and password correspond the password in user arraylist

    public String Passtest(String un, String p) {


        if (Findprofilebyname(un) != -1) {
            String salasana = users.get(Findprofilebyname(un)).getPassword();
            if (salasana.equals(p)) {
                return "OK";
            } else {
                return "Wrong";

            }
        } else {
        return "Not found";

        }
    }



    //Saving the generic user data as csv
    public void SaveUsers(){



        try{


            OutputStreamWriter ows = new OutputStreamWriter(context.openFileOutput("users.csv", Context.MODE_PRIVATE));

            StringBuilder usersstring = new StringBuilder();

            for (int i = 0; i < this.users.size(); i++) {

                //iterating the userarraylist, saving the different attributes with stringbuilder as string

                userprofile profile = this.users.get(i);


                usersstring.append(profile.getName());
                usersstring.append(",");
                usersstring.append(profile.getUsername());
                usersstring.append(",");
                usersstring.append(profile.getPassword());
                usersstring.append(",");
                usersstring.append(profile.getAge());
                usersstring.append(",");
                usersstring.append(profile.getHeight());
                usersstring.append(",");
                usersstring.append(profile.getGender());
                usersstring.append(",");
                usersstring.append(profile.getCity());
                usersstring.append("\n");

            }

            String s = usersstring.toString();

            System.out.println(s);

            ows.write(s);
            ows.close();


        } catch (IOException e) {
            Log.e("IOException", "Virhe syötteessä");

        }finally   {
            System.out.println("kirjoitettu");



        }


    }

    public void FillUserArray() {


        //filling the user arraylist with csv data

        users.clear();

        try{
            InputStream ins = context.openFileInput("users.csv");
            String s = "";

            BufferedReader br = new BufferedReader(new InputStreamReader(ins));

            while((s=br.readLine()) !=null){

                String[] values = s.split(",");

                //using array form to get specific attributes to each object

                this.newUser(values[0], values[1],values[2], Integer.parseInt(values[3]),Integer.parseInt(values[4]),values [5],values[6]);


            }

            ins.close();


        }catch (IOException e){
            Log.e("IOException", "Virhe syötteessä");
        }





        }


    /*Puting regions to stringarray, to be used in a spinner.
    This method first captures strings to an arraylist, then converts arraylist to string array.
    Perhaps not the most straightforward */

    public String[] Cityarray() {

        List<String> oList = new ArrayList<String>();

        for (CitiesAndIndexes city : cities) {
                oList.add(city.getCity());
            }

        String[] cityarray = oList.toArray(new String[oList.size()]);

        return cityarray;
    }

    //Simple string array of genders

    public String[] Genders(){

        String[] str = {"male", "female"};

        return str;
    }

    //Putting indexes in a string array. Same as CityArray.

    public String FindIndexByCity(String c){

        String indexes="";

        for (CitiesAndIndexes city : cities){
         if(city.getCity().equals(c))   {
         indexes = city.getIndexcode();
         }
        }
        return indexes;

    }



    // This method uses THL data to create City objects with corresponding Id numbers.

    public void FillCityArray(){

        String resultString = "";

        //executing http call

        try {
            URL url = new URL("https://www.sotkanet.fi/rest/1.1/regions");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            InputStream in = new BufferedInputStream(conn.getInputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            StringBuilder sb = new StringBuilder();

            String line = null;
            while((line = br.readLine()) !=null){
                sb.append(line).append("\n");

            }

            resultString = sb.toString();

            //finding JSONobject "id", and object within an object "title" called "fi"

            JSONArray jsonArray = new JSONArray(resultString);
            for(int i=0; i<jsonArray.length(); i++){
                JSONObject jobject = jsonArray.getJSONObject(i);

                if(jobject.getString("category").equals("MAAKUNTA")){

                    JSONObject townfi = jobject.getJSONObject("title");

                    this.newCity(townfi.getString("fi"), String.valueOf(jobject.getInt("id")));


                }

            }




            in.close();

        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally{

        }

    }




    //calculating the probability of smoking with using age and gender as parameters

    public String SmokingPropability(String un){

        String resultString = "";

        /* Originally functions purpose was to use all parameters, but since in the case of this indicator
        THL data has only parameters of gender and age, so parameter region is useless in case of this indicator*/

        String year = "2019";
        String gender = users.get(Findprofilebyname(un)).getGender();
        String city = users.get(Findprofilebyname(un)).getCity();
        String Id = FindIndexByCity(city);
        String Indicator = "";
        Integer age = users.get(Findprofilebyname(un)).getAge();

        //Changing indicator with age group

        if(age>=75){
         Indicator = "4407";
        }else{
        if(age>=20 && age<=64){
        Indicator = "4405";
        }else{
        if(age>=65 && age<75){
        Indicator = "4406";
        }
        else{
        Indicator = "0";
        }
        }
        }

        if (!Indicator.equals("0")) {

            //Executing search
            try {
                URL url = new URL("https://www.sotkanet.fi/rest/1.1/json?indicator=" + Indicator + "&years=" + year + "&genders=" + gender);

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                InputStream in = new BufferedInputStream(conn.getInputStream());
                BufferedReader br = new BufferedReader(new InputStreamReader(in));
                StringBuilder sb = new StringBuilder();

                String line = null;
                while ((line = br.readLine()) != null) {
                    sb.append(line).append("\n");

                }

                resultString = sb.toString();

                System.out.println(resultString);

                //returning the probability value as a string only one object, since only one value for the whole country

                JSONArray jsonArray = new JSONArray(resultString);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jobject = jsonArray.getJSONObject(i);

                        return jobject.getString("value");

                }


                in.close();

            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {

            }



        }

            return "Not studied yet";


    }









}



