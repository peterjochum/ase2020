package org.steambuddy.app.mapper;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.steambuddy.api.dto.GenreDTO;
import org.steambuddy.app.entity.GenreEntity;

@Service
public class GenreMapper {

	@Autowired
	private GameMapper gameMapper;
	
	public List<GenreDTO> mapEntityToDTO(Collection<GenreEntity> from) {
		return from.stream().map(g -> entityToDTO(g, false)).collect(Collectors.toList());
	}
	
	public List<GenreDTO> mapEntityToDTOWithGames(Collection<GenreEntity> from) {
		return from.stream().map(g -> entityToDTO(g, true)).collect(Collectors.toList());
	}

	public GenreDTO entityToDTO(GenreEntity from, boolean withGames) {
		GenreDTO to = new GenreDTO();
		to.setId(from.getId());
		to.setName(from.getName());
		if(withGames)
			to.setGames(gameMapper.mapEntityToDTO(from.getGames()));
		return to;
	}

}
