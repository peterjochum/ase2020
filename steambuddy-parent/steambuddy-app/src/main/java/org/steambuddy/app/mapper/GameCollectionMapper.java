package org.steambuddy.app.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.steambuddy.api.dto.GameCollectionDTO;
import org.steambuddy.app.entity.GameCollectionEntity;

@Service
public class GameCollectionMapper {
	
	@Autowired
	private GameMapper gameMapper;
	
	public List<GameCollectionDTO> mapEntityToDTO(List<GameCollectionEntity> from) {
		return from.stream().map(g -> entityToDTO(g)).collect(Collectors.toList());
	}
	
	public GameCollectionDTO entityToDTO(GameCollectionEntity from) {
		GameCollectionDTO to = new GameCollectionDTO();
		to.setId(from.getId());
		to.setGames(gameMapper.mapEntityToDTO(from.getGames()));
		return to;
	}
	
}
