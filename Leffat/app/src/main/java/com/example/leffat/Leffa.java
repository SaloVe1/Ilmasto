package com.example.leffat;

public class Leffa {

    private String kaupunki ="Helsinki";
    private String area = "tennispalatsi";
    private String date = "2021-03-19";
    private String time = "00:00:00";
    private String title = "Testileffa";
    private String ID = "1033";


    public Leffa(String a, String d, String t, String k, String n, String i) {

        area = a;
        date = d;
        time = t;
        kaupunki = k;
        title = n;
        ID = i;


    }

    public String getKaupunki(){

        return kaupunki;


    }

    public String getArea(){

    return area;


    }

    public String getDate(){
        return date;


    }

    public String getTime(){
        return time;


    }

    public String getTitle(){
        return title;


    }


    public String getID(){
        return ID;


    }



}
