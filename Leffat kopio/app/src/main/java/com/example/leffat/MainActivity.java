package com.example.leffat;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashSet;

public class MainActivity extends AppCompatActivity {

    Context context = null;
    Spinner leffat;
    Spinner tarjontaspinner;
    EditText datetext;
    EditText datetext2;
    TextView scrollattava;
    Button find;
    Button Findbutton2;
    Button titlebutton;

    private ArrayList<Leffa> Leffa_array;
    private ArrayList<Aluelista> Alue_list;
    private ArrayList<tarjonta> Tarjonta_list;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = MainActivity.this;
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Leffa_array = new ArrayList<>();
        Alue_list = new ArrayList<>();
        Tarjonta_list = new ArrayList<>();


        tarjontaspinner = (Spinner) findViewById(R.id.tarjontaspinner);
        leffat = (Spinner) findViewById(R.id.Teatterit);
        datetext = (EditText) findViewById(R.id.datetext);
        find = (Button) findViewById(R.id.findbutton);
        Findbutton2 = (Button) findViewById(R.id.Findbutton2);
        scrollattava = (TextView) findViewById(R.id.scorllattava);
        datetext2 = (EditText) findViewById(R.id.Datetext2);
        titlebutton = (Button) findViewById(R.id.titlebutton);




        this.leffalista();
        this.addToList();


