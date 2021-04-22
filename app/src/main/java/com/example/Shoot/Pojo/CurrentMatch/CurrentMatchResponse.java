package com.example.Shoot.Pojo.CurrentMatch;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CurrentMatchResponse {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("current_time")
    @Expose
    private String currentTime;
    @SerializedName("slot_start")
    @Expose
    private String slotStart;
    @SerializedName("slot_ends")
    @Expose
    private String slotEnds;
    @SerializedName("result")
    @Expose
    private List<CurrentMatchResult> result = null;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }

    public String getSlotStart() {
        return slotStart;
    }

    public void setSlotStart(String slotStart) {
        this.slotStart = slotStart;
    }

    public String getSlotEnds() {
        return slotEnds;
    }

    public void setSlotEnds(String slotEnds) {
        this.slotEnds = slotEnds;
    }

    public List<CurrentMatchResult> getResult() {
        return result;
    }

    public void setResult(List<CurrentMatchResult> result) {
        this.result = result;
    }

}