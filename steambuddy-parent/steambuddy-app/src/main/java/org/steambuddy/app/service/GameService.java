package org.steambuddy.app.service;

import java.util.List;

import org.steambuddy.api.dto.GameDTO;

public interface GameService {

	List<GameDTO> getGames();
	List<GameDTO> getGames(String name);
	
}
