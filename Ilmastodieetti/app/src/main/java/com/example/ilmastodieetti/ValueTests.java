package com.example.ilmastodieetti;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValueTests {

    /* ValueTests is a singleton class-object that can be used for various test such
    as testing user input size, wether or not its an integer, how many characters it contains etc.
    It is designed to be used in any time of the program lifecycle */


    private String name = "valuetester";
    private static ValueTests instance = new ValueTests();


    public static ValueTests getInstance() {

        return instance;
    }



    // Integer or not
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

    // Does the input contain spaces
    public static boolean HasSpaces(String n){

        if (n.indexOf(' ') != -1) {
            return true;
        } else {
            return false;
        }

    }

    // How many characters does the word consist
    public static Integer WordCount(String w){
    int count = 0;

        for (int i = 0; i < w.length(); i++) {
            if (w.charAt(i) != ' ')
                count++;
        }
    return count;


    }

    // How many numbers does the string contain
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
    // Does the string contain special characters

    public static boolean SpecialCharacter(String p){

    Pattern pattern = Pattern.compile("[^A-Za-z0-9 ]");
    Matcher matcher = pattern.matcher(p);

    if(matcher.find()){
        return true;
    }
    else {
        return false;
    }


    }

    // Does the string contain Both capital and lower case letters

    public static boolean UpperAndLower(String str) {
        char ch;
        boolean capitalL = false;
        boolean lowerCaseL = false;
        for(int i=0;i < str.length();i++) {
            ch = str.charAt(i);

           if (Character.isUpperCase(ch)) {
               capitalL = true;
            } else if (Character.isLowerCase(ch)) {
               lowerCaseL = true;
            }
            if(capitalL && lowerCaseL)
                return true;
        }
        return false;
    }











}
