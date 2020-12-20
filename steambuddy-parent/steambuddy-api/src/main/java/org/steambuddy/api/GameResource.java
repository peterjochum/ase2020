package org.steambuddy.api;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.steambuddy.api.dto.GameDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "GameResource")
public interface GameResource {
	
	@ApiOperation(value = "Get the list of all games.",
			nickname = "games",
			notes = "Returns all games.",
			tags = "GameResources",
			response = GameDTO.class,
			responseContainer = "List")
	@ResponseBody
	@GetMapping("/games")
	List<GameDTO> getGames();
	
}
