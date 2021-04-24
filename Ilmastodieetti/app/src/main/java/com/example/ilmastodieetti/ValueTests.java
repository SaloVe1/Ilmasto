package com.example.ilmastodieetti;

public class ValueTests {


    private String name = "valuetester";
    private static ValueTests instance = new ValueTests();


    public static ValueTests getInstance() {

        return instance;
    }

    public static boolean NumeroTesti(String n) {

        int intValue;

        try {
            intValue = Integer.parseInt(n);
            return true;
        } catch (NumberFormatException e) {
            System.out.println("Not an integer");
        }
        return false;

    }
    public static boolean HasSpaces(String n){

        if (n.indexOf(' ') != -1) {
            return true;
        } else {
            return false;
        }

    }

    public static Integer WordCount(String w){
    int count = 0;

        for (int i = 0; i < w.length(); i++) {
            if (w.charAt(i) != ' ')
                count++;
        }
    return count;


    }


    public static Integer NumberAmount(String p) {

        int count = 0;
        char[] chars = p.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (char c : chars) {
            if (Character.isDigit(c)) {
                sb.append(c);
            }
            String numbers = sb.toString();

            for (int i = 0; i < numbers.length(); i++) {

                    count++;

            }



        }
        return count;

    }











}
