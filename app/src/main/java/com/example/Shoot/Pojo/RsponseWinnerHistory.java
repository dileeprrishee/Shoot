package com.example.Shoot.Pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RsponseWinnerHistory {

    @SerializedName("date_played")
    @Expose
    private String datePlayed;
    @SerializedName("match_id")
    @Expose
    private String matchId;
    @SerializedName("number")
    @Expose
    private String number;
    @SerializedName("amount")
    @Expose
    private String amount;
    @SerializedName("won_or_not")
    @Expose
    private String wonOrNot;
    @SerializedName("won_number")
    @Expose
    private String wonNumber;

    public String getDatePlayed() {
        return datePlayed;
    }

    public void setDatePlayed(String datePlayed) {
        this.datePlayed = datePlayed;
    }

    public String getMatchId() {
        return matchId;
    }

    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getWonOrNot() {
        return wonOrNot;
    }

    public void setWonOrNot(String wonOrNot) {
        this.wonOrNot = wonOrNot;
    }

    public String getWonNumber() {
        return wonNumber;
    }

    public void setWonNumber(String wonNumber) {
        this.wonNumber = wonNumber;
    }

}