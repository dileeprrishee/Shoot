package com.example.Shoot.ServiceClass;

import android.app.Application;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClass extends Application {
    public static final String BASE_URL = "https://hayrenstudio.com/";

    static OkHttpClient.Builder httpclient=null;
    private static Retrofit retrofit=null;

    @Override
    public void onCreate() {
        super.onCreate();
    }
    private static OkHttpClient buildClient() {
        return new OkHttpClient.Builder().build();
    }

    private static OkHttpClient.Builder setClient() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder client = new OkHttpClient.Builder()
                .retryOnConnectionFailure(true);
        client.addInterceptor(httpLoggingInterceptor)
                .build();
        return client;
    }

    public static Retrofit getClient(){
       if (retrofit==null){
           retrofit=new Retrofit.Builder()
                   .client(setClient().build())
                   .addConverterFactory(GsonConverterFactory.create())
                   .baseUrl(BASE_URL)
                   .build();


       }
       return retrofit;
    }

}
