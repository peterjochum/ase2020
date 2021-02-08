package org.steambuddy.app.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "friendRequest")
public class FriendRequestEntity extends AbstractEntity {

	
	@Column(name = "timestamp")
	private Timestamp timestamp;

	@Column(name = "accepted")
	private Boolean accepted;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sender", nullable = false)
	private UserEntity sender;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "receiver", nullable = false)
	private UserEntity receiver;

	public FriendRequestEntity(Long id, Timestamp timestamp, Boolean accepted, UserEntity from, UserEntity to) {
		this.id = id;
		this.timestamp = timestamp;
		this.accepted = accepted;
		this.sender = from;
		this.receiver = to;
	}
	
	public FriendRequestEntity() {}

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

	public UserEntity getSender() {
		return sender;
	}

	public void setSender(UserEntity sender) {
		this.sender = sender;
	}

	public UserEntity getReceiver() {
		return receiver;
	}

	public void setReceiver(UserEntity receiver) {
		this.receiver = receiver;
	}
	
}
