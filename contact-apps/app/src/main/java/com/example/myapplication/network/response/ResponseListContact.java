package com.example.myapplication.network.response;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseListContact{

	@SerializedName("data")
	private List<DataItem> data;

	@SerializedName("error")
	private boolean error;

	@SerializedName("message")
	private String message;

	public List<DataItem> getData(){
		return data;
	}

	public boolean isError(){
		return error;
	}

	public String getMessage(){
		return message;
	}
}