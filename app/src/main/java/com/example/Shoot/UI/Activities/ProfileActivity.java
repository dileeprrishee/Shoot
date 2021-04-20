package com.example.Shoot.UI.Activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.Shoot.ApiInterfaces.ApiServiceInterface;
import com.example.Shoot.Pojo.ProfileResponse;
import com.example.Shoot.R;
import com.example.Shoot.ServiceClass.ServiceGenerator;
import com.example.Shoot.Storage.PreferenceController;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ProfileActivity extends AppCompatActivity {
    private TextView tv_uname, tv_umobile, tv_umobileemail, tv_ucountry, tv_wallet;
    private Retrofit retrofit;
    private ImageView iv_back;
    private ApiServiceInterface Ui;
    private String skey = "uHtbabjrxcKQTeuwjQ==";
    private JsonArray jsonArray;
    private JsonObject jsonObject;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        tv_uname = findViewById(R.id.tv_uname);
        tv_umobile = findViewById(R.id.tv_umobile);
        tv_umobileemail = findViewById(R.id.tv_umobileemail);
        tv_ucountry = findViewById(R.id.tv_ucountry);
        tv_wallet = findViewById(R.id.tv_wallet);
        iv_back = findViewById(R.id.iv_back);
        jsonArray = new JsonArray();
        jsonObject = new JsonObject();
        progressDialog = new ProgressDialog(ProfileActivity.this);
        Progressdilog();
        RftClass();
        ApioForProfile();
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void RftClass() {
        retrofit = ServiceGenerator.builder.build();
        Ui = retrofit.create(ApiServiceInterface.class);
    }

    private void Progressdilog() {
        progressDialog.setMessage("Loading");
        progressDialog.setCancelable(true); // disable dismiss by tapping outside of the dialog
        progressDialog.show();

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
                progressDialog.cancel();
            }

            @Override
            public void onFailure(Call<List<ProfileResponse>> call, Throwable t) {
                Log.w("no", "onFailure: ",t );

            }
        });

    }

    private void setPrefs(List<ProfileResponse> profileResponse) {
        tv_uname.setText(profileResponse.get(0).getUName());
        tv_umobile.setText(profileResponse.get(0).getUMobile());
        tv_umobileemail.setText(profileResponse.get(0).getUEmail());
        tv_wallet.setText(profileResponse.get(0).getUWalet()+" ₹");
    }
}