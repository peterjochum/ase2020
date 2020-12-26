package org.steambuddy.api;

import static org.steambuddy.api.ModuleConfigurationConstants.INTERNAL_PATH_PREFIX;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.steambuddy.api.dto.GameDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "GameResource")
@RequestMapping(value = INTERNAL_PATH_PREFIX + "/api/game")
public interface GameResource {
	
	@ApiOperation(value = "Get the list of all games.",
			nickname = "getGames",
			notes = "Returns all games.",
			tags = "GameResource",
			response = GameDTO.class,
			responseContainer = "List")
	@ResponseBody
	@GetMapping("/games")
	List<GameDTO> getGames();
	
}
