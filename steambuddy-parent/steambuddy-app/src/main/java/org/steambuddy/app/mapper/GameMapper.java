package org.steambuddy.app.mapper;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.steambuddy.api.dto.GameDTO;
import org.steambuddy.app.entity.GameEntity;

@Service
public class GameMapper {
	
	@Autowired
	private GenreMapper genreMapper;
	
	public List<GameDTO> mapEntityToDTO(Collection<GameEntity> from) {
		return from.stream().map(g -> entityToDTO(g)).collect(Collectors.toList());
	}
	
	public GameDTO entityToDTO(GameEntity from) {
		GameDTO to = new GameDTO();
		to.setId(from.getId());
		to.setImage(from.getImage());
		to.setName(from.getName());
		to.setText(from.getText());
		to.setYear(from.getYear());
		to.setGenres(genreMapper.mapEntityToDTO(from.getGenres()));
		return to;
	}
	
}
