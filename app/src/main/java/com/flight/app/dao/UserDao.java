package com.flight.app.dao;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.flight.app.exception.FlightException;
import com.flight.app.models.Airport;
import com.flight.app.models.Credentials;
import com.flight.app.models.Flight;
import com.flight.app.models.Ticket;
import com.flight.app.models.User;

@Repository
public class UserDao implements IUserDao {

	@Autowired
	private EntityManager entityManager;
	
	@Override
	public boolean registerUser(User user) throws FlightException {
		try {
		if(entityManager.find(User.class,user.getMobileNo())!=null)
		{
			return false;
		}
		entityManager.persist(user);
		return true;
		}
		catch(Exception e) {
			throw new FlightException("User already registered");
		}
	}
	public boolean validateCredentials(Credentials credential) throws FlightException{
		try {
		User u=entityManager.find(User.class, credential.getMobileNo());
		if(u.getMobileNo().equals(credential.getMobileNo())&&u.getPassword().equals(credential.getPassword()))
			return true;
		else
			return false;
		}catch(Exception e) {
			throw new FlightException("Invalid Username or Password");
		}
	}
	
	public User getUserById(String mobileNo) {
		User u=entityManager.find(User.class, mobileNo);
		return u;
	}
	

	@Override
	public ArrayList<Flight> searchFlight(Airport airport) {
		ArrayList<Flight> al= new ArrayList<>();
		String qStr="Select flight from Flight flight where deptAirport=:pdept and arrAirport=:parr";
		TypedQuery<Flight> query=entityManager.createQuery(qStr,Flight.class);
		query.setParameter("pdept",airport.getDeptAirport());
		query.setParameter("parr", airport.getArrAirport());
		System.out.println(query.getResultList().size());
		al=(ArrayList<Flight>) query.getResultList();
		
		return al;
	}

	@Override
	public boolean bookFlight(int seat, Flight flight, String mobileNo) {
	
		User u=entityManager.find(User.class, mobileNo);
		flight.setSeats(flight.getSeats()-seat);
		Ticket ticket = new Ticket(u,flight);
		u.addTicket(ticket);
		flight.addTicket(ticket);
		entityManager.persist(ticket);
		return true;
		
	}

	@Override
	public boolean cancelFlight(int ticketId) {
		String qStr="Select ticket from Ticket ticket where ticketId = :tno and status='booked'";
		TypedQuery<Ticket> query=entityManager.createQuery(qStr,Ticket.class);
		query.setParameter("tno", ticketId);
		Ticket t=query.getSingleResult();
		
		if(t==null) {
			return false;
		}
		else {
			Flight flight=t.getFlight();
			flight.setSeats(flight.getSeats()+1);
			t.setStatus("cancelled");
			entityManager.merge(flight);
			entityManager.merge(t);
			return true;
		}
	}

	@Override
	public ArrayList<Ticket> viewTicket(String mobileNo) {
		ArrayList<Ticket> al=new ArrayList<>();
		String qStr="Select ticket from Ticket ticket where mobile_No = :mobileNo ";
		TypedQuery<Ticket> query=entityManager.createQuery(qStr,Ticket.class);
		query.setParameter("mobileNo", mobileNo);
		al=(ArrayList<Ticket>) query.getResultList();
		return al;
		
	}

}
