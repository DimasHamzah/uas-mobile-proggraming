package com.example.myapplication.network.retrofit;

import com.example.myapplication.network.response.ResponseDetail;
import com.example.myapplication.network.response.ResponseListContact;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiService {
  @GET("contact")
  Call<ResponseListContact> getListContact();

  @GET("contact/{id}")
  Call<ResponseDetail> requestGetDetailContact(@Path("id") Integer id);

  @POST("contact")
  Call<ResponseDetail> requestPostContact();

  @PUT("contact/{id}")
  Call<ResponseDetail> requestUpdateContact(@Path("id") Integer id);

  @DELETE("contact/{id}")
  Call<ResponseDetail> requestDeleteContact(@Path("id") Integer id);
}
