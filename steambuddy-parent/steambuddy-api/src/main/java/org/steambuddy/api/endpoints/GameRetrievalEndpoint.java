package org.steambuddy.api.endpoints;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.steambuddy.api.models.Game;

@RestController
public class GameRetrievalEndpoint {

	@GetMapping("/games")
	public List<Game> getGames() {
		List<Game> games = new ArrayList<Game>();

		games.add(Game.getInstance("Cyberpunk 2077", 2020));
		games.add(Game.getInstance("The Witcher 3", 2016));
		games.add(Game.getInstance("The Elderscrolls VI", 2220));
		return games;
	}

}
