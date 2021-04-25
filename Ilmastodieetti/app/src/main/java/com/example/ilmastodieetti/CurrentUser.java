package com.example.ilmastodieetti;

//Class is used to store username and currently latest chosen testnumber

public class CurrentUser {

    private static CurrentUser instance = new CurrentUser();

    private String username;
    private String currentestnumber;



    public static CurrentUser getInstance(){

        return instance;
    }


    public String getCurrentestnumber(){
        return currentestnumber;

    }
    public void setCurrentestnumber(String t){

        currentestnumber = t;

    }

    public String getUsername(){
    return username;

    }

    public void setUsername(String u){

    username = u;

    }















}
