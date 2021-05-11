package org.steambuddy.api;

import static org.steambuddy.api.ModuleConfigurationConstants.INTERNAL_PATH_PREFIX;

import java.lang.reflect.Member;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.steambuddy.api.dto.GameCollectionDTO;
import org.steambuddy.api.dto.GameDTO;
import org.steambuddy.api.dto.RatingDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(value = "GameResource")
@RequestMapping(value = INTERNAL_PATH_PREFIX + "/api/games")
public interface GameResource {

	@ApiOperation(value = "Get the list of all games.", nickname = "getGames", notes = "Returns all games.", tags = "GameResource", response = GameDTO.class, responseContainer = "List")
	@ResponseBody
	@ApiImplicitParams({
	    @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query",
	            value = "Results page you want to retrieve (0..N)")})
	@GetMapping("/")
	List<GameDTO> getGames(Pageable pageable);

	@ApiOperation(value = "Get a list of suggested games by genres.", nickname = "getSuggestedGames", notes = "Returns a list of suggested games.", tags = "GameResource", response = GameDTO.class, responseContainer = "List")
	@ResponseBody
	@GetMapping(path="/suggested/bygenres/{id}")
	List<GameDTO> getGameSuggestionsByGenres(@PathVariable("id") Long userId);

	@ApiOperation(value = "Get a list of suggested games by rating.", nickname = "getSuggestedGames", notes = "Returns a list of suggested games.", tags = "GameResource", response = GameDTO.class, responseContainer = "List")
	@ResponseBody
	@GetMapping(path="/suggested/byrating/{id}")
	List<GameDTO> getGameSuggestionsByRatings(@PathVariable("id") Long userId);
	
	
	@ApiOperation(value = "Get games which contain the given name", nickname = "getGamesByName", notes = "Returns found games.", tags = "GameResource", response = GameDTO.class, responseContainer = "List")
	@ResponseBody
	@GetMapping(path = "/{name}")
	@ApiImplicitParams({
	    @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query",
	            value = "Results page you want to retrieve (0..N)")})
	List<GameDTO> getGames(@PathVariable("name") String name, Pageable pageable);

	@ApiOperation(value = "Get game by id.", nickname = "getGame", notes = "Returns game or null.", tags = "GameResource", response = GameDTO.class)
	@ResponseBody
	@GetMapping(path = "/game/{id}")
	GameDTO getGame(@PathVariable("id") Long id);
	
	@ApiOperation(value = "Add a rating plus optional text to a specific game", nickname = "addRating", notes = "Add a new rating", tags = "GameResource", response = RatingDTO.class)
	@ResponseBody
	@PostMapping(path = "/rating")
	RatingDTO addRating(@RequestBody RatingDTO rating);
	
	@ApiOperation(value = "Remove the rating for a specific game and user", nickname = "removeRating", notes = "Remove a rating", tags = "GameResource", response = RatingDTO.class)
	@ResponseBody
	@DeleteMapping(path = "/rating/{id}/{gameId}")
	RatingDTO removeRating(@PathVariable("id") Long id, @PathVariable("gameId") Long gameId);
	
	@ApiOperation(value = "Add a game by id to the gamecollection.", nickname = "addGame", notes = "adds game to collection", tags = "GameResource", response = GameCollectionDTO.class)
	@ResponseBody
	@PutMapping(path = "/gamecollection/{id}/{gameId}")
	GameCollectionDTO addGameToCollection(@PathVariable("id") Long id, @PathVariable("gameId") Long gameId);

	@ApiOperation(value = "Delete a game by id to the gamecollection.", nickname = "deleteGame", notes = "deletes game to collection", tags = "GameResource", response = GameCollectionDTO.class)
	@ResponseBody
	@DeleteMapping(path = "/gamecollection/{id}/{gameId}")
	GameCollectionDTO removeGameFromCollection(@PathVariable("id") Long id, @PathVariable("gameId") Long gameId);

	@ApiOperation(value = "Get game gamecollection from user.", nickname = "getGamecollection", notes = "get gamecollection from user", tags = "GameResource", response = GameCollectionDTO.class)
	@ResponseBody
	@GetMapping(path = "/gamecollection/{id}")
	GameCollectionDTO getCollection(@PathVariable("id") Long userId);
	
	
	

}
