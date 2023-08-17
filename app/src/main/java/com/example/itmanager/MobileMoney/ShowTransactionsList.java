package com.example.itmanager.MobileMoney;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;


public class ShowTransactionsList extends AppCompatActivity {

    String temp1 = "";
    String temp2 = "";
    String temp3 = "";

    ListView list;

    TextView head,result;

    Connection con;
    String un,pass,db,ip;
    ProgressDialog progressDialog;
    SimpleAdapter ad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_transaction);

        head = (TextView) findViewById(R.id.txthead);
        result = (TextView) findViewById(R.id.resultSett);
        list = (ListView) findViewById(R.id.simpleListView);
      //  charges = (TextView) findViewById(R.id.textView5);

        // Declaring Server ip, username, database name and password
        ip = "192.168.0.13";
        db = "NACCUGSoft_DATA";
        un = "DB_A45989_Walleto_admin";
        pass = "Kuyateh@k13";


        //chapter = (Spinner) findViewById(R.id.spinner3);
      //  progressDialog = new ProgressDialog(ShowTransactionsList.this);
      //  progressDialog.setMessage("Loading Details ... Please Wait..."); // Setting Message
      ///  progressDialog.setTitle("ProgressDialog"); // Setting Title
      //  progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER); // Progress Dialog Style Spinner
      //  progressDialog.show(); // Display Progress Dialog
      //  progressDialog.setCancelable(false);

     //   DisplayDetails displayDetails = new DisplayDetails();
     //   displayDetails.execute("");

      //  SimpleAdapter ad;
        List<Map<String, String>> Mydatalist = null;
        ListItem  mydata = new ListItem();
        Mydatalist = mydata.getlist();

        String[] ala = {"resultSet", "textview2","textview3"};
        int[] Town = {R.id.resultSet, R.id.textview2, R.id.textview3};
        ad = new SimpleAdapter(ShowTransactionsList.this, Mydatalist, R.layout.mylist, ala, Town);
        list.setAdapter(ad);
    }

    // Loading the Account

    public class DisplayDetails extends AsyncTask<String,String,String>
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
         //   Toast.makeText(ShowTransaction.this, r, Toast.LENGTH_SHORT).show();
            if(isSuccess)
            {
             //   Toast.makeText(CashPowerActivity.this , "Query Successfull" , Toast.LENGTH_LONG).show();
                //finish();
            }
        }
        @Override
        protected String doInBackground(String... params)
        {
            // String MemberName = membername.getText().toString();
           // String AccountNumber = desc.getText().toString();
           // String MemberBalance = balance.getText().toString();

            GlobalClass globalClass = (GlobalClass) getApplicationContext();

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
                    //      String query = "select * from collection where left(name_creditunion,3)= '" + bar.trim().toString() + "' and periods = '" + periodd.trim().toString() + "'";
                    String query = "select top(15) ctrandesc,ntranamnt, dtrandate from tranhist where right(cacctnumb,3) = '260' and LoginCode = '"+globalClass.getLoginCode().trim()+"'";
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery(query);

                    if (rs.next()) {

                        String s2 = rs.getString("ctrandesc");
                        String s3 = rs.getString("ntranamnt");
                        String s4 = rs.getString("dtrandate");
                       // String Code = globalClass.getLoginCode().substring(0, 3);

                        // temp = temp + "\t Member Name: " + s2;
                        temp1 = temp1 + "\t"+ s2;
                        temp2 = temp2 + "\t" + s3;
                        temp3 = temp3 + "\t" + s4;
                        String[] data = {temp1,temp2,temp3};
                   //     final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.mylist, data);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                              //   .setText(temp);
                       //         list.setAdapter(arrayAdapter);
                              //  desc = temp1;
                              //  amount = temp2;
                              //  ddate = temp3;

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