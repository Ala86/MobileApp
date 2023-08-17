package com.example.itmanager.MobileMoney;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//public class ListItem
public class ListItem
{
    Connection con;
    String Connectionresult = "";
    Boolean issuccess = false;
   // Connection con;
  //  String un,pass,db,ip;

    // Declaring Server ip, username, database name and password
 //   ip = "192.168.0.13";
     String db = "NACCUGSoft_DATA";
     String   un = "DB_A45989_Walleto_admin";
     String pass = "Kuyateh@k13";
    String z = "";
    Boolean isSuccess = false;
   // GlobalClass globalClass = (GlobalClass) getApplicationContext();
    public List<Map<String ,String>>getlist()
  {

      List<Map<String, String>> data = null;
      data = new ArrayList<Map<String, String>>();

      try{
          con = connectionclass();        // Connect to database
          if (con == null)
          {
              z = "Check Your Internet Access!";
          }
          else
          {
              z = "You are at the right place";
              String query = "select top(15) ctrandesc,ntranamnt, dtrandate from tranhist where right(cacctnumb,3) = '260' ";
              Statement stmt = con.createStatement();
              ResultSet rs = stmt.executeQuery(query);
              if (rs.next()){

                  Map<String, String> dtname = new HashMap<String , String>();
                  dtname.put("resultSet",rs.getString("ctrandesc"));
                  dtname.put("textview2",rs.getString("ntranamnt"));
                  dtname.put("textview3",rs.getString("dtrandate"));
                  data.add(dtname);
              }
              con.close();
          }
      } catch (SQLException e)
      {
          e.printStackTrace();
      }
      return data;
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
