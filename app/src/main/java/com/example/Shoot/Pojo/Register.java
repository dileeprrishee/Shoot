package com.example.Shoot.Pojo;

import com.google.gson.annotations.SerializedName;

public class Register {
    @SerializedName("name")
    private String name;
    @SerializedName("mobile")
    private String mobile;
    @SerializedName("email")
    private String email;
    @SerializedName("country_id")
    private String country_id;
    @SerializedName("password")
    private String password;
    @SerializedName("skey")
    private String skey;


    public Register(String name, String mobile, String email, String country_id, String password, String skey) {
        this.name = name;
        this.mobile = mobile;
        this.email = email;
        this.country_id = country_id;
        this.password = password;
        this.skey = skey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry_id() {
        return country_id;
    }

    public void setCountry_id(String country_id) {
        this.country_id = country_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
