package org.steambuddy.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	public GameCollectionDTO addGameToCollection(Long id, Long gameId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GameCollectionDTO getCollection(Long userId) {
		return gameService.getGameCollection(userId);
	}

}
