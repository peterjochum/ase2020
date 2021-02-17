package org.steambuddy.api;

import static org.steambuddy.api.ModuleConfigurationConstants.INTERNAL_PATH_PREFIX;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.steambuddy.api.dto.GroupDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "GroupResource")
@RequestMapping(value = INTERNAL_PATH_PREFIX + "/api/groups")
public interface GroupResource {

	/**
	 * Get all {@link GroupDTO}s
	 * 
	 * @return a {@link List} of {@link GroupDTO}s
	 */
	@ApiOperation(value = "Get all groups.",
			nickname = "getGroups",
			notes = "Returns all Groups.",
			tags = "GroupResource",
			response = GroupDTO.class,
			responseContainer = "List")
	@ResponseBody
	@GetMapping("/")
	List<GroupDTO> getGroups();
	
	/**
	 * Creates a new {@link GroupDTO}
	 * 
	 * @param group to be created
	 * @return the created {@link GroupDTO}
	 */
	@ApiOperation(value = "Create a new group.",
			nickname = "createGroup",
			notes = "Returns the created Group.",
			tags = "GroupResource",
			response = GroupDTO.class)
	@ResponseBody
	@PostMapping("/")
	GroupDTO createGroup(@RequestBody GroupDTO group);
	
	/**
	 * Deletes the {@link GroupDTO} with the given id
	 * 
	 * @param id of the group
	 */
	@ApiOperation(value = "Delete a Group.",
            nickname = "deleteGroup",
            notes = "Delete a Group.",
            tags = "GroupResource",
            response = GroupDTO.class)
    @ResponseBody
    @DeleteMapping(path = "/{id}")
	void deleteGroup(@PathVariable("id") Long id);
	
	/**
	 * Gets all {@link GroupDTO} of a {@link UserDTO}
	 * 
	 * @param user_id of the owner
	 * @return a {@link List} of {@link GroupDTO}s
	 */
	@ApiOperation(value = "Get all owned groups of user.",
			nickname = "getGroupsByUserId",
			notes = "Returns all owned Groups.",
			tags = "GroupResource",
			response = GroupDTO.class,
			responseContainer = "List")
	@ResponseBody
	@GetMapping("/{user_id}")
	List<GroupDTO> getGroupsByUserId(@PathVariable("user_id") Long user_id);
	
}
