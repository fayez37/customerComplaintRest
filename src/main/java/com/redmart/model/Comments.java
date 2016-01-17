package com.redmart.model;

import java.sql.Timestamp;

public class Comments
{
	private long id;
	private String comments;
	private Timestamp commentCreateTime;

	public Comments(){
		id=0;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	
	public Timestamp getCommentCreateTime() {
		return commentCreateTime;
	}
	public void setCommentCreateTime(Timestamp commentCreateTime) {
		this.commentCreateTime = commentCreateTime;
	}
}
