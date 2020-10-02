package com.example.Shoot.Controller;

import android.content.Context;

import com.example.Shoot.ServiceInterfaces.LoginCall;
import com.example.Shoot.ServiceInterfaces.LoginCallback;

public class LoginController  implements LoginCall {
    private Context context;
    private LoginCallback loginCallback;

    public LoginController(Context applicationContext, LoginCallback loginCallback) {
        this.context = applicationContext;
        this.loginCallback = loginCallback;
    }

    @Override
    public void getLoginData(String username, String password) {

    }

//    public void getLoginData(final String username, final String password) {
//        Retrofit retrofit= RetrofitClass.getClient();
//        ApiServiceInterface serviceInterfaceRegister = retrofit.create(ApiServiceInterface.class);
//
//        ArrayList<Login> params = new ArrayList<>();
//        params.add(new Login(username,password,"uHtbabjrxcKQTeuwjQ=="));
//        serviceInterfaceRegister.postLogin(params).enqueue(new Callback<LoginResponse>() {
//            @Override
//            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
//                Log.w("Login_Resp", response.body() + "");
//                if (username.equals(username) && password.equals(password)){
//                    String userId = response.body().getUid();
//                    Toast.makeText(context, ""+userId, Toast.LENGTH_SHORT).show();
//                    loginCallback.getLoginResponse(userId);
//
//
//                }else Toast.makeText(context, "Please check username & password!", Toast.LENGTH_SHORT).show();
//
//            }
//
//            @Override
//            public void onFailure(Call<LoginResponse> call, Throwable t) {
//                Log.e("Login_Error", t.getMessage() + "");
//            }
//        });
//    }
}
