package com.example.ilmastodieetti;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DailyUpdates extends AppCompatActivity {


    Context context = null;
    userbank bank = userbank.getInstance();
    CurrentUser current = CurrentUser.getInstance();
    testbank tester = testbank.getInstance();

    Spinner runningh;
    Spinner runningm;
    Spinner cyclingh;
    Spinner cyclingm;
    Spinner walkingh;
    Spinner walkingm;
    Spinner gymm;
    Spinner gymh;
    Spinner morningweight;
    Spinner jogah;
    Spinner jogam;
    TextView Testresultfield;




    Button sumbit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_updates);
        context = getApplicationContext();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


        bank.setContext(getApplicationContext());
        tester.setContext(getApplicationContext());


        Testresultfield = (TextView) findViewById(R.id.Resultfield);
        runningh = (Spinner) findViewById(R.id.Runninghours);
        runningm = (Spinner) findViewById(R.id.Runningminutes);
        cyclingh = (Spinner) findViewById(R.id.Cyclinghours);
        cyclingm = (Spinner) findViewById(R.id.Cyclingminutes);
        walkingh = (Spinner) findViewById(R.id.Walkinghours);
        walkingm = (Spinner) findViewById(R.id.Walkingminutes);
        gymh = (Spinner) findViewById(R.id.Gymhours);
        gymm = (Spinner) findViewById(R.id.Gymminutes);
        jogah = (Spinner) findViewById(R.id.Jogahours);
        jogam = (Spinner) findViewById(R.id.Jogaminutes);
        morningweight = (Spinner) findViewById(R.id.Morningweightspinner);
        sumbit = (Button) findViewById(R.id.sumpit);


        ArrayAdapter<Integer> Hours = new ArrayAdapter<Integer>(this, android.R.layout.simple_list_item_1,tester.AbstractArray(5,0));
        Hours.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        ArrayAdapter<Integer> Minute = new ArrayAdapter<Integer>(this, android.R.layout.simple_list_item_1,tester.AbstractArray(59,0));
        Minute.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        ArrayAdapter<Integer> Weightarray = new ArrayAdapter<Integer>(this, android.R.layout.simple_list_item_1,tester.AbstractArray(160,40));
        Weightarray.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        runningh.setAdapter(Hours);
        runningm.setAdapter(Minute);
        cyclingh.setAdapter(Hours);
        cyclingm.setAdapter(Minute);
        walkingh.setAdapter(Hours);
        walkingm.setAdapter(Minute);
        gymh.setAdapter(Hours);
        gymm.setAdapter(Minute);
        jogah.setAdapter(Hours);
        jogam.setAdapter(Minute);
        morningweight.setAdapter(Weightarray);

        tester.FillWeightTrackerArray();


    }

    public void ExecuteWeightracker(View v){


        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        Integer take = 0;

    Integer rh = (Integer) runningh.getSelectedItem();
    Integer rm = (Integer) runningm.getSelectedItem();
        Integer ch = (Integer) cyclingh.getSelectedItem();
        Integer cm = (Integer) cyclingm.getSelectedItem();
        Integer wh = (Integer) walkingh.getSelectedItem();
        Integer wm = (Integer) walkingm.getSelectedItem();
        Integer gh = (Integer) gymh.getSelectedItem();
        Integer gm = (Integer) gymm.getSelectedItem();
        Integer jh = (Integer) jogah.getSelectedItem();
        Integer jm = (Integer) jogam.getSelectedItem();



        Float calorieconsumtion = tester.CalorieConsumtion(rh,rm,ch,cm,wh,wm,gh,gm,jh,jm);


        Testresultfield.setText("The calorie consumption according to your activities was " + String.valueOf(calorieconsumtion) + "Kcal. " +
         "Your daily weight was recorded, and your progress can be viewed in the progress sheet");

        tester.addWeightTracker(current.getUsername(),(Integer) morningweight.getSelectedItem(),formatter.format(date),take);

        tester.SaveWeightTest();



    }




}