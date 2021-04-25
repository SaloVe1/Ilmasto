package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {


String message;
String message2;
String latausnimike;
String syöte;
String avaaja;

TextView text;
TextView entertext;
EditText enterkenttä;
EditText text2;
EditText kirjoita;
EditText lataa;
EditText avaa;


    Context context = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = MainActivity.this;
        //this.testFunction();


        text = (TextView) findViewById(R.id.textView);
        entertext = (TextView) findViewById(R.id.textView2);
        enterkenttä = (EditText) findViewById(R.id.enterkenttä);
        text2 = (EditText) findViewById(R.id.editTextTextPersonName);
        kirjoita = (EditText) findViewById(R.id.Kirjoitatama);
        lataa = (EditText) findViewById(R.id.Lataasisältö);
        avaa = (EditText) findViewById(R.id.Tallennusnimike);


        enterkenttä.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
             if(event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {

                message2 = enterkenttä.getText().toString();

                 entertext.setText(message2);

                 return true;
             }
                return false;
            }
        });



        System.out.println(context.getFilesDir());
    }

    public void testFunction (View v)  {

        text.setText("Hello World!");
        System.out.println("Hello World!");
    }

    public void pushButton (View v)  {

        message = text2.getText().toString();

        text.setText(message);


    }








    public void readFile (View v){
    try{

        avaaja = avaa.getText().toString();
        InputStream ins = context.openFileInput(avaaja + ".txt");

        StringBuilder out = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(ins));
        String line;
        while ((line = br.readLine()) !=null) {
        out.append(line);

        }
        kirjoita.setText(out.toString());

        ins.close();
    } catch (IOException e) {
        Log.e("IOException", "Virhe syötteessä");
    } finally   {
    System.out.println("luettu");
    avaa.setText("");


    }

    }

    public void writeFile (View v){



        try{

            latausnimike = lataa.getText().toString();
            syöte = kirjoita.getText().toString();
            OutputStreamWriter ows = new OutputStreamWriter(context.openFileOutput(latausnimike + ".txt", Context.MODE_PRIVATE));

            String s = syöte;

            ows.write(s);
            ows.close();



        } catch (IOException e) {
            Log.e("IOException", "Virhe syötteessä");

        }finally   {
            System.out.println("kirjoitettu");
            lataa.setText("");
            kirjoita.setText("");

        }


    }


}