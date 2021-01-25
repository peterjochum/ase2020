package org.steambuddy.app.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.steambuddy.api.dto.GameCollectionDTO;
import org.steambuddy.api.dto.GameDTO;
import org.steambuddy.api.dto.UserDTO;
import org.steambuddy.app.entity.GameEntity;
import org.steambuddy.app.entity.GenreEntity;
import org.steambuddy.app.entity.UserEntity;

import org.steambuddy.app.entity.GameCollectionEntity;
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
	public List<GameDTO> getGameSuggestions(Long id) {
		//get user
		
		
		//-> Get nullpointer exeption, why? GameCollectionEntity collection=getGameCollectionEntityByUserId(id);
		
		//quickfix, manual copying, fix problem later
		Optional<UserEntity> optUser = userRepository.findById(id);
		GameCollectionEntity collection=new GameCollectionEntity();
		if (optUser.isPresent())
			collection= optUser.get().getGameCollection();
		else 
			System.out.println("User not found!");
		
		Set<GameEntity> userGames=collection.getGames();
		
		System.out.println("Games " + userGames.size());
		
		
		//get preferred changes based on user collection
		SortedMap<Long,Long> genrePrefs=new TreeMap<Long,Long>();
		long genreID;
		for (GameEntity game: userGames) {
			Set<GenreEntity> genres=game.getGenres();
			for (GenreEntity genre: genres) {
				genreID=genre.getId();
				if(!genrePrefs.containsKey(genreID)) 
					genrePrefs.put(genreID,1L);
				else 
					genrePrefs.put(genreID,genrePrefs.get(genreID)+1L);
						
			}
		}
		

		System.out.println("Genreprefs " + userGames.size());
		
		List<Long> bestGenres=new LinkedList<Long>();
		int genreCount=0;
		long worstGenreCount=0;

		

		//find 5 most common genres of user
		for (long genre: genrePrefs.keySet()) {
			if(genreCount<5) {
				bestGenres.add(genre);
				genreCount++;
			}
			else {
				
			}
			if(genrePrefs.get(genre)>worstGenreCount) {//if genre is more common, remove currently least common genre and add new one instead
				for (long genre2: bestGenres) {
					if(genrePrefs.get(genre2)==worstGenreCount) {
						bestGenres.remove(genre2);
						bestGenres.add(genre);
						break;
					}
					
				}
				worstGenreCount=Long.MAX_VALUE;//set new worst value
				for (long genre2: bestGenres) {
					if(genrePrefs.get(genre2)<worstGenreCount) {
						worstGenreCount=genrePrefs.get(genre2);
					}
				}
				
			}
			
		}
		
		
		//debug
		if(bestGenres.isEmpty()) {
			System.out.println("No games with genres found!");
			return null;
		}
		
		
		
		return mapper.mapEntityToDTO(gameRepository.findByCategory(bestGenres));
		
		/*
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
	*/
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
