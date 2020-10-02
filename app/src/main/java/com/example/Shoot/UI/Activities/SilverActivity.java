package com.example.Shoot.UI.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.Shoot.ApiInterfaces.ApiServiceInterface;
import com.example.Shoot.OnFragmentInteractionListener;
import com.example.Shoot.Pojo.SendData;
import com.example.Shoot.R;
import com.example.Shoot.ServiceClass.ServiceGenerator;
import com.example.Shoot.Storage.PreferenceController;
import com.example.Shoot.UI.Activities.Results.Result;
import com.google.gson.JsonObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SilverActivity extends AppCompatActivity implements OnFragmentInteractionListener {
    private static final String TAG = "pref";
    ;
    private TextView button_time;
    private String time00 = "00",time15 = "15",time30 = "30",time45 = "45";
    private ApiServiceInterface UI;
    private final Handler handler=new Handler();
    private Retrofit retrofit;
    private ProgressDialog progress ;
    private ImageView save,iv_clear;
    private TextView tv_wallet,zero,one,two,three,four,five,six,seven,eight,nine,start_time,end_time;
    private ConstraintLayout lay1,lay0,lay2, lay3,lay4,lay5,lay6,lay7,lay8,lay9;
    private String walletbalance="100";
    private static final long Fifteen_munites=150000;
    private ArrayList<String> numbers = new ArrayList<>();
    private ArrayList<String> count = new ArrayList<>();
    private int tagId;
    private JsonObject jsonvalues = new JsonObject();
    private String time,result_time;
    private boolean playStatus ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_silver);
        Toolbar toolbar4=findViewById(R.id.toolbar4);
        setSupportActionBar(toolbar4);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar4.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        save                =findViewById(R.id.save);
        iv_clear            =findViewById(R.id.iv_clear);

        button_time         =(TextView) findViewById(R.id.button_time);
        tv_wallet           =(TextView) findViewById(R.id.tv_wallet);
        start_time          =(TextView) findViewById(R.id.start_time);
        end_time            =(TextView) findViewById(R.id.end_time);
        zero                =(TextView) findViewById(R.id.zero);
        one                 =(TextView) findViewById(R.id.one);
        two                 =(TextView) findViewById(R.id.two);
        three               =(TextView) findViewById(R.id.three);
        four                =(TextView) findViewById(R.id.four);
        five                =(TextView) findViewById(R.id.five);
        six                 =(TextView) findViewById(R.id.six);
        seven               =(TextView) findViewById(R.id.seven);
        eight               =(TextView) findViewById(R.id.eight);
        nine                =(TextView) findViewById(R.id.nine);
        lay0                =(ConstraintLayout) findViewById(R.id.lay0);
        lay1                =(ConstraintLayout) findViewById(R.id.lay1);
        lay2                =(ConstraintLayout) findViewById(R.id.lay2);
        lay3                =(ConstraintLayout) findViewById(R.id.lay3);
        lay4                =(ConstraintLayout) findViewById(R.id.lay4);
        lay5                =(ConstraintLayout) findViewById(R.id.lay5);
        lay6                =(ConstraintLayout) findViewById(R.id.lay6);
        lay7                =(ConstraintLayout) findViewById(R.id.lay7);
        lay8                =(ConstraintLayout) findViewById(R.id.lay8);
        lay9                =(ConstraintLayout) findViewById(R.id.lay9);

        retrofit = ServiceGenerator.builder.build();
        UI = retrofit.create(ApiServiceInterface.class);


        time();

        Firsttime();

        WalletAmount();

        setHandler();

        setPrefs();

        iv_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearAll();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               saveData();
            }
        });


        lay0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tagId=0;
                numbers.add("1");
                setPopUpWindow();
            }
        });
        lay1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tagId=1;
                numbers.add("2");
                setPopUpWindow();
            }
        });
        lay2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tagId=2;
                numbers.add("3");
                setPopUpWindow();
            }
        });
        lay3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tagId=3;
                numbers.add("4");
                setPopUpWindow();
            }
        });
        lay4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tagId=4;
                numbers.add("5");
                setPopUpWindow();

            }
        });
        lay5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tagId=5;
                numbers.add("6");
                setPopUpWindow();

            }
        });
        lay6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tagId=6;
                numbers.add("7");
                setPopUpWindow();

            }
        });
        lay7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tagId=7;
                numbers.add("8");
                setPopUpWindow();


            }
        });
        lay8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tagId=8;
                numbers.add("9");
                setPopUpWindow();

            }
        });
        lay9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tagId=9;
                numbers.add("10");
                setPopUpWindow();

            }
        });

    }

    private void Firsttime() {
        playStatus = PreferenceController.isFirstTime(getApplicationContext());

        if (playStatus){
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

            PreferenceController.setPreference(getApplicationContext(), PreferenceController.PreferenceKeys.PREFERENCE_FIRST_TIME,false);
        }
    }


    private void setPrefs() {
        zero.setText(PreferenceController.getStringPreference(getApplicationContext(), PreferenceController.PreferenceKeys.PREFERENCE_AMOUNT_0));
        one.setText(PreferenceController.getStringPreference(getApplicationContext(), PreferenceController.PreferenceKeys.PREFERENCE_AMOUNT_1));
        two.setText(PreferenceController.getStringPreference(getApplicationContext(), PreferenceController.PreferenceKeys.PREFERENCE_AMOUNT_2));
        three.setText(PreferenceController.getStringPreference(getApplicationContext(), PreferenceController.PreferenceKeys.PREFERENCE_AMOUNT_3));
        four.setText(PreferenceController.getStringPreference(getApplicationContext(), PreferenceController.PreferenceKeys.PREFERENCE_AMOUNT_4));
        five.setText(PreferenceController.getStringPreference(getApplicationContext(), PreferenceController.PreferenceKeys.PREFERENCE_AMOUNT_5));
        six.setText(PreferenceController.getStringPreference(getApplicationContext(), PreferenceController.PreferenceKeys.PREFERENCE_AMOUNT_6));
        seven.setText(PreferenceController.getStringPreference(getApplicationContext(), PreferenceController.PreferenceKeys.PREFERENCE_AMOUNT_7));
        eight.setText(PreferenceController.getStringPreference(getApplicationContext(), PreferenceController.PreferenceKeys.PREFERENCE_AMOUNT_8));
        nine.setText(PreferenceController.getStringPreference(getApplicationContext(), PreferenceController.PreferenceKeys.PREFERENCE_AMOUNT_9));

    }

    private void setHandler() {
        final Handler someHandler=new Handler(getMainLooper());
        someHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                button_time.setText(new SimpleDateFormat("hh:mm:ss", Locale.US).format(new Date()));
                someHandler.postDelayed(this, 1000);
            }
        }, 10);

    }

    private void saveData() {
        String n = zero.getText().toString();
        if (zero.getText().toString().equals("0")&&one.getText().toString().equals("0")
                &&two.getText().toString().equals("0")&&three.getText().toString().equals("0")
                &&four.getText().toString().equals("0")&&five.getText().toString().equals("0")
                &&six.getText().toString().equals("0")&&seven.getText().toString().equals("0")
                &&eight.getText().toString().equals("0")&&nine.getText().toString().equals("0"))

        {
            Toast.makeText(SilverActivity.this, "Please Enter Any One Of Amount", Toast.LENGTH_SHORT).show();
        }
        else {
            SaveDataApi();
            UpdateWalletAmount();
            savePref();
        }

    }

    private void savePref() {

        PreferenceController.setPreference(getApplicationContext(), PreferenceController.PreferenceKeys.PREFERENCE_AMOUNT_0,zero.getText().toString());
        PreferenceController.setPreference(getApplicationContext(), PreferenceController.PreferenceKeys.PREFERENCE_AMOUNT_1,one.getText().toString());
        PreferenceController.setPreference(getApplicationContext(), PreferenceController.PreferenceKeys.PREFERENCE_AMOUNT_2,two.getText().toString());
        PreferenceController.setPreference(getApplicationContext(), PreferenceController.PreferenceKeys.PREFERENCE_AMOUNT_3,three.getText().toString());
        PreferenceController.setPreference(getApplicationContext(), PreferenceController.PreferenceKeys.PREFERENCE_AMOUNT_4,four.getText().toString());
        PreferenceController.setPreference(getApplicationContext(), PreferenceController.PreferenceKeys.PREFERENCE_AMOUNT_5,five.getText().toString());
        PreferenceController.setPreference(getApplicationContext(), PreferenceController.PreferenceKeys.PREFERENCE_AMOUNT_6,six.getText().toString());
        PreferenceController.setPreference(getApplicationContext(), PreferenceController.PreferenceKeys.PREFERENCE_AMOUNT_7,seven.getText().toString());
        PreferenceController.setPreference(getApplicationContext(), PreferenceController.PreferenceKeys.PREFERENCE_AMOUNT_8,eight.getText().toString());
        PreferenceController.setPreference(getApplicationContext(), PreferenceController.PreferenceKeys.PREFERENCE_AMOUNT_9,nine.getText().toString());

    }

    private void clearAll() {
        zero.setText("0");
        one.setText("0");
        two.setText("0");
        three.setText("0");
        four.setText("0");
        five.setText("0");
        six.setText("0");
        seven.setText("0");
        eight.setText("0");
        nine.setText("0");

    }

    private void UpdateWalletAmount() {
        PreferenceController.setPreference(getApplicationContext(),
                PreferenceController.PreferenceKeys.PREFERENCE_WALLET_BALANCE,tv_wallet.getText().toString());
    }

    private void WalletAmount() {
        tv_wallet.setText(PreferenceController.getStringPreference(getApplicationContext(),
                PreferenceController.PreferenceKeys.PREFERENCE_WALLET_BALANCE));
    }

    private void SaveDataApi() {

        progress= new ProgressDialog(SilverActivity.this);
        progress.setMessage("Loading");
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
        progress.show();
        time = new SimpleDateFormat("HH:mm", Locale.US).format(new Date());

        jsonvalues.addProperty("segment",time);
        jsonvalues.addProperty("user_id", PreferenceController.getStringPreference(getApplicationContext(),PreferenceController.PreferenceKeys.PREFERENCE_CUSTOMER_ID));
        jsonvalues.addProperty("numbers", String.valueOf(numbers));
        jsonvalues.addProperty("count", String.valueOf(count));
        jsonvalues.addProperty("skey","uHtbabjrxcKQTeuwjQ==");

        Call<SendData> sendDataCall = UI.sendData("application/json",jsonvalues);
        sendDataCall.enqueue(new Callback<SendData>()
        {
            @Override
            public void onResponse(Call<SendData> call, Response<SendData> response)

            {
                PreferenceController.setPreference(getApplicationContext(),PreferenceController.PreferenceKeys.PREFERENCE_MID,response.body().getMatchId());
                Log.i("ACCOUNT_RESPONSE", "<==" + response.code());
                progress.cancel();

                SendData data = response.body();
                Log.i("ACCOUNT_RESPONSE", "<==" + data.getStatus());
                Log.i("ACCOUNT_RESPONSE", "<==" + data.getMatchId());
                PreferenceController.setPreference(getApplicationContext(),PreferenceController.PreferenceKeys.PREFERENCE_GAME_END_TIME,result_time);
                Intent intent = new Intent(getApplicationContext(), Result.class);
                startActivity(intent);





            }
            @Override
            public void onFailure(Call<SendData> call, Throwable t)
            {

                //Toast.makeText(SilverActivity.this, "data", Toast.LENGTH_SHORT).show();
                Log.e("EEEEEEERRR", t.getMessage());
            }
        });

    }

    private void time() {

        StartingTime();



    }

    private void StartingTime() {
        int time ;
        start_time.setText(new SimpleDateFormat("hh:mm", Locale.US).format(new Date()));
        String munites = start_time.getText().toString();
        String[] splitmunit = munites.split(":");
        String hrs = splitmunit[0];
        String munts = splitmunit[1];
        time = Integer.parseInt(munts);

        if (time <= 15){
            start_time.setText(hrs+":"+"00");
        }
        else if (time <= 30){
            start_time.setText(hrs+":"+time15);
        }
        else if (time <= 45){
            start_time.setText(hrs+":"+time30);
        }
        else if (time <= 59){
            start_time.setText(hrs+":"+time45);
        }

        String endtime = start_time.getText().toString();
        Endtime(endtime);
    }

    private void Endtime(String endtime) {
        String[] split = endtime.split(":");
        String hrs = split[0];
        String mnts = split[1];
        int time = Integer.parseInt(mnts);
        time   = time + 14;
        end_time.setText(hrs+":"+time+"");

        result_time = end_time.getText().toString();

    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }
    private void setPopUpWindow() {
        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.popup, null);
        dialogBuilder.setView(dialogView);
        final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(R.color.appbar));
        alertDialog.getWindow().setLayout(800,350);
        final EditText editText = (EditText) dialogView.findViewById(R.id.et);
        final Button ok=(Button)dialogView.findViewById(R.id.ok);
        final Button close=(Button)dialogView.findViewById(R.id.close);
        final Button reset=(Button)dialogView.findViewById(R.id.reset);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText("");
            }
        });
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.cancel();
            }
        });
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                walletbalance= editText.getText().toString();
                if (walletbalance.isEmpty()) {
                    Toast.makeText(SilverActivity.this, "Enter A Number Multiple Of 10", Toast.LENGTH_SHORT).show();

                }
                else {

                String balnce= tv_wallet.getText().toString();
                int balance=Integer.parseInt(balnce);
                int d= Integer.parseInt(walletbalance);
                Log.w("qq",d+"");
                if ((d % 10 == 0 ) && (!balnce.equals("0"))){

                    switch(tagId){
                        case 0:
                            balance=balance-d;
                            if (balance>=0) {
                                tv_wallet.setText("" + balance);
                                zero.setText(walletbalance);

                                d= d/10;
                                count.add(d+"");
                                alertDialog.cancel();
                            }
                            else {
                                Toast.makeText(SilverActivity.this, "Insufficiant Balance", Toast.LENGTH_SHORT).show();
                            }
                            break;
                        case 1:
                            balance=balance-d;
                            if (balance>=0) {
                                tv_wallet.setText("" + balance);
                                one.setText(walletbalance);

                                d= d/10;
                                count.add(d+"");
                                alertDialog.cancel();
                            }
                            else {
                                Toast.makeText(SilverActivity.this, "Insufficiant Balance", Toast.LENGTH_SHORT).show();
                            }
                            break;
                        case 2:
                            balance=balance-d;
                            if (balance>=0) {
                                tv_wallet.setText("" + balance);
                                two.setText(walletbalance);
                                d= d/10;
                                count.add(d+"");

                                alertDialog.cancel();
                            }
                            else {
                                Toast.makeText(SilverActivity.this, "Insufficiant Balance", Toast.LENGTH_SHORT).show();
                            }
                            break;
                        case 3:
                            balance=balance-d;
                            if (balance>=0) {
                                tv_wallet.setText("" + balance);
                                three.setText(walletbalance);
                                d= d/10;
                                count.add(d+"");

                                alertDialog.cancel();
                            }
                            else {
                                Toast.makeText(SilverActivity.this, "Insufficiant Balance", Toast.LENGTH_SHORT).show();
                            }
                            break;
                        case 4:
                            balance=balance-d;
                            if (balance>=0) {
                                tv_wallet.setText("" + balance);
                                four.setText(walletbalance);
                                d= d/10;
                                count.add(d+"");

                                alertDialog.cancel();
                            }
                            else {
                                Toast.makeText(SilverActivity.this, "Insufficiant Balance", Toast.LENGTH_SHORT).show();
                            }
                            break;
                        case 5:
                            balance=balance-d;
                            if (balance>=0) {
                                tv_wallet.setText("" + balance);
                                five.setText(walletbalance);
                                d= d/10;
                                count.add(d+"");

                                alertDialog.cancel();
                            }
                            else {
                                Toast.makeText(SilverActivity.this, "Insufficiant Balance", Toast.LENGTH_SHORT).show();
                            }
                            break;
                        case 6:
                            balance=balance-d;
                            if (balance>=0) {
                                tv_wallet.setText("" + balance);
                                six.setText(walletbalance);
                                d= d/10;
                                count.add(d+"");

                                alertDialog.cancel();
                            }
                            else {
                                Toast.makeText(SilverActivity.this, "Insufficiant Balance", Toast.LENGTH_SHORT).show();
                            }
                            break;
                        case 7:
                            balance=balance-d;
                            if (balance>=0) {
                                tv_wallet.setText("" + balance);
                                seven.setText(walletbalance);
                                d= d/10;
                                count.add(d+"");

                                alertDialog.cancel();
                            }
                            else {
                                Toast.makeText(SilverActivity.this, "Insufficiant Balance", Toast.LENGTH_SHORT).show();
                            }
                            break;
                        case 8:
                            balance=balance-d;
                            if (balance>=0) {
                                tv_wallet.setText("" + balance);
                                eight.setText(walletbalance);
                                d= d/10;
                                count.add(d+"");

                                alertDialog.cancel();
                            }
                            else {
                                Toast.makeText(SilverActivity.this, "Insufficiant Balance", Toast.LENGTH_SHORT).show();
                            }
                            break;
                        case 9:
                            balance=balance-d;
                            if (balance>=0) {
                                tv_wallet.setText("" + balance);
                                nine.setText(walletbalance);
                                d= d/10;
                                count.add(d+"");

                                alertDialog.cancel();
                            }
                            else {
                                Toast.makeText(SilverActivity.this, "Insufficiant Balance", Toast.LENGTH_SHORT).show();
                            }
                        break;
                        default:
                            break;
                    }

                }
                else if (balnce.equals("0")){
                    Toast.makeText(SilverActivity.this, "Not Enough Balance", Toast.LENGTH_SHORT).show();
                }
                else if (walletbalance.isEmpty()){
                    Toast.makeText(SilverActivity.this, "Enter A value", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(SilverActivity.this, "Not A Multiple Of 10", Toast.LENGTH_SHORT).show();
                }

            }
            }
        });






    }
}
