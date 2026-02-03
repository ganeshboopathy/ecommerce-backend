package com.Hero_Service.Hero_Service.apiresponse;

import java.time.LocalDateTime;

public class ApiResponse<T> {

	int success;
	String message;
	T data;
	LocalDateTime timestamp;
	
	public int getSuccess() {
		return success;
	}
	public void setSuccess(int success) {
		this.success = success;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
	public ApiResponse(int success, String message, T data) {
		super();
		this.success = success;
		this.message = message;
		this.data = data;
		this.timestamp = LocalDateTime.now();
	}
	public ApiResponse() {
		super();
	}
	
}
