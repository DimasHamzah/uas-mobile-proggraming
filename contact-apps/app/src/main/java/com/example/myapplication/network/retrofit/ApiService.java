package com.example.myapplication.network.retrofit;

import com.example.myapplication.network.response.ResponseListContact;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
  @GET("contact")
  Call<ResponseListContact> getListContact();

}
