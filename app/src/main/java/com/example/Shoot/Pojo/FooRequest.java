package com.example.Shoot.Pojo;

import com.google.gson.annotations.SerializedName;

public class FooRequest {
    @SerializedName("segment")
    private String segment;
    @SerializedName("user_id")
    private String user_id;
    @SerializedName("numbers")
    private String numbers;
    @SerializedName("count")
    private String count;
    @SerializedName("skey")
    private String skey;

    public FooRequest(String segment, String user_id, String numbers, String count, String skey) {
        this.segment = segment;
        this.user_id = user_id;
        this.numbers = numbers;
        this.count = count;
        this.skey = skey;
    }

    public String getSegment() {
        return segment;
    }

    public void setSegment(String segment) {
        this.segment = segment;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getNumbers() {
        return numbers;
    }

    public void setNumbers(String numbers) {
        this.numbers = numbers;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getSkey() {
        return skey;
    }

    public void setSkey(String skey) {
        this.skey = skey;
    }
}
