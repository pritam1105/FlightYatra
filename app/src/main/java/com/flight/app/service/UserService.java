package com.flight.app.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flight.app.dao.UserDao;
import com.flight.app.exception.FlightException;
import com.flight.app.models.Airport;
import com.flight.app.models.Credentials;
import com.flight.app.models.Flight;
import com.flight.app.models.Ticket;
import com.flight.app.models.User;

@Service
@Transactional
public class UserService implements IUserService {

	
	@Autowired
	UserDao dao;
	
	
	@Override
	public boolean registerUser(User user) throws FlightException {
		
		return dao.registerUser(user);
	}
	
	public boolean validateCredentials(Credentials credential) throws FlightException {
		if (dao.validateCredentials(credential)) {
			return true;
		}
		return false;
	}
	
	public User getUserById(String mobileNo)
	{
		return dao.getUserById(mobileNo);
	}

	@Override
	public ArrayList<Flight> searchFlight(Airport airport) {
		
		return dao.searchFlight(airport);
	}

	@Override
	public boolean bookFlight(int seat, Flight flight, String mobileNo) {
		return dao.bookFlight(seat, flight, mobileNo);
	}

	@Override
	public boolean cancelFlight(int ticketId) {
		
		return dao.cancelFlight(ticketId);
	}

	@Override
	public ArrayList<Ticket> viewTicket(String mobileNo) {
		
		return dao.viewTicket(mobileNo);
	}

}
