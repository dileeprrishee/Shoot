package com.example.Shoot.Pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RsponseWinnerHistory {

    @SerializedName("match_id")
    @Expose
    private String matchId;
    @SerializedName("number_won")
    @Expose
    private String numberWon;
    @SerializedName("details")
    @Expose
    private List<WinnerDetails> winnerDetails = null;

    public String getMatchId() {
        return matchId;
    }

    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }

    public String getNumberWon() {
        return numberWon;
    }

    public void setNumberWon(String numberWon) {
        this.numberWon = numberWon;
    }

    public List<WinnerDetails> getWinnerDetails() {
        return winnerDetails;
    }

    public void setWinnerDetails(List<WinnerDetails> winnerDetails) {
        this.winnerDetails = winnerDetails;
    }

}