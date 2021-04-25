package com.example.internetselain;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Context;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Context context = null;
    WebView webView;
    EditText searchfield;
    ImageButton refreshbutton;
    ImageButton backbutton;
    ImageButton forwardbutton;
    String osoite;
    Button initialize;
    Button shoutout;
    private Integer backcounter;

    private ArrayList<browseractions> webpagelist;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = MainActivity.this;
        webView = (WebView) findViewById(R.id.webwindow);
        searchfield = (EditText) findViewById(R.id.searchfield);
        refreshbutton = (ImageButton) findViewById(R.id.refreshbutton);
        osoite = "";
        initialize = (Button) findViewById(R.id.initialize);
        shoutout = (Button) findViewById(R.id.shoutout);
        backbutton = (ImageButton) findViewById(R.id.backbutton);
        forwardbutton = (ImageButton) findViewById(R.id.forwardbutton);
        webpagelist = new ArrayList<>();
        backcounter = 0;



        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.loadUrl("https://duckduckgo.com");
        searchfield.setText("https://duckduckgo.com");
        webView.setWebViewClient(new WebViewClient());


        searchfield.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {

                    goToaddress();

                    return true;
                }
                return false;
            }
        });


        webpagelist.add(new browseractions(searchfield.getText().toString()));

    }



    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();

        }

    }
    public void Shoutout(View v){

        webView.evaluateJavascript("javascript:shoutOut()",null);



    }
    public void Initialize(View v){

        webView.evaluateJavascript("javascript:initialize()",null);



    }

    public void goToaddress() {

        if (backcounter > 0) {

            for (int i = 0; i <= backcounter; i++) {

                if (i > 0) {
                    webpagelist.remove(webpagelist.size() - 1);
                }

            }

            backcounter = backcounter - backcounter;
        }


        if (searchfield.getText().toString().equals("index.html")) {

            webView.loadUrl("file:///android_asset/index.html");

        } else {

            osoite = searchfield.getText().toString();

            int count = 0;
            for (int i = 0; i < osoite.length(); i++) {
                if (Character.isLetter(osoite.charAt(i)))
                    count++;
            }
            if (count >= 4) {
                if (osoite.substring(0, 4).equals("http")) {
                    try {
                        URL url = new URL(osoite);

                        webView.loadUrl(url.toString());
                        System.out.println(osoite);
                        searchfield.setText(url.toString());

                        webpagelist.add(new browseractions(searchfield.getText().toString()));


                    } catch (
                            IOException e) {
                        e.printStackTrace();


                    }

                } else {
                    try {
                        URL url = new URL("http://" + osoite);

                        webView.loadUrl(url.toString());
                        System.out.println(osoite);
                        searchfield.setText(url.toString());

                        webpagelist.add(new browseractions(searchfield.getText().toString()));


                    } catch (
                            IOException e) {
                        e.printStackTrace();


                    }
                }


            }
            else{


                    webView.loadUrl("http//" + osoite);
                    System.out.println(osoite);
                    searchfield.setText(osoite);

                    webpagelist.add(new browseractions(searchfield.getText().toString()));





                }




            if (webpagelist.size() > 11) {

                webpagelist.remove(0);
            }


        }
    }

    public void goBack(View v) {



        if(webpagelist.size()-backcounter>1 && backcounter<10) {

            backcounter += 1;
            Integer taaksepain = backcounter + 1;



                String sivusto = webpagelist.get(webpagelist.size() - taaksepain).getName();
                webView.loadUrl(sivusto);
                searchfield.setText(sivusto);


        }else{

            System.out.println("No history");
        }

    }

    public void forWard(View v){


        if(backcounter>0) {

            backcounter -= 1;
            Integer eteenpain = backcounter + 1;

            Integer indeksit = webpagelist.size()-1;


                String sivusto = webpagelist.get(webpagelist.size() - eteenpain).getName();
                webView.loadUrl(sivusto);
                searchfield.setText(sivusto);


        }else{

            System.out.println("No history");
        }




    }





















    public void reFresh(View v){




        osoite = webView.getUrl().toString();

        webView.loadUrl(osoite);
        searchfield.setText(osoite);



    }



}