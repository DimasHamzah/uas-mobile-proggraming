package com.example.myapplication.viewmodel;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.example.myapplication.di.Injection;
import com.example.myapplication.repository.ContactRepository;

public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {
  private static volatile ViewModelFactory INSTANCE;

  private final ContactRepository contactRepository;

  public ViewModelFactory(ContactRepository contactRepository) {
    this.contactRepository = contactRepository;
  }

  public static ViewModelFactory getInstance(Context context) {
    if(INSTANCE == null) {
      synchronized (ViewModelFactory.class) {
        INSTANCE = new ViewModelFactory(Injection.contactRepository(context));
      }
    }
    return INSTANCE;
  }

  @SuppressWarnings("unchecked")
  @NonNull
  @Override
  public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
    if(modelClass.isAssignableFrom(ContactViewModel.class)) {
      return (T) new ContactViewModel(contactRepository);
    }

    throw new IllegalArgumentException("Unknown ViewModel class : " + modelClass.getName());
  }
}
