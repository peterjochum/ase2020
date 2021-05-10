package org.steambuddy.app.compositekeys;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class MessageKey implements Serializable {

   // @Column(name = "game_id")
    private Long userId1;

   // @Column(name = "user_id")
    private Long userId2;

    private Long timeStamp;
    
	public MessageKey(Long userId1, Long userId2,Long timeStamp) {
		this.userId1 = userId1;
		this.userId2 = userId2;
		this.timeStamp=timeStamp;
		
	}
	
	public MessageKey(){
		
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MessageKey key = (MessageKey) o;
        return userId1.equals(key.userId1) &&
        		userId2.equals(key.userId2)&&timeStamp.equals(key.timeStamp);
    }
	
    @Override
    public int hashCode() {
        return Objects.hash(userId1, userId2,timeStamp);
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

	public Long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Long timeStamp) {
		this.timeStamp = timeStamp;
	}
	
    // standard constructors, getters, and setters
    // hashcode and equals implementation
}