package com.example.Shoot.Pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProfileResponse {

@SerializedName("u_name")
@Expose
private String uName;
@SerializedName("u_email")
@Expose
private String uEmail;
@SerializedName("u_mobile")
@Expose
private String uMobile;
@SerializedName("u_country")
@Expose
private String uCountry;
@SerializedName("u_walet")
@Expose
private String uWalet;

public String getUName() {
return uName;
}

public void setUName(String uName) {
this.uName = uName;
}

public String getUEmail() {
return uEmail;
}

public void setUEmail(String uEmail) {
this.uEmail = uEmail;
}

public String getUMobile() {
return uMobile;
}

public void setUMobile(String uMobile) {
this.uMobile = uMobile;
}

public String getUCountry() {
return uCountry;
}

public void setUCountry(String uCountry) {
this.uCountry = uCountry;
}

public String getUWalet() {
return uWalet;
}

public void setUWalet(String uWalet) {
this.uWalet = uWalet;
}

}