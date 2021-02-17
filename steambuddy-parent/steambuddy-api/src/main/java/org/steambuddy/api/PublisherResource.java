package org.steambuddy.api;

import static org.steambuddy.api.ModuleConfigurationConstants.INTERNAL_PATH_PREFIX;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.steambuddy.api.dto.PublisherDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(value = "PublisherResource")
@RequestMapping(value = INTERNAL_PATH_PREFIX + "/api/publisher")
public interface PublisherResource {

	@ApiOperation(value = "Get the list of all publisher.", nickname = "getPublisher", notes = "Returns all publisher.", tags = "PublisherResource", response = PublisherDTO.class, responseContainer = "List")
	@ResponseBody
	@GetMapping("/")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "page", dataType = "integer", paramType = "query", value = "Results page you want to retrieve (0..N)") })
	List<PublisherDTO> getPublisher(Pageable pageable);

	@ApiOperation(value = "Get publisher which contain the given name", nickname = "getPublisherByName", notes = "Returns found publisher.", tags = "PublisherResource", response = PublisherDTO.class, responseContainer = "List")
	@ResponseBody
	@GetMapping(path = "/{name}")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "page", dataType = "integer", paramType = "query", value = "Results page you want to retrieve (0..N)") })
	List<PublisherDTO> getPublishers(@PathVariable("name") String name, Pageable pageable);

	@ApiOperation(value = "Get publisher by id.", nickname = "getPublisher", notes = "Returns publisher or null.", tags = "PublisherResource", response = PublisherDTO.class)
	@ResponseBody
	@GetMapping(path = "/{id}")
	PublisherDTO getPublisher(@PathVariable("id") Long id);
}
