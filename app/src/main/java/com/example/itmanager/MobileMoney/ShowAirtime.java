
package com.example.itmanager.MobileMoney;

//package="com.example.itmanager.LocalTransfer"

        import android.content.Intent;
        import android.os.Bundle;
        import android.widget.Button;
        import android.widget.TextView;

        import androidx.appcompat.app.AppCompatActivity;

        import java.sql.Connection;

 /*
        This source code could be used for academic purposes only. Posting on other websites or blogs is only allowed with a dofollow link to the orignal content.
    */

public class ShowAirtime extends AppCompatActivity
{
    //Intent toy;
    // Declaring layout button, edit texts
    Button memberenquiry,deposit,withdrawal,transfer,otherservices;
    TextView lable,result;
    // Declaring connection variables
    Connection con;
    String un,pass,db,ip;
    //End Declaring connection variables

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showairtime);

        lable = (TextView) findViewById(R.id.txthead);
        result = (TextView) findViewById(R.id.resultSet);

        Bundle b=getIntent().getExtras();
        if(b!=null)
        {
            String fud=b.getString("a");
            result.setText(fud);
        }

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent myIntent = new Intent(ShowAirtime.this,
                AirtimeActivity.class);
        startActivity(myIntent);
        finish();
    }

}

