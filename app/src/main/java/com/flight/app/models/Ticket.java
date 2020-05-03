package com.flight.app.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="ticket_record")
public class Ticket {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int ticketId;
	
	@ManyToOne
	@JoinColumn(name="mobileNo")
	User user;
	
	@ManyToOne
	@JoinColumn(name="flightID")
	Flight flight;
	
	private String status;

	public Ticket() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public Ticket(User user, Flight flight) {
		super();
		this.user = user;
		this.flight = flight;
		this.status="booked";
	}

	public int getTicketId() {
		return ticketId;
	}

	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	

}
