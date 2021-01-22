package org.steambuddy.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.steambuddy.api.dto.GameCollectionDTO;
import org.steambuddy.api.dto.GameDTO;
import org.steambuddy.app.entity.GameCollectionEntity;
import org.steambuddy.app.entity.GameEntity;
import org.steambuddy.app.entity.UserEntity;
import org.steambuddy.app.mapper.GameCollectionMapper;
import org.steambuddy.app.mapper.GameMapper;
import org.steambuddy.app.repository.GameCollectionRepository;
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
	private GameCollectionRepository gameCollectionRepository;

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
		Optional<GameEntity> optGame = gameRepository.findById(id);
		if (optGame.isPresent())
			return mapper.entityToDTO(optGame.get());
		return null;
	}

	@Override
	public GameCollectionDTO getGameCollectionByUserId(Long userId) {
		GameCollectionEntity entitiy = getGameCollectionEntityByUserId(userId);
		if (entitiy != null)
			return gameCollectionMapper.entityToDTO(entitiy);
		return null;
	}

	private GameCollectionEntity getGameCollectionEntityByUserId(Long userId) {
		Optional<UserEntity> optUser = userRepository.findById(userId);
		if (optUser.isPresent())
			return optUser.get().getGameCollection();
		return null;
	}

	@Override
	public GameCollectionDTO addGameToGameCollection(Long userId, Long gameId) {
		GameCollectionEntity gameCollection = getGameCollectionEntityByUserId(userId);
		Optional<GameEntity> gameToAdd = gameRepository.findById(gameId);
		if (gameToAdd.isPresent()) {
			gameCollection.addGame(gameToAdd.get());
			gameCollectionMapper.entityToDTO(gameCollection);
			gameCollection = gameCollectionRepository.save(gameCollection);
		}
		return gameCollectionMapper.entityToDTO(gameCollection);
	}
	
	@Override
	public GameCollectionDTO removeGameFromGameCollection(Long userId, Long gameId) {
		GameCollectionEntity gameCollection = getGameCollectionEntityByUserId(userId);
		Optional<GameEntity> gameToRemove = gameRepository.findById(gameId);
		if (gameToRemove.isPresent()) {
			gameCollection.removeGame(gameToRemove.get());
			gameCollectionMapper.entityToDTO(gameCollection);
			gameCollection = gameCollectionRepository.save(gameCollection);
		}
		return gameCollectionMapper.entityToDTO(gameCollection);
	}

}
