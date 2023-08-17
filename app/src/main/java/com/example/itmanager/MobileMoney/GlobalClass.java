package com.example.itmanager.MobileMoney;

import android.app.Application;

public class GlobalClass extends Application {
    private String ccustcode;
    private  String LoginCode;

    public String getCcustcode() {
        return ccustcode;
    }

    public void setCcustcode(String ccustcode) {
        this.ccustcode = ccustcode;
    }

    public String getLoginCode() {
        return LoginCode;
    }

    public void setLoginCode(String loginCode) {
        LoginCode = loginCode;
    }
}
