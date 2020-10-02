package com.example.Shoot.Controller;

import android.content.Context;

import com.example.Shoot.ServiceInterfaces.GetProfileCall;
import com.example.Shoot.ServiceInterfaces.GetProfileCallback;

public class GetProfileController implements GetProfileCall {
  private Context context;
  private GetProfileCallback getProfileCallback;

    public GetProfileController(Context context, GetProfileCallback getProfileCallback) {
        this.context = context;
        this.getProfileCallback = getProfileCallback;
    }

    @Override
    public void getProfileData(String user_id) {

    }

//    @Override
//    public void getProfileData(final String user_id) {
//        Retrofit retrofit= RetrofitClass.getClient();
//        ApiServiceInterface apiServiceInterface=retrofit.create(ApiServiceInterface.class);
//
//
//        ArrayList<GetProfile> params=new ArrayList<>();
//        params.add(new GetProfile("user_id","uHtbabjrxcKQTeuwjQ=="));
//        apiServiceInterface.postProfile(",params).enqueue(new Callback<GetProfileResponse>() {
//            @Override
//            public void onResponse(Call<GetProfileResponse> call, Response<GetProfileResponse> response) {
//                if (response.isSuccessful()){
//                    GetProfileResponse getProfileResponse=response.body();
//
//                  String uName=getProfileResponse.getUName();
//                  String uEmail=getProfileResponse.getUEmail();
//                  String uMobile=getProfileResponse.getUMobile();
//                  String uCountry=getProfileResponse.getUCountry();
//
//
//                    getProfileCallback.getProfileResponse("uName","uEmail","uMobile","uCountry");
//                }else {
//                    Toast.makeText(context,"Failed",Toast.LENGTH_LONG).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<GetProfileResponse> call, Throwable t) {
//                Log.e("Error...", t.getMessage() + "");
//            }
//        });


    }

