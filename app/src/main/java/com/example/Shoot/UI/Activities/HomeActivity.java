package com.example.Shoot.UI.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.Shoot.ApiInterfaces.ApiServiceInterface;
import com.example.Shoot.OnFragmentInteractionListener;
import com.example.Shoot.Pojo.CurrentMatch.CurrentMatchData;
import com.example.Shoot.Pojo.CurrentMatch.CurrentMatchResponse;
import com.example.Shoot.Pojo.CurrentMatch.CurrentMatchResult;
import com.example.Shoot.Pojo.ProfileResponse;
import com.example.Shoot.R;
import com.example.Shoot.ServiceClass.ServiceGenerator;
import com.example.Shoot.Storage.PreferenceController;
import com.example.Shoot.UI.Activities.login.LoginActivity;
import com.example.Shoot.UI.Fragments.Home2Activity;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.List;

import pl.droidsonroids.gif.GifImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,
        OnFragmentInteractionListener, View.OnClickListener {
    private Button button_start;
    private TextView tv_history, tv_profile, tv_logout, tv_results, tv_name, tv_wallet,
            tv_help, tv_conditions, tv_about, tv_how_to_play, tv_hints, tv_share,tv_recharge;
    private Toolbar toolbar;
    private boolean winnerStatus;
    private Retrofit retrofit;
    private ApiServiceInterface Ui;
    private String skey = "uHtbabjrxcKQTeuwjQ==";
    private JsonArray jsonArray;
    private JsonObject jsonObject;
    private boolean playedstatus;
    private GifImageView imageView2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        toolbar = findViewById(R.id.toolbar2);
        tv_logout = findViewById(R.id.tv_logout);
        button_start = findViewById(R.id.button_start);
        tv_name = findViewById(R.id.tv_name);
        tv_wallet = findViewById(R.id.tv_wallet);
        tv_history = findViewById(R.id.tv_history);
        tv_profile = findViewById(R.id.tv_myac);
        tv_results = findViewById(R.id.tv_results);
        tv_help = findViewById(R.id.tv_help);
        tv_conditions = findViewById(R.id.tv_conditions);
        tv_about = findViewById(R.id.tv_about);
        tv_how_to_play = findViewById(R.id.tv_how_to_play);
        tv_hints = findViewById(R.id.tv_hints);
        tv_share = findViewById(R.id.tv_share);
        tv_recharge = findViewById(R.id.tv_recharge);
        imageView2 = findViewById(R.id.imageView2);




        jsonArray = new JsonArray();
        jsonObject = new JsonObject();
        jsonArray = new JsonArray();
        jsonObject = new JsonObject();

        RftClass();
        ApioForProfile();
        Actionbarcall();
        NavigationDrawer();
        CallForName();
        getCurrenntMatch();
        CallForWallet();
        Clicks();


    }

    @Override
    protected void onResume() {
        super.onResume();
        getCurrenntMatch();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        getCurrenntMatch();
    }

    private void getCurrenntMatch() {
        jsonObject.addProperty("uid", PreferenceController.getStringPreference(getApplicationContext(),
                PreferenceController.PreferenceKeys.PREFERENCE_CUSTOMER_ID));
        jsonObject.addProperty("skey", skey);
        jsonArray.add(jsonObject);
        Call<CurrentMatchResponse> currentMatchResponseCall = Ui.CurrentMatch(jsonArray);
        currentMatchResponseCall.enqueue(new Callback<CurrentMatchResponse>() {
            @Override
            public void onResponse(Call<CurrentMatchResponse> call, Response<CurrentMatchResponse> response) {
                if (response.isSuccessful()) {
                    CurrentMatchResponse matchResponse = response.body();
                    playedstatus = matchResponse.getStatus();
                    PreferenceController.setPreference(getApplicationContext(), PreferenceController.PreferenceKeys.PREFERENCE_PLAY_STATUS,playedstatus);
                    PreferenceController.setPreference(getApplicationContext(), PreferenceController.PreferenceKeys.PREFERENCE_START_TIME,matchResponse.getSlotStart());
                    PreferenceController.setPreference(getApplicationContext(), PreferenceController.PreferenceKeys.PREFERENCE_END_TIME,matchResponse.getSlotEnds());
                    CheckStatus(matchResponse);
                }

            }

            @Override
            public void onFailure(Call<CurrentMatchResponse> call, Throwable t) {
                Log.w("TwAG", "onFailure: ", t);
            }
        });
    }

    private void CheckStatus(CurrentMatchResponse matchResponse) {
        if (playedstatus) {
            List<CurrentMatchResult> matchResult = matchResponse.getResult();
            for (int i = 0; i < matchResult.size(); i++) {
                List<CurrentMatchData> matchData = matchResult.get(i).getDetails();
                SetGameprefs(matchData);
            }
        } else if (!playedstatus) {
            setGamePref("0");
        }
    }

    private void SetGameprefs(List<CurrentMatchData> matchData) {
        for (int i = 0; i < matchData.size(); i++) {
            switch (matchData.get(i).getNumberReserved()) {
                case "0":
                    PreferenceController.setPreference(getApplicationContext(), PreferenceController.PreferenceKeys.PREFERENCE_AMOUNT_0, matchData.get(i).getAmount() + "");
                    break;
                case "1":
                    PreferenceController.setPreference(getApplicationContext(), PreferenceController.PreferenceKeys.PREFERENCE_AMOUNT_1, matchData.get(i).getAmount() + "");
                    break;
                case "2":
                    PreferenceController.setPreference(getApplicationContext(), PreferenceController.PreferenceKeys.PREFERENCE_AMOUNT_2, matchData.get(i).getAmount() + "");
                    break;
                case "3":
                    PreferenceController.setPreference(getApplicationContext(), PreferenceController.PreferenceKeys.PREFERENCE_AMOUNT_3, matchData.get(i).getAmount() + "");
                    break;
                case "4":
                    PreferenceController.setPreference(getApplicationContext(), PreferenceController.PreferenceKeys.PREFERENCE_AMOUNT_4, matchData.get(i).getAmount() + "");
                    break;
                case "5":
                    PreferenceController.setPreference(getApplicationContext(), PreferenceController.PreferenceKeys.PREFERENCE_AMOUNT_5, matchData.get(i).getAmount() + "");
                    break;
                case "6":
                    PreferenceController.setPreference(getApplicationContext(), PreferenceController.PreferenceKeys.PREFERENCE_AMOUNT_6, matchData.get(i).getAmount() + "");
                    break;
                case "7":
                    PreferenceController.setPreference(getApplicationContext(), PreferenceController.PreferenceKeys.PREFERENCE_AMOUNT_7, matchData.get(i).getAmount() + "");
                    break;
                case "8":
                    PreferenceController.setPreference(getApplicationContext(), PreferenceController.PreferenceKeys.PREFERENCE_AMOUNT_8, matchData.get(i).getAmount() + "");
                    break;
                case "9":
                    PreferenceController.setPreference(getApplicationContext(), PreferenceController.PreferenceKeys.PREFERENCE_AMOUNT_9, matchData.get(i).getAmount() + "");
                    break;
            }
        }
    }

    private void setGamePref(String s) {
        PreferenceController.setPreference(getApplicationContext(), PreferenceController.PreferenceKeys.PREFERENCE_AMOUNT_0, s);
        PreferenceController.setPreference(getApplicationContext(), PreferenceController.PreferenceKeys.PREFERENCE_AMOUNT_1, s);
        PreferenceController.setPreference(getApplicationContext(), PreferenceController.PreferenceKeys.PREFERENCE_AMOUNT_2, s);
        PreferenceController.setPreference(getApplicationContext(), PreferenceController.PreferenceKeys.PREFERENCE_AMOUNT_3, s);
        PreferenceController.setPreference(getApplicationContext(), PreferenceController.PreferenceKeys.PREFERENCE_AMOUNT_4, s);
        PreferenceController.setPreference(getApplicationContext(), PreferenceController.PreferenceKeys.PREFERENCE_AMOUNT_5, s);
        PreferenceController.setPreference(getApplicationContext(), PreferenceController.PreferenceKeys.PREFERENCE_AMOUNT_6, s);
        PreferenceController.setPreference(getApplicationContext(), PreferenceController.PreferenceKeys.PREFERENCE_AMOUNT_7, s);
        PreferenceController.setPreference(getApplicationContext(), PreferenceController.PreferenceKeys.PREFERENCE_AMOUNT_8, s);
        PreferenceController.setPreference(getApplicationContext(), PreferenceController.PreferenceKeys.PREFERENCE_AMOUNT_9, s);
    }

    private void CallForWallet() {
        tv_wallet.setText(PreferenceController.getStringPreference(getApplicationContext(), PreferenceController.PreferenceKeys.PREFERENCE_WALLET_BALANCE));
    }

    private void RftClass() {
        retrofit = ServiceGenerator.builder.build();
        Ui = retrofit.create(ApiServiceInterface.class);
    }

    private void ApioForProfile() {
        jsonObject.addProperty("uid", PreferenceController.getStringPreference(getApplicationContext(),
                PreferenceController.PreferenceKeys.PREFERENCE_CUSTOMER_ID));
        jsonObject.addProperty("skey", skey);
        jsonArray.add(jsonObject);
        Call<List<ProfileResponse>> profileResponseCall = Ui.GetwProfile("application/json", jsonArray);
        profileResponseCall.enqueue(new Callback<List<ProfileResponse>>() {
            @Override
            public void onResponse(Call<List<ProfileResponse>> call, Response<List<ProfileResponse>> response) {
                List<ProfileResponse> profileResponse = response.body();
                setPrefs(profileResponse);
            }

            @Override
            public void onFailure(Call<List<ProfileResponse>> call, Throwable t) {
                Log.w("no", "onFailure: ", t);

            }
        });

    }

    private void setPrefs(List<ProfileResponse> profileResponse) {
        PreferenceController.setPreference(getApplicationContext(), PreferenceController.PreferenceKeys.PREFERENCE_WALLET_BALANCE, profileResponse.get(0).getUWalet());

    }

    private void CallForName() {

        tv_name.setText(PreferenceController.getStringPreference(getApplicationContext(),
                PreferenceController.PreferenceKeys.PREFERENCE_LOGGED_FIRST_NAME));

    }

    private void Actionbarcall() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void Clicks() {
        tv_logout.setOnClickListener(this);
        button_start.setOnClickListener(this);
        tv_profile.setOnClickListener(this);
        tv_help.setOnClickListener(this);
        tv_conditions.setOnClickListener(this);
        tv_about.setOnClickListener(this);
        tv_how_to_play.setOnClickListener(this);
        tv_hints.setOnClickListener(this);
        tv_share.setOnClickListener(this);
        tv_results.setOnClickListener(this);
        tv_history.setOnClickListener(this);
        tv_wallet.setOnClickListener(this);
        tv_recharge.setOnClickListener(this);
    }

    private void NavigationDrawer() {
        DrawerLayout drawer = findViewById(R.id.drawer);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.getDrawerArrowDrawable().setColor(Color.WHITE);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        toggle.setDrawerIndicatorEnabled(true);
        drawer.closeDrawers();
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        DrawerLayout drawer = findViewById(R.id.drawer);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_logout:
                PreferenceController.setPreference(getApplicationContext(), PreferenceController.PreferenceKeys.PREFERENCE_LOGGED_STATUS, false);
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                Toast.makeText(HomeActivity.this, "Logout Sucessfull", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button_start:
//                if (playedstatus){
                    intent = new Intent(getApplicationContext(), Home2Activity.class);
                    startActivity(intent);
//                }else {
//                    Toast.makeText(this, "Please wait utill the current match is over", Toast.LENGTH_SHORT).show();
//                }

                break;
            case R.id.tv_history:
                intent = new Intent(getApplicationContext(), PlayHistory.class);
                startActivity(intent);
                break;
            case R.id.tv_myac:
                intent = new Intent(getApplicationContext(), ProfileActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_help:
                intent = new Intent(getApplicationContext(), Help.class);
                startActivity(intent);
                break;
            case R.id.tv_conditions:
                intent = new Intent(getApplicationContext(), TeremsConditions.class);
                startActivity(intent);
                break;
            case R.id.tv_about:
                intent = new Intent(getApplicationContext(), Home2Activity.class);
                startActivity(intent);
                break;
            case R.id.tv_how_to_play:
                goToYoutube();
                break;
            case R.id.tv_results:
                intent = new Intent(getApplicationContext(), LastMatchResults.class);
                startActivity(intent);
                break;
            case R.id.tv_hints:
                intent = new Intent(getApplicationContext(), PredictionHints.class);
                startActivity(intent);
                break;
            case R.id.tv_share:
                Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.android.vending");
                startActivity(launchIntent);
                break;
            case R.id.tv_wallet:
                startActivity(new Intent(getApplicationContext(), Payment.class));
                break;
            case R.id.tv_recharge:
                startActivity(new Intent(getApplicationContext(), Payment.class));
                break;

        }
    }

    private void goToYoutube() {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=bzSTpdcs-EI"));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setPackage("com.google.android.youtube");
        startActivity(intent);
    }

}
