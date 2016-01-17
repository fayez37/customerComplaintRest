package com.redmart.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.redmart.common.constants.ComplaintAreas;
import com.redmart.common.constants.TicketStatus;
import com.redmart.model.Comments;
import com.redmart.model.Ticket;

@Service("ticketService")
@Transactional
public class TicketServiceImpl implements TicketService
{
	private static final AtomicLong counter = new AtomicLong();
	private static final AtomicLong commentCounter = new AtomicLong();
	
	private static List<Ticket> tickets;
	
	static{
		tickets= populateDummyTickets();
	}

	public List<Ticket> findAllTickets() {
		return tickets;
	}
	
	public Ticket findById(long id) {
		for(Ticket ticket : tickets){
			if(ticket.getId() == id){
				return ticket;
			}
		}
		return null;
	}
	
	public void saveTicket(Ticket ticket) {
		ticket.setId(counter.incrementAndGet());
		tickets.add(ticket);
	}

	public void saveComment(Comments newComment,int ticketId)
	{
		Ticket ticket = findById(ticketId);
		Set<Comments> commentSet = ticket.getComments();
		newComment.setId(commentCounter.incrementAndGet());
		newComment.setCommentCreateTime(new Timestamp(new Date().getTime()));
		commentSet.add(newComment);
	}
	
	public void updateTicket(Ticket ticket) {
		int index = tickets.indexOf(ticket);
		tickets.set(index, ticket);
	}

	public void deleteTicketById(long id) {
		
		for (Iterator<Ticket> iterator = tickets.iterator(); iterator.hasNext(); ) {
		    Ticket ticket = iterator.next();
		    if (ticket.getId() == id) {
		        iterator.remove();
		    }
		}
	}

	public boolean isTicketExist(Ticket ticket) {
		return findById(ticket.getId())!=null;
	}
	
	private static List<Ticket> populateDummyTickets(){
		List<Ticket> tickets = new ArrayList<Ticket>();
		Set<Comments> comments = new HashSet<Comments>();
		Comments lComment = new Comments();
		lComment.setCommentCreateTime(new Timestamp(new Date().getTime()));
		lComment.setId(commentCounter.incrementAndGet());
		lComment.setComments("Hello");
		comments.add(lComment);
		
		Set<Comments> comments2 = new HashSet<Comments>();
		Comments lComment2 = new Comments();
		lComment2.setCommentCreateTime(new Timestamp(new Date().getTime()));
		lComment2.setId(commentCounter.incrementAndGet());
		lComment2.setComments("Exceee");
		comments2.add(lComment2);
		
		Set<Comments> comments3 = new HashSet<Comments>();
		
		tickets.add(new Ticket(counter.incrementAndGet(),"Guru", "Guru", TicketStatus.ENUM_NEW, ComplaintAreas.ENUM_ACCOUNT,comments));
		tickets.add(new Ticket(counter.incrementAndGet(),"Guru", "Geo", TicketStatus.ENUM_OPEN, ComplaintAreas.ENUM_DELIVERY,comments2));
		tickets.add(new Ticket(counter.incrementAndGet(),"Guru", "Guru", TicketStatus.ENUM_CLOSED, ComplaintAreas.ENUM_PAYMENT,comments3));
		return tickets;
	}


}
