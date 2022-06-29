package com.example.myapplication.ui.main;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentAddContactBinding;
import com.example.myapplication.network.response.Data;
import com.example.myapplication.viewmodel.ContactViewModel;
import com.example.myapplication.viewmodel.ViewModelFactory;

public class AddContactFragment extends Fragment {
  private FragmentAddContactBinding binding;


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_add_contact, container, false);
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    binding = FragmentAddContactBinding.bind(view);
    ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
    actionBar.hide();

    binding.buttonAdd.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        addContact();
      }
    });
  }

  private void addContact() {
    ViewModelFactory factory = ViewModelFactory.getInstance(getActivity());
    ContactViewModel contactViewModel = new ViewModelProvider(this, (ViewModelProvider.Factory) factory).get(ContactViewModel.class);

    contactViewModel.addContact(
      binding.usernameEditText.getText().toString().trim(),
      binding.alamatEditText.getText().toString().trim(),
      binding.teleponeEditText.getText().toString().trim(),
      binding.emailEditText.getText().toString().trim(),
      binding.tanggalLahirEditText.getText().toString().trim(),
      binding.genderEditText.getText().toString().trim()
    ).observe(getViewLifecycleOwner(), response -> {
      String message =  response.getContentIfNotHandled();
      if(message != null) {
        Toast.makeText(requireActivity(), message, Toast.LENGTH_SHORT).show();
      }
    });
    contactViewModel.getError().observe(getViewLifecycleOwner(), error ->{
      String message = error.getContentIfNotHandled();
      Toast.makeText(requireActivity(), message, Toast.LENGTH_SHORT).show();
    });
  }
}