        leffat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                eloKuvat();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }


    public void eloKuvat(){


        String k = "";

        for(int i = 0; i <Alue_list.size(); i++) {

            Aluelista aluelista = Alue_list.get(i);
            if (aluelista.getNimi().contains(leffat.getSelectedItem().toString())) {

                k = aluelista.getID();

            }

            Tarjonta_list.clear();
        Tarjonta_list.add(new tarjonta("leffan nimi"));

        for (Leffa leffa: Leffa_array)   {

            if (leffa.getID().contains(k)) {
                Tarjonta_list.add(new tarjonta(leffa.getTitle()));
            }

            }

    }
        ArrayList <String> tarjontalist = new ArrayList();
        for(tarjonta tarjol:Tarjonta_list ){

        System.out.println(tarjol.getTarjontaNimi());

        tarjontalist.add(tarjol.getTarjontaNimi());


        }
        LinkedHashSet<String> lhs = new LinkedHashSet<String>();
        lhs.addAll(tarjontalist);
        tarjontalist.clear();
        tarjontalist.addAll(lhs);




        ArrayAdapter <String> tarjol = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,tarjontalist);
        tarjol.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tarjontaspinner.setAdapter(tarjol);










    }


    public void findbyMovie(View v){

        scrollattava.setText("");

        String d = datetext.getText().toString();
        String k = "";
        String m = tarjontaspinner.getSelectedItem().toString();

        for(int i = 0; i <Alue_list.size(); i++){

            Aluelista aluelista = Alue_list.get(i);
            if(aluelista.getNimi().contains(leffat.getSelectedItem().toString())){

                k=aluelista.getID();

            }

        }


        StringBuilder menu = new StringBuilder();

        for (int i = 0; i < Leffa_array.size(); i++){

            Leffa leffa = Leffa_array.get(i);
            System.out.println(m);
            if (leffa.getDate().contains(d) && leffa.getID().contains(k)) {
                if(leffa.getTitle().equals(m)) {

                    menu.append("\n");
                    menu.append(leffa.getTitle());
                    menu.append("\n");
                    menu.append(leffa.getDate());
                    menu.append("\n");
                    menu.append(leffa.getTime());
                    menu.append("\n");
                    menu.append(leffa.getArea());
                    menu.append("\n");
                    menu.append(leffa.getKaupunki());
                    menu.append("\n");
                }
            }
            else{
                menu.append("Nothing found");
                break;
            }

        }

        scrollattava.setText(menu.toString());

        System.out.println(m);

    }





    public void aikaJApaiva(View v){

        scrollattava.setText("");

        String time1 = (datetext2.getText().toString().substring(0,2)+ ":00:00");
        String time2 = (datetext2.getText().toString().substring(3,5)+ ":00:00");
        String d = datetext.getText().toString();
        String start = (d +" "+ time1);
        String end = (d +" "+ time2);

        try {
        SimpleDateFormat sdf1= new SimpleDateFormat("yyyy-MM-DD hh:mm:ss");

        Date starttime = sdf1.parse(start);
        Date endtime = sdf1.parse(end);

        String k = "";

        for(int i = 0; i <Alue_list.size(); i++){

            Aluelista aluelista = Alue_list.get(i);
            if(aluelista.getNimi().contains(leffat.getSelectedItem().toString())){

                k=aluelista.getID();
            }
        }

        StringBuilder menu = new StringBuilder();


        for (Leffa leffa: Leffa_array){

            String na = (leffa.getDate()+ " "+leffa.getTime());
            Date aika = sdf1.parse(na);

            System.out.println(aika);
            System.out.println(leffa.getID());
            System.out.println(k);

            if (leffa.getID().contains(k) && leffa.getDate().contains(d)) {


                    if (starttime.before(aika) && endtime.after(aika)){

                        menu.append(leffa.getTime());
                    menu.append("\n");
                    menu.append(leffa.getArea());
                    System.out.println(aika);
                    menu.append("\n");
                    menu.append(leffa.getDate());
                    menu.append("\n");
                    menu.append(leffa.getTitle());
                    menu.append("\n");
                    menu.append("\n");

                }

            }
            else{
                menu.append("Nothing found");
                break;
            }

        }

            System.out.println(starttime);
            System.out.println(endtime);

        scrollattava.setText(menu.toString());

    } catch(Exception e){
        e.printStackTrace();
    }

    }





    public void tarjollaList(View v){

        scrollattava.setText("");

        String d = datetext.getText().toString();
        String k = "";

        for(int i = 0; i <Alue_list.size(); i++){

            Aluelista aluelista = Alue_list.get(i);
            if(aluelista.getNimi().contains(leffat.getSelectedItem().toString())){

            k=aluelista.getID();

            }

        }


        StringBuilder menu = new StringBuilder();

        for (int i = 0; i < Leffa_array.size(); i++){

        Leffa leffa = Leffa_array.get(i);

        if (leffa.getDate().contains(d) && leffa.getID().contains(k) ) {

             menu.append("\n");
             menu.append(leffa.getTitle());
             menu.append("\n");
             menu.append(leffa.getDate());
            menu.append("\n");
            menu.append(leffa.getTime());
            menu.append("\n");
            menu.append(leffa.getArea());
            menu.append("\n");
            menu.append(leffa.getKaupunki());
            menu.append("\n");
       }
         else{
             menu.append("Nothing found");
             break;
         }

        }

        scrollattava.setText(menu.toString());

    }






    public void addToList() {


       for(Aluelista aluelista:Alue_list) {

           DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
           String currentdate = df.format(new Date());

           String urlStringtest = ("https://www.finnkino.fi/xml/Schedule/?area=" + aluelista.getID() + "+&dt=" + currentdate);

           System.out.println(urlStringtest);

           try {
               DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();

               String urlString = ("https://www.finnkino.fi/xml/Schedule/?area=" + aluelista.getID() + "+&dt=" + currentdate);

               System.out.println(urlString);

               Document doc = builder.parse(urlString);
               doc.getDocumentElement().normalize();

               NodeList nList = doc.getDocumentElement().getElementsByTagName("Show");


               for (int i = 0; i < nList.getLength(); i++) {

                   Node node = nList.item(i);
                   if (node.getNodeType() == Node.ELEMENT_NODE) {
                       Element element = (Element) node;

                       String Kaupunki = element.getElementsByTagName("Theatre").item(0).getTextContent();
                       String Theatre = element.getElementsByTagName("TheatreAndAuditorium").item(0).getTextContent();
                       String Date = element.getElementsByTagName("dttmShowStart").item(0).getTextContent();
                       String Time = element.getElementsByTagName("dttmShowStart").item(0).getTextContent();
                       String Title = element.getElementsByTagName("Title").item(0).getTextContent();


                       Leffa_array.add(new Leffa(Theatre, Date.substring(0, 10), Time.substring(11, 19), Kaupunki, Title, aluelista.getID()));


                   }

                   for (Leffa leffa : Leffa_array) {
                       System.out.println(leffa.getKaupunki());
                       System.out.println(leffa.getArea());
                       System.out.println(leffa.getDate());
                       System.out.println(leffa.getTime());
                       System.out.println(leffa.getID());
                       System.out.println(urlString);
                   }


               }

           } catch (IOException e) {
               e.printStackTrace();
           } catch (SAXException e) {
               e.printStackTrace();
           } catch (ParserConfigurationException e) {
               e.printStackTrace();
           } finally {
               System.out.println("DONE");
           }
       }


    }





    public void leffalista() {
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();

            String urlString = "https://www.finnkino.fi/xml/TheatreAreas/?format=xml";

            Document doc = builder.parse(urlString);
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getDocumentElement().getElementsByTagName("TheatreArea");



            for (int i = 0; i < nList.getLength() ; i++){

            Node node = nList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE){
                Element element = (Element) node;

                String Teatteri = element.getElementsByTagName("Name").item(0).getTextContent();
                String ID = element.getElementsByTagName("ID").item(0).getTextContent();

                Alue_list.add(new Aluelista(Teatteri,ID));

            }



            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        finally{
        System.out.println("DONE");
        }


        ArrayList <String> teatterilist = new ArrayList();

        for (Aluelista aluelista: Alue_list){

                teatterilist.add(aluelista.getNimi());

            }


        ArrayAdapter <String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,teatterilist);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        leffat.setAdapter(adapter);

    }


}












