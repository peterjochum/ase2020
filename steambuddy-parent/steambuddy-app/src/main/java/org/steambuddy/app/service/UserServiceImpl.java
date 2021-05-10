package org.steambuddy.app.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.steambuddy.api.dto.MessageDTO;
import org.steambuddy.api.dto.UserDTO;
import org.steambuddy.app.compositekeys.GameRatingKey;
import org.steambuddy.app.compositekeys.MessageKey;
import org.steambuddy.app.entity.MessageEntity;
import org.steambuddy.app.entity.RatingEntity;
import org.steambuddy.app.entity.UserEntity;
import org.steambuddy.app.mapper.MessageMapper;
import org.steambuddy.app.mapper.UserMapper;
import org.steambuddy.app.repository.MessageRepository;
import org.steambuddy.app.repository.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private MessageRepository messageRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserMapper mapper;
	
	@Autowired
	private MessageMapper messageMapper;
	
	@Override
	public UserDTO authenticateUser(UserDTO user) {
		UserEntity u = userRepository.findByName(user.getName());
		if (u != null) {
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
		u = mapper.updateEntity(u, user);
		return mapper.entityToDTO(userRepository.save(u));
	}

	@Override
	public List<UserDTO> getUsers() {
		return mapper.mapEntityToDTO((List<UserEntity>) userRepository.findAll());
	}

	@Override
	public UserDTO getUserById(Long id) {
		return mapper.entityToDTO(userRepository.findById(id).get());
	}

	@Override
	public void addFriend(UserEntity user, UserEntity friend) {
		Set<UserEntity> friends = user.getFriends();
		friends.add(friend);
		user.setFriends(friends);
		userRepository.save(user);
	}
	
	@Override
	public MessageDTO sendMessage(MessageDTO message) {
		 
		Timestamp curTime=new Timestamp(System.currentTimeMillis());
		MessageKey key = new MessageKey(message.getUserId1(),message.getUserId2(),curTime.getTime());
	    MessageEntity messageE = new MessageEntity(key,message.getMessage());
		
		messageRepository.save(messageE);
	    //return messageMapper.entityToDTO(messageRepository.findById(key).get());
		return messageMapper.entityToDTO(messageE);
	}
	
}
