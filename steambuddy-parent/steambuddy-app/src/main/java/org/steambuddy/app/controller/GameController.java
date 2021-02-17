package org.steambuddy.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RestController;
import org.steambuddy.api.GameResource;
import org.steambuddy.api.dto.GameCollectionDTO;
import org.steambuddy.api.dto.GameDTO;
import org.steambuddy.app.service.GameService;

@RestController
public class GameController implements GameResource {

	@Autowired
	private GameService gameService;

	@Override
	public List<GameDTO> getGames(Pageable pageable) {
		return gameService.getGames(pageable);
	}

	@Override
	public List<GameDTO> getGames(String name, Pageable pageable) {
		return gameService.getGames(name, pageable);
	}

	@Override
	public GameDTO getGame(Long id) {
		return gameService.getGame(id);
	}
	

	@Override
	public List<GameDTO> getGameSuggestions(Long id) {
		return gameService.getGameSuggestions(id);
	}

	@Override
	public GameCollectionDTO addGameToCollection(Long userId, Long gameId) {
		return gameService.addGameToGameCollection(userId, gameId);
	}

	@Override
	public GameCollectionDTO getCollection(Long userId) {
		return gameService.getGameCollectionByUserId(userId);
	}

	@Override
	public GameCollectionDTO removeGameFromCollection(Long id, Long gameId) {
		return gameService.removeGameFromGameCollection(id, gameId);
	}

}
