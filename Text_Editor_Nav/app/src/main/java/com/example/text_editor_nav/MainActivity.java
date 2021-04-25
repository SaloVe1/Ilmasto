package com.example.text_editor_nav;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout drawer;
    EditText kirjoitusboksi;
    TextView lahetysboksi;
    TextView customtext;
    private Integer fonttikoko;
    private Float fonttileveys;
    private Float fonttikorkeus;
    private Float fonttispacing;
    private String displaytext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        customtext = findViewById(R.id.customtext);
        kirjoitusboksi = findViewById(R.id.kirjoitusboksi);
        lahetysboksi = findViewById(R.id.lahetysboksi);
        fonttikoko = 0;
        fonttileveys = null;
        fonttikorkeus = null;
        fonttispacing = null;


        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if(savedInstanceState == null){

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new Blank_fragement()).commit(); }



    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.kirjoita:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new Kirjoitus_fragement()).commit();
                break;
            case R.id.fonttikoko:
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new Font_fragement()).commit();
            break;
            case R.id.korkeus:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new Korkeus_fragement()).commit();
                break;
            case R.id.leveys:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new Leveys_fragement()).commit();
                break;
            case R.id.letterspacing:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new Rivivali_fragement()).commit();
                break;
            case R.id.Displaytext:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new Displaytext_fragement()).commit();
                break;

        }


        return true;
    }

    public void onBackPressed(){
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);

        }else {
            super.onBackPressed();

        }





    }

    public void vastaanOtto(View view){

       // try{


        //    String text = getIntent().getExtras().get("KEY").toString();
            System.out.println("Tämö on arvo");

      //  } catch (Exception e){

      //  }





    }

    public void fontInt(Integer s1){

        fonttikoko=s1;

    }

    public void setDisplaytext(String s){

        displaytext=s;
        customtext.setText(displaytext);
    }

    public void fontWidth(Float s2){

        fonttileveys=s2;

    }

    public void fontHeight(Float s3){

        fonttikorkeus=s3;

    }

    public void fontSpace(Float s4){

        fonttispacing=s4;

    }



    public void setTextSize(View view){

       lahetysboksi.setTextSize(fonttikoko);


    }
    public void setCustomText(View view){




    }


    public void setTextWidth(View view){

        lahetysboksi.setScaleX(fonttileveys);


    }
    public void setTextHeight(View view){

        lahetysboksi.setScaleY(fonttikorkeus);


    }

    public void setTextSpace(View view){

        lahetysboksi.setLetterSpacing(fonttispacing);


    }




    public void sendText(View view){

        lahetysboksi.setText("");
        lahetysboksi.setText(kirjoitusboksi.getText().toString());
        kirjoitusboksi.setEnabled(false);


    }
    public void enableText(View view){

        kirjoitusboksi.setText("");
        kirjoitusboksi.setEnabled(true);




    }


}