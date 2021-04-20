package com.example.Shoot.UI.Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.Shoot.ApiInterfaces.ApiServiceInterface;
import com.example.Shoot.Pojo.LastWinnerResponse;
import com.example.Shoot.R;
import com.example.Shoot.ServiceClass.ServiceGenerator;
import com.example.Shoot.Storage.PreferenceController;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LastMatchResults extends AppCompatActivity {
    private TextView tv_number;
    private Retrofit retrofit;
    private ApiServiceInterface UI;
    private ImageView iv_back;

    private String skey = "uHtbabjrxcKQTeuwjQ==";
    private JsonArray jsonArray;
    private JsonObject jsonObject;
    private boolean PREFERENCE_PLAY_STATUS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last_match_results);
        tv_number = findViewById(R.id.tv_number);
        iv_back = findViewById(R.id.iv_back);

        PREFERENCE_PLAY_STATUS = PreferenceController.getBooleanPreference(getApplicationContext(), PreferenceController.PreferenceKeys.PREFERENCE_PLAY_STATUS);
        jsonArray = new JsonArray();
        jsonObject = new JsonObject();
        retrofitClass();
        if (!PREFERENCE_PLAY_STATUS) {
            CallLastWinNO();
        } else {
            Toast.makeText(this, "Results will be published after ending the match", Toast.LENGTH_SHORT).show();
            finish();
        }
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    private void CallLastWinNO() {
        jsonObject.addProperty("skey", skey);
        jsonArray.add(jsonObject);
        Call<LastWinnerResponse> lastWinnerResponseCall = UI.LastWinner(jsonArray);
        lastWinnerResponseCall.enqueue(new Callback<LastWinnerResponse>() {
            @Override
            public void onResponse(Call<LastWinnerResponse> call, Response<LastWinnerResponse> response) {
                if (response.isSuccessful()) {
                    LastWinnerResponse results = response.body();
                    tv_number.setText(results.getWinNumber() + "");
                }
            }

            @Override
            public void onFailure(Call<LastWinnerResponse> call, Throwable t) {
                Toast.makeText(LastMatchResults.this, "Fail", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void retrofitClass() {
        retrofit = ServiceGenerator.builder.build();
        UI = retrofit.create(ApiServiceInterface.class);
    }
}