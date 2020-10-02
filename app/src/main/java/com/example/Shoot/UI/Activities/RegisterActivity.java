package com.example.Shoot.UI.Activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.Shoot.ApiInterfaces.ApiServiceInterface;
import com.example.Shoot.Pojo.CountryResponse;
import com.example.Shoot.R;
import com.example.Shoot.ServiceClass.ServiceGenerator;
import com.example.Shoot.Storage.PreferenceController;
import com.example.Shoot.UI.Activities.login.LoginActivity;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class RegisterActivity extends AppCompatActivity implements  View.OnClickListener {
    private Spinner spinner_country;
    private TextView tv_sign_in;
    private ConstraintLayout layout_signup;
    private ProgressDialog progressDialog;
    private EditText et_fullname, et_phone, et_password, et_confirm_password, et_email;
    private ImageView img_password, img_confirm_password;
    private boolean showPassword = true;
    private boolean showConfirmPassword = true;
    private ArrayAdapter countryAdapter;
    private Retrofit retrofit,retrofit2;
    private ApiServiceInterface UI,UI2;
    private JsonArray requestcountry,requestsignup;
    private JsonObject valuecountry,valuesignup;
    private ArrayList country;
    private List<CountryResponse> countryResponsesdata;
    private String CountryId,name,mobile,email,password,password2;
    private String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        tv_sign_in           = findViewById(R.id.tv_sign_in);
        spinner_country      = findViewById(R.id.spinner_country);
        layout_signup        = findViewById(R.id.layout_signup);
        et_fullname          = findViewById(R.id.et_fullname);
        et_phone             = findViewById(R.id.et_phone);
        et_password          = findViewById(R.id.et_password);
        et_confirm_password  = findViewById(R.id.et_confirm_password);
        et_email             = findViewById(R.id.et_email);
        img_password         = findViewById(R.id.img_password);
        img_confirm_password = findViewById(R.id.img_confirm_password);
        progressDialog       = new ProgressDialog(RegisterActivity.this);
        retrofit             = ServiceGenerator.builder.build();
        UI                   = retrofit.create(ApiServiceInterface.class);
        retrofit2            = ServiceGenerator.builder2.build();
        UI2                  = retrofit2.create(ApiServiceInterface.class);
        requestcountry       = new JsonArray();
        valuecountry         = new JsonObject();
        requestsignup        = new JsonArray();
        valuesignup          = new JsonObject();
        country              = new ArrayList();
        countryResponsesdata = new ArrayList<>();

        Clicks();

        img_password.setImageResource(R.drawable.ic_hide_password);
        img_confirm_password.setImageResource(R.drawable.ic_hide_password);

        callForProgress();

        SpinnerCountry();
    }


    private void Emailvalidation() {
        if(et_email.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(),"enter email address",Toast.LENGTH_SHORT).show();
        }else {
            if (et_email.getText().toString().trim().matches(emailPattern)) {
            } else {
                Toast.makeText(getApplicationContext(),"Invalid email address", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void Clicks() {
        img_password.setOnClickListener(this);
        img_confirm_password.setOnClickListener(this);
        layout_signup.setOnClickListener(this);
        tv_sign_in.setOnClickListener(this);

    }

    private void Signin() {
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);    }

    private void CallForRegister() {
        getAlldata();
        if (name.isEmpty()||password.isEmpty()||email.isEmpty()||mobile.isEmpty()){
            Toast.makeText(RegisterActivity.this, "Field Missing", Toast.LENGTH_SHORT).show();
        }
        else {
            ApiforRegister();
        }

    }

    private void ApiforRegister() {
        valuesignup.addProperty("name",name);
        valuesignup.addProperty("mobile",mobile);
        valuesignup.addProperty("email",email);
        valuesignup.addProperty("country_id",CountryId);
        valuesignup.addProperty("password",password);
        String key = "uHtbabjrxcKQTeuwjQ==";
        valuesignup.addProperty("skey",key);
        requestsignup.add(valuesignup);



        Gson gson = new Gson();
        String jsonString = requestsignup.toString();


        Call<String> signupCall = UI2.Signup("application/json",jsonString);
        signupCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                Log.d("TEST RUN", "onResponse: "+response.toString());

                if (response.isSuccessful()){
                    String responsestring = response.body();
                    Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                    startActivity(intent);
                    progressDialog.dismiss();
                    Toast.makeText(RegisterActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });

    }


    private void getAlldata() {
        name        = et_fullname.getText().toString();
        email       = et_email.getText().toString();
        password    = et_password.getText().toString();
        mobile      = et_phone.getText().toString();
        password2   = et_confirm_password.getText().toString();

    }

    private void setPrefs() {
        PreferenceController.setPreference(getApplicationContext(), PreferenceController.PreferenceKeys.PREFERENCE_USER_COUNTRY_ID,CountryId);

    }

    private void callForProgress() {
        progressDialog.setMessage("Loading");
        progressDialog.setCancelable(true); // disable dismiss by tapping outside of the dialog
        progressDialog.show();
    }

    private void SpinnerCountry() {
        ApiForSpinner();
    }

    private void ApiForSpinner() {
        valuecountry.addProperty("skey","uHtbabjrxcKQTeuwjQ==");
        requestcountry.add(valuecountry);
        Call<List<CountryResponse>> countryResponseCall = UI.postCountry("application/json", requestcountry);
        countryResponseCall.enqueue(new Callback<List<CountryResponse>>() {
            @Override
            public void onResponse(Call<List<CountryResponse>> call, Response<List<CountryResponse>> response) {
                countryResponsesdata = response.body();
                for (int i = 0; i < countryResponsesdata.size(); i++) {
                    country.add(countryResponsesdata.get(i).getCountryName());
//                    country.add(response.body().get(i).getCountryName());
                }
                countryAdapter = new ArrayAdapter(RegisterActivity.this, R.layout.spinner_item, country);
                countryAdapter.setDropDownViewResource(R.layout.spinner_item);
                spinner_country.setAdapter(countryAdapter);
                progressDialog.dismiss();

                spinner_country.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                        String cntry =spinner_country.getSelectedItem().toString();
                        for (CountryResponse data : countryResponsesdata){
                                if (cntry == data.getCountryName()){
                                    CountryId = data.getId();
                                }
                        }

                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }

            @Override
            public void onFailure(Call<List<CountryResponse>> call, Throwable t) {

            }
        } );

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_password:
                if (showPassword) {
                    showPassword = false;
                    img_password.setImageResource(R.drawable.ic_show_password);
                    et_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());

                } else {
                    showPassword = true;
                    img_password.setImageResource(R.drawable.ic_hide_password);
                    et_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                break;
            case R.id.img_confirm_password:
                if (showConfirmPassword) {
                    showConfirmPassword = false;
                    img_confirm_password.setImageResource(R.drawable.ic_show_password);
                    et_confirm_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());

                } else {
                    showConfirmPassword = true;
                    img_confirm_password.setImageResource(R.drawable.ic_hide_password);
                    et_confirm_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                break;
            case R.id.layout_signup:
                progressDialog.show();
                Emailvalidation();
                Phonenovalidation();
                break;
            case R.id.tv_sign_in:
                setPrefs();
                Signin();
                break;


        }

    }

    private void Phonenovalidation() {
        String phno = et_phone.getText().toString();
        int length  = phno.length();
        if (length==10){
           CallForRegister();
        }
        else {
            Toast.makeText(this, "Invalid Phone Number", Toast.LENGTH_SHORT).show();
        }
    }





}

