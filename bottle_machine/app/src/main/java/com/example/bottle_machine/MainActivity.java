package com.example.bottle_machine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.view.View;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;

import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity {

    TextView consoletext;
    TextView juomalista;
    SeekBar SeekBar;
    TextView kolikot;
    Spinner spDrinks;
    Button buybutton;
    Button receipt;
    Button kuitinkirjoitus;

    Context context = null;
    private static int bottles;
    private static DecimalFormat df;
    private ArrayList<Bottle>bottle_array;
    private float money;
    private String Kaikkijuomat;
    private float spendtotal;
    private int kuittilaskuri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = MainActivity.this;

        consoletext = (TextView) findViewById(R.id.consoltext);
        juomalista = (TextView) findViewById(R.id.Juomalista);
        SeekBar = (SeekBar) findViewById(R.id.seekBar);
        kolikot = (TextView) findViewById(R.id.kolikot);
        spDrinks = (Spinner) findViewById(R.id.spDrinks);
        buybutton = (Button) findViewById(R.id.buybutton);
        receipt = (Button) findViewById(R.id.Receipt);
        kuitinkirjoitus = (Button) findViewById(R.id.Kuitinkirjoitus);
        bottles = 5;
        money = 0;
        df = new DecimalFormat("0.00");
        spendtotal = 0;
        Kaikkijuomat = "";
        kuittilaskuri = 0;

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.Drinks, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spDrinks.setAdapter(adapter);


        bottle_array = new ArrayList<>();

        Bottle bottle1 = new Bottle("Pepsi Max medium", "pepsi",0.3F,0.5F,1.8F);
        bottle_array.add(bottle1);
        Bottle bottle2 = new Bottle("Pepsi Max large", "pepsi",0.3F,1.5F,2.2F);
        bottle_array.add(bottle2);
        Bottle bottle3 = new Bottle("Coca Cola Zero medium", "Coca Cola Company",0.3F,0.5F,2.0F);
        bottle_array.add(bottle3);
        Bottle bottle4 = new Bottle("Coca Cola Zero large", "Coca Cola Company",0.3F,1.5F,2.5F);
        bottle_array.add(bottle4);
        Bottle bottle5 = new Bottle("Fanta Zero medium", "Coca Cola Company",0.3F,0.5F,1.95F);
        bottle_array.add(bottle5);

        this.atDisplayBottles();


        SeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(android.widget.SeekBar seekBar, int progress, boolean fromUser) {

            kolikot.setText("add " + String.valueOf(progress) + " dollars" );


            }


            @Override
            public void onStartTrackingTouch(android.widget.SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(android.widget.SeekBar seekBar) {

            }
        });





    }

    public static void wait(int ms)
    {
        try
        {
            Thread.sleep(ms);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
    }


    public void addMoney(View v){
        money +=SeekBar.getProgress();
        spendtotal +=SeekBar.getProgress();
        consoletext.setText(SeekBar.getProgress() + " dollars worth money added");


    }

    public void atDisplayBottles(){
    StringBuilder out = new StringBuilder();
        int index=0;
        for (Bottle bottle: bottle_array){
        out.append((index++ + 1)+ ". " + bottle.getName());
        out.append("     Size: " + bottle.getSize() + "     Price: " + bottle.getPrize());
        out.append("\n");
        out.append("\n");
        }

        juomalista.setText(out.toString());


    }

    public int Drinknumber (String A){

        for(int i = 0; i< bottle_array.size(); i++){

      Bottle bottle = bottle_array.get(i);
      if(bottle.getName().equals(A))
      {
       return i;
      }


      }
        return -1;

    }


    public void buyBottle(View v){

    String a = spDrinks.getSelectedItem().toString();
    int i = Drinknumber(a);

   if(Drinknumber(a)==-1){
       consoletext.setText("bottle not found");
   }
    else
    if(i<= bottle_array.size()) {
        Bottle bottle = bottle_array.get(i);

        if (money >= bottle.getPrize()) {

                bottles -= 1;

                money -= bottle.getPrize();
                consoletext.setText("You bought " + bottle.getName());
                bottle_array.remove(i);
                atDisplayBottles();

                StringBuilder kuitti = new StringBuilder();
                kuitti.append("\n");
                kuitti.append("Name: " + bottle.getName());
                kuitti.append("Prize: " + bottle.getPrize());

                Kaikkijuomat +=(kuitti.toString());

        } else
            consoletext.setText("add money first!");
    }

    else
        consoletext.setText("couldn't find that bottle!");

    }
    public void Kuitti(View v) {

        StringBuilder loppukuitti = new StringBuilder();

        float drinkscost = spendtotal - money;

        loppukuitti.append("Total cost: " + df.format(drinkscost));
        loppukuitti.append("\n");
        loppukuitti.append("\n");
        loppukuitti.append("Total money in machine: " + df.format(money));
        loppukuitti.append("\n");
        loppukuitti.append("\n");
        loppukuitti.append("Complete list of bought items");
        loppukuitti.append("\n");
        loppukuitti.append(Kaikkijuomat);

        juomalista.setText(loppukuitti.toString());


    }

    public void kuittiKirjoitus (View v){

            kuittilaskuri +=1;

        try{


            OutputStreamWriter ows = new OutputStreamWriter(context.openFileOutput( kuittilaskuri + "kuitti.txt", Context.MODE_PRIVATE));

            StringBuilder loppukuitti = new StringBuilder();

            float drinkscost = spendtotal - money;

            loppukuitti.append("Total cost: " + df.format(drinkscost));
            loppukuitti.append("\n");
            loppukuitti.append("\n");
            loppukuitti.append("Total money in machine: " + df.format(money));
            loppukuitti.append("\n");
            loppukuitti.append("\n");
            loppukuitti.append("Complete list of bought items");
            loppukuitti.append("\n");
            loppukuitti.append(Kaikkijuomat);


            String s = loppukuitti.toString();

            ows.write(s);
            ows.close();

            juomalista.setText("Receipt available in: " + getFilesDir());


        } catch (IOException e) {
            Log.e("IOException", "Virhe syötteessä");

        }finally   {
            System.out.println("kirjoitettu");



        }


    }




    }















