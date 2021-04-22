package com.example.Shoot.Pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LastWinnerResponse {

@SerializedName("win_number")
@Expose
private Integer winNumber;

public Integer getWinNumber() {
return winNumber;
}

public void setWinNumber(Integer winNumber) {
this.winNumber = winNumber;
}

}