package com.flight.app.service;

import java.util.ArrayList;

import com.flight.app.exception.FlightException;
import com.flight.app.models.Airport;
import com.flight.app.models.Flight;
import com.flight.app.models.Ticket;
import com.flight.app.models.User;

public interface IUserService {
	
	boolean registerUser(User user) throws FlightException;
	ArrayList<Flight> searchFlight(Airport airport);
	boolean bookFlight(int seat, Flight flight, String mobileNo);
	boolean cancelFlight(int ticketId);
	ArrayList<Ticket> viewTicket(String mobileNo);

}
