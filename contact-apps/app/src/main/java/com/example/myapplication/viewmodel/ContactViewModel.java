package com.example.myapplication.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.helper.Event;
import com.example.myapplication.network.response.ResponseDetail;
import com.example.myapplication.network.response.ResponseListContact;
import com.example.myapplication.repository.ContactRepository;

import okhttp3.Response;

public class ContactViewModel extends ViewModel {
  private final ContactRepository contactRepository;

  public ContactViewModel(ContactRepository contactRepository) {
    this.contactRepository = contactRepository;
  }

  public LiveData<ResponseListContact> getListContact() {
    return contactRepository.getAllContact();
  }

  public LiveData<Event<String>> getError() {
    return contactRepository.getError();
  }

  public LiveData<Boolean> getLoading() {
    return contactRepository.getLoading();
  }

  public LiveData<ResponseDetail> getDetailContact(Integer id) {
    return contactRepository.requestGetDetail(id);
  }
  
  public LiveData<Event<String>> addContact(String username,  String alamat, String noTelphone, String email, String tanggalLahir, String jenisKelamin) {
    return contactRepository.requestPostContact(username, alamat, noTelphone, email, tanggalLahir, jenisKelamin);
  }
  
  public LiveData<Event<String>> updateContact(int id, String username, String alamat, String noTelphone, String email, String tanggalLahir, String jenisKelamin) {
    return contactRepository.requestUpdateContact(id, username, alamat, noTelphone, email, tanggalLahir, jenisKelamin);
  }
  
  public LiveData<Event<String>> deleteContact(int id) {
    return contactRepository.requestDeleteContact(id);
  }
}
