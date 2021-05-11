package org.steambuddy.app.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.steambuddy.api.dto.MessageDTO;
import org.steambuddy.app.compositekeys.GameRatingKey;
import org.steambuddy.app.compositekeys.MessageKey;
import org.steambuddy.app.entity.MessageEntity;
import org.steambuddy.app.entity.RatingEntity;
import org.steambuddy.app.mapper.MessageMapper;
import org.steambuddy.app.mapper.UserMapper;
import org.steambuddy.app.repository.MessageRepository;
import org.steambuddy.app.repository.RatingRepository;
import org.steambuddy.app.repository.UserRepository;
import org.steambuddy.app.service.UserServiceImpl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertTrue;
//@DataJpaTest
@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace =  AutoConfigureTestDatabase.Replace.NONE)

public class UserServiceTest {
	/*
	 * It is not really possible to do unit tests, because most services are just database queries, so every test would have to use the actual database to make any sense but then it is not a unit test anymore
	 * 
	 * 
	 * 
	 * 
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private MessageRepository messageRepository;
	
	@Autowired
	private MessageMapper messageMapper;
	
	@Autowired
	private UserServiceImpl userService;
	

	
	public MessageDTO createMessageSample() {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		MessageDTO message=MessageDTO.getInstance(1L,2L,"TestMessage",timestamp.getTime());
		return message;
	}
	
	public MessageDTO getRepoDTO(MessageDTO message) {
		MessageEntity repoMessage=messageRepository.findById(new MessageKey(message.getFromId(),message.getToId(),message.getTimeStamp())).get();
    	MessageDTO repoMessageDTO=messageMapper.entityToDTO(repoMessage);
    	return repoMessageDTO;
	}
	
	*/
   
}


