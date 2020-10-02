package com.example.Shoot.UI.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.Shoot.R;
import com.example.Shoot.Storage.PreferenceController;

public class Selection extends AppCompatActivity implements View.OnClickListener {
    private TextView tv_silver,tv_wallet;
    private ImageView iv_silver;
    private Toolbar toolbar4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);
        toolbar4            = findViewById(R.id.toolbar4);
        tv_wallet           = (TextView)findViewById(R.id.tv_wallet);
        tv_silver           = (TextView)findViewById(R.id.tv_silver);
        iv_silver           = (ImageView) findViewById(R.id.iv_silver);
        ToolbarActions();
        WalletBalance();
        Clicks();

    }

    private void ToolbarActions() {
        setSupportActionBar(toolbar4);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar4.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void Clicks() {
        tv_wallet.setOnClickListener(this);
        iv_silver.setOnClickListener(this);
    }

    private void WalletBalance() {
        tv_wallet.setText(PreferenceController.getStringPreference(getApplicationContext(),
                PreferenceController.PreferenceKeys.PREFERENCE_WALLET_BALANCE));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_silver:
                Intent intent = new Intent(getApplicationContext(), SilverActivity.class);
                startActivity(intent);
                break;
            case R.id.iv_silver:
                intent = new Intent(getApplicationContext(), SilverActivity.class);
                startActivity(intent);
                break;

        }
    }
}