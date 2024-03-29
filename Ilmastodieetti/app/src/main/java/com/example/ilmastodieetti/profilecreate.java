package com.example.ilmastodieetti;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class profilecreate extends AppCompatActivity {
    Context context = null;

    EditText ProfileName;
    EditText ProfileAge;
    EditText ProfileHeight;
    EditText ProfileUsername;
    EditText ProfilePassword;
    Spinner CityS;
    Spinner GenderS;
    Button Create;
    Button Return;
    TextView agealert;
    TextView heightalert;
    TextView usernamealert;
    TextView passwordalert;
    TextView namealert;
    TextView profilealert;
    userbank bank = userbank.getInstance();
    ValueTests valueTests = ValueTests.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profilecreate);
        context = getApplicationContext();
        bank.setContext(getApplicationContext());
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        ProfileName = (EditText) findViewById(R.id.ProfileName);
        ProfileAge = (EditText) findViewById(R.id.ProfileAge);
        ProfileHeight = (EditText) findViewById(R.id.ProfileHeight);
        ProfileUsername = (EditText) findViewById(R.id.ProfileUsername);
        ProfilePassword = (EditText) findViewById(R.id.ProfilePassword);
        Create = (Button) findViewById(R.id.Create);
        Return = (Button) findViewById(R.id.returnTOlogin);
        agealert = (TextView) findViewById(R.id.agealert);
        heightalert = (TextView) findViewById(R.id.heightalert);
        usernamealert = (TextView) findViewById(R.id.usernamealert);
        passwordalert = (TextView) findViewById(R.id.passwordalert);
        namealert = (TextView)findViewById(R.id.namealert);
        profilealert = (TextView)findViewById(R.id.Profilealert);
        CityS = (Spinner)findViewById(R.id.City);
        GenderS = (Spinner)findViewById(R.id.Gender);


        bank.FillCityArray();



        //City array is fetched from THL data with Cittyarray method in userbank class

        ArrayAdapter<String> City = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,bank.Cityarray());
        City.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        CityS.setAdapter(City);


        ArrayAdapter<String> Gender = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,bank.Genders());
        Gender.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        GenderS.setAdapter(Gender);




        ProfileName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(!ProfileName.getText().toString().isEmpty() && !ProfileName.getText().toString().equals("Name")){
                    namealert.setTextColor(Color.GREEN);
                    namealert.setText("OK!");}
                 else{
                     namealert.setTextColor(Color.RED);
                     namealert.setText("Enter a new Name!");
                    }



            }

            @Override
            public void afterTextChanged(Editable s) {

                if(!ProfileName.getText().toString().isEmpty() && !ProfileName.getText().toString().equals("Name")){
                    namealert.setTextColor(Color.GREEN);
                    namealert.setText("OK!");}
                 else{
                        namealert.setTextColor(Color.RED);
                        namealert.setText("Enter a new Name!");
                    }



            }
        });


        //The following functions use logical argumets to test user inputs.


        ProfileUsername.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }



            //Test if method wordcount is larger than 4 and smaller than 10 and wether or not the profilename already exists

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(bank.Findprofiletruefalse(ProfileUsername.getText().toString())==true){
                    usernamealert.setTextColor(Color.RED);
                    usernamealert.setText("Profile name is taken");
                }else{
                    if(valueTests.WordCount(ProfileUsername.getText().toString())>4 && valueTests.WordCount(ProfileUsername.getText().toString())<11 && !ProfileUsername.getText().toString().equals("Username")){
                        usernamealert.setTextColor(Color.GREEN);
                        usernamealert.setText("OK!");
                    }
                    else{
                        usernamealert.setTextColor(Color.RED);
                        usernamealert.setText("In min 4 characters, at max 10 characters");
                    }

                }


            }

            //Test if method wordcount is larger than 4 and smaller than 10 and wether or not the profilename already exists

            @Override
            public void afterTextChanged(Editable s) {

                if(bank.Findprofiletruefalse(ProfileUsername.getText().toString())==true){
                    usernamealert.setTextColor(Color.RED);
                    usernamealert.setText("Profile name is taken");
                }else{
                    if(valueTests.WordCount(ProfileUsername.getText().toString())>4 && valueTests.WordCount(ProfileUsername.getText().toString())<11 && !ProfileUsername.getText().toString().equals("Username")){
                        usernamealert.setTextColor(Color.GREEN);
                        usernamealert.setText("OK!");
                    }
                    else{
                        usernamealert.setTextColor(Color.RED);
                        usernamealert.setText("In min 4 characters, at max 10 characters");
                    }

                }


            }
        });

        ProfileAge.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }



            //Test if input value is number, if input value is bigger than 12 and smaller than 120

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            if(valueTests.NumeroTesti(ProfileAge.getText().toString())==true && Integer.parseInt(ProfileAge.getText().toString())>11 && Integer.parseInt(ProfileAge.getText().toString())<121){
                agealert.setTextColor(Color.GREEN);
                agealert.setText("OK!");
            }else{
                agealert.setTextColor(Color.RED);
                agealert.setText("Number value between 12-120!");
            }
            }


            //Test if input value is number, if input value is bigger than 12 and smaller than 120

            @Override
            public void afterTextChanged(Editable s) {
                if(valueTests.NumeroTesti(ProfileAge.getText().toString())==true && Integer.parseInt(ProfileAge.getText().toString())>12 && Integer.parseInt(ProfileAge.getText().toString())<120){
                    agealert.setTextColor(Color.GREEN);
                    agealert.setText("OK!");
                }else{
                    agealert.setTextColor(Color.RED);
                    agealert.setText("Number value between 12-120!");
                }

            }
        });
        ProfileHeight.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }

            //Test if input value is number, if input value is smaller than 240

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(valueTests.NumeroTesti(ProfileHeight.getText().toString())==true && Integer.parseInt(ProfileHeight.getText().toString())>100 && Integer.parseInt(ProfileHeight.getText().toString())<260){
                    heightalert.setTextColor(Color.GREEN);
                    heightalert.setText("OK!");
                }else{
                    heightalert.setTextColor(Color.RED);
                    heightalert.setText("Set a valid number for height!");
                }

            }

            //Test if input value is number, if input value is smaller than 240

            @Override
            public void afterTextChanged(Editable s) {
                if(valueTests.NumeroTesti(ProfileHeight.getText().toString())==true && Integer.parseInt(ProfileHeight.getText().toString())>100 && Integer.parseInt(ProfileHeight.getText().toString())<260){
                    heightalert.setTextColor(Color.GREEN);
                    heightalert.setText("OK!");
                }else{
                    heightalert.setTextColor(Color.RED);
                    heightalert.setText("Set a valid number for height!");
                }

            }
        });




        ProfilePassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }


            //Test if password has 1 number characters and 1 special character and is at least 8 characters long

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(valueTests.NumberAmount(ProfilePassword.getText().toString())>=4 &&
                        valueTests.HasSpaces(ProfilePassword.getText().toString())==false &&
                        valueTests.WordCount(ProfilePassword.getText().toString())>11 &&
                        valueTests.SpecialCharacter(ProfilePassword.getText().toString())==true &&
                        valueTests.UpperAndLower(ProfilePassword.getText().toString())==true){
                passwordalert.setTextColor(Color.GREEN);
                passwordalert.setText("OK!");
                }else{
                    passwordalert.setTextColor(Color.RED);
                    passwordalert.setText("1 number, 1 special character, 12 characters total," + "\n" + "one lower and uppercase letter");
                }
            }

            //Test if password has 1 number characters and 1 special character and is at least 8 characters long

            @Override
            public void afterTextChanged(Editable s) {
                if(valueTests.NumberAmount(ProfilePassword.getText().toString())>=2 &&
                        valueTests.HasSpaces(ProfilePassword.getText().toString())==false &&
                        valueTests.WordCount(ProfilePassword.getText().toString())>11 &&
                        valueTests.SpecialCharacter(ProfilePassword.getText().toString())==true&&
                        valueTests.UpperAndLower(ProfilePassword.getText().toString())==true){
                    passwordalert.setTextColor(Color.GREEN);
                    passwordalert.setText("OK!");
                }else{
                    passwordalert.setTextColor(Color.RED);
                    passwordalert.setText("1 number, 1 special character, 12 characters total," + "\n" + "one lower and uppercase letter");
                }

            }
        });



    }


    //Checks that the valuetests were passed and creates a new userprofile object

    public void NewUserEntry(View v){

        if(bank.Findprofilebyname(ProfileUsername.getText().toString())==-1){

          if(passwordalert.getText().toString().equals("OK!") && namealert.getText().toString().equals("OK!")
          && usernamealert.getText().toString().equals("OK!") && agealert.getText().toString().equals("OK!") && heightalert.getText().toString().equals("OK!")){

              bank.newUser(ProfileName.getText().toString(), ProfileUsername.getText().toString(), ProfilePassword.getText().toString(),
                      Integer.parseInt(ProfileAge.getText().toString()), Integer.parseInt(ProfileHeight.getText().toString()), GenderS.getSelectedItem().toString(), CityS.getSelectedItem().toString());

              bank.SaveUsers();



              profilealert.setText("New User Created");
              Intent intent = new Intent(profilecreate.this,Login.class);


              startActivity(intent);


          }
           else{

              profilealert.setText("Make sure all fields are OK!");
          }


        }
        else{
            profilealert.setText("User allready exists");
        }


    }

    public void ReturnToMain(View v){

        Intent intent = new Intent(profilecreate.this,Login.class);


        startActivity(intent);





    }







}