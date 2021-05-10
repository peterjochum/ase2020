package org.steambuddy.api.dto;

public class MessageDTO {
	private Long userId1;
	private Long userId2;
	private String message;
	
	public static MessageDTO getInstance(Long userId1, Long userId2, String message) {
		MessageDTO instance = new MessageDTO();
		instance.setUserId1(userId1);
		instance.setUserId2(userId2);
		instance.setMessage(message);
		return instance;
	}

	public Long getUserId1() {
		return userId1;
	}

	public void setUserId1(Long userId1) {
		this.userId1 = userId1;
	}



	public Long getUserId2() {
		return userId2;
	}



	public void setUserId2(Long userId2) {
		this.userId2 = userId2;
	}



	public String getMessage() {
		return message;
	}



	public void setMessage(String message) {
		this.message = message;
	}

	
}
