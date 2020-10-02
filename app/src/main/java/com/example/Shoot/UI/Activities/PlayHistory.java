package com.example.Shoot.UI.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;

import com.example.Shoot.Adapters.AdapterWinHistory;
import com.example.Shoot.ApiInterfaces.ApiServiceInterface;
import com.example.Shoot.Pojo.RsponseWinnerHistory;
import com.example.Shoot.R;
import com.example.Shoot.ServiceClass.ServiceGenerator;
import com.example.Shoot.Storage.PreferenceController;
import com.example.Shoot.UI.Progress;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class PlayHistory extends AppCompatActivity  {
    private RecyclerView recyler_history;
    private JsonArray jsonArray;
    private JsonObject jsonObject;
    private Retrofit retrofit;
    private ApiServiceInterface UI;
    private List<RsponseWinnerHistory> histories;
    private String skey = "uHtbabjrxcKQTeuwjQ==";
    private Progress listner;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_history);

        recyler_history         = findViewById(R.id.recyler_history);

        jsonArray               = new JsonArray();
        jsonObject              = new JsonObject();
        histories               = new ArrayList<>();

        progressDialog          = new ProgressDialog(PlayHistory.this);

        retrofit                = ServiceGenerator.builder.build();
        UI                      = retrofit.create(ApiServiceInterface.class);

        CallForHistory();
        Progressdilog();

    }

    private void Progressdilog() {
        progressDialog.setMessage("Loading");
        progressDialog.setCancelable(true); // disable dismiss by tapping outside of the dialog
        progressDialog.show();

    }

    private void CallForHistory() {
        jsonObject.addProperty("uid", PreferenceController.getStringPreference(getApplicationContext(),
                PreferenceController.PreferenceKeys.PREFERENCE_CUSTOMER_ID));
        jsonObject.addProperty("skey", skey);
        jsonArray.add(jsonObject);
        final Call<List<RsponseWinnerHistory>> historyCall = UI.getHistory("application/json", jsonArray);
        historyCall.enqueue(new Callback<List<RsponseWinnerHistory>>() {
            @Override
            public void onResponse(Call<List<RsponseWinnerHistory>> call, Response<List<RsponseWinnerHistory>> response) {
                if(response.isSuccessful()){
                    histories = response.body();
                    recyler_history.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));
                  recyler_history.setAdapter(new AdapterWinHistory(getApplicationContext(),histories));
                    progressDialog.cancel();

                }
            }

            @Override
            public void onFailure(Call<List<RsponseWinnerHistory>> call, Throwable t) {

            }
        });
    }


}