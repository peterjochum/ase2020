package org.steambuddy.app.compositekeys;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class GameRatingKey implements Serializable {

   // @Column(name = "game_id")
    private Long gameId;

   // @Column(name = "user_id")
    private Long userId;

    public Long getGameId() {
		return gameId;
	}
    
    

	public void setGameId(Long id) {
		this.gameId = id;
	}

    public Long getUserId() {
		return userId;
	}

	public void setUserId(Long id) {
		this.userId = id;
	}
	
	public GameRatingKey(Long gameId, Long userId) {
		this.gameId = gameId;
		this.userId = userId;
	}
	
	public GameRatingKey(){
		
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameRatingKey key = (GameRatingKey) o;
        return gameId.equals(key.gameId) &&
                userId.equals(key.userId);
    }
	
    @Override
    public int hashCode() {
        return Objects.hash(gameId, userId);
    }
	
    // standard constructors, getters, and setters
    // hashcode and equals implementation
}