package com.example.myapplication.repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.myapplication.helper.Event;
import com.example.myapplication.network.response.ResponseDetail;
import com.example.myapplication.network.response.ResponseListContact;
import com.example.myapplication.network.retrofit.ApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContactRepository {
  private volatile static ContactRepository INSTANCE = null;
  private final ApiService apiService;

  public ContactRepository(@NonNull ApiService apiService) {
    this.apiService = apiService;
  }

  public static ContactRepository getInstance(ApiService apiService) {
    if(INSTANCE == null) {
      synchronized (ContactRepository.class) {
        INSTANCE = new ContactRepository(apiService);
      }
    }
    return INSTANCE;
  }

  private final MutableLiveData<ResponseListContact> responseContact = new MutableLiveData<>();
  private final MutableLiveData<ResponseDetail> responseDetailContact = new MutableLiveData<>();
  private final MutableLiveData<Event<String>> responsePostContact = new MutableLiveData<>();
  private final MutableLiveData<Event<String>> responseUpdateContact = new MutableLiveData<>();
  private final MutableLiveData<Event<String>> responseDeleteContact = new MutableLiveData<>();

  private final MutableLiveData<Event<String>> error = new MutableLiveData<>();
  public LiveData<Event<String>> getError() {
    return error;
  }

  private final MutableLiveData<Boolean> loading = new MutableLiveData<>();
  public LiveData<Boolean> getLoading() {
    return loading;
  }

  public LiveData<ResponseListContact> getAllContact() {
    Call<ResponseListContact> client = apiService.getListContact();
    client.enqueue(new Callback<ResponseListContact>() {
      @Override
      public void onResponse(Call<ResponseListContact> call, Response<ResponseListContact> response) {
        loading.setValue(false);
        if(response.isSuccessful()) {
          responseContact.setValue(response.body());
        }else{
          error.setValue(new Event<>(response.message()));
        }
      }

      @Override
      public void onFailure(Call<ResponseListContact> call, Throwable t) {
        loading.setValue(false);
        error.setValue(new Event<>(t.getMessage()));
      }
    });
    return responseContact;
  }

  public LiveData<ResponseDetail> requestGetDetail(Integer id) {
    Call<ResponseDetail> client = apiService.requestGetDetailContact(id);
    client.enqueue(new Callback<ResponseDetail>() {
      @Override
      public void onResponse(Call<ResponseDetail> call, Response<ResponseDetail> response) {
        if(response.isSuccessful()) {
          responseDetailContact.setValue(response.body());
        }else{
          error.setValue(new Event<>(response.message()));
        }
      }

      @Override
      public void onFailure(Call<ResponseDetail> call, Throwable t) {
        error.setValue(new Event<>(t.getMessage()));
      }
    });

    return responseDetailContact;
  }

  public LiveData<Event<String>> requestPostContact(String username,  String alamat, String noTelphone, String email, String tanggalLahir, String jenisKelamin) {
    Call<ResponseDetail> client = apiService.requestPostContact(username, alamat, noTelphone, email, tanggalLahir, jenisKelamin);
    client.enqueue(new Callback<ResponseDetail>() {
      @Override
      public void onResponse(Call<ResponseDetail> call, Response<ResponseDetail> response) {
        if(response.isSuccessful()) {
          responsePostContact.setValue(new Event<>(response.body().getMessage()));
        }else{
          error.setValue(new Event<>(response.message()));
        }
      }

      @Override
      public void onFailure(Call<ResponseDetail> call, Throwable t) {
        error.setValue(new Event<>(t.getMessage()));
      }
    });

    return responsePostContact;
  }

  public LiveData<Event<String>> requestUpdateContact(int id, String username, String alamat, String noTelphone, String email, String tanggalLahir, String jenisKelamin) {
    Call<ResponseDetail> client = apiService.requestUpdateContact(id, username, alamat, noTelphone, email, tanggalLahir, jenisKelamin);
    client.enqueue(new Callback<ResponseDetail>() {
      @Override
      public void onResponse(Call<ResponseDetail> call, Response<ResponseDetail> response) {
        if(response.isSuccessful()) {
          responseUpdateContact.setValue(new Event<>(response.body().getMessage()));
        }else  {
          error.setValue(new Event<>(response.message()));
        }
      }

      @Override
      public void onFailure(Call<ResponseDetail> call, Throwable t) {
        error.setValue(new Event<>(t.getMessage()));
      }
    });

    return responseUpdateContact;
  }

  public LiveData<Event<String>> requestDeleteContact(int id) {
    Call<ResponseDetail> client = apiService.requestDeleteContact(id);
    client.enqueue(new Callback<ResponseDetail>() {
      @Override
      public void onResponse(Call<ResponseDetail> call, Response<ResponseDetail> response) {
        if(response.isSuccessful()) {
          responseDeleteContact.setValue(new Event<>(response.body().getMessage()));
        }else{
          error.setValue(new Event<>(response.message()));
        }
      }

      @Override
      public void onFailure(Call<ResponseDetail> call, Throwable t) {
        error.setValue(new Event<>(t.getMessage()));
      }
    });
    return responseDeleteContact;
  }
}
