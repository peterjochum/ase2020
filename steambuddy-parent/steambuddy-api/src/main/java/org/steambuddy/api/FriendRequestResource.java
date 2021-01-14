package org.steambuddy.api;

import static org.steambuddy.api.ModuleConfigurationConstants.INTERNAL_PATH_PREFIX;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.steambuddy.api.dto.FriendRequestDTO;
import org.steambuddy.api.dto.UserDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "FriendRequestResource")
@RequestMapping(value = INTERNAL_PATH_PREFIX + "/api/friend-request")
public interface FriendRequestResource {

	/**
	 * Gets all incoming {@link FriendRequestDTO}s of a {@link UserDTO}
	 * 
	 * @param receiver of the friendRequests
	 * @return a {@link List} of {@link FriendRequestDTO}s
	 */
	@ApiOperation(value = "Get all incomin friendRequests of a user.",
			nickname = "getIncoming",
			notes = "Returns all incoming friendRequests.",
			tags = "FriendRequestResource",
			response = FriendRequestDTO.class,
			responseContainer = "List")
	@ResponseBody
	@GetMapping("/friend-requests/incoming")
	List<FriendRequestDTO> getIncoming(@RequestBody UserDTO receiver);
	
	/**
	 * Gets all outgoing {@link FriendRequestDTO}s of a {@link UserDTO}
	 * 
	 * @param sender of the friendRequests
	 * @return a {@link List} of {@link FriendRequestDTO}s
	 */
	@ApiOperation(value = "Get all outgoing friendRequests of a user.",
			nickname = "getOutgoing",
			notes = "Returns all outgoing friendRequests.",
			tags = "FriendRequestResource",
			response = FriendRequestDTO.class,
			responseContainer = "List")
	@ResponseBody
	@GetMapping("/friend-requests/outgoing")
	List<FriendRequestDTO> getOutgoing(@RequestBody UserDTO sender);
	
	/**
	 * Creates a new {@link FriendRequestDTO}
	 * 
	 * @param request to be created
	 * @return id of the new {@link FriendRequestDTO}
	 */
	@ApiOperation(value = "Create a new friendRequest.",
			nickname = "createRequest",
			notes = "Returns the id of the created friendRequest.",
			tags = "FriendRequestResource",
			response = Long.class)
	@ResponseBody
	@PostMapping("/friend-requests")
	Long createRequest(@RequestBody FriendRequestDTO request);
	
	/**
	 * Updates a {@link FriendRequestDTO}
	 * 
	 * @param request to be updated
	 * @return the id of the updated {@link FriendRequestDTO}
	 */
	@ApiOperation(value = "Updated a friendRequest.",
			nickname = "updateRequest",
			notes = "Returns the id of the updated friendRequest.",
			tags = "FriendRequestResource",
			response = Long.class)
	@ResponseBody
	@PutMapping("/friend-requests")
	Long updateRequest(@RequestBody FriendRequestDTO request);
	
	/**
	 * Get the {@link FriendRequestDTO} with the given id
	 * 
	 * @param id of the {@link FriendRequestDTO}
	 * @return the wanted {@link FriendRequestDTO}
	 */
	@ApiOperation(value = "Get friendRequest by id.",
			nickname = "getRequestById",
			notes = "Returns the friendRequest with given id.",
			tags = "FriendRequestResource",
			response = FriendRequestDTO.class)
	@ResponseBody
	@GetMapping("/friend-requests/{id}")
	FriendRequestDTO getRequestById(@PathVariable("id") Long id);
	
}
