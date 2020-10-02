package com.example.Shoot.Pojo; ;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CountryResponse {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("country_name")
    @Expose
    private String countryName;
    @SerializedName("country_short_name")
    @Expose
    private String countryShortName;
    @SerializedName("country_currency")
    @Expose
    private String countryCurrency;
    @SerializedName("country_dialect")
    @Expose
    private String countryDialect;
    @SerializedName("country_mob_length")
    @Expose
    private String countryMobLength;
    @SerializedName("country_time_zone")
    @Expose
    private String countryTimeZone;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryShortName() {
        return countryShortName;
    }

    public void setCountryShortName(String countryShortName) {
        this.countryShortName = countryShortName;
    }

    public String getCountryCurrency() {
        return countryCurrency;
    }

    public void setCountryCurrency(String countryCurrency) {
        this.countryCurrency = countryCurrency;
    }

    public String getCountryDialect() {
        return countryDialect;
    }

    public void setCountryDialect(String countryDialect) {
        this.countryDialect = countryDialect;
    }

    public String getCountryMobLength() {
        return countryMobLength;
    }

    public void setCountryMobLength(String countryMobLength) {
        this.countryMobLength = countryMobLength;
    }

    public String getCountryTimeZone() {
        return countryTimeZone;
    }

    public void setCountryTimeZone(String countryTimeZone) {
        this.countryTimeZone = countryTimeZone;
    }

}