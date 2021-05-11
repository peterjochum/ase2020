package org.steambuddy.api.dto;

public class GroupMessageDTO {
	private Long groupId;
	private Long userId;
	private Long timeStamp;
	private String message;
	
	public static GroupMessageDTO getInstance(Long groupId, Long userId, String message,Long timeStamp) {
		GroupMessageDTO instance = new GroupMessageDTO();
		instance.setGroupId(groupId);
		instance.setUserId(userId);
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


	public Long getGroupId() {
		return groupId;
	}


	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}


	public Long getUserId() {
		return userId;
	}


	public void setUserId(Long userId) {
		this.userId = userId;
	}





	
	
}
