package com.example.myapplication.viewmodel;

import androidx.lifecycle.LiveData;

import com.example.myapplication.network.response.ResponseListContact;
import com.example.myapplication.repository.ContactRepository;

public class ContactViewModel {
  private final ContactRepository contactRepository;


  public ContactViewModel(ContactRepository contactRepository) {
    this.contactRepository = contactRepository;
  }

  public LiveData<ResponseListContact> getListContact() {
    return contactRepository.getAllContact();
  }

  public LiveData<String> getError() {
    return contactRepository.getError();
  }

  public LiveData<Boolean> getLoading() {
    return contactRepository.getLoading();
  }
}
