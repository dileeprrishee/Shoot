package com.example.Shoot.UI.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.example.Shoot.OnFragmentInteractionListener;
import com.example.Shoot.R;
import com.example.Shoot.Storage.PreferenceController;
import com.example.Shoot.UI.Activities.Results.Result;
import com.example.Shoot.UI.Activities.login.LoginActivity;
import com.example.Shoot.UI.Fragments.Home2Activity;
import com.google.android.material.navigation.NavigationView;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,
        OnFragmentInteractionListener,View.OnClickListener {
    private Button button_start;
    private TextView tv_wallet_bal,tv_profile,tv_logout,tv_results,tv_name,tv_wallet,tv_History,
                      tv_help,tv_conditions,tv_about,tv_how_to_play,tv_hints,tv_share;
    private Toolbar toolbar;
    private boolean winnerStatus;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        toolbar             = findViewById(R.id.toolbar2);
        tv_logout           = findViewById(R.id.tv_logout);
        button_start        = (Button)findViewById(R.id.button_start);
        tv_results          = (TextView) findViewById(R.id.tv_results);
        tv_name             = (TextView) findViewById(R.id.tv_name);
        tv_wallet           = (TextView) findViewById(R.id.tv_wallet);
        tv_History          = (TextView) findViewById(R.id.tv_History);
        tv_profile          = (TextView) findViewById(R.id.tv_myac);
        tv_help             = (TextView) findViewById(R.id.tv_help);
        tv_conditions       = (TextView) findViewById(R.id.tv_conditions);
        tv_about            = (TextView) findViewById(R.id.tv_about);
        tv_how_to_play      = (TextView) findViewById(R.id.tv_how_to_play);
        tv_hints            = (TextView) findViewById(R.id.tv_hints);
        tv_share            = (TextView) findViewById(R.id.tv_share);


        Actionbarcall();
        NavigationDrawer();
        CallForName();
        CallForWallet();
        Clicks();


    }

    private void CallForWallet() {
        tv_wallet.setText(PreferenceController.getStringPreference(getApplicationContext(),PreferenceController.PreferenceKeys.PREFERENCE_WALLET_BALANCE));
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
        tv_results.setOnClickListener(this);
        tv_History.setOnClickListener(this);
        tv_profile.setOnClickListener(this);
        tv_help.setOnClickListener(this);
        tv_conditions.setOnClickListener(this);
        tv_about.setOnClickListener(this);
        tv_how_to_play.setOnClickListener(this);
        tv_hints.setOnClickListener(this);
        tv_share.setOnClickListener(this);

    }

    private void NavigationDrawer() {
        DrawerLayout drawer = findViewById(R.id.drawer);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer,toolbar , R.string.navigation_drawer_open, R.string.navigation_drawer_close);
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
                PreferenceController.setPreference(getApplicationContext(),PreferenceController.PreferenceKeys.PREFERENCE_LOGGED_STATUS,false);
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                Toast.makeText(HomeActivity.this, "Logout Sucessfull", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button_start:
                 intent=new Intent(getApplicationContext(), Home2Activity.class);
                startActivity(intent);
                break;
            case R.id.tv_results:
                intent = new Intent(getApplicationContext(), PlayHistory.class);
                startActivity(intent);
                break;
            case R.id.tv_History:
                intent = new Intent(getApplicationContext(),PlayHistory.class);
                startActivity(intent);
                break;
            case R.id.tv_myac:
                intent = new Intent(getApplicationContext(),ProfileActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_help:
                intent = new Intent(getApplicationContext(),Help.class);
                startActivity(intent);
                break;
            case R.id.tv_conditions:
                intent = new Intent(getApplicationContext(),TeremsConditions.class);
                startActivity(intent);
                break;
            case R.id.tv_about:
                intent = new Intent(getApplicationContext(),Home2Activity.class);
                startActivity(intent);
                break;
            case R.id.tv_how_to_play:
                 goToYoutube();
                break;
            case R.id.tv_hints:
                intent = new Intent(getApplicationContext(),PredictionHints.class);
                startActivity(intent);
                break;
            case R.id.tv_share:
                Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.android.vending");
                startActivity(launchIntent);
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
