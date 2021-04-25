package com.example.bottle_machine;

public class Bottle {


    private String name = "Pepsi max";
    private String manufacturer = "Pepsi";
    private float total_energy = 0.3F;
    private float size = 0.5F;
    private float prize = 1.8F;

    public Bottle(String n, String m, float t, float e, float p) {
        name = n;
        manufacturer = m;
        total_energy = t;
        size = e;
        prize = p;



    }
    public String getName() {
        return name;
    }
    public String getManufacturer() {
        return manufacturer;
    }
    public float getPrize() {
        return prize;
    }
    public float getTotal_energy() {
        return total_energy;
    }
    public float getSize() {
        return size;
    }


}
