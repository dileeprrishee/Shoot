package com.example.Shoot.UI.Activities.Results;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.Shoot.ApiInterfaces.ApiServiceInterface;
import com.example.Shoot.Pojo.ResponseWinnner;
import com.example.Shoot.R;
import com.example.Shoot.ServiceClass.ServiceGenerator;
import com.example.Shoot.Storage.PreferenceController;
import com.example.Shoot.UI.Activities.HomeActivity;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Result extends AppCompatActivity {

    private Handler mHandler, timehandler;
    private HandlerThread mHandlerThread;
    private Retrofit retrofit;
    private ApiServiceInterface UI;
    private String mid, end_time,uid;
    private TextView tv_date, tv_slotno, tv_time, tv_currenttime, tv_winnertime,tv_winner,tv_winnumber;
    private Calendar calander;
    private JsonObject values;
    private JsonArray request;
    private String skey = "uHtbabjrxcKQTeuwjQ==";
    private Vibrator vibrator;
    private ResultViewModel resultViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        tv_date = findViewById(R.id.tv_date);
        tv_slotno = findViewById(R.id.tv_slotno);
        tv_time = findViewById(R.id.tv_time);
        tv_currenttime = findViewById(R.id.tv_currenttime);
        tv_winnertime = findViewById(R.id.tv_winnertime);
        tv_winnumber = findViewById(R.id.tv_winnumber);
        tv_winner = findViewById(R.id.tv_winner);
        resultViewModel = new ViewModelProvider(this).get(ResultViewModel.class);

        vibrator = (Vibrator) getSystemService(Result.this.VIBRATOR_SERVICE);

        calander = calander.getInstance();
        timehandler = new Handler(getMainLooper());


        values = new JsonObject();
        request= new JsonArray();

        retrofit = ServiceGenerator.builder.build();
        UI = retrofit.create(ApiServiceInterface.class);


        mid = PreferenceController.getStringPreference(getApplicationContext()
                , PreferenceController.PreferenceKeys.PREFERENCE_MID);
        uid = PreferenceController.getStringPreference(getApplicationContext(),
                PreferenceController.PreferenceKeys.PREFERENCE_CUSTOMER_ID);
        tv_slotno.setText(mid);


        GetIntents();
        TimeAndDate();
        CurrentTime();
        WinningTime();
//        ApiForWinner();


        startService(new Intent(this, BackgroundService.class));


        startHandlerThread();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ApiForWinner();
                stopService();
            }
        }, 900000);

    }



    private void WinningTime() {
        tv_winnertime.setText(end_time);
    }

    private void GetIntents() {
        end_time = PreferenceController.getStringPreference(getApplicationContext(),
                PreferenceController.PreferenceKeys.PREFERENCE_GAME_END_TIME);

    }

    private void CurrentTime() {
        timehandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                tv_currenttime.setText(new SimpleDateFormat("hh:mm:ss", Locale.US).format(new Date()));
                timehandler.postDelayed(this, 1000);

            }
        }, 10);

    }

    private void TimeAndDate() {
        tv_time.setText(new SimpleDateFormat("hh:mm", Locale.US).format(new Date()));
        SimpleDateFormat month_date = new SimpleDateFormat("dd - MMM - yy");
        String ma = month_date.format(calander.getTime());
        tv_date.setText(ma);
    }

    private void ApiForWinner() {

        values.addProperty("mid", mid);
        values.addProperty("uid",uid);
        values.addProperty("skey", skey);
        request.add(values);

        Call<ResponseWinnner> responseWinnnerCall = UI.Getwinner("application/json", request);
        responseWinnnerCall.enqueue(new Callback<ResponseWinnner>() {
            @Override
            public void onResponse(Call<ResponseWinnner> call, Response<ResponseWinnner> response) {
                if (response.isSuccessful()) {
                    ResponseWinnner responseWinnner = response.body();
                    tv_winner.setVisibility(View.VISIBLE);
                    tv_winnumber.setVisibility(View.VISIBLE);
                    tv_winner.setText(responseWinnner.getIsWinner());
                    Toast.makeText(Result.this, responseWinnner.getIsWinner(), Toast.LENGTH_SHORT).show();
                    tv_winnumber.setText(responseWinnner.getWinNumber() + "");
                }
            }

            @Override
            public void onFailure(Call<ResponseWinnner> call, Throwable t) {
                Log.w( "onFailure: ",t );
            }
        });


    }


    public void stopService() {
        setPref();
        stopService(new Intent(getApplicationContext(), BackgroundService.class));
    }

    private void setPref() {
        PreferenceController.setPreference(getApplicationContext(), PreferenceController.PreferenceKeys.PREFERENCE_AMOUNT_0,"0");
        PreferenceController.setPreference(getApplicationContext(), PreferenceController.PreferenceKeys.PREFERENCE_AMOUNT_1,"0");
        PreferenceController.setPreference(getApplicationContext(), PreferenceController.PreferenceKeys.PREFERENCE_AMOUNT_2,"0");
        PreferenceController.setPreference(getApplicationContext(), PreferenceController.PreferenceKeys.PREFERENCE_AMOUNT_3,"0");
        PreferenceController.setPreference(getApplicationContext(), PreferenceController.PreferenceKeys.PREFERENCE_AMOUNT_4,"0");
        PreferenceController.setPreference(getApplicationContext(), PreferenceController.PreferenceKeys.PREFERENCE_AMOUNT_5,"0");
        PreferenceController.setPreference(getApplicationContext(), PreferenceController.PreferenceKeys.PREFERENCE_AMOUNT_6,"0");
        PreferenceController.setPreference(getApplicationContext(), PreferenceController.PreferenceKeys.PREFERENCE_AMOUNT_7,"0");
        PreferenceController.setPreference(getApplicationContext(), PreferenceController.PreferenceKeys.PREFERENCE_AMOUNT_8,"0");
        PreferenceController.setPreference(getApplicationContext(), PreferenceController.PreferenceKeys.PREFERENCE_AMOUNT_9,"0");
    }

    public void startHandlerThread() {
        mHandlerThread = new HandlerThread("HandlerThread");
        mHandlerThread.start();
        mHandler = new Handler(mHandlerThread.getLooper());
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
        startActivity(intent);
    }
}

