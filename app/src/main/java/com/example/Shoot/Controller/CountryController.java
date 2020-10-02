//package com.example.taxaal.Controller;
//
//import android.content.Context;
//import android.util.Log;
//import android.widget.Toast;
//
//import com.example.taxaal.ApiInterfaces.ApiServiceInterface;
//import com.example.taxaal.Pojo.Country;
//import com.example.taxaal.Pojo.CountryResponse;
//import com.example.taxaal.ServiceClass.RetrofitClass;
//import com.example.taxaal.ServiceInterfaces.CountryCall;
//import com.example.taxaal.ServiceInterfaces.CountryCallback;
//
//import java.util.ArrayList;
//
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//import retrofit2.Retrofit;
//
//public class CountryController implements CountryCall {
//    private Context context;
//    private CountryCallback countryCallback;
//
//    public CountryController(Context context, CountryCallback countryCallback) {
//        this.context = context;
//        this.countryCallback = countryCallback;
//    }
//
//
//
//
//
//    @Override
//    public void getCountryData(String skey) {
//        Retrofit retrofit= RetrofitClass.getClient();
//        ApiServiceInterface apiServiceInterfaceRegister=retrofit.create(ApiServiceInterface.class);
//
//        ArrayList<Country> params=new ArrayList<>();
//        params.add(new Country("uHtbabjrxcKQTeuwjQ=="));
//        apiServiceInterfaceRegister.postCountry(params).enqueue(new Callback<CountryResponse>() {
//            @Override
//            public void onResponse(Call<CountryResponse> call, Response<CountryResponse> response) {
//                if (response.isSuccessful()){
//                    Log.d("CountryData:", String.valueOf(response.body()));
//
//                    CountryResponse countryResponse=response.body();
//                    String id=countryResponse.getId().toString();
//
//                    countryCallback.countryResponse("id","countryName","countryShortName","countryCurrency","countryDialect","countryMobLength");
//
//                }else {
//                    Toast.makeText(context, "failed", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<CountryResponse> call, Throwable t) {
//                Log.e("CountryResponse-error", t.getMessage());
//            }
//        });
//
//
//
//
//    }
//}
