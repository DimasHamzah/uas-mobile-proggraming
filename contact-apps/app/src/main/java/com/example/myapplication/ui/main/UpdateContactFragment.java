package com.example.myapplication.ui.main;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentUpdateContactBinding;
import com.example.myapplication.network.response.ResponseDetail;
import com.example.myapplication.viewmodel.ContactViewModel;
import com.example.myapplication.viewmodel.ViewModelFactory;

public class UpdateContactFragment extends Fragment {
  private FragmentUpdateContactBinding binding;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_update_contact, container, false);
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    binding = FragmentUpdateContactBinding.bind(view);

    int id = getArguments().getInt("id");

    ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
    actionBar.hide();

    ViewModelFactory viewModelFactory = ViewModelFactory.getInstance(requireActivity());
    ContactViewModel contactViewModel = new ViewModelProvider(this, (ViewModelProvider.Factory) viewModelFactory).get(ContactViewModel.class);

    contactViewModel.getDetailContact(id).observe(getViewLifecycleOwner(), this::setField);

    binding.buttonBackHome.setOnClickListener(
      Navigation.createNavigateOnClickListener(R.id.action_updateContactFragment_to_listContactFragment)
    );

    binding.buttonUpdate.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        updateContact(id);
      }
    });
  }

  private void setField(ResponseDetail responseDetail) {
    binding.usernameEditText.setText(responseDetail.getData().getUsername());
    binding.alamatEditText.setText(responseDetail.getData().getAlamat());
    binding.teleponeEditText.setText(responseDetail.getData().getNotelphone());
    binding.emailEditText.setText(responseDetail.getData().getEmail());
    binding.genderEditText.setText(responseDetail.getData().getJenisKelasmin());
    binding.tanggalLahirEditText.setText(responseDetail.getData().getTanggalLahir());
  }

  private void updateContact(int id) {
    ViewModelFactory viewModelFactory = ViewModelFactory.getInstance(requireActivity());
    ContactViewModel contactViewModel = new ViewModelProvider(this, (ViewModelProvider.Factory) viewModelFactory).get(ContactViewModel.class);

    contactViewModel.updateContact(
      id,
      binding.usernameEditText.getText().toString().trim(),
      binding.alamatEditText.getText().toString().trim(),
      binding.teleponeEditText.getText().toString().trim(),
      binding.emailEditText.getText().toString().trim(),
      binding.tanggalLahirEditText.getText().toString().trim(),
      binding.genderEditText.getText().toString().trim()
      ).observe(getViewLifecycleOwner(), response -> {
      String message = response.getContentIfNotHandled();
      if(message != null) {
        Toast.makeText(requireActivity(), message, Toast.LENGTH_SHORT).show();
      }
    });

    contactViewModel.getError().observe(getViewLifecycleOwner(), message -> {
      String error = message.getContentIfNotHandled();
      if(error != null) {
        Toast.makeText(requireActivity(), error, Toast.LENGTH_SHORT).show();
      }
    });
  }
}