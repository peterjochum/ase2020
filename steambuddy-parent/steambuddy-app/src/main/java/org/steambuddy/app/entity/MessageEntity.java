package org.steambuddy.app.entity;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import org.steambuddy.app.compositekeys.GameRatingKey;
import org.steambuddy.app.compositekeys.MessageKey;

@Entity
//@Table(name = "rating")
public class MessageEntity{

	@EmbeddedId
	private MessageKey messageKey;
	

	//@Column(name = "rating_text")
	private String message;

	public String getMessage() {
		return message;
	}

	public void setmessage(String message) {
		this.message = message;
	}

	public MessageEntity() {
		
	}
	public MessageEntity(MessageKey messageKey,String message) {
		this.setMessageKey(messageKey);
		this.message = message;
	}

	public MessageKey getMessageKey() {
		return messageKey;
	}

	public void setMessageKey(MessageKey messageKey) {
		this.messageKey = messageKey;
	}


}
