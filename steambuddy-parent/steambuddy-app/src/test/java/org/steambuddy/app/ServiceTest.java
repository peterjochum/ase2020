package org.steambuddy.app;

import static org.junit.Assert.assertTrue;

import java.awt.print.Printable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.steambuddy.api.dto.GameDTO;
import org.steambuddy.api.dto.GroupDTO;
import org.steambuddy.api.dto.UserDTO;
import org.steambuddy.app.controller.GameController;
import org.steambuddy.app.entity.GameEntity;
import org.steambuddy.app.repository.GameRepository;
import org.steambuddy.app.service.GameService;
import org.steambuddy.app.service.GameServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Unit test for simple App.
 */
//@RunWith(SpringRunner.class)
//@SpringBootTest
//Only when we need the full application @SpringBootTest

@RunWith(SpringRunner.class)
@SpringBootTest
//@DataJpaTest
//@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
public class ServiceTest 
{
	/* @TestConfiguration
	    static class GameServiceImplTestContextConfiguration {
	 
	        @Bean
	        public GameService gameService() {
	            return new GameServiceImpl();
	        }
	        
	    }
*/
	@Autowired 
	private GameController gameController;
	
	//@Autowired 
	//private GameService gameService;
	
	//@MockBean -> Create Fake Bean, that where we can define custom return values for functions
	//more at this link: https://www.baeldung.com/spring-boot-testing
	
	//@MockBean
	//private GameRepository gameRepository;
	
	
	//@Before
	//public void setUp() {
	//	Mockito.when(gameRepository.findByName("Quake")).thenReturn(new ArrayList<GameEntity>());
	//}
	

	
	
//	@Test
//    public void getAllGames()
//    {
//		
//    	List<GameDTO> entities=gameController.getGames();
//    	System.out.println("EntitiesFound: " + entities.size());
//        assertTrue( true );
//    }
	
	
	
	@Test
    public void getGameSuggestions()
    {
		
    	List<GameDTO> entities=gameController.getGameSuggestions(1L);
    	
    	for (GameDTO game: entities) {
    		System.out.println("Game " + game.getName() + ", description: " + game.getText());
    	}
    	
        assertTrue( true );
    }
}
