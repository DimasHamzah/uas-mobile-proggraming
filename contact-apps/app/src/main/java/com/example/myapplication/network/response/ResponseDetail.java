package com.example.myapplication.network.response;

public class ResponseDetail{
	private Data data;
	private boolean error;
	private String message;

	public Data getData(){
		return data;
	}

	public boolean isError(){
		return error;
	}

	public String getMessage(){
		return message;
	}
}
