package org.steambuddy.api.dto;

import java.util.List;

public class GameCollectionDTO {

	private Long id;

	private List<GameDTO> games;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<GameDTO> getGames() {
		return games;
	}

	public void setGames(List<GameDTO> games) {
		this.games = games;
	}

}
