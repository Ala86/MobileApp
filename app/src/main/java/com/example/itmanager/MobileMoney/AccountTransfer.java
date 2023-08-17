package com.example.itmanager.MobileMoney;
//package="com.example.itmanager.LocalTransfer"

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.icu.text.SimpleDateFormat;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class AccountTransfer extends AppCompatActivity
{
    //Intent toy;
    // Declaring layout button, edit texts
    Button sumbmit,deposit,withdrawal,transfer,otherservices;
    EditText baccount,amount,phone,pinnumber;
    TextView account,balance,charges;
    // ProgressBar progressBar;
    private JsonPlaceHolderApi jsonPlaceHolderApi;
    String temp1 ="";
    String temp2 ="";
    ProgressDialog progressDialog;
    // Declaring connection variables
    Connection con;
    String un,pass,db,ip;
    //End Declaring connection variables

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.transfer);

        // Locate the button in activity_main.xml
        // Getting values from button, texts and progress bar
        account = (TextView) findViewById(R.id.textView3);
        balance = (TextView) findViewById(R.id.textView4);
        charges = (TextView) findViewById(R.id.textView5);
      //  meternumber = (EditText) findViewById(R.id.editText1);
        baccount = (EditText) findViewById(R.id.editText2);
        amount = (EditText) findViewById(R.id.editText3);
        pinnumber = (EditText) findViewById(R.id.editText4);
        sumbmit = (Button) findViewById(R.id.button);
        //chapter = (Spinner) findViewById(R.id.spinner3);

        // End Getting values from button, texts and progress bar

        // Declaring Server ip, username, database name and password
        ip = "192.168.0.13";
        db = "NACCUGSoft_DATA";
        un = "DB_A45989_Walleto_admin";
        pass = "Kuyateh@k13";

        //chapter = (Spinner) findViewById(R.id.spinner3);
        progressDialog = new ProgressDialog(AccountTransfer.this);
        progressDialog.setMessage("Loading Details ... Please Wait..."); // Setting Message
        progressDialog.setTitle("ProgressDialog"); // Setting Title
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER); // Progress Dialog Style Spinner
        progressDialog.show(); // Display Progress Dialog
        progressDialog.setCancelable(false);

        // Display the accounts
        DisplayDetails displayDetails = new DisplayDetails();
        displayDetails.execute("");

        sumbmit.setOnClickListener(new View.OnClickListener()
        {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v)
            {

                String Amount = amount.getText().toString();
                // balance = (TextView) findViewById(R.id.textView4);
                String Balance = balance.getText().toString();
                Date currentTime = Calendar.getInstance().getTime();
                GlobalClass globalClass = (GlobalClass) getApplicationContext();
                double Amountt = Double.parseDouble(Amount);
                double Balances = Double.parseDouble(Balance);

                if (Amountt <= Balances && Balances > 0.00) {
                    progressDialog = new ProgressDialog(AccountTransfer.this);
                    progressDialog.setMessage("Loading... Please Wait..."); // Setting Message
                    progressDialog.setTitle("ProgressDialog"); // Setting Title
                    progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER); // Progress Dialog Style Spinner
                    progressDialog.show(); // Display Progress Dialog
                    progressDialog.setCancelable(false);

                   // createPost();
                    MainAccount mainAccount = new MainAccount();// this is the Asynctask, which is used to process in background to reduce load on app process
                    mainAccount.execute("");
                    MainAccountCharges mainAccountCharges = new MainAccountCharges();
                    mainAccountCharges.execute("");
                    Baccount baccount = new Baccount();
                    baccount.execute("");
                    UpdateMainAccount updateMainAccount = new UpdateMainAccount();
                    updateMainAccount.execute("");
                    UpdateMainAccountCharges updateMainAccountCharges = new UpdateMainAccountCharges();
                    updateMainAccountCharges.execute("");
                    UpdateBAccount updateBAccount = new UpdateBAccount();
                    updateBAccount.execute("");
                }else{
                    Toast.makeText(AccountTransfer.this , "Please recharge your Walleto!!! " , Toast.LENGTH_LONG).show();
                }
                //super.onCreate(savedInstanceState);
                // setContentView(R.layout.transfer_layout);
            }
        });
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
            Toast.makeText(AccountTransfer.this, r, Toast.LENGTH_SHORT).show();
            if(isSuccess)
            {
                Toast.makeText(AccountTransfer.this , "Query Successfull" , Toast.LENGTH_LONG).show();
                //finish();
            }
        }
        @Override
        protected String doInBackground(String... params)
        {
            // String MemberName = membername.getText().toString();
            String AccountNumber = account.getText().toString();
            String MemberBalance = balance.getText().toString();

            GlobalClass globalClass = (GlobalClass) getApplicationContext();
          //  globalClass.setCcustcode(globalClass.getLoginCode().substring(4, 6));
            // globalClass.getLoginCode();

            //  String bar = namecreditt.substring(0, 3);

            // if(AccountNumber.trim().equals(""))
            // if(usernam.trim().equals("")|| passwordd.trim().equals(""))
            //    z = "Please enter Username and Password";
            //   else
            //   {
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
                    String query = "select cacctnumb, cacctname,nbookbal,acode from glmast where acode = '260' and LoginCode = '"+globalClass.getLoginCode().trim()+"'";
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery(query);

                    if (rs.next()) {

                        String s2 = rs.getString("cacctnumb");
                        String s3 = rs.getString("nbookbal");
                        String Code = globalClass.getLoginCode().substring(0, 3);

                        // temp = temp + "\t Member Name: " + s2;
                        temp1 = temp1 + "\t" +Code + s2;
                        temp2 = temp2 + "\t" + s3;

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                // membername.setText(temp);
                                account .setText(temp1);
                                balance.setText(temp2);

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

    // Main Account insert
    public class MainAccount extends AsyncTask<String,String,String>
    {
        String z = "";
        Boolean isSuccess = false;

        @Override
        protected void onPostExecute(String r)
        {
            // progressBar.setVisibility(View.GONE);
            // Toast.makeText(CashPowerActivity.this, r, Toast.LENGTH_SHORT).show();
            if(isSuccess)
            {
                Toast.makeText(AccountTransfer.this , "Insert Successfull" , Toast.LENGTH_LONG).show();
                //finish();
            }
        }
        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        protected String doInBackground(String... params)
        {
            String Account = account.getText().toString();
            String BAccount = baccount.getText().toString();
            String Balance = balance.getText().toString();
            String Amount = amount.getText().toString();
            String PinNumber = pinnumber.getText().toString();
            String cdesc = "Account Transfer "+BAccount.trim();
            Date currentTime = Calendar.getInstance().getTime();

            Date c = Calendar.getInstance().getTime();
            // System.out.println("Current time => " + c);
            SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
            String dDate = df.format(c);

            GlobalClass globalClass = (GlobalClass) getApplicationContext();
            String AccountNo = Account.substring(4, 11);
            double Amountt = Double.parseDouble(Amount);

            if(Account.trim().equals("")&& Balance.trim().equals("")&& Amount.trim().equals("")&& PinNumber.trim().equals(""))
                // if(usernam.trim().equals("")|| passwordd.trim().equals(""))
                z = "Please enter Enter the period";
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
                        String query = "insert into tranhist (cacctnumb,dpostdate,dvaluedate,ntranamnt,ctrandesc,ctrancode,ccurrcode,dtrandate,branchid,ccustcode,LoginCode) values ('" + AccountNo.trim() + "','" + dDate.trim() + "','" + dDate.trim() + "','" + -Amountt + "','" + cdesc.trim() + "','" + 34 + "','" + 1 + "','" + dDate.trim() + "','" + 16 +"','" + globalClass.getCcustcode().trim()+ "','" + globalClass.getLoginCode().trim() + "')";
                        PreparedStatement preparedStatement = con.prepareStatement(query);
                        preparedStatement.executeUpdate();
                        z = "Main account Insert successful";
                        isSuccess=true;
                        con.close();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                // amount.setText("");
                                // phone.setText("");
                                // pinnumber.setText("");
                                // meternumber.setText("");
                                // charges.setText("");
                            }
                        });
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
    }

    // Account transfer chares
    public class MainAccountCharges extends AsyncTask<String,String,String>
    {
        String z = "";
        Boolean isSuccess = false;

        @Override
        protected void onPostExecute(String r)
        {
            // progressBar.setVisibility(View.GONE);
            // Toast.makeText(CashPowerActivity.this, r, Toast.LENGTH_SHORT).show();
            if(isSuccess)
            {
                Toast.makeText(AccountTransfer.this , "Insert Successfull" , Toast.LENGTH_LONG).show();
                //finish();
            }
        }
        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        protected String doInBackground(String... params)
        {
            String Account = account.getText().toString();
            String Balance = balance.getText().toString();
            String Charges = charges.getText().toString();
            String PinNumber = pinnumber.getText().toString();
            String cdesc = "Account Transfer Charges "+Account.trim();
            Date currentTime = Calendar.getInstance().getTime();

            Date c = Calendar.getInstance().getTime();
            // System.out.println("Current time => " + c);
            SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
            String dDate = df.format(c);

            GlobalClass globalClass = (GlobalClass) getApplicationContext();
            String AccountNo = Account.substring(4, 11);
            double Charge = Double.parseDouble(Charges);

            if(Account.trim().equals("")&& Balance.trim().equals("")&& Charges.trim().equals("")&& PinNumber.trim().equals(""))
                z = "Please enter Enter the period";
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
                        String query = "insert into tranhist (cacctnumb,dpostdate,dvaluedate,ntranamnt,ctrandesc,ctrancode,ccurrcode,dtrandate,branchid,ccustcode,LoginCode) values ('" + AccountNo.trim() + "','" + dDate.trim() + "','" + dDate.trim() + "','" + -Charge + "','" + cdesc.trim() + "','" + 34 + "','" + 1 + "','" + dDate.trim() + "','" + 16 +"','" + globalClass.getCcustcode().trim()+ "','" + globalClass.getLoginCode().trim() + "')";
                        PreparedStatement preparedStatement = con.prepareStatement(query);
                        preparedStatement.executeUpdate();
                        z = "Main account Insert successful";
                        isSuccess=true;
                        con.close();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                // amount.setText("");
                                // phone.setText("");
                                // pinnumber.setText("");
                                // meternumber.setText("");
                                // charges.setText("");
                            }
                        });
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
    }

    // Beneficiary Account

    public class Baccount extends AsyncTask<String,String,String>
    {
        String z = "";
        Boolean isSuccess = false;

        @Override
        protected void onPostExecute(String r)
        {
            // progressBar.setVisibility(View.GONE);
            Toast.makeText(AccountTransfer.this, r, Toast.LENGTH_SHORT).show();
            if(isSuccess)
            {
                Context context = getApplicationContext();
                CharSequence text = "Saving Data... Please wait...!";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                //  Toast.makeText(CashPowerActivity.this , "Insert Successfull" , Toast.LENGTH_LONG).show();
                //finish();
            }
        }
        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        protected String doInBackground(String... params)
        {
            String Baccount = baccount.getText().toString();
            String Balance = balance.getText().toString();
            String Account = account.getText().toString();
            String Amount = amount.getText().toString();
            String Phone = phone.getText().toString();
            String PinNumber = pinnumber.getText().toString();
            String cdesc = "Account Transfer " +Account.trim();
            Date currentTime = Calendar.getInstance().getTime();
            // System.out.println("Current time => " + currentTime);
            Date c = Calendar.getInstance().getTime();

            SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
            String dDate = df.format(c);
            GlobalClass globalClass = (GlobalClass) getApplicationContext();
            String compid = Baccount.substring(0, 3);
            String BAccountNo = Baccount.substring(4, 11);
            String Mcode = BAccountNo.substring(4, 6);
            String LoginCode = compid.trim()+Mcode.trim();

            double Amountt = Double.parseDouble(Amount);

            if(Baccount.trim().equals("") && Balance.trim().equals("") && Amount.trim().equals("") && PinNumber.trim().equals(""))
                // if(usernam.trim().equals("")|| passwordd.trim().equals(""))
                z = "Please enter Enter the period";
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
                        String query = "insert into tranhist (cacctnumb,dpostdate,dvaluedate,ntranamnt,ctrandesc,ctrancode,ccurrcode,dtrandate,branchid,ccustcode,LoginCode) values ('" + BAccountNo.trim() + "','" + dDate.trim() + "','" + dDate.trim() + "','" + Amountt + "','" + cdesc.trim() + "','" + 34 + "','" + 1 + "','" + dDate.trim() + "','" + 16 + "','" + Mcode.trim() + "','" + LoginCode.trim() + "')";
                        PreparedStatement preparedStatement = con.prepareStatement(query);
                        preparedStatement.executeUpdate();
                        z = "Beneficiary account Insert successful";
                        isSuccess=true;
                        con.close();

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
    }

    // Updating the Main Account

    public class UpdateMainAccount extends AsyncTask<String,String,String> {
        String z = "";
        Boolean isSuccess = false;
        @Override
        protected void onPostExecute(String r) {
            //  progressBar.setVisibility(View.GONE);
            //   Toast.makeText(withdrawalActivity.this, r, Toast.LENGTH_SHORT).show();
            if (isSuccess) {
                Toast.makeText(AccountTransfer.this, "Update Successfull", Toast.LENGTH_LONG).show();
            }
        }

        //  @Override
        protected String doInBackground(String... params) {
            String AccountNumber = account.getText().toString();
            String Amount = amount.getText().toString();

            GlobalClass globalClass = (GlobalClass) getApplicationContext();
             String AccountNo = AccountNumber.substring(4, 11);

            if (Amount.trim().equals("") && AccountNumber.trim().equals(""))
                z = "Please enter Ref. Number";
            else {
                try {
                    con = connectionclass();        // Connect to database
                    if (con == null) {
                        z = "Check Your Internet Access!";
                    } else {
                        // Change below query according to your own database.
                        String query = "update glmast set nbookbal = nbookbal - '" + Amount.trim() + "' where cacctnumb = '" + AccountNo.trim() + "' and LoginCode = '" + globalClass.getLoginCode().trim() + "'";
                        PreparedStatement preparedStatement = con.prepareStatement(query);
                        preparedStatement.executeUpdate();// rs.first();
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

    // Updating the Main Account Charges

    public class UpdateMainAccountCharges extends AsyncTask<String,String,String> {
        String z = "";
        Boolean isSuccess = false;
        @Override
        protected void onPostExecute(String r) {
            //  progressBar.setVisibility(View.GONE);
            //   Toast.makeText(withdrawalActivity.this, r, Toast.LENGTH_SHORT).show();
            if (isSuccess) {
                Toast.makeText(AccountTransfer.this, "Update Charges Successfull", Toast.LENGTH_LONG).show();
            }
        }

        //  @Override
        protected String doInBackground(String... params) {
            String AccountNumber = account.getText().toString();
            String Charges = charges.getText().toString();

            GlobalClass globalClass = (GlobalClass) getApplicationContext();
            String AccountNo = AccountNumber.substring(4, 11);

            if (Charges.trim().equals("") && AccountNumber.trim().equals(""))
                z = "Please enter Ref. Number";
            else {
                try {
                    con = connectionclass();        // Connect to database
                    if (con == null) {
                        z = "Check Your Internet Access!";
                    } else {
                        // Change below query according to your own database.
                        String query = "update glmast set nbookbal = nbookbal - '" + Charges.trim() + "' where cacctnumb = '" + AccountNo.trim() + "' and LoginCode = '" + globalClass.getLoginCode().trim() + "'";
                        PreparedStatement preparedStatement = con.prepareStatement(query);
                        preparedStatement.executeUpdate();// rs.first();
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



    // Update Charges

    public class UpdateBAccount extends AsyncTask<String,String,String> {
        String z = "";
        Boolean isSuccess = false;

        //@Override
        // protected void onPreExecute() {
        //    progressBar.setVisibility(View.VISIBLE);
        //}

        @Override
        protected void onPostExecute(String r) {
            //  progressBar.setVisibility(View.GONE);
            //   Toast.makeText(withdrawalActivity.this, r, Toast.LENGTH_SHORT).show();
            if (isSuccess) {
                Toast.makeText(AccountTransfer.this, "Update Beneficiary account Successfull", Toast.LENGTH_LONG).show();
            }
        }

        //  @Override
        protected String doInBackground(String... params) {
            String AccountNumber = baccount.getText().toString();
            String Amount = amount.getText().toString();

            GlobalClass globalClass = (GlobalClass) getApplicationContext();
            String compid = AccountNumber.substring(0, 3);
            String BAccountNo = AccountNumber.substring(4, 11);
            String Mcode = BAccountNo.substring(4, 6);
            String LoginCode = compid.trim()+Mcode.trim();
         //   GlobalClass globalClass = (GlobalClass) getApplicationContext();
            // globalClass.getLoginCode();
            //    String compid = LloginCode.substring(0, 3);

            if (Amount.trim().equals("") && AccountNumber.trim().equals(""))
                z = "Please enter Ref. Number";
            else {
                try {
                    con = connectionclass();        // Connect to database
                    if (con == null) {
                        z = "Check Your Internet Access!";
                    } else {
                        // Change below query according to your own database.
                        String query = "update glmast set nbookbal = nbookbal + '" + Amount.trim() + "' where cacctnumb = '" + BAccountNo.trim() + "' and LoginCode = '" + LoginCode.trim() + "'";
                        PreparedStatement preparedStatement = con.prepareStatement(query);
                        preparedStatement.executeUpdate();// rs.first();
                        z = "Update successful!";
                        isSuccess = true;
                        con.close();
                    }

                } catch (Exception ex) {
                    isSuccess = false;
                    z = ex.getMessage();
                }
                progressDialog.dismiss();
            }
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

