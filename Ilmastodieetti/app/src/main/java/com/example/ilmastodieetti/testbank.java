package com.example.ilmastodieetti;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.widget.Spinner;

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
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class testbank {


/* TestBank class-object is used to handle most methods regarding tests and interaction with interfaces. Tesbank is also used to store and create objects of three kinds of tests.
Questionaire object contains various attributes of a new test. OldTest object stores information of old testresults. Weighttrack object stores information of daily weight added in the daily updates  */

    private String name = "testbank";
    private final ArrayList<Questionaire_data> questionaire;
    private final ArrayList<OldTests> OldTestArraylist;
    private final ArrayList<weighttracker>weighttrack;

    private static testbank instance = new testbank();
    Context context;





    private testbank() {

        questionaire = new ArrayList<>();
        OldTestArraylist = new ArrayList<>();
        weighttrack = new ArrayList<>();




    }

    public static testbank getInstance(){

        return instance;
    }

    public void setContext(Context c){

        context = c;


    }

    //Creating new questionaire object, adding the take number given by getQuestionaireTake method to the last object in the questionaire arraylist.

    public void newQuestionaire(Integer ta,String us, String d, boolean l, Integer b, Integer f, Integer p,
                                Integer da, Integer c, Integer r, Integer e, Integer w, Integer Sp) {

        questionaire.add(new Questionaire_data(ta, us, d, l, b, f, p, da, c, r, e, w, Sp));


        questionaire.get(questionaire.size()-1).settakes(getQuestionaireTake(us));


    }

    //Creating new OldTest object

    public void addOldTests(String t, String d, String un, String r){

        OldTestArraylist.add(new OldTests(t,d,un,r));

    }

    //Creating new weightTracker object, similar to method newQuestionaire

    public void addWeightTracker(String un, Integer we, String da, Integer ta){

        weighttrack.add(new weighttracker(un,we,da,ta));


        weighttrack.get(weighttrack.size()-1).setTake(getWeightestTake(un));

    }


    //Writing csv from weight tests, getting objects from weight track arraylist

    public void SaveWeightTest() {


        try {


            OutputStreamWriter ows = new OutputStreamWriter(context.openFileOutput("Weighttests.csv", Context.MODE_PRIVATE));

            StringBuilder usersstring = new StringBuilder();

            for (int i = 0; i < this.weighttrack.size(); i++) {

                weighttracker weightTracker = this.weighttrack.get(i);


                usersstring.append(weightTracker.getUsername());
                usersstring.append(",");
                usersstring.append(weightTracker.getWeight());
                usersstring.append(",");
                usersstring.append(weightTracker.getDate());
                usersstring.append(",");
                usersstring.append(weightTracker.getTake());
                usersstring.append("\n");

            }

            String s = usersstring.toString();


            ows.write(s);
            ows.close();


        } catch (IOException e) {
            Log.e("IOException", "Virhe syötteessä");

        } finally {
            System.out.println("kirjoitettu");


        }
    }


    //Creating string array of all weights in weight test history, by username

    public String[] WeightProgression (String un){

        List<String> oList = new ArrayList<String>();

        for (weighttracker weighttracker : weighttrack) {
            if(weighttracker.getUsername().equals(un)){
                oList.add(String.valueOf(weighttracker.getWeight()));
            }
        }
        String[] weightarray = oList.toArray(new String[oList.size()]);

        return weightarray;

    }

    //Creating string array of all times corrresponding a weight-take, by username

    public String[] WeightProgressionTaketime (String un){

        List<String> oList = new ArrayList<String>();

        for (weighttracker weighttracker : weighttrack) {
            if(weighttracker.getUsername().equals(un)){
                oList.add(String.valueOf(weighttracker.getDate()));
            }
        }
        String[] datearray = oList.toArray(new String[oList.size()]);

        return datearray;

    }

    //Creating string array of all indexes corrresponding a weight-take, by username

    public String[] WeightProgressionTakeIndex (String un){

        List<String> oList = new ArrayList<String>();

        for (weighttracker weighttracker : weighttrack) {
            if(weighttracker.getUsername().equals(un)){
                oList.add(String.valueOf(weighttracker.getTake()));
            }
        }
        String[] takearray = oList.toArray(new String[oList.size()]);

        return takearray;

    }


    //Creating weighttrack objects with existing csv

    public void FillWeightTrackerArray(){

        //clearing weightrack arraylist

        weighttrack.clear();


        try{
            InputStream ins = context.openFileInput("Weighttests.csv");
            String s = "";

            BufferedReader br = new BufferedReader(new InputStreamReader(ins));

            while((s=br.readLine()) !=null){

             //adding attributes to weighttrack object from from a string array

                String[] values = s.split(",");

                this.addWeightTracker(values[0],Integer.parseInt(values[1]),values[2],Integer.parseInt(values[3]));



            }

            ins.close();


        }catch (IOException e){
            Log.e("IOException", "Virhe syötteessä");
        }


    }


    /*Calculates calorie consuption by avarage values for different sports kcal per hour
    this method could/should be expanded with precise values determined by users weight
     */

    public Float CalorieConsumtion(Integer rh, Integer rm, Integer ch, Integer cm, Integer wh, Integer wm, Integer gh, Integer gm, Integer jh, Integer jm){

    Float running = ((rh.floatValue()*60 + rm.floatValue())/60)*700;
    Float cycling = ((ch.floatValue()*60 + cm.floatValue())/60)*420;
    Float walking = ((wh.floatValue()*60 + wm.floatValue())/60)*500;
    Float gym = ((gh.floatValue()*60 + gm.floatValue())/60)*400;
    Float yoga = ((jh.floatValue()*60 + jm.floatValue())/60)*300;

    Float consumption = running+cycling+walking+gym+yoga;

    return consumption;

    }


    //Finding LATEST weight test take with known username

    public Integer getWeightestTake(String un) {

        int count = 0;

        for (int i = 0; i < weighttrack.size(); i++) {

            weighttracker weighttracker = weighttrack.get(i);
            if (weighttracker.getUsername().equals(un)) {

                count++;

            }

        }
        return count;
    }

    //Simple integer array

    public Integer[] AbstractArray(Integer x, Integer y){

        Integer[] nums = new Integer[x];

        for (Integer i = 0; i < nums.length; i++) {
            nums[i] = i + y;
        }
        return nums;

    }

    //Gets the result string from old testresult JSON data. (Inputs are JSON as a String and the wanted value as String)

    public String getOldTestResult(String r, String s){

        try {
        JSONObject jobject = null;

            jobject = new JSONObject(r);


        DecimalFormat numberFormat = new DecimalFormat("#.00");

        String qresult = numberFormat.format(Float.parseFloat(jobject.getString(s)));

       return qresult;



    } catch (JSONException e) {
        e.printStackTrace();
            return "Failed";

    }

    }

    //Creates an array of takes by username

    public String[] takeArray(String un) {

        List<String> oList = new ArrayList<String>();

        for (OldTests oldtest : OldTestArraylist) {
        if(oldtest.getUsername().equals(un)){
        oList.add(oldtest.getTake());
        }
        }
        String[] takearray = oList.toArray(new String[oList.size()]);

        return takearray;

    }



    //Creates an array of times by username

    public String[] taketimeArray(String un) {

        List<String> oList = new ArrayList<String>();

        for (OldTests oldtest : OldTestArraylist) {
            if(oldtest.getUsername().equals(un)){
                oList.add(oldtest.getDate());
            }
        }
        String[] datearray = oList.toArray(new String[oList.size()]);

        return datearray;
    }

    //Finds the index of a certain element in spinner

    public int getSpinnerIndex(Spinner spinner, String s) {

        for (int i = 0; i < spinner.getCount(); i++) {
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(s)) {
            return i;
            }
        }
        return 0;

    }


    //Finds take by time and username

   public String getTakebyDate(String un, String ti){

       String take = "";

       for (OldTests oldtest : OldTestArraylist) {
           if (oldtest.getUsername().equals(un) && oldtest.getDate().equals(ti)) {

               take = oldtest.getTake();
           }
       }
       return take;

   }

    //Finds weight test time by take number and username

    public String getWeightTestbyDate(String un, String ti){

        String take = "";

        for (weighttracker weighttracker : weighttrack) {
            if (weighttracker.getUsername().equals(un) && weighttracker.getDate().equals(ti)) {

                take = String.valueOf(weighttracker.getTake());
            }
        }
        return take;

    }

    //Finds weight test take by time and username

    public String getWeightTestbyTake(String un, String ti){

        String Date = "";

        for (weighttracker weighttracker : weighttrack) {
            if (weighttracker.getUsername().equals(un) && weighttracker.getTake()==(Integer.parseInt(ti))) {

                Date = weighttracker.getDate();
            }
        }
        return Date;

    }


    //Finds emissiontest date by username and take

    public String getDatebyTake(String un, String ti){

        String Date = "";

        for (OldTests oldtest : OldTestArraylist) {
            if (oldtest.getUsername().equals(un) && oldtest.getTake().equals(ti)) {

                Date = oldtest.getDate();
            }
        }
        return Date;

    }


    //Finds emissiontest take by username and date

    public String getEmissionResultbyTake(String un, String ta) {

        String testresult = "";

       for (OldTests oldtest : OldTestArraylist) {
           if (oldtest.getUsername().equals(un) && oldtest.getTake().equals(ta)) {

               testresult = oldtest.getResult();
           }

       }

       return testresult;
   }

    //Finds questionaire latest POSITION of a certain username

    public Integer getQuestionaireTake(String un) {

        int count = 0;

        for (int i = 0; i < questionaire.size(); i++) {

            Questionaire_data data = questionaire.get(i);
            if (data.getusername().equals(un)) {

                count++;

            }

        }
        return count;
    }


    //This is used for printing all questionarie variables to console

    public void printAll(){

        for (int i = 0; i < questionaire.size(); i++) {

            Questionaire_data data = questionaire.get(i);

            System.out.print(data.gettakes() + " takes , ");
            System.out.print(data.getusername() + " username , ");
            System.out.print(data.getdiet() + " diet , ");
            System.out.print(data.getlowCarbonPreference() + " lc pref , ");
            System.out.print(data.getbeefLevel() + " beef, ");
            System.out.print(data.getfishLevel() + " fish, ");
            System.out.print(data.getporkPoultryLevel() + " poultry , ");
            System.out.print(data.getdairyLevel() + " dairy, ");
            System.out.print(data.getcheeseLevel() + " cheese, ");
            System.out.print(data.getriceLevel() + " rice, ");
            System.out.print(data.geteggLevel() + " egg, ");
            System.out.print(data.getwinterSaladLevel() + " wintersalad, ");
            System.out.print(data.getrestaurantSpending() + " spending, ");
            System.out.println("  ");
            System.out.println("  ");


            }




    }


    // Returns value as an integer

    public Integer SpendingEuros (String s) {

        if (s.equals("10 Euros")) {
            return 10;
        } else {
            if (s.equals("50 Euros")) {
                return 50;
            } else {
                if (s.equals("100 Euros")) {
                    return 100;


                }else {
                    if (s.equals("150 Euros")) {
                        return 150;


                    }else {
                        if (s.equals("200 Euros")) {
                            return 200;


                        }else {
                            if (s.equals("300 Euros")) {
                                return 300;


                            }else {
                                if (s.equals("400 Euros")) {
                                    return 400;


                                }else {
                                    return 500;

                                }

                            }

                        }

                    }

                }

            }


        }
    }



    // Returns value as an integer

    public Integer Change (String c) {

        Integer not = 0;
        Integer minor = 25;
        Integer half = 50;
        Integer les = 75;
        Integer avr = 100;
        Integer more = 125;
        Integer large = 150;
        Integer tupla = 200;


    if(c.equals("Average consumption")){
    return avr;
    }
    else{
    if(c.equals("More than average")){
    return more;
    }
    else{
        if(c.equals("Half more to average")){
            return large;
        }
        else{
            if(c.equals("Double the average")){
                return tupla;
            }
            else{
                if(c.equals("Less than average")){
                    return les;
                }
                else{
                    if(c.equals("Half to average")){
                        return half;
                    }
                    else{
                        if(c.equals("Minor consumption")){
                            return minor;
                        }
                        else{
                      {
                                return not;}}}}}}}}


        }


    // Basic boolean

        public boolean yesNo(String n){

        if(n.equals("Yes")){return true;}
        else{
            return false;

        }

        }


    /*Saves results of Emissiontest as csv this method checks if the csv exists, if it doesn't (In the frist open of the program)it writes
      a new one. If it does it copies the existing material and writes some more and replaces the original this differs to my other methods */

    public void SaveResults(Integer t, String un, String result){



        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();

        try{
            InputStream ins = context.openFileInput("EmissionResults.csv");
            Scanner scan = new Scanner(new InputStreamReader(ins));



            StringBuilder builder = new StringBuilder();

            //read existing material and write a string with a stringbuilder

            while(scan.hasNextLine()){
            builder.append(scan.nextLine());
                builder.append("\n");

            }

            ins.close();

            //add the new test

            builder.append(t);
            builder.append(";");
            builder.append(formatter.format(date));
            builder.append(";");
            builder.append(un);
            builder.append(";");
            builder.append(result);



            OutputStreamWriter ows = new OutputStreamWriter(context.openFileOutput("EmissionResults.csv", Context.MODE_PRIVATE));

            ows.write(builder.toString());
            ows.close();

            //console testing

            System.out.println(builder.toString());



        }catch (IOException e){
            Log.e("IOException", "Tyhjä");
            try{

                //write on an empty

                OutputStreamWriter ows = new OutputStreamWriter(context.openFileOutput("EmissionResults.csv", Context.MODE_PRIVATE));

                StringBuilder builder2 = new StringBuilder();

                builder2.append(t);
                builder2.append(";");
                builder2.append(formatter.format(date));
                builder2.append(";");
                builder2.append(un);
                builder2.append(";");
                builder2.append(result);




                String s = builder2.toString();

                System.out.println(s);

                ows.write(s);
                ows.close();

                System.out.println(builder2.toString());



            } catch (IOException f) {
                Log.e("IOException", "Virhe syötteessä");

            }finally   {
                System.out.println("kirjoitettu");



            }

            //important! add objects to arraylist with FillOldTestArray method

            this.FillOldTestArray();


        }


    }



    // This writes a completely new csv from the taken test answers from object

    public void SaveTest(){



        try{


            OutputStreamWriter ows = new OutputStreamWriter(context.openFileOutput("tests.csv", Context.MODE_PRIVATE));

            StringBuilder usersstring = new StringBuilder();

            for (int i = 0; i < this.questionaire.size(); i++) {

                Questionaire_data data = this.questionaire.get(i);


                usersstring.append(data.gettakes());
                usersstring.append(",");
                usersstring.append(data.getusername());
                usersstring.append(",");
                usersstring.append(data.getdiet());
                usersstring.append(",");
                usersstring.append(data.getlowCarbonPreference());
                usersstring.append(",");
                usersstring.append(data.getbeefLevel());
                usersstring.append(",");
                usersstring.append(data.getfishLevel());
                usersstring.append(",");
                usersstring.append(data.getporkPoultryLevel());
                usersstring.append(",");
                usersstring.append(data.getdairyLevel());
                usersstring.append(",");
                usersstring.append(data.getcheeseLevel());
                usersstring.append(",");
                usersstring.append(data.getriceLevel());
                usersstring.append(",");
                usersstring.append(data.geteggLevel());
                usersstring.append(",");
                usersstring.append(data.getwinterSaladLevel());
                usersstring.append(",");
                usersstring.append(data.getrestaurantSpending());
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

    // This one creates Questionaire objects from existing csv

    public void FillTestArray() {


        questionaire.clear();

        try{
            InputStream ins = context.openFileInput("tests.csv");
            String s = "";

            BufferedReader br = new BufferedReader(new InputStreamReader(ins));

            while((s=br.readLine()) !=null){

                String[] values = s.split(",");

                //Iterating through list in an array form, adding objects attributes from array values

                this.newQuestionaire(Integer.parseInt(values[0]), values[1], values[2], Boolean.parseBoolean(values[3]),
                        Integer.parseInt(values[4]), Integer.parseInt(values[5]), Integer.parseInt(values[6]), Integer.parseInt(values[7]),
                        Integer.parseInt(values[8]), Integer.parseInt(values[9]), Integer.parseInt(values[10]), Integer.parseInt(values[11]), Integer.parseInt(values[12]));






            }

            ins.close();


        }catch (IOException e){
            Log.e("IOException", "Virhe syötteessä");
        }



    }

    // Similar to previous, now filling the old test arraylist from EmissionResults csv

    public void FillOldTestArray(){

        OldTestArraylist.clear();


        try{
            InputStream ins = context.openFileInput("EmissionResults.csv");
            String s = "";

            BufferedReader br = new BufferedReader(new InputStreamReader(ins));

            while((s=br.readLine()) !=null){

                String[] values = s.split(";");

                this.addOldTests(values[0],values[1],values[2],values[3]);






            }

            ins.close();


        }catch (IOException e){
            Log.e("IOException", "Virhe syötteessä");
        }



    }






    }
