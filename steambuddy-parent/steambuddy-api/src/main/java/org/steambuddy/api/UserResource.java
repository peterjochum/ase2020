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
import org.steambuddy.api.dto.GameDTO;
import org.steambuddy.api.dto.GroupMessageDTO;
import org.steambuddy.api.dto.MessageDTO;
import org.steambuddy.api.dto.RatingDTO;
import org.steambuddy.api.dto.UserDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "UserResource")
@RequestMapping(value = INTERNAL_PATH_PREFIX + "/api/users")
public interface UserResource {

	/**
	 * Tries to authenticate a {@link UserDTO}
	 * 
	 * @param user to authenticate
	 * @return the logged in {@link UserDTO}
	 */
	@ApiOperation(value = "Authenticate a user.",
			nickname = "authenticateUser",
			notes = "Returns the logged in User.",
			tags = "UserResource",
			response = UserDTO.class)
	@ResponseBody
	@PutMapping("/authenticate")
	UserDTO authenticateUser(@RequestBody UserDTO user);
	
	/**
	 * Registers a new {@link UserDTO}
	 * 
	 * @param user to register
	 * @return the new {@link UserDTO}
	 */
	@ApiOperation(value = "Register a new user.",
			nickname = "registerUser",
			notes = "Returns the created User.",
			tags = "UserResource",
			response = UserDTO.class)
	@ResponseBody
	@PostMapping("/register")
	UserDTO registerUser(@RequestBody UserDTO user);
	
	/**
	 * Updates a {@link UserDTO}
	 * 
	 * @param user to be updated
	 * @return the updated {@link UserDTO}
	 */
	@ApiOperation(value = "Update a user.",
			nickname = "updateUser",
			notes = "Returns the updated User.",
			tags = "UserResource",
			response = UserDTO.class)
	@ResponseBody
	@PutMapping("/")
	UserDTO updateUser(@RequestBody UserDTO user);
	
	/**
	 * Get all {@link UserDTO}s for friend search
	 * 
	 * @return a {@link List} of {@link UserDTO}s
	 */
	@ApiOperation(value = "Get all users.",
			nickname = "getUsers",
			notes = "Returns all Users.",
			tags = "UserResource",
			response = UserDTO.class,
			responseContainer = "List")
	@ResponseBody
	@GetMapping("/")
	List<UserDTO> getUsers();
	
	@ApiOperation(value = "Get user by id.",
			nickname = "getUserById",
			notes = "Returns user with given id.",
			tags = "UserResource",
			response = UserDTO.class)
	@ResponseBody
	@GetMapping("/{id}")
	UserDTO getUserById(@PathVariable("id") Long id);
	 
	@ApiOperation(value = "Send message from User to User", nickname = "sendMessage", notes = "Send messages between users", tags = "UserResource", response = MessageDTO.class)
	@ResponseBody
	@PostMapping(path = "/message")
	MessageDTO sendMessage(@RequestBody MessageDTO message);
	
	@ApiOperation(value = "Get all received messages of a specific user", nickname = "getReceivedMessages", notes = "Get received messages", tags = "UserResource", response = MessageDTO.class)
	@ResponseBody
	@GetMapping(path = "/messages/received/{id}")
	List<MessageDTO> getReceivedMessages(@PathVariable("id") Long id);
	
	@ApiOperation(value = "Get all sent messages of specific user", nickname = "getSentMessages", notes = "Get sent messages", tags = "UserResource", response = MessageDTO.class)
	@ResponseBody
	@GetMapping(path = "/messages/sent/{id}")
	List<MessageDTO> getSentMessages(@PathVariable("id") Long id);
	
	@ApiOperation(value = "Get all messages sent between two specific users", nickname = "getAllMessagesSpecific", notes = "Get all messages between two specific users", tags = "UserResource", response = MessageDTO.class)
	@ResponseBody
	@GetMapping(path = "/messages/all/{userid}/{partnerid}")
	List<MessageDTO> getAllMessagesSpecific(@PathVariable("userid") Long userId, @PathVariable("partnerid") Long partnerId);
	
	@ApiOperation(value = "Send message to all users of a group", nickname = "sendGroupMessage", notes = "Send messages to a group", tags = "UserResource", response = GroupMessageDTO.class)
	@ResponseBody
	@PostMapping(path = "/groupmessage")
	GroupMessageDTO sendGroupMessage(@RequestBody GroupMessageDTO message);
	
	@ApiOperation(value = "Receive all messages from a user group", nickname = "receiveGroupMessages", notes = "Receive messages from a group", tags = "UserResource", response = GroupMessageDTO.class)
	@ResponseBody
	@GetMapping(path = "/groupmessages/{id}")
	List<GroupMessageDTO> receiveGroupMessages(@PathVariable("id") Long groupId );
	
}
