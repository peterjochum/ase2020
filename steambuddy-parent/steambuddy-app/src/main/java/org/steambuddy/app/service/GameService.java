package org.steambuddy.app.service;

import java.util.List;

import org.steambuddy.api.dto.GameCollectionDTO;
import org.steambuddy.api.dto.GameDTO;
import org.steambuddy.api.dto.UserDTO;

public interface GameService {

	List<GameDTO> getGames();

	List<GameDTO> getGames(String name);
	
	List<GameDTO> getGameSuggestions(Long userId);

	GameDTO getGame(Long id);

	GameCollectionDTO getGameCollectionByUserId(Long userId);

	GameCollectionDTO addGameToGameCollection(Long gameCollection, Long gameId);

	GameCollectionDTO removeGameFromGameCollection(Long userId, Long gameId);
}
