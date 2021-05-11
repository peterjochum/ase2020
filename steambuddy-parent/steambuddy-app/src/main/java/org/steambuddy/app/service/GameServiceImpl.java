package org.steambuddy.app.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

import javax.persistence.Tuple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.steambuddy.api.dto.GameCollectionDTO;
import org.steambuddy.api.dto.GameDTO;
import org.steambuddy.api.dto.RatingDTO;
import org.steambuddy.app.compositekeys.GameRatingKey;
import org.steambuddy.app.entity.GameCollectionEntity;
import org.steambuddy.app.entity.GameEntity;
import org.steambuddy.app.entity.RatingEntity;
import org.steambuddy.app.entity.UserEntity;
import org.steambuddy.app.mapper.GameCollectionMapper;
import org.steambuddy.app.mapper.GameMapper;
import org.steambuddy.app.mapper.RatingMapper;
import org.steambuddy.app.repository.GameCollectionRepository;
import org.steambuddy.app.repository.GameRepository;
import org.steambuddy.app.repository.RatingRepository;
import org.steambuddy.app.repository.UserRepository;

@Service
@Transactional
public class GameServiceImpl implements GameService {

	@Autowired
	private GameRepository gameRepository;
	
	@Autowired
	private RatingRepository ratingRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private GameCollectionRepository gameCollectionRepository;

	@Autowired
	private GameMapper mapper;
	
	@Autowired
	private RatingMapper ratingMapper;

	@Autowired
	private GameCollectionMapper gameCollectionMapper;

	@Override
	public List<GameDTO> getGames(Pageable pageable) {
		return mapper.mapEntityToDTO((List<GameEntity>) gameRepository.findAll(pageable).getContent());
	}

	@Override
	public List<GameDTO> getGames(String name, Pageable pageable) {
		return mapper.mapEntityToDTO(gameRepository.findByName(name,pageable));
	}

	@Override
	public GameDTO getGame(Long id) {
		Optional<GameEntity> optGame = gameRepository.findById(id);
		if (optGame.isPresent())
			return mapper.entityToDTO(optGame.get());
		return null;
	}

	
	@Override
	public RatingDTO addRating(RatingDTO rating) {
        GameRatingKey key = new GameRatingKey(rating.getGameId(),rating.getUserId());
        RatingEntity ratingE = new RatingEntity(key,rating.getRating(),rating.getRatingText());
        ratingRepository.save(ratingE);
        
		return ratingMapper.entityToDTO(ratingE);  
	}
	
	@Override
	public RatingDTO removeRating(Long id,Long gameId) {
        GameRatingKey key = new GameRatingKey(gameId,id);
        RatingEntity ratingE=ratingRepository.findById(key).get(); //we store the variable here to return it back, because the delete function only returns void
        
        ratingRepository.delete(ratingE);
        
		return ratingMapper.entityToDTO(ratingE);  
	}	
	
	@Override
	public List<GameDTO> getGameSuggestionsByNeighbourhood(Long id) {

		UserEntity user;
		try {
			user = userRepository.findById(id).get(); 
		}
		catch (NoSuchElementException e){
			System.out.println("User not found!");
			return new ArrayList<GameDTO>();
		}
		
		// get collection of user
		GameCollectionEntity collection = getGameCollectionEntityByUserId(id);
		
		Set<UserEntity> friends = user.getFriends();

		HashMap<Long, Integer> gameRating = new HashMap<Long, Integer>();

		Set<GameCollectionEntity> friendCols = new HashSet<GameCollectionEntity>();
		
		List<Long> friendIds=new ArrayList<Long>();
		Long friendId;
		
		List<Tuple> userRatingsT=ratingRepository.getGameRatingsForUser(id);
		Map<Long,Long> userRatings=new HashMap<Long,Long>();
		for (Tuple t: userRatingsT) {
			userRatings.put(Long.parseLong(t.get(0).toString()),Long.parseLong(t.get(1).toString()));
		}
		
		//userRatings=ratingRepository.getGameRatingsForUser(id);
		
		List<Tuple> friendRatings=new ArrayList<Tuple>();
		
		boolean similaritiesFound; //if we want to be more restrictive, we would not only watch for 1 match but like 5 game_ids in common
		Map<Long,Double> distance=new HashMap<Long,Double>();
		Long friendRating;
		Long curGameKey;
		Double curDistance;
		
		List<Long> friendsForAlgo=new ArrayList<Long>();
		
		for (UserEntity friend : friends) { //do the euclid distance
			similaritiesFound=false;
			friendId=friend.getId();
			friendRatings=ratingRepository.getGameRatingsForUser(friendId);
			distance.put(friendId,0D);
			for (Tuple t: friendRatings) { 
				curGameKey=Long.parseLong(t.get(0).toString());
				if(userRatings.containsKey(curGameKey)) {
					similaritiesFound=true;
					friendRating=Long.parseLong(t.get(1).toString());
					curDistance=Math.pow((userRatings.get(curGameKey)-friendRating),2);
					distance.put(friendId,distance.get(friendId)+curDistance);
				}
			}
			distance.put(friendId,Math.sqrt(distance.get(friendId)));
			
			if(similaritiesFound) {
				friendsForAlgo.add(friendId);
			}
		}
		
		//Find k nearest neighbours, we just take 1 and only look at our friends
		
		Double smallestDistance=Double.MAX_VALUE;
		Long bestFriendForAlgo=1L;
		for (Long fittingFriend: friendsForAlgo) {
			curDistance=distance.get(fittingFriend);
			if(curDistance<smallestDistance) {
				smallestDistance=curDistance;
				bestFriendForAlgo=fittingFriend;
			}
		}
		
		List<GameEntity> suggestions=new ArrayList<GameEntity>();
		
		int maxSuggestions=10;
		int curSuggestion=1;
		GameCollectionEntity bestFriendGameCol=getGameCollectionEntityByUserId(bestFriendForAlgo);
		for(GameEntity game: bestFriendGameCol.getGames()) {
			if(!collection.containsGame(game)) {
				if(curSuggestion<=maxSuggestions) {
					suggestions.add(game);
					curSuggestion++;
				}
			}
		}
		

		
		return mapper.mapEntityToDTO(suggestions);
		

	}
	

