package com.example.Shoot.Pojo.Generate;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WalletBalance {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("current_walet_amount")
    @Expose
    private String currentWaletAmount;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCurrentWaletAmount() {
        return currentWaletAmount;
    }

    public void setCurrentWaletAmount(String currentWaletAmount) {
        this.currentWaletAmount = currentWaletAmount;
    }

}