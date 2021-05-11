Test classes got removed due to 2 reasons:
-It is barely possible to make unit tests because most functions just use data base queries as an operation -> Might as well just test the database itself
-Integration tests or system tests (Like below) work in the backend itself, but the automated execution of tests can't handle the creation of the needed instances

Previous test examples:


package org.steambuddy.app.test;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.steambuddy.app.compositekeys.GameRatingKey;
import org.steambuddy.app.compositekeys.MessageKey;
import org.steambuddy.app.entity.MessageEntity;
import org.steambuddy.app.entity.RatingEntity;
import org.steambuddy.app.repository.MessageRepository;
import org.steambuddy.app.repository.RatingRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
//@DataJpaTest
@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace =  AutoConfigureTestDatabase.Replace.NONE)

public class RepoTest {
    @Autowired RatingRepository repository;
    @Autowired MessageRepository messageRepo;
    //@Autowired TestEntityManager testEntityManager;
    @Test
    public void ratingRepoCorrectlySavesRating() {
        GameRatingKey key = new GameRatingKey(10L,10L);
        RatingEntity rating = new RatingEntity(key,5L,"Test");
        rating=repository.save(rating);
        
        RatingEntity ratingFromR=repository.findById(key).get();
        assertTrue(key.equals(ratingFromR.getRatingKey()) && rating.getRating()==ratingFromR.getRating() && rating.getRatingText().equals(ratingFromR.getRatingText()));
    }
    
    @Test
    public void ratingRepoCorrectlyRemovesRating() {
        GameRatingKey key = new GameRatingKey(11L,11L);
        RatingEntity rating = new RatingEntity(key,5L,"Test");
        rating=repository.save(rating);
        
        RatingEntity ratingFromR=repository.findById(key).get();
        repository.delete(rating);
        Optional<RatingEntity> emptyRating=repository.findById(key);
        assertEquals(emptyRating,Optional.empty());
    }
    
    @Test
    public void messageRepoCorrectlySavesMessages() {
        MessageKey key = new MessageKey(10L,10L,1000L);
        MessageEntity message = new MessageEntity(key,"This is my message");
        message=messageRepo.save(message);
        
        MessageEntity messageFromR=messageRepo.findById(key).get();
        assertTrue(key.equals(messageFromR.getMessageKey()) && message.getMessage().equals(messageFromR.getMessage()));
    }    

    @Test
    public void messageRepoCorrectlyRemovesRating() {
        MessageKey key = new MessageKey(10L,10L,1000L);
        MessageEntity message = new MessageEntity(key,"This is my message");
        message=messageRepo.save(message);
        
        MessageEntity messageFromR=messageRepo.findById(key).get();
        assertTrue(key.equals(messageFromR.getMessageKey()) && message.getMessage().equals(messageFromR.getMessage()));
        
        messageRepo.delete(message);
        Optional<MessageEntity> emptyMessage=messageRepo.findById(key);
        assertEquals(emptyMessage,Optional.empty());
    }
    
}