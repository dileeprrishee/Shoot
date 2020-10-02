package com.example.Shoot.Pojo;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Country{

    @SerializedName("skey")
    @Expose
    private ArrayList<String> skey;

    public Country(ArrayList<String> skey) {
        this.skey = skey;
    }

    public ArrayList<String> getSkey() {
        return skey;
    }

    public void setSkey(ArrayList<String> skey) {
        this.skey = skey;
    }
}
