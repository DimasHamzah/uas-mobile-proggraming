package com.example.myapplication.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.network.response.ResponseDetail;
import com.example.myapplication.network.response.ResponseListContact;
import com.example.myapplication.repository.ContactRepository;

public class ContactViewModel extends ViewModel {
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

  public LiveData<ResponseDetail> getDetailContact(Integer id) {
    return contactRepository.requestGetDetail(id);
  }
}
