package org.steambuddy.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.steambuddy.api.dto.GameDTO;
import org.steambuddy.app.entity.GameEntity;
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
		List<GameEntity> games = new ArrayList<GameEntity>();

		// TODO: call Repository
		games = (List<GameEntity>) gameRepository.findAll();

		games.add(new GameEntity("Cyberpunk 2077", 2020,
				"https://images.igdb.com/igdb/image/upload/t_cover_big/co2mjs.jpg",
				"Cyberpunk 2077 is a role-playing video game developed and published by CD Projekt. Adapted from the Cyberpunk franchise, the game is an open world, non-linear RPG with an FPS style in which players are able to heavily customize their character to suit their play style. Gun play, exploration, player choice and activities such as hacking are to feature heavily throughout the game with missions, quests and objectives being completed in a variety of different ways. The world will have dynamic weather and a day/night cycle to make it truly immersive."));
		games.add(new GameEntity("The Witcher 3: Wild Hunt", 2015,
				"https://images.igdb.com/igdb/image/upload/t_cover_big/co1wyy.jpg",
				"RPG and sequel to The Witcher 2 (2011), The Witcher 3 follows witcher Geralt of Rivia as he seeks out his former lover and his young subject while intermingling with the political workings of the wartorn Northern Kingdoms. Geralt has to fight monsters and deal with people of all sorts in order to solve complex problems and settle contentious disputes, each ranging from the personal to the world-changing."));
		games.add(new GameEntity("The Elderscrolls VI", 2220,
				"https://images.igdb.com/igdb/image/upload/t_cover_big/co1ycv.jpg",
				"The long awaited next installment in the Elder Scrolls franchise."));
		return mapper.mapEntityToDTO(games);
	}

	@Override
	public List<GameDTO> getGames(String name) {
		return mapper.mapEntityToDTO(gameRepository.findByName(name));
	}

}
