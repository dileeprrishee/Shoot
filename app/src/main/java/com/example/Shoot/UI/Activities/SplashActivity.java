package com.example.Shoot.UI.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.Shoot.R;
import com.example.Shoot.Storage.PreferenceController;
import com.example.Shoot.UI.Activities.login.LoginActivity;

public class SplashActivity extends AppCompatActivity {
    private Handler handler;
    private Boolean islogedin,winner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        getPrefs();

    }

    private void getPrefs() {
        islogedin = PreferenceController.isUsrLoggedIn(getApplicationContext());
        winner    = PreferenceController.isWinnerLogin(getApplicationContext());

        checkLogin();
    }

    private void checkLogin() {
        handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if  (islogedin){
                    Intent intent=new Intent(SplashActivity.this, HomeActivity.class);
                    startActivity(intent);
                }
                else {
                    Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
                finish();
            }
        },3000);
    }
}
