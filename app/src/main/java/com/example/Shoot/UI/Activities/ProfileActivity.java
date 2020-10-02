package com.example.Shoot.UI.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.Shoot.R;
import com.example.Shoot.Storage.PreferenceController;

public class ProfileActivity extends AppCompatActivity {
    private TextView tv_uname,tv_umobile,tv_umobileemail,tv_ucountry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        tv_uname                = findViewById(R.id.tv_uname);
        tv_umobile              = findViewById(R.id.tv_umobile);
        tv_umobileemail         = findViewById(R.id.tv_umobileemail);
        tv_ucountry             = findViewById(R.id.tv_ucountry);

        setPrefs();

    }

    private void setPrefs() {
        tv_uname.setText(PreferenceController.getStringPreference(getApplicationContext(), PreferenceController.PreferenceKeys.PREFERENCE_LOGGED_FIRST_NAME));
        tv_umobile.setText(PreferenceController.getStringPreference(getApplicationContext(), PreferenceController.PreferenceKeys.PREFERENCE_LOGGED_TELEPHONE));
        tv_umobileemail.setText(PreferenceController.getStringPreference(getApplicationContext(), PreferenceController.PreferenceKeys.PREFERENCE_LOGGED_EMAIL));

    }
}