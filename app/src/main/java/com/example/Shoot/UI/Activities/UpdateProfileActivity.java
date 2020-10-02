package com.example.Shoot.UI.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.Shoot.Controller.UpdateProController;
import com.example.Shoot.R;
import com.example.Shoot.ServiceInterfaces.UpdateProCallback;

public class UpdateProfileActivity extends AppCompatActivity implements UpdateProCallback {
TextView tv_upname,tv_upemail;
private UpdateProController updateProController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);
        tv_upname=(TextView)findViewById(R.id.tv_upname);
        tv_upemail=(TextView)findViewById(R.id.tv_upemail);

        updateProController=new UpdateProController(getApplicationContext(),this);

//        updateProController.getUpdateProData("uid","name","mobile","email","countryId");




    }









    @Override
    public void getUpdateProResponse(String code) {

    }
}
