package com.redmart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

import com.redmart.model.Comments;
import com.redmart.model.Ticket;
import com.redmart.service.TicketService;

@RestController
public class TicketController 
{
	@Autowired
    TicketService ticketService; 
	
	@RequestMapping(value = "/tickets", method = RequestMethod.GET)
	public ResponseEntity<List<Ticket>> listAllUsers() 
	{
        List<Ticket> tickets = ticketService.findAllTickets();
        if(tickets.isEmpty()){
            return new ResponseEntity<List<Ticket>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Ticket>>(tickets, HttpStatus.OK);
    }
	
	@RequestMapping(value = "/ticket/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Ticket> getUser(@PathVariable("id") long id) {
        System.out.println("Fetching User with id " + id);
        Ticket ticket = ticketService.findById(id);
        if (ticket == null) {
            System.out.println("Ticket with id " + id + " not found");
            return new ResponseEntity<Ticket>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Ticket>(ticket, HttpStatus.OK);
    }
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView getIndexPage() 
	{
		ModelAndView modelView = new ModelAndView();
		modelView.setViewName("tickets");
        return modelView;
    }
	
	 @RequestMapping(value = "/ticket/", method = RequestMethod.POST)
	    public ResponseEntity<Void> createUser(@RequestBody Ticket ticket,    UriComponentsBuilder ucBuilder) {
	        System.out.println("Creating Ticket " + ticket.getId());
	 
	        if (ticketService.isTicketExist(ticket)) {
	            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
	        }
	 
	        ticketService.saveTicket(ticket);
	 
	        HttpHeaders headers = new HttpHeaders();
	        headers.setLocation(ucBuilder.path("/ticket/{id}").buildAndExpand(ticket.getId()).toUri());
	        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	    }
	 
	 @RequestMapping(value = "/addComment/{id}", method = RequestMethod.POST)
	    public ResponseEntity<Void> addComment(@RequestBody Comments newComment,@PathVariable("id") int id,UriComponentsBuilder ucBuilder) {
	        System.out.println("Creating comment for  ticket " + id);
	 
	        ticketService.saveComment(newComment,id);
	 
	       // HttpHeaders headers = new HttpHeaders();
	        //headers.setLocation(ucBuilder.path("/addComment/{id}").buildAndExpand(newComment.getId()).toUri());
	        //headers.setLocation();
	        return new ResponseEntity<Void>(HttpStatus.CREATED);
	    }
	 
	     
	    @RequestMapping(value = "/ticket/{id}", method = RequestMethod.PUT)
	    public ResponseEntity<Ticket> updateUser(@PathVariable("id") long id, @RequestBody Ticket ticketFromForm) {
	        System.out.println("Updating User " + id);
	         
	        Ticket ticket = ticketService.findById(id);
	         
	        if (ticket==null) {
	            return new ResponseEntity<Ticket>(HttpStatus.NOT_FOUND);
	        }
	 
	        ticket.setCreatedBy(ticketFromForm.getCreatedBy());
	        ticket.setAssignedTo(ticketFromForm.getAssignedTo());
	        ticket.setComplaintArea(ticketFromForm.getComplaintArea());
	        ticket.setStatus(ticketFromForm.getStatus());
	        ticket.setId(ticketFromForm.getId());
	         
	        ticketService.updateTicket(ticket);
	        return new ResponseEntity<Ticket>(ticket, HttpStatus.OK);
	    }
}
