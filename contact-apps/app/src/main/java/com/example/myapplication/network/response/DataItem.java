package com.example.myapplication.network.response;

import com.google.gson.annotations.SerializedName;

public class DataItem{

	@SerializedName("createdAt")
	private String createdAt;

	@SerializedName("jenisKelasmin")
	private String jenisKelasmin;

	@SerializedName("id")
	private int id;

	@SerializedName("notelphone")
	private String notelphone;

	@SerializedName("tanggalLahir")
	private String tanggalLahir;

	@SerializedName("email")
	private String email;

	@SerializedName("username")
	private String username;

	@SerializedName("alamat")
	private String alamat;

	@SerializedName("updatedAt")
	private String updatedAt;

	public String getCreatedAt(){
		return createdAt;
	}

	public String getJenisKelasmin(){
		return jenisKelasmin;
	}

	public int getId(){
		return id;
	}

	public String getNotelphone(){
		return notelphone;
	}

	public String getTanggalLahir(){
		return tanggalLahir;
	}

	public String getEmail(){
		return email;
	}

	public String getUsername(){
		return username;
	}

	public String getAlamat(){
		return alamat;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}
}