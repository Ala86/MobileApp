package com.example.itmanager.MobileMoney;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.util.Objects;

 /*
        This source code could be used for academic purposes only. Posting on other websites or blogs is only allowed with a dofollow link to the orignal content.
    */

public class ShowTransaction extends AppCompatActivity
{
    //Intent toy;
    // Declaring layout button, edit texts
    Button memberenquiry,deposit,withdrawal,transfer,otherservices;
    TextView lable;
    TextView result;
    String ala;
    String ala1;
    // Declaring connection variables
    Connection con;
    String un,pass,db,ip;
    String gcCustName = "";
    String gcMeterNo = "";
    String gcTokenNo = "";
    String gcVatAmt = "";
    String gcAmount = "";
    String gcKwh = "";
    String gcFee ="";
    String gcRecept = "";
    String StatusCode = "";

    String meter1;
  //  String ala1 = "";
    //End Declaring connection variables

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showcashpower);

        lable = (TextView) findViewById(R.id.txthead);
        result = (TextView) findViewById(R.id.resultSet);

      //  Textv = (TextView)findViewById(R.id.tv);
        String output = Objects.requireNonNull(getIntent().getExtras()).getString("ala");
    //   result.setText(output);
      //  int dslen = output.trim().length();
      //  assert output != null;
        int ncustnameindex0 = output.indexOf(':',3);
        int ncustnameindex1 = output.indexOf( ',', 2);

        int nmeternoindex0 = output.indexOf(':', 4);
        int nmeternoindex1 = output.indexOf(',', 3);

        int ntokenindex0 = output.indexOf(':', 5);
        int ntokenindex1 = output.indexOf(',', 4);

        int nvatindex0 = output.indexOf(':', 6);
        int nvatindex1 = output.indexOf( ',', 5);


        int namtindex0 = output.indexOf(':', 7);
        int namtindex1 = output.indexOf(',', 6);

        int nkwhindex0 = output.indexOf(':', 8);
        int nkwhindex1 = output.indexOf(',', 7);

        int nfeeindex0 = output.indexOf(':', 9);
        int nfeeindex1 = output.indexOf(',', 8);

        int nrecptindex0 = output.indexOf(':', 10);
        int nrecptindex1 = output.indexOf(',', 9);

      //  s.substring(begin, Math.min(s.length(), end));
        //myText.substring(0, Math.min(6, myText.length()))
     //   gcCustName = result.Substring(ncustnameindex0 + 1, ncustnameindex1 - ncustnameindex0).Replace(',', ' ').Trim();
       // gcCustName = output.substring(ncustnameindex0, (ncustnameindex1, ncustnameindex0));
       // gcMeterNo  = output.substring(nmeternoindex0 ,(nmeternoindex1, nmeternoindex0));
       // gcTokenNo  = output.substring(ntokenindex0 ,(ntokenindex1 ,ntokenindex0));
        gcCustName = output.substring(ncustnameindex0 + 1, (ncustnameindex1 - ncustnameindex0)).replace(',',' ').trim();
       gcMeterNo  = output.substring(nmeternoindex0 + 1 , (nmeternoindex1 - nmeternoindex0)).replace(',',' ').trim();;
        gcTokenNo  = output.substring(ntokenindex0 + 1, (ntokenindex1 - ntokenindex0)).replace(',',' ').trim();;

       // gcCustName = output.substring(ncustnameindex0 , Math.min(ncustnameindex1 - ncustnameindex0, output.length()));
       //gcMeterNo  = output.substring(nmeternoindex0 , Math.min(nmeternoindex1 - nmeternoindex0,output.length()));
      // gcTokenNo  = output.substring(ntokenindex0 , Math.min(ntokenindex1 - ntokenindex0,output.length()));
      //  gcVatAmt   = output.substring(nvatindex0 + 1, Math.min(nvatindex1 - nvatindex0,output.length()));
      //  gcAmount   = output.substring(namtindex0 + 1, Math.min(namtindex1 - namtindex0,output.length()));
      //  gcKwh      = output.substring(nkwhindex0 + 1,Math.min( nkwhindex1 - nkwhindex0,output.length()));
       // gcFee      = output.substring(nfeeindex0 + 1, Math.min(nfeeindex1 - nfeeindex0, output.length()));
       // gcRecept   = output.substring(nrecptindex0 + 1, Math.min(nrecptindex1 - nrecptindex0,output.length()));
        ala1 = "Transaction Report\n \nCustomer Name : " + gcCustName + "\nMeter No     : " + gcMeterNo +"\nToken         : " + gcTokenNo;
      //  ala1 = "Transaction Report\n \nCustomer Name : " + gcCustName + "\nMeter No     : " + gcMeterNo +
        //       "\nToken         : " + gcTokenNo + "\nVat           : " + gcVatAmt + "\nAmount        : " + gcAmount + "\nAmountKwh     : " + gcKwh + "\nFee           : " + gcFee +
         //       "\nReceipt No    : " + gcRecept;
        result.setText(ala1);

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent myIntent = new Intent(ShowTransaction.this,
                CashPowerActivity.class);
        startActivity(myIntent);
        finish();
    }

}

