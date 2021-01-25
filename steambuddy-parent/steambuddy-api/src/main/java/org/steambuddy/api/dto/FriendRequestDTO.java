package org.steambuddy.api.dto;

import java.sql.Timestamp;

public class FriendRequestDTO {

	private Long id;
	
	private Timestamp timestamp;

	private Boolean accepted;

	private UserDTO sender;
	
	private UserDTO receiver;

	public FriendRequestDTO(Long id, Timestamp timestamp, Boolean accepted, UserDTO from, UserDTO to) {
		this.id = id;
		this.timestamp = timestamp;
		this.accepted = accepted;
		this.sender = from;
		this.receiver = to;
	}
	
	public FriendRequestDTO() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public Boolean getAccepted() {
		return accepted;
	}

	public void setAccepted(Boolean accepted) {
		this.accepted = accepted;
	}

	public UserDTO getSender() {
		return sender;
	}

	public void setSender(UserDTO sender) {
		this.sender = sender;
	}

	public UserDTO getReceiver() {
		return receiver;
	}

	public void setReceiver(UserDTO receiver) {
		this.receiver = receiver;
	}
	
}
