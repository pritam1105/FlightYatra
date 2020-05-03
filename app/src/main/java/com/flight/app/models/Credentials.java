package com.flight.app.models;

public class Credentials {
	private String mobileNo;
	private String password;
	
	public Credentials(String mobileNo, String password) {
		super();
		this.mobileNo = mobileNo;
		this.password = password;
	}
	public Credentials() {
		
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	

}
