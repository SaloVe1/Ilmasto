package com.example.ilmastodieetti;

public class Questionaire_data {

    private Integer takes;
    private String username;
    private String diet;
    private boolean lowCarbonPreference;
    private Integer beefLevel;
    private Integer fishLevel;
    private Integer porkPoultryLevel;
    private Integer dairyLevel;
    private Integer cheeseLevel;
    private Integer riceLevel;
    private Integer eggLevel;
    private Integer winterSaladLevel;
    private Integer restaurantSpending;


    public Questionaire_data(Integer ta,String us, String d, boolean l, Integer b, Integer f, Integer p,
                             Integer da, Integer c, Integer r, Integer e, Integer w, Integer Sp) {

    takes = ta;
    username = us;
    diet = d;
    lowCarbonPreference = l;
    beefLevel = b;
    fishLevel = f;
    porkPoultryLevel = p;
    dairyLevel = da;
    cheeseLevel = c;
    riceLevel = r;
    eggLevel = e;
    winterSaladLevel = w;
    restaurantSpending = Sp;


    }


    public Integer gettakes(){return takes;}
    public String getusername(){
        return username;
    }
    public String getdiet(){
        return diet;
    }
    public boolean getlowCarbonPreference(){
        return lowCarbonPreference;
    }
    public Integer getbeefLevel() { return beefLevel;}
    public Integer getfishLevel() { return fishLevel;}
    public Integer getporkPoultryLevel() { return porkPoultryLevel;}
    public Integer getdairyLevel() { return dairyLevel;}
    public Integer getcheeseLevel() { return cheeseLevel;}
    public Integer getriceLevel() { return riceLevel;}
    public Integer geteggLevel() { return eggLevel;}
    public Integer getwinterSaladLevel() { return winterSaladLevel;}
    public Integer getrestaurantSpending() { return restaurantSpending;}


    public void settakes (Integer i){

    takes = i;

    }




}