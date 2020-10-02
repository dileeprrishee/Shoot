package com.example.Shoot.Pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseWinnner {

@SerializedName("win_number")
@Expose
private Integer winNumber;
@SerializedName("is_winner")
@Expose
private String isWinner;

public Integer getWinNumber() {
return winNumber;
}

public void setWinNumber(Integer winNumber) {
this.winNumber = winNumber;
}

public String getIsWinner() {
return isWinner;
}

public void setIsWinner(String isWinner) {
this.isWinner = isWinner;
}

}