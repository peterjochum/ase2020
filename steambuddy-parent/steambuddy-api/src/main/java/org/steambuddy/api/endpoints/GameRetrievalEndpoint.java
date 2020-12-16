package org.steambuddy.api.endpoints;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.steambuddy.api.models.Game;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "GameResources")
@RestController
public class GameRetrievalEndpoint {

	@ApiOperation(value = "Get the list of all games.", nickname = "games", notes = "Returns all games.", tags = "GameResources", response = Game.class, responseContainer = "List")
	@GetMapping("/games")
	public List<Game> getGames() {
		List<Game> games = new ArrayList<Game>();

		games.add(Game.getInstance("Cyberpunk 2077", 2020));
		games.add(Game.getInstance("The Witcher 3", 2016));
		games.add(Game.getInstance("The Elderscrolls VI", 2220));
		return games;
	}

}
