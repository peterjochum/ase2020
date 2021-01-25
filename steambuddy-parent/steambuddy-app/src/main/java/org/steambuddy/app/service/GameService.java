package org.steambuddy.app.service;

import java.util.List;

import org.steambuddy.api.dto.GameDTO;
import org.steambuddy.api.dto.UserDTO;

public interface GameService {

	List<GameDTO> getGames();
	List<GameDTO> getGames(String name);
	List<GameDTO> getGameSuggestions(UserDTO user);
	GameDTO getGame(Long id);
	
}
