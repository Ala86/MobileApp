package com.example.itmanager.MobileMoney;
//package="com.example.itmanager.LocalTransfer"

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;

 /*
        This source code could be used for academic purposes only. Posting on other websites or blogs is only allowed with a dofollow link to the orignal content.
    */

public class Otherservices extends AppCompatActivity
{
    //Intent toy;
    // Declaring layout button, edit texts
    Button memberenquiry,deposit,withdrawal,transfer,otherservices;
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
        setContentView(R.layout.otherservices);

        // Locate the button in activity_main.xml
        // Getting values from button, texts and progress bar
        memberenquiry = (Button) findViewById(R.id.button9);
        deposit = (Button) findViewById(R.id.button10);
        withdrawal = (Button) findViewById(R.id.button11);
        // transfer = (Button) findViewById(R.id.button6);
        otherservices = (Button) findViewById(R.id.button12);

        //chapter = (Spinner) findViewById(R.id.spinner3);

        // End Getting values from button, texts and progress bar

        // Declaring Server ip, username, database name and password

        // Setting up the function when button login is clicked
       // memberenquiry.setOnClickListener(new View.OnClickListener()
        //{
          //  @Override
            //public void onClick(View v)
            //{
              //  Intent myIntent = new Intent(MenuActivity.this,
                //        transferactivity.class);
                //startActivity(myIntent);
            //}
        //});

     //   transfer.setOnClickListener(new View.OnClickListener()
       // {
         //   @Override
          //  public void onClick(View v)
          //  {
       //         Intent myIntent = new Intent(MenuActivity.this,
         //               transfer_activity.class);
           //     startActivity(myIntent);}
       // });

       // withdrawal.setOnClickListener(new View.OnClickListener()
       // {
        //    @Override
         //   public void onClick(View v)
          //  {
            //   Intent myIntent = new Intent(MenuActivity.this,
             //           withdrawalActivity.class);
             //   startActivity(myIntent);
           // }
        //});

       // deposit.setOnClickListener(new View.OnClickListener()
       // {
        //    @Override
        //    public void onClick(View v)
         //   {
           //     Intent myIntent = new Intent(MenuActivity.this,
            //            depositAcitivity.class);
            //    startActivity(myIntent);
           // }
      //  });

        //End Setting up the function when button login is clicked
    }


}
