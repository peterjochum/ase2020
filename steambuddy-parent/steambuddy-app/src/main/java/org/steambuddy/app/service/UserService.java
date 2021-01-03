package org.steambuddy.app.service;

import org.steambuddy.api.dto.UserDTO;

public interface UserService {

	UserDTO authenticateUser(UserDTO user);
	
	UserDTO registerUser(UserDTO user);
	
	UserDTO updateUser(UserDTO user);
	
}
