
package com.example.itmanager.MobileMoney;
/**
 * Created by Alagie Kuyateh on 9/8/2018.
 */
import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

 /*
        This source code could be used for academic purposes only. Posting on other websites or blogs is only allowed with a dofollow link to the orignal content.
    */

public class depositAcitivity extends AppCompatActivity
{
    //Intent toy;
    // Declaring layout button, edit texts
    Button paid,pay;
    TextView mview,sfname,slname,mbalance;
    EditText refnumber,sphone,scity,rfname,rlname,rphone,rcity,cCharged,amount,identification;
    Spinner chapter,namecredit;
    ProgressBar progressBar;
    //String temp = "";
    String temp1 = "";
    String temp2 = "";
    String temp3 = "";
    String temp4 = "";
    String temp5 = "";
    String temp6 = "";
    String temp7 = "";
    String temp8 = "";
    String temp9 = "";
    String temp10 = "";
    // End Declaring layout button, edit texts

    // Declaring connection variables
    Connection con;
    //  connection conn;
    String un,pass,db,ip;
    //End Declaring connection variables

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.deposit);


        // Locate the button in activity_main.xml

        //init();

        //runOnUiThread(new Runnable() {
        //  @Override
        //  public void run() {
        // Getting values from button, texts and progress bar
        paid = (Button) findViewById(R.id.button);
        refnumber = (EditText) findViewById(R.id.editText3);
        mbalance = (TextView) findViewById(R.id.textView5);
        sfname = (TextView) findViewById(R.id.textView2);
        slname = (TextView) findViewById(R.id.textView4);
        amount = (EditText) findViewById(R.id.editText);
        //  rfname = (EditText) findViewById(R.id.editText8);
        // rlname = (EditText) findViewById(R.id.editText11);
        // rphone = (EditText) findViewById(R.id.editText9);
        //rcity = (EditText) findViewById(R.id.editText2);
        //cCharged = (EditText) findViewById(R.id.editText10);
        // amount = (EditText) findViewById(R.id.editText);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        // pay = (Button) findViewById(R.id.button2);
        //identification = (EditText) findViewById(R.id.editText12);
        //  }
        // });



        // End Getting values from button, texts and progress bar

        // Declaring Server ip, username, database name and password
        ip = "192.168.0.13";
        db = "NACCUGSoft_DATA";
        un = "sa";
        pass = "Whatever4999!";
        // Declaring Server ip, username, database name and password

        // Setting up the function when button login is clicked
        paid.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                CheckLogin checkLogin = new CheckLogin();// this is the Asynctask, which is used to process in background to reduce load on app process
                checkLogin.execute("");


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
            Toast.makeText(depositAcitivity.this, r, Toast.LENGTH_SHORT).show();
            //if(isSuccess)
            ///{
            // Toast.makeText(transferactivity.this , "Login Successfull" , Toast.LENGTH_LONG).show();

            // Start NewActivity.class
            //init();
            //finish();
            // }
        }


        @Override
        protected String doInBackground (String...params) {

            String refnum = refnumber.getText().toString();
            // String sfnam = sfname.getText().toString();
            //String slnam = slname.getText().toString();
            //String sphon = sphone.getText().toString();
            //String scit = scity.getText().toString();
            //String rfnam = rfname.getText().toString();
            //String rlnam = rlname.getText().toString();
            //String sphon = sphone.getText().toString();
            //String rphn = rphone.getText().toString();
            //String rcit = rcity.getText().toString();


            if (refnum.trim().equals(""))
                // if(usernam.trim().equals("")|| passwordd.trim().equals(""))
                z = "Please enter Ref. Number";
            else {
                try {
                    con = connectionclass();        // Connect to database
                    if (con == null) {
                        z = "Check Your Internet Access!";
                    } else {
                        // Change below query according to your own database.
                        String query = "select * from member where meternumber= '" + refnum.toString() + "' and paid = 1 ";
                        Statement stmt = con.createStatement();
                        ResultSet rs = stmt.executeQuery(query);

                        //sfnam v=(sfname)findViewById(R.id.v);
                        // rs.first();
                        //String temp = "";
                        //  String temp1 = "";
                        //String temp2 = "";
                        //String temp3 = "";
                        //String temp4 = "";
                        //String temp5 = "";
                        //String temp6 = "";
                        //String temp7 = "";
                        //String temp8 = "";
                        //String temp9 = "";
                        //String temp10 = "";

                        if (rs.next()) {
                            //sfnam.trim().equals("sfnam");
                            String s1 = rs.getString("amount");
                            String s2 = rs.getString("fname");
                            String s3 = rs.getString("lname");
                            //String s5 = rs.getString("city");
                            //String S6 = rs.getString("rfname");
                            //String s7 = rs.getString("rlname");
                            //String S8 = rs.getString("rphone");
                            // String s9 = rs.getString("rcity");
                            // String S10 = rs.getString("charged");
                            //String s11 = rs.getString("amount");
                            // String s12 = rs.getString("mobilenumber");


                            temp1 = temp1 + "\t Balance Amount: " + s1;
                            // temp1 = temp1 + "\t Hi, " + s2 + " This is your Mobile Money Balance:  " + s1 + ". Thank you so much. Have a nice day!";
                            temp2 = temp2 + "\t Member First Name: " + s2;
                            temp3 = temp3 + "\t Member Last name: " + s3;
                            // temp4 = temp4 + "\t Receiver First Name: " + S6;
                            // temp5 = temp5 + "\t Receiver Last Name: " + s7;
                            // temp6 = temp6 + "\t Receiver Phone: " + S8;
                            // temp7 = temp7 + "\t Receiver City: " + s9;
                            // temp8 = temp8 + "\t Amount Charged: " + S10;
                            //temp9 = temp9 + "\t Amount: " + s11;
                            //temp10 = temp10 + "\t Indentification No: " + s12;

                            //  rs.next();
                            z = "Query successful!";
                            isSuccess = true;
                            con.close();
                            // init();
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    //sfname.setText(temp);
                                    mbalance.setText(temp1);
                                    sfname.setText(temp2);
                                    slname.setText(temp3);
                                    // rfname.setText(temp4);
                                    // rlname.setText(temp5);
                                    //// rphone.setText(temp6);
                                    // rcity.setText(temp7);
                                    // cCharged.setText(temp8);
                                    // amount.setText(temp9);
                                    // identification.setText(temp10);
                                }
                            });

                        }
                        // v.setText(temp);
                        else {
                            z = "Invalid Ref. Number or Has been Paid!";
                            isSuccess = false;
                        }
                        // runOnUiThread(new Runnable() {
                        //runOnUiThread(new Runnable() {
                        //  @Override
                        //  public void run() {

                        //}});

                    }

                } catch (Exception ex) {
                    isSuccess = false;
                    z = ex.getMessage();
                }
            }
            return z;
            // }});
        }
    }

