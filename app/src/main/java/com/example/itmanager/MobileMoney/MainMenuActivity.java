package com.example.itmanager.MobileMoney;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MainMenuActivity extends AppCompatActivity
{
    //Intent toy;
    // Declaring layout button, edit texts
    Button login,transfer,transaction;
    TextView transhist,title,membername,memberaccount,memberbalance;
    ProgressBar progressBar;
    Spinner chapter,namecredit;
    String temp = "";
    String temp1 = "";
    String temp2 = "";
    String temp3 = "";
    String temp4 = "";
    // End Declaring layout button, edit texts
    ProgressDialog progressDialog;
    // Declaring connection variables
    Connection con;
    String un,pass,db,ip;
    //End Declaring connection variables

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainmenu);
//
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_recents:
                        Intent myIntent = new Intent(MainMenuActivity.this,
                                MainMenuActivity.class);
                        startActivity(myIntent);
                        //    Toast.makeText(MainActivity.this, "Recents", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_favorites:
                        Intent myIntent1 = new Intent(MainMenuActivity.this,
                                MenuActivity.class);
                        startActivity(myIntent1);
                        //  Toast.makeText(MainActivity.this, "Favorites", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_nearby:
                        Intent myIntent2 = new Intent(MainMenuActivity.this,
                                Otherservices.class);
                        startActivity(myIntent2);
                        // Toast.makeText(MainActivity.this, "Nearby", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });

        // Locate the button in activity_main.xml
        // Getting values from button, texts and progress bar
        transfer = (Button) findViewById(R.id.button);
     //   transfer = (Button) findViewById(R.id.button13);
        transaction = (Button) findViewById(R.id.button14);
        membername = (TextView) findViewById(R.id.membername);
        memberaccount = (TextView) findViewById(R.id.memberaccount);
        memberbalance = (TextView) findViewById(R.id.memberbalance);
        transhist = (TextView) findViewById(R.id.textView14);
        title = (TextView) findViewById(R.id.textView3);
       // progressBar = (ProgressBar) findViewById(R.id.progressBar);
        //chapter = (Spinner) findViewById(R.id.spinner3);


        // Declaring Server ip, username, database name and password
        ip = "192.168.0.13";
        db = "NACCUGSoft_DATA";
        un = "DB_A45989_Walleto_admin";
        pass = "Kuyateh@k13";
        // Declaring Server ip, username, database name and password

        progressDialog = new ProgressDialog(MainMenuActivity.this);
        progressDialog.setMessage("Loading... Please Wait..."); // Setting Message
        progressDialog.setTitle("Loading"); // Setting Title
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER); // Progress Dialog Style Spinner
        progressDialog.show(); // Display Progress Dialog
        progressDialog.setCancelable(false);

        CheckLogin checkLogin = new CheckLogin();// this is the Asynctask, which is used to process in background to reduce load on app process
        checkLogin.execute("");

        // Setting up the function when button login is clicked
        transfer.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //CheckLogin checkLogin = new CheckLogin();// this is the Asynctask, which is used to process in background to reduce load on app process
                //checkLogin.execute("");

                Intent myIntent = new Intent(MainMenuActivity.this,
                        AccountTransfer.class);
                startActivity(myIntent);
                //super.onCreate(savedInstanceState);
                // setContentView(R.layout.transfer_layout);
            }
        });

        transaction.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                Intent myIntent = new Intent(MainMenuActivity.this,
                        ShowTransactionsList.class);
                startActivity(myIntent);
                //super.onCreate(savedInstanceState);
                // setContentView(R.layout.transfer_layout);
            }
        });

        //End Setting up the function when button login is clicked
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_options_menu, menu);
        return true;}

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                startActivity(new Intent(this, MainMenuActivity.class));
                return true;
            case R.id.payment:
                startActivity(new Intent(this, MenuActivity.class));
                return true;
            case R.id.others:
                startActivity(new Intent(this, Otherservices.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public class CheckLogin extends AsyncTask<String,String,String>
    {
        String z = "";
        Boolean isSuccess = false;

      //  @Override
        protected void onPreExecute()
        {
      //      progressBar.setVisibility(View.VISIBLE);

          //  ProgressDialog dialog = ProgressDialog.show(context, "Loading", "Please wait...", true);
        }

        @Override
        protected void onPostExecute(String r)
        {
          //  progressBar.setVisibility(View.GONE);
            Toast.makeText(MainMenuActivity.this, r, Toast.LENGTH_SHORT).show();
            if(isSuccess)
            {
                Toast.makeText(MainMenuActivity.this , "Query Successfull" , Toast.LENGTH_LONG).show();
                //finish();
            }
        }
        @Override
        protected String doInBackground(String... params)
        {
            String MemberName = membername.getText().toString();
            String AccountNumber = memberaccount.getText().toString();
            String MemberBalance = memberbalance.getText().toString();
            GlobalClass globalClass = (GlobalClass) getApplicationContext();
            // globalClass.getLoginCode();
                try
                {
                    con = connectionclass();        // Connect to database
                    if (con == null)
                    {
                        z = "Check Your Internet Access!";
                    }
                    else
                    {

                  //      String query = "select * from collection where left(name_creditunion,3)= '" + bar.trim().toString() + "' and periods = '" + periodd.trim().toString() + "'";
                        String query = "select g.cacctnumb, g.cacctname,g.nbookbal,g.acode from glmast g where g.acode = '260' and g.LoginCode = '"+globalClass.getLoginCode()+"'";
                        Statement stmt = con.createStatement();
                        ResultSet rs = stmt.executeQuery(query);

                        if (rs.next()) {
                            //sfnam.trim().equals("sfnam");
                            //String s1 = rs.getString("chapters");
                            String s2 = rs.getString("cacctnumb");
                            String s3 = rs.getString("cacctname");
                            String s4 = rs.getString("nbookbal");
                            String Code = globalClass.getLoginCode().substring(0, 3);
                           // String S5 = rs.getString("total_female");

                            temp = temp + "\t Acount Number: " + Code + s2;
                             temp1 = temp1 + "\t Account Name: " + s3;
                            temp2 = temp2 + "\t Account Balance: " + s4;

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    membername.setText(temp);
                                   memberaccount .setText(temp1);
                                   memberbalance.setText(temp2);

                                }
                            });

                        }
                        else
                            {
                            z = "No information available!!!";
                            isSuccess = false;
                        }
                        //z = "Insert successful";
                        //isSuccess=true;
                        //con.close();
                    }
                }
                catch (Exception ex)
                {
                    isSuccess = false;
                    z = ex.getMessage();
                }
            progressDialog.dismiss();
            return z;
        }
    }
   // Back Press
   // Back press
   @Override
   public void onBackPressed() {
       super.onBackPressed();
       Intent myIntent = new Intent(MainMenuActivity.this,
               MenuActivity.class);
       startActivity(myIntent);
       finish();
   }

   // Connection String
    @SuppressLint("NewApi")
    public Connection connectionclass()
    {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection connection = null;
        String ConnectionURL = null;
        try
        {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            // ConnectionURL = "jdbc:jtds:sqlserver://" + ip +";databaseName="+ db + ";user=" + un+ ";password=" + pass + ";";
            //  ConnectionURL = "jdbc:jtds:sqlserver://" 212.60.68.150:1433";databaseName="+ db + ";user=" + un+ ";password=" + pass + ";";
            ConnectionURL = "jdbc:jtds:sqlserver://SQL5081.site4now.net/DB_A45989_Walleto;user=" + un + ";password=" + pass + ";";
            connection = DriverManager.getConnection(ConnectionURL);
        }
        catch (SQLException se)
        {
            Log.e("error here 1 : ", se.getMessage());
        }
        catch (ClassNotFoundException e)
        {
            Log.e("error here 2 : ", e.getMessage());
        }
        catch (Exception e)
        {
            Log.e("error here 3 : ", e.getMessage());
        }
        return connection;
    }
}
