package com.example.Shoot.Controller;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.Shoot.ApiInterfaces.ApiServiceInterface;
import com.example.Shoot.Pojo.UpdateProfile;
import com.example.Shoot.ServiceClass.RetrofitClass;
import com.example.Shoot.ServiceInterfaces.UpdateProCall;
import com.example.Shoot.ServiceInterfaces.UpdateProCallback;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class UpdateProController implements UpdateProCall {
 private Context context;
 private UpdateProCallback updateProCallback;

    public UpdateProController(Context context, UpdateProCallback updateProCallback) {
        this.context = context;
        this.updateProCallback = updateProCallback;
    }

    @Override
    public void getUpdateProData(final Integer uid, final String name, final String mobile, final String email, final String countryid) {
        Retrofit retrofit= RetrofitClass.getClient();
        ApiServiceInterface apiServiceInterface=retrofit.create(ApiServiceInterface.class);

        ArrayList<UpdateProfile> params=new ArrayList<>();
        params.add(new UpdateProfile(uid,"name","mobile","email","countryId","uHtbabjrxcKQTeuwjQ=="));
        apiServiceInterface.postUpdateProfile(params).enqueue(new Callback<UpdateProfile>() {
            @Override
            public void onResponse(Call<UpdateProfile> call, Response<UpdateProfile> response) {
                if (response.isSuccessful()) {
//                    UpdateProfile updateProfile=response.body();
//                    Integer uid=updateProfile.getUid();
//                    String name=updateProfile.getName();
//                    String mobile=updateProfile.getMobile();
//                    String countryId=updateProfile.getCountryId();
                    Log.w("Response Data", String.valueOf(response.body()));
                    if (response.body() != null) {
                        if (response.body().equals("1"))
                            Toast.makeText(context, "Updated Successfully", Toast.LENGTH_SHORT).show();
                        else if (response.body().equals("0"))
                            Toast.makeText(context, "Mobile number already exist", Toast.LENGTH_SHORT).show();
                        else if (response.body().equals("2"))
                            Toast.makeText(context, "EMail ID already exist", Toast.LENGTH_SHORT).show();
                        else Toast.makeText(context, "failed", Toast.LENGTH_SHORT).show();
                    }


                    updateProCallback.getUpdateProResponse("code");
                } else {
                    Toast.makeText(context, "Failed", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<UpdateProfile> call, Throwable t) {
                Log.e("Error...", t.getMessage() + "");
            }
        });
    }
}
