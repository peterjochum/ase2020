package org.steambuddy.app.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.steambuddy.api.dto.GameDTO;
import org.steambuddy.api.dto.UserDTO;
import org.steambuddy.app.entity.GameEntity;
import org.steambuddy.app.entity.GenreEntity;
import org.steambuddy.app.entity.UserEntity;
import org.steambuddy.app.mapper.GameMapper;
import org.steambuddy.app.repository.GameRepository;

@Service
@Transactional
public class GameServiceImpl implements GameService {

	@Autowired
	private GameRepository gameRepository;

	@Autowired
	private GameMapper mapper;

	@Override
	public List<GameDTO> getGames() {
		return mapper.mapEntityToDTO((List<GameEntity>) gameRepository.findAll());
	}

	@Override
	public List<GameDTO> getGames(String name) {
		return mapper.mapEntityToDTO(gameRepository.findByName(name));
	}

	@Override
	public GameDTO getGame(Long id) {
		Optional<GameEntity> optGame= gameRepository.findById(id);
		if (optGame.isPresent())
			return mapper.entityToDTO(optGame.get());
		return null;
	}
	
	@Override
	public List<GameDTO> getGameSuggestions(UserDTO user) {
		//get user
		UserEntity userE=new UserEntity();
		
		//get games of user
		//get game categories of these games
		Set<GenreEntity> userGenres=new HashSet<GenreEntity>();
		//find games with these categories
		
		//gameRepository.findByCategory(new HashSet<GenreEntity>()
		//returns candidates
		List<GameEntity> candidates=new ArrayList<GameEntity>();
		

		//get friends
		Set<UserEntity> friends=userE.getFriends();
		
		
		
		
		
		
		Set<GenreEntity> genres;
		
		//Map<long,>
		for(GameEntity candidate: candidates) {
			genres=candidate.getGenres();
			
		}
		
		
		
		return mapper.mapEntityToDTO(gameRepository.findByCategory(new HashSet<GenreEntity>()));
	}

}