	@Override
	public List<GameDTO> getGameSuggestionsByRatings(Long id) {

		// get collection of user
		GameCollectionEntity collection = getGameCollectionEntityByUserId(id);
		if (collection == null) {
			System.out.println("User not found!");
			return new ArrayList<GameDTO>();
		}
		
		
		List<Tuple> tupleRatings=ratingRepository.getGameToRatingAggregation();
		
		List<GameEntity> suggestions=new ArrayList<GameEntity>();
		
		
		
		Long suggestedGameId;
		GameEntity suggestion;
		for (int i=0; i<10;i++) {
			
			if(i>=tupleRatings.size()) {//if there are too few suggestions, break
				break;
			}
			
			suggestedGameId=Long.parseLong(tupleRatings.get(0).get(0).toString());
			suggestion=gameRepository.findById(suggestedGameId).get();
			
			if(!collection.containsGame(suggestion)) { //only add if the user doesnt already own it
				suggestions.add(suggestion);
			}

			
		}
		
		return mapper.mapEntityToDTO(suggestions);
		

	}
	
	@Override
	public List<GameDTO> getGameSuggestionsByGenres(Long id) {


		// get collection of user
		GameCollectionEntity collection = getGameCollectionEntityByUserId(id);
		if (collection == null) {
			System.out.println("User not found!");
			return new ArrayList<GameDTO>();
		}

		// get top genres of user
		List<Long> bestGenres = collection.getBestGenres();

		if (bestGenres.isEmpty()) {
			// no best genres = no games in library
			System.out.println("No games in user library!");
			return new ArrayList<GameDTO>();
		}


		
		// Find a certain number of games with the specified categories (at least one in
		// common)
		List<GameEntity> allSuggestions = gameRepository.findByCategoryRandomly(bestGenres);
		System.out.println("Number of suggestions: " + allSuggestions.size());
		// map gameids to gameEntities
		HashMap<Long, GameEntity> gameIdToGame = new HashMap<Long, GameEntity>();
		for (GameEntity game : allSuggestions)
			gameIdToGame.put(game.getId(), game);

		List<Entry<Long, Integer>> gameRatings = getGameRatings(id, allSuggestions);

		// get the suggested games based on its ratings and the number of games that we
		// want
		List<GameEntity> result = getBestGameSuggestions(gameIdToGame, gameRatings, 10,collection);
		System.out.println("Number of suggestions: " + result.size());
		// map to output
		return mapper.mapEntityToDTO(result);

	}

	private List<GameEntity> getBestGameSuggestions(HashMap<Long, GameEntity> gameIdToGame,
			List<Entry<Long, Integer>> gameRatings, int numberOfSuggestions,GameCollectionEntity collection) {

		List<GameEntity> bestGames = new ArrayList<GameEntity>();
		GameEntity suggestion;
		for (Entry<Long, Integer> gameCount : gameRatings) {
			if (numberOfSuggestions > 0) {
				suggestion=gameIdToGame.get(gameCount.getKey());
				if(!collection.containsGame(suggestion)){
					bestGames.add(suggestion);
					numberOfSuggestions--;
				}

			} else {
				break;
			}

		}

		return bestGames;

	}

	private List<Entry<Long, Integer>> getGameRatings(long userId, List<GameEntity> allSuggestions) {
		UserEntity user;
		try {
			user = userRepository.findById(userId).get(); 
		}
		catch (NoSuchElementException e){
			System.out.println("User not found!");
			return new ArrayList<Entry<Long, Integer>>();
		}
		
		Set<UserEntity> friends = user.getFriends();

		HashMap<Long, Integer> gameRating = new HashMap<Long, Integer>();

		Set<GameCollectionEntity> friendCols = new HashSet<GameCollectionEntity>();
		for (UserEntity friend : friends) {
			friendCols.add(friend.getGameCollection());
		}

		// count how many friends have this game too
		long curID;
		//HashMap<Long, GameEntity> gameIdToGame = new HashMap<Long, GameEntity>();

		for (GameEntity game : allSuggestions) {
			curID = game.getId();
			gameRating.put(curID, 0);

			for (GameCollectionEntity friendCol : friendCols) {
				if (friendCol.containsGame(game)) {
					gameRating.put(curID, gameRating.get(curID) + 1);
				}
			}

		}
		// add comparator for sorting
		Comparator<Entry<Long, Integer>> valueComparator = new Comparator<Entry<Long, Integer>>() {

			@Override
			public int compare(Entry<Long, Integer> e1, Entry<Long, Integer> e2) {
				Integer v1 = e1.getValue();
				Integer v2 = e2.getValue();
				return -1 * v1.compareTo(v2);// descending order
			}
		};
		// sort
		List<Entry<Long, Integer>> sortedEntities = new ArrayList<Entry<Long, Integer>>(gameRating.entrySet());
		Collections.sort(sortedEntities, valueComparator);
		return sortedEntities;
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
