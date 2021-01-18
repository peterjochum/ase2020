package org.steambuddy.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.steambuddy.api.dto.GameCollectionDTO;
import org.steambuddy.api.dto.GameDTO;
import org.steambuddy.app.entity.GameEntity;
import org.steambuddy.app.entity.UserEntity;
import org.steambuddy.app.mapper.GameCollectionMapper;
import org.steambuddy.app.mapper.GameMapper;
import org.steambuddy.app.repository.GameRepository;
import org.steambuddy.app.repository.UserRepository;

@Service
@Transactional
public class GameServiceImpl implements GameService {

	@Autowired
	private GameRepository gameRepository;

	@Autowired 
	private UserRepository userRepository;
	
	@Autowired
	private GameMapper mapper;

	@Autowired
	private GameCollectionMapper gameCollectionMapper;
	
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
	public GameCollectionDTO getGameCollection(Long userId) {
		Optional<UserEntity> optUser= userRepository.findById(userId);
		if (optUser.isPresent())
			return gameCollectionMapper.entityToDTO(optUser.get().getGameCollection());
		return null;
	}

	@Override
	public GameCollectionDTO addGameToGameCollection(Long gameCollection, Long gameId) {
		// TODO Auto-generated method stub
		return null;
	}

}
