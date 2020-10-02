package com.example.Shoot.Pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetProfile {

    @SerializedName("uid")
    @Expose
    private String uid;
    @SerializedName("skey")
    @Expose
    private String skey;

    public GetProfile(String uid, String skey) {
        this.uid = uid;
        this.skey = skey;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getSkey() {
        return skey;
    }

    public void setSkey(String skey) {
        this.skey = skey;
    }

}


