package com.example.ilmastodieetti;

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
