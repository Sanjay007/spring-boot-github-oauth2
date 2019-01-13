package com.example.springsocial.pojos;

public class ApiResponse {

	Object data;

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public ApiResponse(Object data) {
		
		this.data = data;
	}
	
}
