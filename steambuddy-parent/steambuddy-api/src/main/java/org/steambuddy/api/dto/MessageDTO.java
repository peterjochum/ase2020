package org.steambuddy.api.dto;

public class MessageDTO {
	private Long fromId;
	private Long toId;
	private Long timeStamp;
	private String message;
	
	public static MessageDTO getInstance(Long fromId, Long toId, String message,Long timeStamp) {
		MessageDTO instance = new MessageDTO();
		instance.setFromId(fromId);
		instance.setToId(toId);
		instance.setMessage(message);
		instance.setTimeStamp(timeStamp);
		return instance;
	}

	



	public String getMessage() {
		return message;
	}



	public void setMessage(String message) {
		this.message = message;
	}

	public Long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Long timeStamp) {
		this.timeStamp = timeStamp;
	}





	public Long getFromId() {
		return fromId;
	}





	public void setFromId(Long fromId) {
		this.fromId = fromId;
	}





	public Long getToId() {
		return toId;
	}





	public void setToId(Long toId) {
		this.toId = toId;
	}

	
}
