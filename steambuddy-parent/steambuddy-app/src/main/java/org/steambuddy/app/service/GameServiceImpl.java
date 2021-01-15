package org.steambuddy.app.service;

import java.util.List;
import java.util.Optional;

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

}
