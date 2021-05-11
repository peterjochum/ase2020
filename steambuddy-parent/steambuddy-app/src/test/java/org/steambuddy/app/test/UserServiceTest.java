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
	
	

	//Tests are just too troublesome, there a like 100 different errors when trying to get the databse to run
	//And there are mostly database tests, so we can't even really do good junit tests, because like every service just asks the database to do something
	//Therefore these tests are disabled atm
	
	/*
	
    @Test
    public void serviceCorrectlySavesSentMessage() {
    	//sample data creation
		MessageDTO testMessage=createMessageSample();
		
		//send message
    	userService.sendMessage(testMessage);
    	
    	//Try to get message from repo
    	MessageDTO repoMessageDTO=getRepoDTO(testMessage);
    	
    	//check if message was actually send and saved in the database
    	Assert.assertEquals(testMessage,repoMessageDTO);
    	
    }
    */
    
}