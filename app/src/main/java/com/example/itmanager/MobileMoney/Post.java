package com.example.itmanager.MobileMoney;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Post {
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("Meternumber")
    @Expose
    private String meternumber;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("amount")
    @Expose
    private double amount;

    public Post(String type, String meterNumber, String phoneNumber, double amount) {
        this.type = type;
        this.meternumber = meterNumber;
        this.phone = phoneNumber;
        this.amount = amount;
    }

    public String getType() {

        return type;
    }

    public String getMeternumber() {
        return meternumber;
    }

    public String getPhone() {
        return phone;
    }

    public double getAmount() {
        return amount;
    }
}
