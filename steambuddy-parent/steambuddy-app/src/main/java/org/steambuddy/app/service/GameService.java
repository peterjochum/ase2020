package org.steambuddy.app.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.steambuddy.api.dto.GameCollectionDTO;
import org.steambuddy.api.dto.GameDTO;
import org.steambuddy.api.dto.RatingDTO;

public interface GameService {

	public static int PAGE_SIZE = 25;
	
	List<GameDTO> getGames(Pageable pageable);

	List<GameDTO> getGames(String name, Pageable pageable);
	
	List<GameDTO> getGameSuggestionsByGenres(Long userId);
	
	List<GameDTO> getGameSuggestionsByRatings(Long userId);

	RatingDTO addRating(RatingDTO rating);
	
	RatingDTO removeRating(Long id,Long gameId);
	
	GameDTO getGame(Long id);

	GameCollectionDTO getGameCollectionByUserId(Long userId);

	GameCollectionDTO addGameToGameCollection(Long gameCollection, Long gameId);

	GameCollectionDTO removeGameFromGameCollection(Long userId, Long gameId);
}
