package com.example.myapplication.repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
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

  private final MutableLiveData<String> error = new MutableLiveData<>();
  public LiveData<String> getError() {
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
          error.setValue(response.message());
        }
      }

      @Override
      public void onFailure(Call<ResponseListContact> call, Throwable t) {
        loading.setValue(false);
        error.setValue(t.getMessage());
      }
    });
    return responseContact;
  }

}
