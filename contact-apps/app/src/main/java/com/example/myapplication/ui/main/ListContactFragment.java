package com.example.myapplication.ui.main;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;
import com.example.myapplication.network.response.DataItem;
import com.example.myapplication.network.response.ResponseListContact;
import com.example.myapplication.ui.ListContactAdapter;
import com.example.myapplication.viewmodel.ContactViewModel;
import com.example.myapplication.viewmodel.ViewModelFactory;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ListContactFragment extends Fragment {
  private RecyclerView rvContact;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_list_contact, container, false);
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
    actionBar.hide();

    ViewModelFactory factory = ViewModelFactory.getInstance(getActivity());
    ContactViewModel contactViewModel = new ViewModelProvider(this, (ViewModelProvider.Factory) factory).get(ContactViewModel.class);

    contactViewModel.getListContact().observe(getViewLifecycleOwner(), response -> {
     setListAdapter(view, response.getData());
    });

    FloatingActionButton btnAddContact = (FloatingActionButton) view.findViewById(R.id.addContact);
    btnAddContact.setOnClickListener(
      Navigation.createNavigateOnClickListener(R.id.action_listContactFragment_to_addContactFragment)
    );

  }

  private void setListAdapter(View view, List<DataItem> dataItems) {
    rvContact = view.findViewById(R.id.rv_list_contact);
    rvContact.setLayoutManager(new LinearLayoutManager(getActivity()));
    rvContact.setHasFixedSize(true);
    ListContactAdapter listContactAdapter = new ListContactAdapter(dataItems);
    rvContact.setAdapter(listContactAdapter);
  }
}