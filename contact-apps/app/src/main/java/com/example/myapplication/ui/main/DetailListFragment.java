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

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentDetailListBinding;
import com.example.myapplication.helper.DateFormat;
import com.example.myapplication.network.response.ResponseDetail;
import com.example.myapplication.viewmodel.ContactViewModel;
import com.example.myapplication.viewmodel.ViewModelFactory;

import java.util.TimeZone;

public class DetailListFragment extends Fragment {

  private FragmentDetailListBinding binding;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_detail_list, container, false);
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    binding = FragmentDetailListBinding.bind(view);

    ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
    actionBar.hide();

    Integer id = getArguments().getInt("id");

    Bundle bundle = new Bundle();
    bundle.putInt("id", id);

    ViewModelFactory viewModelFactory = ViewModelFactory.getInstance(requireActivity());
    ContactViewModel contactViewModel = new ViewModelProvider(this, (ViewModelProvider.Factory) viewModelFactory).get(ContactViewModel.class);

    contactViewModel.getDetailContact(id).observe(getViewLifecycleOwner(), this::setDetailContact);

    binding.buttonUpdate.setOnClickListener(
      Navigation.createNavigateOnClickListener(R.id.action_detailListFragment_to_updateContactFragment, bundle)
    );

    binding.buttonDelete.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        deleteContact(id);
      }
    });
  }

  private void setDetailContact(ResponseDetail responseDetail) {
    binding.username.setText(responseDetail.getData().getUsername());
    binding.email.setText(responseDetail.getData().getEmail());
    binding.whatshap.setText(responseDetail.getData().getNotelphone());
    binding.alamat.setText(responseDetail.getData().getAlamat());
    binding.date.setText(responseDetail.getData().getTanggalLahir());
    binding.gender.setText(responseDetail.getData().getJenisKelasmin());
    if(responseDetail.getData().getJenisKelasmin().equalsIgnoreCase("pria")) {
      Glide.with(requireActivity())
        .load(R.drawable.icon_man)
        .circleCrop()
        .into(binding.buttonProfile);
    }else{
      Glide.with(requireActivity())
        .load(R.drawable.icon_woman)
        .circleCrop()
        .into(binding.buttonProfile);
    }
  }

  private void deleteContact(int id) {
    ViewModelFactory factory = ViewModelFactory.getInstance(getActivity());
    ContactViewModel contactViewModel = new ViewModelProvider(this, (ViewModelProvider.Factory) factory).get(ContactViewModel.class);

    contactViewModel.deleteContact(id).observe(getViewLifecycleOwner(), response ->{
      String message = response.getContentIfNotHandled();
      if(message != null) {
        Toast.makeText(requireActivity(), message, Toast.LENGTH_SHORT).show();
      }
    });

    contactViewModel.getError().observe(getViewLifecycleOwner(), message -> {
      String error = message.getContentIfNotHandled();
      Toast.makeText(requireActivity(), error , Toast.LENGTH_SHORT).show();
    });
  }
}