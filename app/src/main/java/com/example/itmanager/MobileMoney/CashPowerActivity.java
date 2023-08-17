package com.example.itmanager.MobileMoney;
//package = "com.example.itmanager.LocalTransfer"

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.StrictMode;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;

 /*
        This source code could be used for academic purposes only. Posting on other websites or blogs is only allowed with a dofollow link to the orignal content.
    */

public class CashPowerActivity extends AppCompatActivity
{
    private  OkHttpClient client = new OkHttpClient();
    public final static String
            MESSAGE_KEY ="com.example.message_key";
    // Declaring layout button, edit texts
    Button sumbmit,deposit,withdrawal,transfer,otherservices;
    EditText meternumber,amount,phone,pinnumber;
    TextView account,balance,charges,result;
    // ProgressBar progressBar;
    ProgressDialog progressDialog;
   // private JsonPlaceHolderApi jsonPlaceHolderApi;
    //End Declaring connection variables
   String resultset = "";
   private Handler mHandler = new Handler(Looper.getMainLooper());
    Connection con;
    String un,pass,db,ip;
    private JsonPlaceHolderApi jsonPlaceHolderApi;
    String temp1 ="";
    String temp2 ="";


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cashpower);

        // Locate the button in activity_main.xml
        // Getting values from button, texts and progress bar
        account = (TextView) findViewById(R.id.textView3);
        balance = (TextView) findViewById(R.id.textView4);
        charges = (TextView) findViewById(R.id.textView5);
        result = (TextView) findViewById(R.id.textView8);
        meternumber = (EditText) findViewById(R.id.editText1);
        amount = (EditText) findViewById(R.id.editText2);
        phone = (EditText) findViewById(R.id.editText3);
        pinnumber = (EditText) findViewById(R.id.editText4);
        sumbmit = (Button) findViewById(R.id.button);

        // Declaring Server ip, username, database name and password
        ip = "192.168.0.13";
        db = "NACCUGSoft_DATA";
        un = "DB_A45989_Walleto_admin";
        pass = "Kuyateh@k13";

        //chapter = (Spinner) findViewById(R.id.spinner3);
        progressDialog = new ProgressDialog(CashPowerActivity.this);
        progressDialog.setMessage("Loading Details ... Please Wait..."); // Setting Message
        progressDialog.setTitle("ProgressDialog"); // Setting Title
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER); // Progress Dialog Style Spinner
        progressDialog.show(); // Display Progress Dialog
        progressDialog.setCancelable(false);

        DisplayDetails displayDetails = new DisplayDetails();
        displayDetails.execute("");


        amount.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                DisplayRange displayRange = new DisplayRange();
                displayRange.execute("");
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            //    Toast.makeText(getApplicationContext(),"before text change",Toast.LENGTH_LONG).show();
                //DisplayRange displayRange = new DisplayRange();
                //displayRange.execute("");
            }

            @Override
            public void afterTextChanged(Editable arg0) {
              // DisplayRange displayRange = new DisplayRange();
              // displayRange.execute("");
            //    Toast.makeText(getApplicationContext(),"after text change",Toast.LENGTH_LONG).show();
            }
        });

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

              //  if (Amountt <= Balances && Balances > 0.00) {
                    //progressDialog = new ProgressDialog(CashPowerActivity.this);
                    //progressDialog.setMessage("Loading... Please Wait..."); // Setting Message
                    //progressDialog.setTitle("ProgressDialog"); // Setting Title
                    //progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER); // Progress Dialog Style Spinner
                    //progressDialog.show(); // Display Progress Dialog
                    //progressDialog.setCancelable(false);

                   createPost();
                    // This is the insert

                   // CheckLogin checkLogin = new CheckLogin();// this is the Asynctask, which is used to process in background to reduce load on app process
                   // checkLogin.execute("");
                   // CashPowerCharges CashPowerCharges = new CashPowerCharges();
                   // CashPowerCharges.execute("");
                  //  UpdateCashPower updateCashPower = new UpdateCashPower();
                  //  updateCashPower.execute("");
                  //  UpdateCharges updateCharges = new UpdateCharges();
                  //  updateCharges.execute("");
             //   }else{
             //       Toast.makeText(CashPowerActivity.this , "Please recharge your Walleto!!! " , Toast.LENGTH_LONG).show();
             //   }
                //super.onCreate(savedInstanceState);
                // setContentView(R.layout.transfer_layout);
            }
        });


    }

    public class CheckLogin extends AsyncTask<String,String,String>
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
                Toast.makeText(CashPowerActivity.this , "Insert Successfull" , Toast.LENGTH_LONG).show();
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
           String Meternumber = meternumber.getText().toString();
            String Amount = amount.getText().toString();
           String Phone = phone.getText().toString();
            String PinNumber = pinnumber.getText().toString();
           String cdesc = "Cash Power";
            Date currentTime = Calendar.getInstance().getTime();

            Date c = Calendar.getInstance().getTime();
            String AccountNo = Account.substring(4, 15);
           // System.out.println("Current time => " + c);
            SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
            String dDate = df.format(c);

            GlobalClass globalClass = (GlobalClass) getApplicationContext();
            double Amountt = Double.parseDouble(Amount);

            if(Account.trim().equals("")&& Balance.trim().equals("")&& Charges.trim().equals("")&& Meternumber.trim().equals("")&& Amount.trim().equals("")&& Phone.trim().equals("")&& PinNumber.trim().equals(""))
                // if(usernam.trim().equals("")|| passwordd.trim().equals(""))
                z = "Please enter Enter the empty field";
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
                        String query = "insert into tranhist (cacctnumb,dpostdate,dvaluedate,ntranamnt,ctrandesc,ctrancode,ccurrcode,dtrandate,branchid,ccustcode,LoginCode,phoneNumber,srv_code) values ('" + AccountNo.trim() + "','" + dDate.trim() + "','" + dDate.trim() + "','" + -Amountt + "','" + cdesc.trim() + "','" + 33 + "','" + 1 + "','" + dDate.trim() + "','" + 16 +"','" + globalClass.getCcustcode().trim()+ "','" + globalClass.getLoginCode().trim() + "','" + Phone.trim() + "','" + Meternumber.trim() + "')";
                     //   String query = "insert into tranhist (cacctnumb,dpostdate,dvaluedate,ntranamnt,ctrandesc,ctrancode,ccurrcode,dtrandate,branchid,ccustcode,LoginCode,phoneNumber,srv_code) values ('" + Account + "','" + dDate + "','" + dDate + "','" + Amount + "','" + cdesc + "',30,'1','" + currentTime + "','16','" + globalClass.getCcustcode().trim() + "','" + globalClass.getLoginCode().trim() + "','" + Phone + "','" + Meternumber + "')";
                        PreparedStatement preparedStatement = con.prepareStatement(query);
                        preparedStatement.executeUpdate();
                        z = "Cash Power Insert successful";
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

 // Cash Power Charges

    public class CashPowerCharges extends AsyncTask<String,String,String>
    {
        String z = "";
        Boolean isSuccess = false;

        @Override
        protected void onPostExecute(String r)
        {
            // progressBar.setVisibility(View.GONE);
            Toast.makeText(CashPowerActivity.this, r, Toast.LENGTH_SHORT).show();
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
            String Account = account.getText().toString();
            String Balance = balance.getText().toString();
            String Charges = charges.getText().toString();
            String Meternumber = meternumber.getText().toString();
            String Amount = amount.getText().toString();
            String Phone = phone.getText().toString();
            String PinNumber = pinnumber.getText().toString();
            String cdesc = "Cash Power";
            Date currentTime = Calendar.getInstance().getTime();
           // System.out.println("Current time => " + currentTime);
            Date c = Calendar.getInstance().getTime();
            String AccountNo = Account.substring(4, 15);
            SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
            String dDate = df.format(c);
           // System.out.println("Current Date => " + dDate);
            GlobalClass globalClass = (GlobalClass) getApplicationContext();


            double Amountt = Double.parseDouble(Charges);
//,'" + 4*100/Amountt + "'
            if(Account.trim().equals("")&& Balance.trim().equals("")&& Charges.trim().equals("")&& Meternumber.trim().equals("")&& Amount.trim().equals("")&& Phone.trim().equals("")&& PinNumber.trim().equals(""))
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
                        String query = "insert into tranhist (cacctnumb,dpostdate,dvaluedate,ntranamnt,ctrandesc,ctrancode,ccurrcode,dtrandate,branchid,ccustcode,LoginCode,phoneNumber,srv_code) values ('" + AccountNo.trim() + "','" + dDate.trim() + "','" + dDate.trim() + "','" + -Amountt + "','" + cdesc.trim() + "','" + 33 + "','" + 1 + "','" + dDate.trim() + "','" + 16 + "','" + globalClass.getCcustcode().trim() + "','" + globalClass.getLoginCode().trim() + "','" + Phone.trim() + "','" + Meternumber.trim() + "')";
                        PreparedStatement preparedStatement = con.prepareStatement(query);
                        preparedStatement.executeUpdate();
                        z = "Cash Power Charges Insert successful";
                        isSuccess=true;
                        con.close();

                       // amount.setText("");
                       // phone.setText("");
                      //  pinnumber.setText("");
                      //  meternumber.setText("");
                      //  charges.setText("");
                        //  nmember.setText("");
                        //  cmember.setText("");

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
            Toast.makeText(CashPowerActivity.this, r, Toast.LENGTH_SHORT).show();
            if(isSuccess)
            {
                Toast.makeText(CashPowerActivity.this , "Query Successfull" , Toast.LENGTH_LONG).show();
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
            // globalClass.getLoginCode();
            //  String namecreditt = namecredit.getSelectedItem().toString();
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
                        temp1 = temp1 + "\t"+ Code + s2;
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

    // Loading the Account

    public class DisplayRange extends AsyncTask<String,String,String>
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
            Toast.makeText(CashPowerActivity.this, r, Toast.LENGTH_SHORT).show();
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
            String AccountNumber = account.getText().toString();
            String Amount = amount.getText().toString();
            temp1 ="";
            double Amountt = Double.parseDouble(Amount);

            try
            {
                con = connectionclass();        // Connect to database
                if (con == null)
                {
                    z = "Check Your Internet Access!";
                }
                else
                {
                    String query = "select t.lower,t.upper,t.charges from range t where " + Amount.trim() + " BETWEEN t.lower AND t.upper ";
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery(query);
                    if (rs.next()) {
                        String s2 = rs.getString("charges");
                       temp1 = temp1 + "\t " + s2;
                       // temp2 = temp2 + "\t Account Balance: " + s3;
                  runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                // membername.setText(temp);
                                charges.setText(temp1);
                                //  balance.setText(temp2);

                            }
                        });

                    }
                    else
                    {
                     //   z = "No information available!!!";
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

    // Updating the payment

    public class UpdateCashPower extends AsyncTask<String,String,String> {
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
                Toast.makeText(CashPowerActivity.this, "Payment Successfull", Toast.LENGTH_LONG).show();
            }
        }

        //  @Override
        protected String doInBackground(String... params) {
            String AccountNumber = account.getText().toString();
            String Amount = amount.getText().toString();

            GlobalClass globalClass = (GlobalClass) getApplicationContext();
            // globalClass.getLoginCode();
              //  String namecreditt = namecredit.getSelectedItem().toString();
           //  String compid = LloginCode.substring(0, 3);
            String AccountNo = AccountNumber.substring(4, 15);

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


    // Update Charges

    public class UpdateCharges extends AsyncTask<String,String,String> {
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
                Toast.makeText(CashPowerActivity.this, "Payment Successfull", Toast.LENGTH_LONG).show();
            }
        }

        //  @Override
        protected String doInBackground(String... params) {
            String AccountNumber = account.getText().toString();
            String Charges = charges.getText().toString();

            GlobalClass globalClass = (GlobalClass) getApplicationContext();
            String AccountNo = AccountNumber.substring(4, 15);
        //    String compid = LloginCode.substring(0, 3);


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
                progressDialog.dismiss();
            }
            return z;
        }
    }

    // Connecting to API Cash Power
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createPost() {
        final String timestamp = String.valueOf(Instant.now().getEpochSecond());
        final String TAG = "Communicator";
        String Meternumber = meternumber.getText().toString();
        String pamount = amount.getText().toString();
        String mobileno = phone.getText().toString();
        String Amount = pamount.trim()+"00";
        String cukey = "7dd7ab27-540a-4b52-8705-27aa429fa93c";
        String nawec = "nawec";
        String Vend = "vend";
        //   String meterNo = meterno.getText().toString();
        // String meternumber = meterno.getText().toString();

        String ala = cukey.trim()+nawec.trim()+Vend.trim()+timestamp.trim()+timestamp.trim()+Meternumber.trim();
        //SHA 512 hash of (key + “nawec” +“vend” + nonce + timestamp +meternumber
        final String signature = encryptThisString(ala);

        // this content display the Headers
        Gson gson = new GsonBuilder().serializeNulls().create();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(100, TimeUnit.SECONDS)
                .readTimeout(100, TimeUnit.SECONDS)
                .readTimeout(100, TimeUnit.SECONDS)
                //   .writeTimeout(35, TimeUnit.SECONDS)
                //   .readTimeout(45, TimeUnit.SECONDS)
                .addInterceptor( new Interceptor() {
                    @Override
                    public okhttp3.Response intercept( Chain chain) throws IOException {

                        Request originalRequest = chain.request();
                        Request newRequest = originalRequest.newBuilder()

                                .header("Content-Type","application/json; charset=utf-8")
                                .header("Accept","application/json")
                                .header("Nonce",timestamp.trim())
                                .header("Timestamp",timestamp.trim())
                                .header("Authorization","Bearer FuSjqqcDmUv9T4rALOrEs2lW8xf_UbkM6muGMjiHCwxPo4oxaDRWLIaXs-64vOEKgMecpdhBaGaWLDfFjXhuudHVPYWg4pQ_2BTkumomGPSs8Fodgqch6t39yMQpjzRLN2AssXDWRfG-AZ9u9fQ22CG7tR4gsmULMiGbqIS2TK0P9bqtqMqBCLnJNeE6mSMR2SJdNTKy43Cz1YJOpYde7K_EVxCPDlO4KP8sPt1W2HhV6hxlDEfw-FW7GGnvTMgDLHDEJ_Kh9R6rt8QQlZOfaTlXNKdIg5jl4YByhoSNWUwdYLyyCY_sIbRUjcXR0LlwB76qp8ejBm-g6bIvPxbLSpW0i0OPoDJd7PlxuX9SfCdHhQ-rWHOop5q3eBAhNFrqvC74OZzvBcSpU6O0Ks6ksNgiPF9xK8FXqN-APz9iIl78AIOjDdWFP-_Lk7AutO9ls1VmOmf-ttfBS1URvehHLcrkTAQ1CHDZRSgl1KDnYm_pK6SXnMi6PVyVxKI70DysQORoTtEg5pNBcbzLtJtDww")
                                .header("Signature",signature.trim())
                                .build();
                        return chain.proceed(newRequest);
                    }
                })
                .addInterceptor(loggingInterceptor)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.gamswitchgm.com/api/nawec/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        //  Post post = new Post("Vend", "07164822632", "2202790316","1000");
        Map<String, String> fields = new HashMap<>();
        fields.put("type", "Vend");
        fields.put("meterNumber", Meternumber.trim());
        fields.put("PhoneNumber", mobileno.trim());
        fields.put("amount", Amount.trim());

        Call<Post> call = jsonPlaceHolderApi.createPost(fields);

      call.enqueue(new Callback<Post>() {
          @Override
          public void onResponse(Call<Post> call, Response<Post> response) {

          }

          @Override
          public void onFailure(Call<Post> call, Throwable t) {

          }
      });

//////////////////////////////////////////////////////////////////
                //String message = result.getText().toString();
                //   String rawresult = JsonPlaceHolderApi.SerializeObject(response, Format.Indented);

                //  Intent intent= new Intent(CashPowerActivity.this ,ShowTransaction.class);

                // intent.putExtra(MESSAGE_KEY, String.valueOf(call));

                //   startActivity(intent);



          //  }
    }
    public void sendmessage (View view)
    {
     //   message_text = (EditText) findViewById(R.id.message_text);

        String message = result.getText().toString();

        Intent intent= new Intent(this ,ShowTransaction.class);

        intent.putExtra(MESSAGE_KEY,message);

        startActivity(intent);
    }
    public static String encryptThisString(String input)
    {
        try {
            // getInstance() method is called with algorithm SHA-512
            MessageDigest md = MessageDigest.getInstance("SHA-512");

            // digest() method is called
            // to calculate message digest of the input string
            // returned as array of byte
            byte[] messageDigest = md.digest(input.getBytes());

            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);

            // Convert message digest into hex value
            String hashtext = no.toString(16);

            // Add preceding 0s to make it 32 bit
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }

            // return the HashText
            return hashtext;
        }

        // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    // Back press
    @Override
    public void onBackPressed() {
            super.onBackPressed();
        Intent myIntent = new Intent(CashPowerActivity.this,
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
            ConnectionURL = "jdbc:jtds:sqlserver://SQL5081.site4now.net/DB_A45989_Walleto;user=" + un + ";password=" + pass + ";";
           // ConnectionURL = "jdbc:jtds:sqlserver://SQL5081.site4now.net/DB_A45989_Walleto;user=" + un + ";password=" + pass + ";";
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
