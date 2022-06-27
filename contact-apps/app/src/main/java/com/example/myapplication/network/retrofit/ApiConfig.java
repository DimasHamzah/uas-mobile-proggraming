package com.example.myapplication.network.retrofit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiConfig {
  public static ApiService getApiService() {
    HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor()
      .setLevel(HttpLoggingInterceptor.Level.BODY);
    OkHttpClient client = new OkHttpClient.Builder()
      .addInterceptor(loggingInterceptor)
      .build();
    Retrofit retrofit = new retrofit2.Retrofit.Builder()
      .baseUrl("192.168.1.69:3000/api/v1/")
      .addConverterFactory(GsonConverterFactory.create())
      .client(client)
      .build();
    return retrofit.create(ApiService.class);
  }
}
