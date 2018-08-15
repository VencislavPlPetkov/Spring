package com.ve.codeinterceptor.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class LogMessage {

	private Long id;
	
	private Date createdOn;
	
	private String message;
	
	
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	public Long getId() {
		return id;
	}

	
	
	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	
	
	@Column(columnDefinition = "TEXT")
	public String getMessage() {
		return message;
	}
	
	

	public void setMessage(String message) {
		this.message = message;
	}

	
	
	
	
	
	
	
	
	
}
















