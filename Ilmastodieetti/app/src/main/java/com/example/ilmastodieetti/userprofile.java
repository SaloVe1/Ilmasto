package com.example.ilmastodieetti;

public class userprofile {

    private String name;
private String username;
private String password;
private int age;
private int height;
private String gender;
private String city;



    public userprofile (String n, String un, String p, int a, int h, String g,String c){

    name = n;
    username = un;
    password = p;
    age = a;
    height = h;
    gender = g;
    city = c;

    }

    public String getUsername(){
        return username;
    }
    public String getName(){
        return name;
    }
    public String getPassword(){
        return password;
    }
    public int getAge(){
        return age;
    }
    public int getHeight(){
        return height;
    }
    public String getGender(){
        return gender;
    }
    public String getCity(){
        return city;
    }




}




