package org.steambuddy.api;

import static org.steambuddy.api.ModuleConfigurationConstants.INTERNAL_PATH_PREFIX;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.steambuddy.api.dto.GameCollectionDTO;
import org.steambuddy.api.dto.GameDTO;
import org.steambuddy.api.dto.UserDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "GameResource")
@RequestMapping(value = INTERNAL_PATH_PREFIX + "/api/game")
public interface GameResource {

	@ApiOperation(value = "Get the list of all games.", nickname = "getGames", notes = "Returns all games.", tags = "GameResource", response = GameDTO.class, responseContainer = "List")
	@ResponseBody
	@GetMapping("/games")
	List<GameDTO> getGames();


	@ApiOperation(value = "Get game by id.", nickname = "getGame", notes = "Returns game or null.", tags = "GamesResource", response = GameDTO.class)
	@ResponseBody
	@GetMapping(path = "/games", params = "id")
	GameDTO getGame(Long id);
	
	@ApiOperation(value = "Get a list of suggested games.",
			nickname = "getSuggestedGames",
			notes = "Returns a list of suggested games.",
			tags = "GameResource",
			response = GameDTO.class,
			responseContainer = "List")
	@ResponseBody
	@GetMapping(path="/games/suggested",params="user")
	List<GameDTO> getGameSuggestions(@RequestBody UserDTO user);
	

	@ApiOperation(value = "Get games which contain the given name", nickname = "getGamesByName", notes = "Returns found games.", tags = "GameResource", response = GameDTO.class, responseContainer = "List")
	@ResponseBody
	@GetMapping(path = "/games/{name}")
	List<GameDTO> getGames(@PathVariable("name") String name);

	@ApiOperation(value = "Get game by id.", nickname = "getGame", notes = "Returns game or null.", tags = "GamesResource", response = GameDTO.class)
	@ResponseBody
	@GetMapping(path = "/{id}")
	GameDTO getGame(@PathVariable("id") Long id);

	@ApiOperation(value = "Add a game by id to the gamecollection.", nickname = "addGame", notes = "adds game to collection", tags = "GamesResource", response = GameCollectionDTO.class)
	@ResponseBody
	@PutMapping(path = "/gamecollection/{id}/{gameId}")
	GameCollectionDTO addGameToCollection(@PathVariable("id") Long id, @PathVariable("gameId") Long gameId);

	@ApiOperation(value = "Add a game by id to the gamecollection.", nickname = "addGame", notes = "adds game to collection", tags = "GamesResource", response = GameCollectionDTO.class)
	@ResponseBody
	@DeleteMapping(path = "/gamecollection/{id}/{gameId}")
	GameCollectionDTO removeGameToCollection(@PathVariable("id") Long id, @PathVariable("gameId") Long gameId);
	
	@ApiOperation(value = "Get game gamecollection from user.", nickname = "getGamecollection", notes = "get gamecollection from user", tags = "GamesResource", response = GameCollectionDTO.class)
	@ResponseBody
	@GetMapping(path = "/gamecollection/{id}")
	GameCollectionDTO getCollection(@PathVariable("id") Long userId);

}
