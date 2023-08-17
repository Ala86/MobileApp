package com.example.itmanager.MobileMoney;
//package="com.example.itmanager.LocalTransfer"

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

 /*
        This source code could be used for academic purposes only. Posting on other websites or blogs is only allowed with a dofollow link to the orignal content.
    */

public class MainActivity extends AppCompatActivity
{
    //Intent toy;
    // Declaring layout button, edit texts
    Button login;
    EditText username,password;
    ProgressBar progressBar;
    Spinner chapter,namecredit;
    // End Declaring layout button, edit texts

    // Declaring connection variables
    Connection con;
    String un,pass,db,ip;
    //End Declaring connection variables

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Locate the button in activity_main.xml

        //init();


        // Getting values from button, texts and progress bar
        login = (Button) findViewById(R.id.button);
        username = (EditText) findViewById(R.id.editText1);
        password = (EditText) findViewById(R.id.editText2);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        //chapter = (Spinner) findViewById(R.id.spinner3);

        // End Getting values from button, texts and progress bar

        // Declaring Server ip, username, database name and password
        ip = "192.168.0.13";
        db = "NACCUGSoft_DATA";
        un = "DB_A45989_Walleto_admin";
        pass = "Kuyateh@k13";
        // Declaring Server ip, username, database name and password
        // Setting up the function when button login is clicked
        login.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                CheckLogin checkLogin = new CheckLogin();// this is the Asynctask, which is used to process in background to reduce load on app process
                checkLogin.execute("");
               // Intent myIntent = new Intent(MainActivity.this,
               //         MainMenuActivity.class);
               // startActivity(myIntent);

                //super.onCreate(savedInstanceState);
               // setContentView(R.layout.transfer_layout);
            }
        });

        //End Setting up the function when button login is clicked
    }

    public class CheckLogin extends AsyncTask<String,String,String>
    {
        String z = "";
        Boolean isSuccess = false;

        @Override
        protected void onPreExecute()
        {
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(String r)
        {
            progressBar.setVisibility(View.GONE);
            Toast.makeText(MainActivity.this, r, Toast.LENGTH_SHORT).show();
            if(isSuccess)
            {
                Toast.makeText(MainActivity.this , "Login Successfull" , Toast.LENGTH_LONG).show();

                // Start NewActivity.class
                Intent myIntent = new Intent(MainActivity.this,
                        MainMenuActivity.class);
                startActivity(myIntent);
                 //init();
                //finish();
            }
        }
        @Override
        protected String doInBackground(String... params)
        {
            String usernam = username.getText().toString();
            //String usernam = username.getText().toString();
            String passwordd = password.getText().toString();

            GlobalClass globalClass = (GlobalClass) getApplicationContext();
            globalClass.setLoginCode(usernam);
            globalClass.setCcustcode(usernam.substring(4, 6));

            if(usernam.trim().equals(""))
               // if(usernam.trim().equals("")|| passwordd.trim().equals(""))
                z = "Please enter Username and Password";
            else
            {
                try
                {
                    con = connectionclass();        // Connect to database
                    if (con == null)
                    {
                        z = "Check Your Internet Access!";
                    }
                    else
                    {
                        // Change below query according to your own database.
                        String query = "select ccustcode, LoginCode from cusreg where LoginCode = '" + usernam.trim() + "' and pinnumber = '"+ passwordd.trim() +"'  ";
                       Statement stmt = con.createStatement();
                        //preparedStatement.executeUpdate();
                        ResultSet rs = stmt.executeQuery(query);
                        if(rs.next())
                        {
                           // globalVariable.setCcustcode("ccustcode");
                           // globalVariable.setLoginCode("LoginCode");
                        z = "Insert successful";
                        isSuccess=true;
                        con.close();
                          // init();
                        }
                        else
                        {
                            z = "Invalid Credentials!";
                            isSuccess = false;
                         }
                    }

                }
                catch (Exception ex)
                {
                    isSuccess = false;
                    z = ex.getMessage();
                }
            }
            return z;
        }

        //@Override
       // protected void onCreate(Bundle savedInstanceState)
       // {
            //super.onCreate(savedInstanceState);
            //setContentView(R.layout.transfer_layout);
    }

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
          //  ConnectionURL = "jdbc:jtds:sqlserver://" + ip +";databaseName="+ db + ";user=" + un+ ";password=" + pass + ";";
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
