package com.example.Shoot.UI.Activities.login;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.Shoot.ApiInterfaces.ApiServiceInterface;
import com.example.Shoot.Pojo.LoginResponse;
import com.example.Shoot.ServiceClass.ServiceGenerator;
import com.google.gson.JsonArray;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class LoginViewModel extends AndroidViewModel {
    private ApiServiceInterface UI;
    private Retrofit retrofit;
    private LoginResponse loginResponse;

    MutableLiveData<LoginResponse> liveData = new MutableLiveData<>();

    public LoginViewModel(@NonNull Application application) {
        super(application);
    }

    public void triggerLogin(JsonArray request) {
        callRetofit();
        final Call<LoginResponse> loginResponseCall = UI.postLogin("application/json", request);
        loginResponseCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()) {
                    loginResponse = response.body();
                    liveData.postValue(loginResponse);
                } else {
                    loginResponse = response.body();
                    liveData.postValue(loginResponse);
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                liveData.postValue(loginResponse);
            }
        });
    }

    public LiveData<LoginResponse> getData() {
        return liveData;
    }

    private void callRetofit() {
        retrofit = ServiceGenerator.builder.build();
        UI = retrofit.create(ApiServiceInterface.class);
    }
}
