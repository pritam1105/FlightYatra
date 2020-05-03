package com.flight.app.controller;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.flight.app.exception.FlightException;
import com.flight.app.models.Airport;
import com.flight.app.models.Credentials;
import com.flight.app.models.Flight;
import com.flight.app.models.Ticket;
import com.flight.app.models.User;
import com.flight.app.models.UserTicket;
import com.flight.app.service.UserService;

@RestController
@CrossOrigin(origins="http://localhost:4200")
public class AppController {
	
	private Logger logger = Logger.getRootLogger();

	
	@Autowired
	UserService service;
	User user;
	
	
	@PostMapping("/login")
	public ResponseEntity<User> authenticateUser(@RequestBody Credentials credential) throws FlightException
	{
		boolean flag=service.validateCredentials(credential);
		if(flag) {
			logger.info("correct credentials entered");
			return new ResponseEntity<User>(user,HttpStatus.OK);
		}
		else {
			logger.info("Either mobile number or password is incorrect");
			return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/register")
	public ResponseEntity<User> registerUser(@RequestBody User user) throws FlightException
	{
		
		boolean result=service.registerUser(user);
		if(result==false) {
			logger.info("User is already registred");
			return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
		}
		else {
			logger.info("New user account is created successfully");
			return new ResponseEntity<User>(user,HttpStatus.OK);
		}
	}
	
	
	@PostMapping("/search")
	public ResponseEntity<ArrayList<Flight>> serachFlight(@RequestBody Airport airport)
	{
		ArrayList<Flight> f=service.searchFlight(airport);
		if(!f.isEmpty()) {
			logger.info("All the flight to corresponding routes");
			return new ResponseEntity<ArrayList<Flight>>(f, HttpStatus.OK);
		}
		else {
			logger.info("no flight found");
			return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
		}		
	}
	
	@PostMapping("/getUserById")
	public ResponseEntity<User> getUserById(@RequestBody String mobileNo)
	{
		user=service.getUserById(mobileNo);
		logger.info("User with given id found");
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}
	
	@PostMapping("/bookFlight")
	public ResponseEntity<User> bookFlight(@RequestBody UserTicket userTicket){
		boolean flag=service.bookFlight(userTicket.getSeat(), userTicket.getFlight(), userTicket.getMobileNo());
		if(flag) {
			logger.info("Flight booked succesfully");
			return new ResponseEntity<User>(user,HttpStatus.OK);
			
		}else {
			logger.info("Flight not booked");
			return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/viewTicket")
	public ResponseEntity<ArrayList<Ticket>> viewTicket(@RequestBody String mobileNo){
		ArrayList<Ticket> ticket=service.viewTicket(mobileNo);
		if(ticket.isEmpty()) {
			logger.info("No booking till now");
			return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
		}else {
			logger.info("Ticket List of user");
			return new ResponseEntity<ArrayList<Ticket>>(ticket,HttpStatus.OK);
		}
	}
	
	@PostMapping("/cancelTicket")
	public ResponseEntity<Ticket> cancelTicket(@RequestBody int ticketId){
		Ticket ticket = new Ticket();
		boolean flag=service.cancelFlight(ticketId);
		if(flag) {
			logger.info("Cancelled Ticket");
			return new ResponseEntity<Ticket>(ticket,HttpStatus.OK);
			
		}else {
			logger.info("No ticket to cancel");
			return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
		}
	}

}
