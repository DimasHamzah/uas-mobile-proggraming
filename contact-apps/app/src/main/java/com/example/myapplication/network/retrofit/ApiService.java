package com.example.myapplication.network.retrofit;

import com.example.myapplication.network.response.ResponseDetail;
import com.example.myapplication.network.response.ResponseListContact;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface ApiService {
  @GET("contact")
  Call<ResponseListContact> getListContact();

  @GET("contact/{id}")
  Call<ResponseDetail> requestGetDetailContact(@Path("id") Integer id);

  @Multipart
  @POST("contact")
  Call<ResponseDetail> requestPostContact(
    @Part("file") MultipartBody.Part file,
    @Part("username")RequestBody username,
    @Part("alamat") RequestBody alamat,
    @Part("notelphone") RequestBody notelphone,
    @Part("email") RequestBody email,
    @Part("tanggalLahir") RequestBody tanggalLahir,
    @Part("jenisKelasmin") RequestBody jenisKelamin
    );

  @Multipart
  @PUT("contact/{id}")
  Call<ResponseDetail> requestUpdateContact(
    @Path("id") Integer id,
    @Part("file") MultipartBody.Part file,
    @Part("username")RequestBody username,
    @Part("alamat") RequestBody alamat,
    @Part("notelphone") RequestBody notelphone,
    @Part("email") RequestBody email,
    @Part("tanggalLahir") RequestBody tanggalLahir,
    @Part("jenisKelasmin") RequestBody jenisKelamin
  );

  @DELETE("contact/{id}")
  Call<ResponseDetail> requestDeleteContact(@Path("id") Integer id);
}
