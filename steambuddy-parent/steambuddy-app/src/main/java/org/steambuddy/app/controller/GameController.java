package org.steambuddy.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.steambuddy.api.GameResource;
import org.steambuddy.api.dto.GameCollectionDTO;
import org.steambuddy.api.dto.GameDTO;
import org.steambuddy.api.dto.UserDTO;
import org.steambuddy.app.service.GameService;

@RestController
public class GameController implements GameResource {

	@Autowired
	private GameService gameService;

	@Override
	public List<GameDTO> getGames() {
		return gameService.getGames();
	}

	@Override
	public List<GameDTO> getGames(String name) {
		return gameService.getGames(name);
	}

	@Override
	public GameDTO getGame(Long id) {
		return gameService.getGame(id);
	}
	

	@Override
	public List<GameDTO> getGameSuggestions(UserDTO user) {
		return gameService.getGameSuggestions(user);
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
	public GameCollectionDTO removeGameToCollection(Long id, Long gameId) {
		return gameService.removeGameFromGameCollection(id, gameId);
	}

}
