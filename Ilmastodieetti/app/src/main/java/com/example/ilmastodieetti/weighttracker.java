package com.example.ilmastodieetti;


//Weight tracker class object collects attributes of one weight test.

public class weighttracker {

    private String username;
    private Integer weight;
    private String date;
    private Integer take;



    public weighttracker (String un, Integer we, String da, Integer ta){

        username = un;
        weight = we;
        date = da;
        take = ta;


    }

    public String getUsername(){
        return username;
    }
    public String getDate(){
        return date;
    }
    public Integer getTake(){
        return take;
    }
    public Integer getWeight(){
        return weight;
    }

    public void setTake (Integer i){

        take = i;

    }



}
