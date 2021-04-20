package com.example.Shoot.ApiInterfaces;

import com.example.Shoot.Pojo.CountryResponse;
import com.example.Shoot.Pojo.CurrentMatch.CurrentMatchResponse;
import com.example.Shoot.Pojo.Generate.GenerateOrderResponse;
import com.example.Shoot.Pojo.Generate.WalletBalance;
import com.example.Shoot.Pojo.GetProfileResponse;
import com.example.Shoot.Pojo.LastWinnerResponse;
import com.example.Shoot.Pojo.LoginResponse;
import com.example.Shoot.Pojo.ProfileResponse;
import com.example.Shoot.Pojo.ResponseWinnner;
import com.example.Shoot.Pojo.RsponseWinnerHistory;
import com.example.Shoot.Pojo.SendData;
import com.example.Shoot.Pojo.UpdateProfile;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiServiceInterface {
    String API_BASE_URL = "https://coredrops.com/shoot/api/";


    //@FormUrlEncoded
    @POST("register.php")
    Call<String> Signup(@Header("Content-Type") String header, @Body String request);

    @POST("login.php")
    Call<LoginResponse> postLogin(@Header("Content-Type")String header,@Body JsonArray request);

    @POST("get_current_match.php")
    Call<CurrentMatchResponse> CurrentMatch( @Body JsonArray request);

    @POST("get_last_winner.php")
    Call<LastWinnerResponse> LastWinner(@Body JsonArray request);

    @POST("countries.php")
    Call<List<CountryResponse>> postCountry(@Header("Content-Type") String header, @Body JsonArray request);


    @POST("get_profile.php")
    Call<GetProfileResponse> postProfile(@Header("Content-Type") String header , @Body JsonArray params);

   @Headers({"skey:uHtbabjrxcKQTeuwjQ==","Content-Type:application/json"})
    @POST("upd_profile.php")
    Call<UpdateProfile> postUpdateProfile(@Body ArrayList<UpdateProfile> params);

    //@FormUrlEncoded
    @POST("seg_checker.php")
    Call<SendData> sendData(@Header("Content-Type") String header ,@Body JsonObject body);

    @POST("get_winner.php")
    Call<ResponseWinnner> Getwinner(@Header("Content-Type") String header, @Body JsonArray request);

    @POST("get_history.php")
    Call<List<RsponseWinnerHistory>> getHistory(@Header("Content-Type") String header , @Body JsonArray request);

    @POST("get_profile.php")
    Call<List<ProfileResponse>> GetwProfile(@Header("Content-Type") String header, @Body JsonArray request);

    @POST("generate_order.php")
    Call<GenerateOrderResponse> GetOrderid(@Header("Content-Type") String header, @Body JsonObject request);

    @POST("update_walet.php")
    Call<WalletBalance> WalletBalance(@Body JsonObject request);

}
