package com.redmart.service;

import java.util.List;

import com.redmart.model.Comments;
import com.redmart.model.Ticket;

public interface TicketService {
	
	Ticket findById(long id);
	
	void saveTicket(Ticket ticket);
	
	void updateTicket(Ticket ticket);
	
	List<Ticket> findAllTickets(); 
	
	public boolean isTicketExist(Ticket ticket);

	void saveComment(Comments newComment,int ticketId);
	

}
