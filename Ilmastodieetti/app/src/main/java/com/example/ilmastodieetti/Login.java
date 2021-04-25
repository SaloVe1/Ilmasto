package com.example.ilmastodieetti;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Login extends AppCompatActivity {

    Context context = null;
    EditText UserName;
    EditText PassWord;
    Button Continue;
    Button CreateProfile;
    userbank bank = userbank.getInstance();
    CurrentUser current = CurrentUser.getInstance();
    TextView LoginMessage;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        context = getApplicationContext();

        UserName = (EditText) findViewById(R.id.UserName);
        PassWord = (EditText) findViewById(R.id.PassWord);
        Continue = (Button) findViewById(R.id.continuebutton);
        CreateProfile = (Button) findViewById(R.id.ProfileCreate);
        LoginMessage = (TextView) findViewById(R.id.LoginMessage);


        bank.setContext(getApplicationContext());


        bank.FillUserArray();

    }



    //Checks password using passtest method in testbank singleton

    public void toApplication(View v){


    if (bank.Passtest(UserName.getText().toString(),PassWord.getText().toString()).equals("OK")){
        Intent intent = new Intent(Login.this,MainActivity.class);
        startActivity(intent);
        current.setUsername(UserName.getText().toString());
    }
    else{
    if (bank.Passtest(UserName.getText().toString(),PassWord.getText().toString()).equals("Wrong")){

        LoginMessage.setText("Wrong Password!");
    }
    else{
        LoginMessage.setText("User" + bank.Passtest(UserName.getText().toString(),PassWord.getText().toString()));

    }

    }





    }
    public void toProfileCreate(View v){

        Intent intent = new Intent(Login.this,profilecreate.class);
        startActivity(intent);




    }









}