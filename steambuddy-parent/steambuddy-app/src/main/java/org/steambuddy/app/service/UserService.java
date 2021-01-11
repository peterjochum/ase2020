package org.steambuddy.app.service;

import java.util.List;

import org.steambuddy.api.dto.UserDTO;

public interface UserService {

	UserDTO authenticateUser(UserDTO user);
	
	UserDTO registerUser(UserDTO user);
	
	UserDTO updateUser(UserDTO user);
	
	List<UserDTO> getUsers();
	
}