//Updating the amount table

    // Updating the payment

    public class paidMember extends AsyncTask<String,String,String> {
        String z = "";
        Boolean isSuccess = false;

        @Override
        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(String r) {
            progressBar.setVisibility(View.GONE);
            Toast.makeText(depositAcitivity.this, r, Toast.LENGTH_SHORT).show();
            if (isSuccess) {
                Toast.makeText(depositAcitivity.this, "Payment Successfull", Toast.LENGTH_LONG).show();
            }
        }

        //  @Override
        protected String doInBackground(String... params) {
            String refnum = refnumber.getText().toString();

            if (refnum.trim().equals(""))
                // if(usernam.trim().equals("")|| passwordd.trim().equals(""))
                z = "Please enter Ref. Number";
            else {
                try {
                    con = connectionclass();        // Connect to database
                    if (con == null) {
                        z = "Check Your Internet Access!";
                    } else {
                        // Change below query according to your own database.
                        String query = "update member set amount = mbalance + amount where meternumber= '" + refnum.toString() + "' and paid = 1";
                        // Statement stmt = con.createStatement();
                        //ResultSet rs = stmt.executeQuery(query);

                        PreparedStatement preparedStatement = con.prepareStatement(query);
                        preparedStatement.executeUpdate();// rs.first();
                        //ResultSet rs = preparedStatement.executeQuery();
                        //String temp="";

                        //if (con == null) {
                        z = "Update successful!";
                        isSuccess = true;
                        con.close();
                    }

                } catch (Exception ex) {
                    isSuccess = false;
                    z = ex.getMessage();
                }
            }
            return z;
        }
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
            ConnectionURL = "jdbc:jtds:sqlserver://212.60.68.150:1433/Nawec_Cash_Power_Data;user=" + un + ";password=" + pass + ";";
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
