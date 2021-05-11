package org.steambuddy.app.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.steambuddy.api.dto.GroupMessageDTO;
import org.steambuddy.api.dto.MessageDTO;
import org.steambuddy.api.dto.UserDTO;
import org.steambuddy.app.compositekeys.GameRatingKey;
import org.steambuddy.app.compositekeys.GroupMessageKey;
import org.steambuddy.app.compositekeys.MessageKey;
import org.steambuddy.app.entity.GroupMessageEntity;
import org.steambuddy.app.entity.MessageEntity;
import org.steambuddy.app.entity.RatingEntity;
import org.steambuddy.app.entity.UserEntity;
import org.steambuddy.app.mapper.GroupMessageMapper;
import org.steambuddy.app.mapper.MessageMapper;
import org.steambuddy.app.mapper.UserMapper;
import org.steambuddy.app.repository.GroupMessageRepository;
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
	private GroupMessageRepository groupMessageRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserMapper mapper;
	
	@Autowired
	private MessageMapper messageMapper;
	
	@Autowired
	private GroupMessageMapper groupMessageMapper;
	
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
		 
		MessageKey key = new MessageKey(message.getFromId(),message.getToId(),message.getTimeStamp());
	    MessageEntity messageE = new MessageEntity(key,message.getMessage());
		
		messageRepository.save(messageE);
	    //return messageMapper.entityToDTO(messageRepository.findById(key).get());
		return messageMapper.entityToDTO(messageE);
	}
	
	@Override
	public List<MessageDTO> getReceivedMessages(Long id) { //get messages send to user
		MessageKey key = new MessageKey();
		key.setToId(id);
		
		MessageEntity example=new MessageEntity();
		example.setMessageKey(key);
		List<MessageEntity> messages=messageRepository.findAll(Example.of(example));
				
		return  messageMapper.mapEntityToDTO(messages);
	}
	
	@Override
	public List<MessageDTO> getSentMessages(Long id) { //get messages send to user
		MessageKey key = new MessageKey();
		key.setFromId(id);
		
		MessageEntity example=new MessageEntity();
		example.setMessageKey(key);
		List<MessageEntity> messages=messageRepository.findAll(Example.of(example));
				
		return  messageMapper.mapEntityToDTO(messages);
	}
	
	@Override
	public List<MessageDTO> getAllMessagesSpecific(Long userId, Long partnerId) { //get messages send to user
		MessageKey key1 = new MessageKey(); 
		key1.setFromId(userId);
		key1.setToId(partnerId);
			
		MessageKey key2 = new MessageKey();
		key1.setFromId(partnerId);
		key1.setToId(userId);
		
		List<MessageEntity> messages1=getMessageEntities(key1);
		List<MessageEntity> messages2=getMessageEntities(key2);
		
		messages1.addAll(messages2); //combine list
				
		return messageMapper.mapEntityToDTO(messages1);
	}
	
	@Override
	public GroupMessageDTO sendGroupMessage(GroupMessageDTO message) {
		
		GroupMessageKey key = new GroupMessageKey(message.getGroupId(),message.getUserId(),message.getTimeStamp());
	    GroupMessageEntity messageE = new GroupMessageEntity(key,message.getMessage());
		
		groupMessageRepository.save(messageE);
	    //return messageMapper.entityToDTO(messageRepository.findById(key).get());
		return groupMessageMapper.entityToDTO(messageE);
	}
	
	 @Override
	 public List<GroupMessageDTO> receiveGroupMessages(Long groupId){
		GroupMessageKey key = new GroupMessageKey();
		key.setGroupId(groupId);
		
		GroupMessageEntity example=new GroupMessageEntity();
		example.setGroupMessageKey(key);
		List<GroupMessageEntity> messages=groupMessageRepository.findAll(Example.of(example));
				
		return  groupMessageMapper.mapEntityToDTO(messages);
	 }
	
	private List<MessageEntity> getMessageEntities(MessageKey key){
		MessageEntity example=new MessageEntity();
		example.setMessageKey(key);
		List<MessageEntity> messages=messageRepository.findAll(Example.of(example));
		return messages;
	}

	
}
