package org.steambuddy.app.compositekeys;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class MessageKey implements Serializable {

   // @Column(name = "game_id")
    private Long fromId;

   // @Column(name = "user_id")
    private Long toId;

    private Long timeStamp;
    
	public MessageKey(Long fromId, Long toId,Long timeStamp) {
		this.fromId = fromId;
		this.toId = toId;
		this.timeStamp=timeStamp;
		
	}
	
	public MessageKey(){
		
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MessageKey key = (MessageKey) o;
        return fromId.equals(key.fromId) &&
        		toId.equals(key.toId)&&timeStamp.equals(key.timeStamp);
    }
	
    @Override
    public int hashCode() {
        return Objects.hash(fromId, toId,timeStamp);
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
	
    // standard constructors, getters, and setters
    // hashcode and equals implementation
}