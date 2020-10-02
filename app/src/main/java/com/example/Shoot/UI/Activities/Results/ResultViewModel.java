package com.example.Shoot.UI.Activities.Results;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.Shoot.ApiInterfaces.ApiServiceInterface;
import com.example.Shoot.Pojo.ResponseWinnner;
import com.example.Shoot.ServiceClass.ServiceGenerator;
import com.google.gson.JsonArray;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ResultViewModel extends AndroidViewModel {
    private ApiServiceInterface UI;
    private Retrofit retrofit;
    private ResponseWinnner responseWinnner;

    MutableLiveData<ResponseWinnner> liveData = new MutableLiveData<ResponseWinnner>();

    public ResultViewModel(@NonNull Application application) {
        super(application);
    }

    public void triggerWinner(JsonArray request) {
        callRetofit();

        Call<ResponseWinnner> responseWinnnerCall = UI.Getwinner("application/json", request);
        responseWinnnerCall.enqueue(new Callback<ResponseWinnner>() {
            @Override
            public void onResponse(Call<ResponseWinnner> call, Response<ResponseWinnner> response) {
                if (response.isSuccessful()) {
                    responseWinnner = response.body();
                    liveData.postValue(responseWinnner);
                } else {
                    responseWinnner = response.body();
                    liveData.postValue(responseWinnner);
                }
            }

            @Override
            public void onFailure(Call<ResponseWinnner> call, Throwable t) {
                liveData.postValue(responseWinnner);
                Log.w( "onFailure: ",t );
            }
        });

    }

    public LiveData<ResponseWinnner> getData() {
        return liveData;
    }

    private void callRetofit() {
        retrofit = ServiceGenerator.builder.build();
        UI = retrofit.create(ApiServiceInterface.class);
    }

}
