package com.example.Shoot.ServiceClass;

import com.example.Shoot.ApiInterfaces.ApiServiceInterface;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by Ajmal Bin Khalid on 24-01-2018.
 */

public class ServiceGenerator {

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    private static OkHttpClient.Builder setClient() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder client = new OkHttpClient.Builder()
                .retryOnConnectionFailure(true);
        client.addInterceptor(httpLoggingInterceptor)
                .build();


        return client;
    }

    public static Retrofit.Builder builder = new Retrofit.Builder()
            .client(setClient()
                    .build())
            .baseUrl(ApiServiceInterface.API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create());

    public static Retrofit.Builder builder2 = new Retrofit.Builder()
            .client(setClient()
                    .build())
            .baseUrl(ApiServiceInterface.API_BASE_URL)
            .addConverterFactory(ScalarsConverterFactory.create());
}