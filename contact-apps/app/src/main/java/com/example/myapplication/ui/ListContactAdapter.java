package com.example.myapplication.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.network.response.DataItem;

import java.util.ArrayList;
import java.util.List;

public class ListContactAdapter extends RecyclerView.Adapter<ListContactAdapter.ListViewHolder> {
  private List<DataItem> listContact;

  public ListContactAdapter(List<DataItem> list) {
    this.listContact = list;
  }

  @NonNull
  @Override
  public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contact, parent, false);
    return new ListViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
    DataItem dataItem = listContact.get(position);
    holder.tvContact.setText(dataItem.getNotelphone());
    holder.tvUsername.setText(dataItem.getUsername());
    holder.tvEmail.setText(dataItem.getEmail());
    Glide.with(holder.itemView.getContext())
      .load("http://192.168.1.69:3000/"+dataItem.getImage())
      .circleCrop()
      .into(holder.imagePhoto);
  }

  @Override
  public int getItemCount() {
    return listContact.size();
  }

  public class ListViewHolder extends RecyclerView.ViewHolder {
    ImageView imagePhoto;
    TextView tvUsername, tvContact, tvEmail;
    public ListViewHolder(@NonNull View itemView) {
      super(itemView);
      imagePhoto = itemView.findViewById(R.id.img_avatar);
      tvUsername = itemView.findViewById(R.id.tv_item_username);
      tvContact = itemView.findViewById(R.id.tv_item_contact);
      tvEmail = itemView.findViewById(R.id.tv_item_email);
    }
  }
}
