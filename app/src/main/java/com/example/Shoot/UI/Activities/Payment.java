package com.example.Shoot.UI.Activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.Shoot.ApiInterfaces.ApiServiceInterface;
import com.example.Shoot.Pojo.Generate.GenerateOrderResponse;
import com.example.Shoot.Pojo.Generate.WalletBalance;
import com.example.Shoot.Pojo.ProfileResponse;
import com.example.Shoot.R;
import com.example.Shoot.ServiceClass.ServiceGenerator;
import com.example.Shoot.Storage.PreferenceController;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.razorpay.Checkout;
import com.razorpay.PaymentData;
import com.razorpay.PaymentResultWithDataListener;

import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Payment extends AppCompatActivity implements View.OnClickListener, PaymentResultWithDataListener {
    private final String KEY = "rzp_test_wCHewtjVtR4lNS";
    private ImageView iv_back;
    private TextView tv_wallet;
    private ConstraintLayout lay_done;
    private EditText et_amount;
    private Retrofit retrofit;
    private ApiServiceInterface Ui;
    private String skey = "uHtbabjrxcKQTeuwjQ==";
    private JsonArray jsonArray;
    private JsonObject jsonObject;
    private ProgressDialog progress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        Initial();
        Clicks();
        RftClass();
        CallForWallet();

    }

    private void Clicks() {
        iv_back.setOnClickListener(this);
        lay_done.setOnClickListener(this);
    }

    private void Initial() {
        iv_back = findViewById(R.id.iv_back);
        tv_wallet = findViewById(R.id.tv_wallet);
        lay_done = findViewById(R.id.lay_done);
        et_amount = findViewById(R.id.et_amount);
        jsonArray = new JsonArray();
        jsonObject = new JsonObject();

    }

    private void RftClass() {
        retrofit = ServiceGenerator.builder.build();
        Ui = retrofit.create(ApiServiceInterface.class);
    }


    private void CallForWallet() {
            jsonObject.addProperty("uid", PreferenceController.getStringPreference(getApplicationContext(),
                    PreferenceController.PreferenceKeys.PREFERENCE_CUSTOMER_ID));
            jsonObject.addProperty("skey", skey);
            jsonArray.add(jsonObject);
            Call<List<ProfileResponse>> profileResponseCall = Ui.GetwProfile("application/json", jsonArray);
            profileResponseCall.enqueue(new Callback<List<ProfileResponse>>() {
                @Override
                public void onResponse(Call<List<ProfileResponse>> call, Response<List<ProfileResponse>> response) {
                    List<ProfileResponse> profileResponse = response.body();
                    tv_wallet.setText(profileResponse.get(0).getUWalet());
                }

                @Override
                public void onFailure(Call<List<ProfileResponse>> call, Throwable t) {
                    Log.w("no", "onFailure: ", t);

                }
            });



    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.lay_done:
                String amount = et_amount.getText().toString();
                if (amount.isEmpty()) {
                    Toast.makeText(Payment.this, "Please Enter The Amount", Toast.LENGTH_SHORT).show();
                } else {
                    startAnimation();
                    getOrderid();
                }
        }
    }

    private void startAnimation() {
        progress = new ProgressDialog(Payment.this);
        progress.setMessage("Loading");
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
        progress.show();
    }

    private void stopAnimation() {
        progress.dismiss();
    }

    private void getOrderid() {
        int amount = Integer.parseInt(et_amount.getText().toString());
        amount = amount * 100;
        jsonObject.addProperty("amount", amount + "");
        jsonObject.addProperty("currency", "INR");
        Call<GenerateOrderResponse> generateOrderResponseCall = Ui.GetOrderid("application/json", jsonObject);
        final int finalAmount = amount;
        generateOrderResponseCall.enqueue(new Callback<GenerateOrderResponse>() {
            @Override
            public void onResponse(Call<GenerateOrderResponse> call, Response<GenerateOrderResponse> response) {
                GenerateOrderResponse orderResponse = response.body();
                if (response.isSuccessful()) {
                    String order_id = orderResponse.getOrderResult().getId();
                    CallPayment(order_id, finalAmount);
                }
            }

            @Override
            public void onFailure(Call<GenerateOrderResponse> call, Throwable t) {
                Log.w("onFailure: ", t);
            }
        });
    }

    private void CallPayment(String order_id, int amount) {
        final Activity activity = Payment.this;
        final Checkout co = new Checkout();
        co.setImage(R.drawable.logo);
        co.setKeyID(KEY);
        try {
            JSONObject options = new JSONObject();
            options.put("name", "Shoot");
            options.put("description", "");
            options.put("currency", "INR");
            options.put("order_id", order_id);
            options.put("amount", amount + "");
            JSONObject preFill = new JSONObject();
            preFill.put("email", PreferenceController.getStringPreference(getApplicationContext(), PreferenceController.PreferenceKeys.PREFERENCE_LOGGED_EMAIL));
            preFill.put("contact", PreferenceController.getStringPreference(getApplicationContext(), PreferenceController.PreferenceKeys.PREFERENCE_LOGGED_TELEPHONE));
            options.put("prefill", preFill);
            co.open(activity, options);
        } catch (Exception e) {
            Toast.makeText(activity, "Error in payment: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

    }

    @Override
    public void onPaymentSuccess(String s, PaymentData paymentData) {
        startAnimation();
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("user_id", PreferenceController.getStringPreference(getApplicationContext(), PreferenceController.PreferenceKeys.PREFERENCE_CUSTOMER_ID));
        jsonObject.addProperty("razorpay_payment_id", paymentData.getPaymentId());
        jsonObject.addProperty("razorpay_order_id", paymentData.getOrderId());
        jsonObject.addProperty("razorpay_signature", paymentData.getSignature());
        jsonObject.addProperty("skey", skey);
        Call<WalletBalance> walletBalanceCall = Ui.WalletBalance(jsonObject);
        walletBalanceCall.enqueue(new Callback<WalletBalance>() {
            @Override
            public void onResponse(Call<WalletBalance> call, Response<WalletBalance> response) {
                WalletBalance walletBalance = response.body();
                try {
                    if (response.isSuccessful()) {
                        String pay = walletBalance.getCurrentWaletAmount();
                        PreferenceController.setPreference(getApplicationContext(), PreferenceController.PreferenceKeys.PREFERENCE_WALLET_BALANCE, pay);
                        startActivity(new Intent(getApplicationContext(), HomeActivity.class));

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<WalletBalance> call, Throwable t) {
                Log.w("onFailure: ", t);
            }
        });

        Toast.makeText(Payment.this, "Success in payment: ", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPaymentError(int i, String s, PaymentData paymentData) {
        Toast.makeText(Payment.this, "Error in payment: ", Toast.LENGTH_SHORT).show();

    }
}