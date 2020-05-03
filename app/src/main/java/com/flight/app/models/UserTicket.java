package com.flight.app.models;

public class UserTicket {
	
	private int seat;
	private Flight flight;
	private String mobileNo;
	public UserTicket(int seat, Flight flight, String mobileNo) {
		super();
		this.seat = seat;
		this.flight = flight;
		this.mobileNo = mobileNo;
	}
	public int getSeat() {
		return seat;
	}
	public void setSeat(int seat) {
		this.seat = seat;
	}
	public Flight getFlight() {
		return flight;
	}
	public void setFlight(Flight flight) {
		this.flight = flight;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	};
	

}
