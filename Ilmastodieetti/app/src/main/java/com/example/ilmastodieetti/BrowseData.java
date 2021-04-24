package com.example.ilmastodieetti;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class BrowseData extends AppCompatActivity {

    Context context = null;
    userbank bank = userbank.getInstance();
    testbank tester = testbank.getInstance();
    CurrentUser current = CurrentUser.getInstance();
    Spinner CateGorySpinner;
    Spinner TakeSpinner;
    Spinner TestSpinner;

    Button GoButton;
    Button ReTurnToM;

    private final ArrayList<String>category = new ArrayList<String>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse_data);
        context = getApplicationContext();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        CateGorySpinner = (Spinner) findViewById (R.id.CateGorySpinner);
        TakeSpinner = (Spinner) findViewById (R.id.TakeSpinner);
        TestSpinner = (Spinner) findViewById (R.id.TestSpinner);

        GoButton = (Button) findViewById (R.id.GoButton);
        ReTurnToM = (Button) findViewById (R.id.ReturnToM);


        bank.setContext(getApplicationContext());
        tester.setContext(getApplicationContext());
        tester.FillOldTestArray();
        tester.FillTestArray();
        tester.FillWeightTrackerArray();

        category.add("Emission tests");
        category.add("Weight progression");


        ArrayAdapter<String> cateGory = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,category);
        cateGory.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        CateGorySpinner.setAdapter(cateGory);


        ArrayAdapter<String> Takes = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,tester.takeArray(current.getUsername()));
        Takes.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<String> WeightTakes = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,tester.WeightProgressionTakeIndex(current.getUsername()));
        WeightTakes.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        ArrayAdapter<String> Times = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,tester.taketimeArray(current.getUsername()));
        Times.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<String> WeightTimes = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,tester.WeightProgressionTaketime(current.getUsername()));
        WeightTimes.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        CateGorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            if(CateGorySpinner.getSelectedItem().toString().equals("Emission tests")){
                TakeSpinner.setAdapter(Takes);
                TestSpinner.setAdapter(Times);
            }else{

                TakeSpinner.setAdapter(WeightTakes);
                TestSpinner.setAdapter(WeightTimes);
            }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                if(CateGorySpinner.getSelectedItem().toString().equals("Emission tests")){
                    TakeSpinner.setAdapter(Takes);
                    TestSpinner.setAdapter(Times);
                }else{

                    TakeSpinner.setAdapter(WeightTakes);
                    TestSpinner.setAdapter(WeightTimes);
                }


            }
        });



        TakeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            String valinta = "";
            String aika = "";

            if(CateGorySpinner.getSelectedItem().toString().equals("Emission tests")) {
                valinta = TakeSpinner.getSelectedItem().toString();
                aika = tester.getDatebyTake(current.getUsername(), valinta);
                System.out.println(aika);
                System.out.println(tester.getSpinnerIndex(TestSpinner, aika));
                System.out.println(valinta);
                current.setCurrentestnumber(valinta);


         }else{

                    valinta = TakeSpinner.getSelectedItem().toString();
                    aika = tester.getWeightTestbyTake(current.getUsername(), valinta);
                    System.out.println(aika);
                    System.out.println(valinta);
                    current.setCurrentestnumber(valinta);


            }

                TestSpinner.setSelection(tester.getSpinnerIndex(TestSpinner, aika));




            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }
        });

        TestSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String valinta = "";
                String otto = "";

               if(CateGorySpinner.getSelectedItem().toString().equals("Emission tests")) {

                    valinta = TestSpinner.getSelectedItem().toString();
                    otto = tester.getTakebyDate(current.getUsername(), valinta);
                    System.out.println(otto);
                    current.setCurrentestnumber(otto);



              }else{

                        valinta = TestSpinner.getSelectedItem().toString();
                        otto = tester.getWeightTestbyDate(current.getUsername(), valinta);
                        System.out.println(otto);
                        current.setCurrentestnumber(otto);



                }

                TakeSpinner.setSelection(tester.getSpinnerIndex(TakeSpinner, otto));

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }
        });


    }

    public void goToFragment(View v){

        Fragment fragment;


        if(!CateGorySpinner.getSelectedItem().toString().equals("Weight progression")) {


            fragment = new BarClassFragment();
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.Fragmentikkuna, fragment);
            transaction.commit();

        }else{

            fragment = new WeighTrackerFragment();
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.Fragmentikkuna, fragment);
            transaction.commit();

        }


    }

    public void GoToMain(View v){

        Intent intent = new Intent(BrowseData.this,MainActivity.class);


        startActivity(intent);


    }






}