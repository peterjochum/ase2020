package org.steambuddy.app.service;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.steambuddy.api.dto.UserDTO;
import org.steambuddy.app.entity.UserEntity;
import org.steambuddy.app.mapper.UserMapper;
import org.steambuddy.app.repository.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserMapper mapper;
	
	@Override
	public UserDTO authenticateUser(UserDTO user) {
		UserEntity u = userRepository.findByName(user.getName());
		if (u != null) {
			u.setFriends(u.getFriends().stream().map(friend -> ignoreFriends(friend)).collect(Collectors.toSet()));
			if (passwordEncoder.matches(user.getPassword(), u.getPassword())) {
				return mapper.entityToDTO(u);
			}
		}
		return null;
	}

	@Override
	public UserDTO registerUser(UserDTO user) {
		if (userRepository.findByName(user.getName()) != null) {
			return null;
		}
		UserEntity toRegister = mapper.dtoToEntity(user);
		toRegister.setPassword(passwordEncoder.encode(user.getPassword()));
		return mapper.entityToDTO(userRepository.save(toRegister));
	}

	@Override
	public UserDTO updateUser(UserDTO user) {
		UserEntity u = userRepository.findById(user.getId()).get();
		u.setFriends(u.getFriends().stream().map(friend -> ignoreFriends(friend)).collect(Collectors.toSet()));
		u = mapper.updateEntity(u, user);
		return mapper.entityToDTO(userRepository.save(u));
	}

	private UserEntity ignoreFriends(UserEntity user) {
		UserEntity temp = new UserEntity();
		temp.setId(user.getId());
		temp.setName(user.getName());
		temp.setFriends(user.getFriends());
		temp.setFriends(null);
		return temp;
	}
	
}
