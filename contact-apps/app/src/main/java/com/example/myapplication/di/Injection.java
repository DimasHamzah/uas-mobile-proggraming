package com.example.myapplication.di;

import android.content.Context;

import com.example.myapplication.network.retrofit.ApiConfig;
import com.example.myapplication.network.retrofit.ApiService;
import com.example.myapplication.repository.ContactRepository;

public class Injection {
  public static ContactRepository contactRepository(Context context) {
    ApiService apiService = ApiConfig.getApiService();
    return ContactRepository.getInstance(apiService);
  }
}
