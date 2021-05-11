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
import org.steambuddy.app.compositekeys.GroupMessageKey;
import org.steambuddy.app.compositekeys.MessageKey;

@Entity
//@Table(name = "rating")
public class GroupMessageEntity{

	@EmbeddedId
	private GroupMessageKey groupMessageKey;
	

	//@Column(name = "rating_text")
	private String message;

	public String getMessage() {
		return message;
	}

	public void setmessage(String message) {
		this.message = message;
	}

	public GroupMessageEntity() {
		
	}
	public GroupMessageEntity(GroupMessageKey groupMessageKey,String message) {
		this.setGroupMessageKey(groupMessageKey);
		this.message = message;
	}

	public GroupMessageKey getGroupMessageKey() {
		return groupMessageKey;
	}

	public void setGroupMessageKey(GroupMessageKey groupMessageKey) {
		this.groupMessageKey = groupMessageKey;
	}


}
