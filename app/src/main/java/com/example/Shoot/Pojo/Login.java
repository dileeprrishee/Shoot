package com.example.Shoot.Pojo;

import com.google.gson.annotations.SerializedName;

public class Login {
    @SerializedName("uname")
    private String uname;
    @SerializedName("pword")
    private String pword;
    @SerializedName("skey")
    private String skey;

    public Login(String uname, String pword,String skey) {
        this.uname = uname;
        this.pword = pword;
        this.skey = skey;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPword() {
        return pword;
    }

    public void setPword(String pword) {
        this.pword = pword;
    }
}
