package org.steambuddy.app.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.steambuddy.api.dto.GameDTO;
import org.steambuddy.app.entity.GameEntity;

@Service
public class GameMapper {
	
	public List<GameDTO> mapEntityToDTO(List<GameEntity> from) {
		return from.stream().map(g -> entityToDTO(g)).collect(Collectors.toList());
	}
	
	private GameDTO entityToDTO(GameEntity from) {
		GameDTO to = new GameDTO();
		to.setId(from.getId());
		to.setImage(from.getImage());
		to.setName(from.getName());
		to.setText(from.getText());
		to.setYear(from.getYear());
		return to;
	}
	
}
