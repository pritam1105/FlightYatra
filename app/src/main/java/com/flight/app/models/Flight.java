package com.flight.app.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="flight_record")
public class Flight implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private int flightId;
	private String flightName;
	private String fightCompany;
	private String deptAirport;
	private String arrAirport;

	private int seats;
	private int fare;

	@OneToMany(mappedBy = "flight", cascade = CascadeType.ALL)
	private List<Ticket> tickets = new ArrayList<Ticket>();

	public Flight() {

	}

	public Flight(int flightId, String flightName, String fightCompany, String deptAirport, String arrAirport,
			int seats, int fare) {
		super();
		this.flightId = flightId;
		this.flightName = flightName;
		this.fightCompany = fightCompany;
		this.deptAirport = deptAirport;
		this.arrAirport = arrAirport;
		this.seats = seats;
		this.fare = fare;
	}

	public int getFlightId() {
		return flightId;
	}

	public void setFlightId(int flightId) {
		this.flightId = flightId;
	}

	public String getFlightName() {
		return flightName;
	}

	public void setFlightName(String flightName) {
		this.flightName = flightName;
	}

	public String getFightCompany() {
		return fightCompany;
	}

	public void setFightCompany(String fightCompany) {
		this.fightCompany = fightCompany;
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

	public int getSeats() {
		return seats;
	}

	public void setSeats(int seats) {
		this.seats = seats;
	}

	public int getFare() {
		return fare;
	}

	public void setFare(int fare) {
		this.fare = fare;
	}
	
	@JsonIgnore
	public List<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

	public void addTicket(Ticket tickets) {
		tickets.setFlight(this);
		this.getTickets().add(tickets);
	}

}
