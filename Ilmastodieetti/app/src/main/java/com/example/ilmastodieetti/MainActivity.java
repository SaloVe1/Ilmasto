package com.example.ilmastodieetti;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Context context = null;
    userbank bank = userbank.getInstance();
    CurrentUser current = CurrentUser.getInstance();
    TextView welcome;
    Button progress;
    Button Emissionestimate;
    Button Dailyupdates;
    Button Logout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = getApplicationContext();
        bank.setContext(getApplicationContext());
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        welcome = (TextView) findViewById(R.id.welcome);
        progress = (Button) findViewById(R.id.progress);
        Emissionestimate = (Button) findViewById(R.id.Emission_estimate);
        Dailyupdates = (Button) findViewById(R.id.Daily_Updates);
        Logout = (Button) findViewById(R.id.Logout);


        welcome.setText("Welcome user " + current.getUsername() + ". According to THL database the probability of you being a tobacco smoker is "
                + bank.SmokingPropability(current.getUsername()) + "%" + ". Be Smart and don't start smoking!");

        bank.FillCityArray();

      //  bank.SmokingPropability(current.getUsername());


    }


    public void ReturnToLogin(View v){

        Intent intent = new Intent(MainActivity.this,Login.class);


        startActivity(intent);



    }
    public void GoToEmission(View v){

        Intent intent = new Intent(MainActivity.this,Questionaire.class);


        startActivity(intent);


    }
    public void GoToDaily(View v){

        Intent intent = new Intent(MainActivity.this,DailyUpdates.class);


        startActivity(intent);


    }





    public void GoToBrowser(View v){

        Intent intent = new Intent(MainActivity.this,BrowseData.class);


        startActivity(intent);


    }

}