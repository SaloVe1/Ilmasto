package com.example.ilmastodieetti;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import cz.msebera.android.httpclient.Header;


public class Questionaire extends AppCompatActivity {



    Spinner Dietspinner;
    Spinner Carbonspinner;
    Spinner SpinnerBeef;
    Spinner SpinnerFish;
    Spinner SpinnerPork;
    Spinner SpinnerDairy;
    Spinner SpinnerCheese;
    Spinner SpinnerRice;
    Spinner SpinnerEgg;
    Spinner SpinnerWinter;
    Spinner RestaurantSpending;
    Button TestSubmit;
    Button Back;
    TextView Tulokset;
    private final ArrayList<String> choose = new ArrayList<String>();
    private final ArrayList<String>diet = new ArrayList<String>();
    private final ArrayList<String>spending = new ArrayList<String>();
    private final ArrayList<String>yesno = new ArrayList<String>();


    Context context = null;
    userbank bank = userbank.getInstance();
    testbank tester = testbank.getInstance();
    CurrentUser current = CurrentUser.getInstance();
    TextView messageboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionaire);
        context = getApplicationContext();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Dietspinner = (Spinner) findViewById(R.id.Dietspinner);
        Carbonspinner = (Spinner) findViewById(R.id.CARBON);
        SpinnerBeef = (Spinner) findViewById(R.id.SpinnerBeef);
        SpinnerFish = (Spinner) findViewById(R.id.SpinnerFish);
        SpinnerPork = (Spinner) findViewById(R.id.SpinnerPork);
        SpinnerDairy = (Spinner) findViewById(R.id.SpinnerDairy);
        SpinnerCheese = (Spinner) findViewById(R.id.SpinnerCheese);
        SpinnerRice = (Spinner) findViewById(R.id.SpinnerRice);
        SpinnerEgg = (Spinner) findViewById(R.id.SpinnerEgg);
        SpinnerWinter = (Spinner) findViewById(R.id.SpinnerWinter);
        RestaurantSpending = (Spinner) findViewById(R.id.RestaurantSpending);
        TestSubmit = (Button) findViewById(R.id.TestSubmit);
        Back = (Button) findViewById(R.id.back);
        Tulokset = (TextView) findViewById(R.id.tulokset);


        choose.add("Not part of diet");
        choose.add("Minor consumption");
        choose.add("Half to average");
        choose.add("Less than average");
        choose.add("Average consumption");
        choose.add("More than average");
        choose.add("Half more to average");
        choose.add("Double the average");

        diet.add("omnivore");
        diet.add("vegan");
        diet.add("vegetarian");


        spending.add("10 Euros");
        spending.add("50 Euros");
        spending.add("100 Euros");
        spending.add("150 Euros");
        spending.add("200 Euros");
        spending.add("300 Euros");
        spending.add("400 Euros");
        spending.add("500 Euros");

        yesno.add("Yes");
        yesno.add("no");


        //Adapters are filled with above innitialized arraylists

        ArrayAdapter<String> Diet = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,diet);
        Diet.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Dietspinner.setAdapter(Diet);

        ArrayAdapter<String> Carbon = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,yesno);
        Carbon.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Carbonspinner.setAdapter(Carbon);

        ArrayAdapter<String> basics = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,choose);
        basics.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpinnerBeef.setAdapter(basics);

        SpinnerFish.setAdapter(basics);
        SpinnerPork.setAdapter(basics);
        SpinnerDairy.setAdapter(basics);
        SpinnerCheese.setAdapter(basics);
        SpinnerRice.setAdapter(basics);
        SpinnerEgg.setAdapter(basics);
        SpinnerWinter.setAdapter(basics);

        ArrayAdapter<String> spender = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,spending);
        spender.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        RestaurantSpending.setAdapter(spender);




        bank.setContext(getApplicationContext());
        tester.setContext(getApplicationContext());

        messageboard = (TextView) findViewById(R.id.MessageBoard);


        messageboard.setText("Welcome. This calculates the emission estimate in kg CO2 eq");
        tester.FillTestArray();








    }


    // Creates new test more detailed explanation ->

    public void NewTestCreate(View v) {

        messageboard.setTextColor(Color.GREEN);
        messageboard.setText("Creating and Saving...wait");

        //added a delay in order to prevent user from taking a test "every second", essentially because every test has its own date/time attribute

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

        //declaring test input values

        Integer takes = 0;
        String user = current.getUsername();
        boolean preference = tester.yesNo(Carbonspinner.getSelectedItem().toString());
        String diet = Dietspinner.getSelectedItem().toString();
        Integer beeflevel = tester.Change(SpinnerBeef.getSelectedItem().toString());
        Integer fishlevel = tester.Change(SpinnerFish.getSelectedItem().toString());
        Integer porklevel = tester.Change(SpinnerPork.getSelectedItem().toString());
        Integer dairylevel = tester.Change(SpinnerDairy.getSelectedItem().toString());
        Integer cheeselevel = tester.Change(SpinnerCheese.getSelectedItem().toString());
        Integer ricelevel = tester.Change(SpinnerRice.getSelectedItem().toString());
        Integer egglevel = tester.Change(SpinnerEgg.getSelectedItem().toString());
        Integer winterlevel = tester.Change(SpinnerWinter.getSelectedItem().toString());
        Integer spending = tester.SpendingEuros(RestaurantSpending.getSelectedItem().toString());

        //creating new questionaire object adding declared values as class attributes

        tester.newQuestionaire(takes, user, diet, preference, beeflevel, fishlevel, porklevel, dairylevel, cheeselevel, ricelevel, egglevel, winterlevel, spending);

        //Save tests creates a csv file of the taken test

        tester.SaveTest();

        //Prints to console

        tester.printAll();


        //Using API to calculate consumptions



                String url = "https://ilmastodieetti.ymparisto.fi/ilmastodieetti/calculatorapi/v1/FoodCalculator?query.diet=" +
                        diet +
                        "&query.lowCarbonPreference=" + preference +
                        "&query.beefLevel=" + beeflevel +
                        "&query.fishLevel=" + fishlevel +
                        "&query.porkPoultryLevel=" + porklevel +
                        "&query.dairyLevel=" + dairylevel +
                        "&query.cheeseLevel=" + cheeselevel +
                        "&query.riceLevel=" + ricelevel +
                        "&query.eggLevel=" + egglevel +
                        "&query.winterSaladLevel=" + winterlevel +
                        "&query.restaurantSpending=" + spending;

                AsyncHttpClient client = new AsyncHttpClient();

                client.get(url , new TextHttpResponseHandler() {
                            @Override
                            public void onSuccess(int statusCode, Header[] headers, String res) {



                                String resultString = res;
                                System.out.println(resultString);

                                //Saving results as csv with save results. The JSON as a whole as one attribute

                                tester.SaveResults(tester.getQuestionaireTake(user),user,resultString);

                                try{

                                JSONObject jobject = new JSONObject(resultString);
                                StringBuilder print = new StringBuilder();

                                //Creating string of JSON object to print on the screen

                                DecimalFormat numberFormat = new DecimalFormat("#.00");

                                print.append("Dairy level: " + numberFormat.format(Float.parseFloat(jobject.getString("Dairy"))) + "kg CO2 eq");
                                print.append("\n");
                                print.append("\n");
                                print.append("Meat level: " + numberFormat.format(Float.parseFloat(jobject.getString("Meat"))) + "kg CO2 eq");
                                print.append("\n");
                                print.append("\n");
                                print.append("Plant level: " + numberFormat.format(Float.parseFloat(jobject.getString("Plant"))) + "kg CO2 eq");
                                print.append("\n");
                                print.append("\n");
                                print.append("Restaurant level: " + numberFormat.format(Float.parseFloat(jobject.getString("Restaurant"))) + "kg CO2 eq");
                                print.append("\n");
                                print.append("\n");
                                print.append("Total level: " + numberFormat.format(Float.parseFloat(jobject.getString("Total"))) + "kg CO2 eq");
                                print.append("\n");
                                print.append("\n");


                                Tulokset.setText(print.toString());


                                messageboard.setTextColor(Color.RED);
                                messageboard.setText("Test calculated and saved");


                            } catch (JSONException e) {
                        e.printStackTrace();
                    } finally{
                    System.out.println("Read");
                    }

                            }

                            @Override
                            public void onFailure(int statusCode, Header[] headers, String res, Throwable t) {
                                    System.out.println("Not working");
                            }
                        }
                );


    }
}, 1000);



    }
    public void Back(View v){

        Intent intent = new Intent(Questionaire.this,MainActivity.class);


        startActivity(intent);





    }










}