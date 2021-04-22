package com.example.Shoot.Pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WinnerDetails {

@SerializedName("date_played")
@Expose
private String datePlayed;
@SerializedName("number_reserved")
@Expose
private String numberReserved;
@SerializedName("amount")
@Expose
private Integer amount;

public String getDatePlayed() {
return datePlayed;
}

public void setDatePlayed(String datePlayed) {
this.datePlayed = datePlayed;
}

public String getNumberReserved() {
return numberReserved;
}

public void setNumberReserved(String numberReserved) {
this.numberReserved = numberReserved;
}

public Integer getAmount() {
return amount;
}

public void setAmount(Integer amount) {
this.amount = amount;
}

}