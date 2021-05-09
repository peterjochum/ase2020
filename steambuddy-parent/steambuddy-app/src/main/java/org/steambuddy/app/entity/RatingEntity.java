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

@Entity
//@Table(name = "rating")
public class RatingEntity{

	@EmbeddedId
	private GameRatingKey ratingKey;
	
	/*
	@ManyToOne
    @MapsId("gameId")
    @JoinColumn(name = "game_id")
    GameEntity game;
	
	@ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    UserEntity user;
	*/
	
	//@Column(name = "rating", nullable = false)
	private Long rating;

	//@Column(name = "rating_text")
	private String ratingText;

	public String getRatingText() {
		return ratingText;
	}

	public void setRatingText(String ratingText) {
		this.ratingText = ratingText;
	}

	public Long getRating() {
		return rating;
	}

	public void setRating(Long rating) {
		this.rating = rating;
	}
	
	public RatingEntity() {
		
	}
	public RatingEntity(GameRatingKey ratingKey,Long rating,String ratingText) {
		this.setRatingKey(ratingKey);
		this.rating = rating;
		this.ratingText = ratingText;
	}

	public GameRatingKey getRatingKey() {
		return ratingKey;
	}

	public void setRatingKey(GameRatingKey ratingKey) {
		this.ratingKey = ratingKey;
	}


}
