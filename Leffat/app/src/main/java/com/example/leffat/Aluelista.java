package com.example.leffat;

public class Aluelista {

    private String teatterinimi = "nimi";
    private String teatteriID = "ID";


    public Aluelista(String n, String i) {

        teatterinimi = n;
        teatteriID = i;

    }

    public String getNimi() {

        return teatterinimi;


    }

    public String getID() {

        return teatteriID;


    }

}
