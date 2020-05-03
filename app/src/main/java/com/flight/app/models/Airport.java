package com.flight.app.models;

public class Airport {
	private String deptAirport;
	private String arrAirport;
	public Airport(String deptAirport, String arrAirport) {
		super();
		this.deptAirport = deptAirport;
		this.arrAirport = arrAirport;
	}
	public String getDeptAirport() {
		return deptAirport;
	}
	public void setDeptAirport(String deptAirport) {
		this.deptAirport = deptAirport;
	}
	public String getArrAirport() {
		return arrAirport;
	}
	public void setArrAirport(String arrAirport) {
		this.arrAirport = arrAirport;
	}
	
	

}
