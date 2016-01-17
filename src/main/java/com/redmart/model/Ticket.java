package com.redmart.model;

import java.util.Set;

import com.redmart.common.constants.TicketStatus;

public class Ticket implements Comparable<Ticket>
{
	private long id;
	private String createdBy;
	private String assignedTo;
	private String status = TicketStatus.ENUM_NEW;
	private String complaintArea;
	private Set<Comments> comments;
	
	public Ticket(){
		id=0;
	}
	
	public Ticket(long id, String createdBy, String assignedTo, String status, String complaintArea, Set<Comments> comments) 
	{
		this.id = id;
		this.createdBy = createdBy;
		this.assignedTo = assignedTo;
		this.status = status;
		this.complaintArea = complaintArea;
		this.comments = comments;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getComplaintArea() {
		return complaintArea;
	}
	public void setComplaintArea(String complaintArea) {
		this.complaintArea = complaintArea;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getAssignedTo() {
		return assignedTo;
	}
	public void setAssignedTo(String assignedTo) {
		this.assignedTo = assignedTo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Set<Comments> getComments() {
		return comments;
	}
	public void setComments(Set<Comments> comments) {
		this.comments = comments;
	}
	public int compareTo(Ticket o) {
		if(this.getId() > o.getId())
			return 1;
		else if(this.getId() < o.getId())
		{
			return -1;
		}
		else 
			return 0;
	}

}
