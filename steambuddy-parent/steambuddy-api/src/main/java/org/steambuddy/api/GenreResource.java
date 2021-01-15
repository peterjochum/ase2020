package org.steambuddy.api;

import static org.steambuddy.api.ModuleConfigurationConstants.INTERNAL_PATH_PREFIX;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.steambuddy.api.dto.GenreDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "GenreResource")
@RequestMapping(value = INTERNAL_PATH_PREFIX + "/api/genre")
public interface GenreResource {

	@ApiOperation(value = "Get the list of all genres.", nickname = "getGenre", notes = "Returns all genres.", tags = "GenreResource", response = GenreDTO.class, responseContainer = "List")
	@ResponseBody
	@GetMapping("/genre")
	List<GenreDTO> getGenres();

	@ApiOperation(value = "Get publisher which contain the given name", nickname = "getGenreByName", notes = "Returns found genres.", tags = "GenreResource", response = GenreDTO.class, responseContainer = "List")
	@ResponseBody
	@GetMapping(path = "/genre", params = "name")
	List<GenreDTO> getGenres(String name);

	@ApiOperation(value = "Get genre by id.", nickname = "getGenre", notes = "Returns genre or null.", tags = "GenreResource", response = GenreDTO.class)
	@ResponseBody
	@GetMapping(path = "/genre", params = "id")
	GenreDTO getGenre(Long id);
}
