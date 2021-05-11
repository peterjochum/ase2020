package org.steambuddy.app.compositekeys;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class GroupMessageKey implements Serializable {

   // @Column(name = "game_id")
    private Long groupId;

   // @Column(name = "user_id")
    private Long userId;

    private Long timeStamp;
    
	public GroupMessageKey(Long groupId, Long userId,Long timeStamp) {
		this.groupId = groupId;
		this.userId = userId;
		this.timeStamp=timeStamp;
		
	}
	
	public GroupMessageKey(){
		
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupMessageKey key = (GroupMessageKey) o;
        return groupId.equals(key.groupId) &&
        		userId.equals(key.userId)&&timeStamp.equals(key.timeStamp);
    }
	
    @Override
    public int hashCode() {
        return Objects.hash(groupId, userId,timeStamp);
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


	
    // standard constructors, getters, and setters
    // hashcode and equals implementation
}