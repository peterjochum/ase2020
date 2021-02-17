package org.steambuddy.app.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.steambuddy.api.dto.GameCollectionDTO;
import org.steambuddy.api.dto.GameDTO;

public interface GameService {

	public static int PAGE_SIZE = 25;
	
	List<GameDTO> getGames(Pageable pageable);

	List<GameDTO> getGames(String name, Pageable pageable);
	
	List<GameDTO> getGameSuggestions(Long userId);

	GameDTO getGame(Long id);

	GameCollectionDTO getGameCollectionByUserId(Long userId);

	GameCollectionDTO addGameToGameCollection(Long gameCollection, Long gameId);

	GameCollectionDTO removeGameFromGameCollection(Long userId, Long gameId);
}
