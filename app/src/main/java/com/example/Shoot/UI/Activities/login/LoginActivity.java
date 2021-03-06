package com.example.Shoot.UI.Activities.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.Shoot.ApiInterfaces.ApiServiceInterface;
import com.example.Shoot.Controller.LoginController;
import com.example.Shoot.Pojo.LoginResponse;
import com.example.Shoot.R;
import com.example.Shoot.ServiceClass.ServiceGenerator;
import com.example.Shoot.Storage.PreferenceController;
import com.example.Shoot.UI.Activities.HomeActivity;
import com.example.Shoot.UI.Activities.RegisterActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import retrofit2.Retrofit;

public class LoginActivity extends AppCompatActivity {
    private Button button_sign;
    private ImageView img_password;
    private boolean showPassword = true;
    private LoginController loginCall;
    private EditText et_phone_number, et_password;
    private ProgressDialog progressDialog;
    private ApiServiceInterface UI;
    private Retrofit retrofit;
    private JsonArray request = new JsonArray();
    private JsonObject value = new JsonObject();
    private String usr, pass,token;
    private TextView tv_signup;
    private ConstraintLayout lay_signin;
    private LoginViewModel loginViewModel;
    private Animation animation;
    private CardView cv_slide;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        tv_signup = findViewById(R.id.tv_signup);
        lay_signin = findViewById(R.id.lay_done);
        et_phone_number = findViewById(R.id.et_amount);
        et_password = findViewById(R.id.et_password);
        img_password = findViewById(R.id.img_password);
        cv_slide = findViewById(R.id.cv_slide);

        img_password.setImageResource(R.drawable.ic_hide_password);

        SlideUp();

        callRetofit();
        Signup();
        Signin();


    }

    private void SlideUp() {
        animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up);
        cv_slide.setVisibility(View.VISIBLE);
        cv_slide.startAnimation(animation);
    }

    private void callRetofit() {
        retrofit = ServiceGenerator.builder.build();
        UI = retrofit.create(ApiServiceInterface.class);
    }

    public void click(View view) {
        int id = view.getId();
        switch (id) {
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

        }
    }

    private void Signin() {
            lay_signin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    GetToken();
                    String uname, pass;
                    uname = et_phone_number.getText().toString();
                    pass = et_password.getText().toString();
                    if (uname.isEmpty() || pass.isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Please Fill The Fields", Toast.LENGTH_SHORT).show();
                    } else {
                        apiLogin();
                        progressDialog = new ProgressDialog(LoginActivity.this);
                        progressDialog.setMessage("Loading");
                        progressDialog.setCancelable(true); // disable dismiss by tapping outside of the dialog
                        progressDialog.show();
                    }
                }
            });


    }

    private void apiLogin() {
        request = new JsonArray();
        value = new JsonObject();

        value.addProperty("uname", et_phone_number.getText().toString());
        value.addProperty("pword", et_password.getText().toString());
        value.addProperty("skey", "uHtbabjrxcKQTeuwjQ==");
        request.add(value);

        loginViewModel.triggerLogin(request);

        loginViewModel.getData().observe(this, new Observer<LoginResponse>() {
            @Override
            public void onChanged(LoginResponse loginResponse) {
                if (getLifecycle().getCurrentState() == Lifecycle.State.RESUMED) {
                    loadApiData(loginResponse);
                }
            }
        });
    }

    private void loadApiData(LoginResponse loginResponse) {
        if (loginResponse != null) {

            PreferenceController.setPreference(getApplicationContext(),
                    PreferenceController.PreferenceKeys.PREFERENCE_CUSTOMER_ID, loginResponse.getUid());

            PreferenceController.setPreference(getApplicationContext(),
                    PreferenceController.PreferenceKeys.PREFERENCE_LOGGED_STATUS, true);

            PreferenceController.setPreference(getApplicationContext(),
                    PreferenceController.PreferenceKeys.PREFERENCE_FIRST_TIME, true);

            PreferenceController.setPreference(getApplicationContext(),
                    PreferenceController.PreferenceKeys.PREFERENCE_LOGGED_FIRST_NAME, loginResponse.getName());

            PreferenceController.setPreference(getApplicationContext(),
                    PreferenceController.PreferenceKeys.PREFERENCE_LOGGED_EMAIL, loginResponse.getEmail());

            PreferenceController.setPreference(getApplicationContext(),
                    PreferenceController.PreferenceKeys.PREFERENCE_LOGGED_TELEPHONE, loginResponse.getMobile());

            PreferenceController.setPreference(getApplicationContext(),
                    PreferenceController.PreferenceKeys.PREFERENCE_WALLET_BALANCE, loginResponse.getWaletAmt());


            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(intent);
            progressDialog.dismiss();

        } else{

            Toast.makeText(getApplicationContext(), "Incorrect username or password", Toast.LENGTH_LONG).show();
            progressDialog.dismiss();
        }

    }


    private void Signup() {
        tv_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    private void GetToken() {
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            Log.w("TAG", "Fetching FCM registration token failed", task.getException());
                            return;
                        }

                        // Get new FCM registration token
                        token = task.getResult();
                        PreferenceController.setPreference(getApplicationContext(), PreferenceController.PreferenceKeys.PREFERENCE_FCM_TOKEN, token);

                        // Log and toast
//                        String msg = getString(Integer.parseInt("R.string.msg_token_fmt"), token);
//                        Log.d("TAG", msg);
//                        Toast.makeText(Splash.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });

    }



}
