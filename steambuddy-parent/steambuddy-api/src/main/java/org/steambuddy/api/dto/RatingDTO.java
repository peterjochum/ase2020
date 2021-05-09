package org.steambuddy.api.dto;

public class RatingDTO {
	private Long gameId;
	private Long userId;
	private Long rating;
	private String ratingText;
	public Long getGameId() {
		return gameId;
	}
	public void setGameId(Long gameId) {
		this.gameId = gameId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getRating() {
		return rating;
	}
	public void setRating(Long rating) {
		this.rating = rating;
	}
	public String getRatingText() {
		return ratingText;
	}
	public void setRatingText(String ratingText) {
		this.ratingText = ratingText;
	}
	
	public static RatingDTO getInstance(Long gameId, Long userId, Long rating, String ratingText) {
		RatingDTO instance = new RatingDTO();
		instance.setGameId(gameId);
		instance.setUserId(userId);
		instance.setRating(rating);
		instance.setRatingText(ratingText);
		return instance;
	}

	
}
