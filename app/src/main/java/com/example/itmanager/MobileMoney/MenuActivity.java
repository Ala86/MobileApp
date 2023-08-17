package com.example.itmanager.MobileMoney;
//package="com.example.itmanager.LocalTransfer"

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;

 /*
        This source code could be used for academic purposes only. Posting on other websites or blogs is only allowed with a dofollow link to the orignal content.
    */

public class MenuActivity extends AppCompatActivity
{
    //Intent toy;
    // Declaring layout button, edit texts
    Button cashpower,airtime,water,otherPayment;
   // EditText username,password;
   // ProgressBar progressBar;
   // Spinner chapter,namecredit;
    // End Declaring layout button, edit texts

    // Declaring connection variables
    Connection con;
    String un,pass,db,ip;
    //End Declaring connection variables

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.paymentsmenu);

        // Locate the button in activity_main.xml
        // Getting values from button, texts and progress bar
        cashpower = (Button) findViewById(R.id.button);
        airtime = (Button) findViewById(R.id.button13);
        water = (Button) findViewById(R.id.button14);
        otherPayment = (Button) findViewById(R.id.button15);
      // otherservices = (Button) findViewById(R.id.button18);
        //chapter = (Spinner) findViewById(R.id.spinner3);

        // End Getting values from button, texts and progress bar

        // Declaring Server ip, username, database name and password

        // Setting up the function when button login is clicked
        cashpower.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
              //  CheckLogin checkLogin = new CheckLogin();// this is the Asynctask, which is used to process in background to reduce load on app process
               // checkLogin.execute("");
                Intent myIntent = new Intent(MenuActivity.this,
                        CashPowerActivity.class);
                startActivity(myIntent);
           }
        });

        airtime.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent myIntent = new Intent(MenuActivity.this,
                        AirtimeActivity.class);
                startActivity(myIntent);
            }
        });

        water.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //  CheckLogin checkLogin = new CheckLogin();// this is the Asynctask, which is used to process in background to reduce load on app process
                // checkLogin.execute("");
                Intent myIntent = new Intent(MenuActivity.this,
                        withdrawalActivity.class);
                startActivity(myIntent);
            }
        });

        otherPayment.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //  CheckLogin checkLogin = new CheckLogin();// this is the Asynctask, which is used to process in background to reduce load on app process
                // checkLogin.execute("");
                Intent myIntent = new Intent(MenuActivity.this,
                        depositAcitivity.class);
                startActivity(myIntent);
            }
        });
        //End Setting up the function when button login is clicked
    }

    // Back press
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent myIntent = new Intent(MenuActivity.this,
                MainMenuActivity.class);
        startActivity(myIntent);
        finish();
    }
}
