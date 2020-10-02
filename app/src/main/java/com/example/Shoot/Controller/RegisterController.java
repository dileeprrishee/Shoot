package com.example.Shoot.Controller;

import android.content.Context;

import com.example.Shoot.ApiInterfaces.ApiServiceInterface;
import com.example.Shoot.Pojo.Register;
import com.example.Shoot.ServiceClass.RetrofitClass;
import com.example.Shoot.ServiceInterfaces.RegisterCall;
import com.example.Shoot.ServiceInterfaces.RegisterCallback;

import java.util.ArrayList;

import retrofit2.Retrofit;

public class RegisterController implements RegisterCall {
     private Context context;
     private RegisterCallback registerCallback;

    public RegisterController(Context context,RegisterCallback registerCallback) {
        this.context = context;
        this.registerCallback = registerCallback;
    }

    @Override
    public void getRegisterData(final String name, final String mobile, String email, String country_id, final String password) {
        Retrofit retrofit= RetrofitClass.getClient();
        ApiServiceInterface serviceInterfaceRegister = retrofit.create(ApiServiceInterface.class);

        ArrayList<Register> params = new ArrayList<>();
        params.add(new Register(name,mobile,email,country_id,password,"uHtbabjrxcKQTeuwjQ=="));

//        serviceInterfaceRegister.datapost(params).enqueue(new Callback<String>() {
//            @Override
//            public void onResponse(Call<String> call, Response<String> response) {
//                if (response.isSuccessful()){
//                    Log.w("Response_Data",response.body());
//                    Toast.makeText(context, "" + response.code() + "\n" + response.body(), Toast.LENGTH_SHORT).show();
//                    if (response.body() != null){
//                        if (response.body().equals("1"))
//                            Toast.makeText(context, "Registered Successfully", Toast.LENGTH_SHORT).show();
//                        else if (response.body().equals("0"))
//                            Toast.makeText(context, "Mobile number already exist", Toast.LENGTH_SHORT).show();
//                        else if (response.body().equals("2"))
//                            Toast.makeText(context, "EMail ID already exist", Toast.LENGTH_SHORT).show();
//                        else Toast.makeText(context, "failed", Toast.LENGTH_SHORT).show();
//                    }
//
//                    registerCallback.getResponse(response.body());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<String> call, Throwable t) {
//                Log.e("Reg_error", t.getMessage());
//            }
//        });
    }
}
