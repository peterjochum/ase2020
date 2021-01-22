package org.steambuddy.app.service;

import java.util.List;

import org.steambuddy.api.dto.GameCollectionDTO;
import org.steambuddy.api.dto.GameDTO;

public interface GameService {

	List<GameDTO> getGames();

	List<GameDTO> getGames(String name);

	GameDTO getGame(Long id);

	GameCollectionDTO getGameCollectionByUserId(Long userId);

	GameCollectionDTO addGameToGameCollection(Long gameCollection, Long gameId);

	GameCollectionDTO removeGameFromGameCollection(Long userId, Long gameId);
}
