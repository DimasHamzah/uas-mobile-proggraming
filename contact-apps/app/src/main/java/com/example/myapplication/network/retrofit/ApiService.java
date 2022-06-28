package com.example.myapplication.network.retrofit;

import com.example.myapplication.network.response.ResponseDetail;
import com.example.myapplication.network.response.ResponseListContact;

import okhttp3.MultipartBody;
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
    @Part("image") MultipartBody.Part image,
    @Field("username") String username,
    @Field("alamat") String alamat,
    @Field("notelphone") String notelphone,
    @Field("email") String email,
    @Field("tanggalLahir") String tanggalLahir,
    @Field("jenisKelasmin") String jenisKelamin
    );

  @Multipart
  @PUT("contact/{id}")
  Call<ResponseDetail> requestUpdateContact(
    @Path("id") Integer id,
    @Part("image") MultipartBody.Part image,
    @Field("username") String username,
    @Field("alamat") String alamat,
    @Field("notelphone") String notelphone,
    @Field("email") String email,
    @Field("tanggalLahir") String tanggalLahir,
    @Field("jenisKelasmin") String jenisKelamin );

  @DELETE("contact/{id}")
  Call<ResponseDetail> requestDeleteContact(@Path("id") Integer id);
}
