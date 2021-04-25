package com.example.ilmastodieetti;

//Class is used to store areas and indexes from THL pages

public class CitiesAndIndexes {

    private String city;
    private String indexcode;


    public CitiesAndIndexes(String c, String i) {

        city = c;
        indexcode = i;

    }

    public String getCity(){return city;}
    public String getIndexcode(){return indexcode;}


}