package org.steambuddy.app.mapper;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.steambuddy.api.dto.GameDTO;
import org.steambuddy.api.dto.RatingDTO;
import org.steambuddy.app.entity.GameEntity;
import org.steambuddy.app.entity.RatingEntity;

@Service
public class RatingMapper {
	
	public List<RatingDTO> mapEntityToDTO(Collection<RatingEntity> from) {
		return from.stream().map(g -> entityToDTO(g)).collect(Collectors.toList());
	}
	
	public RatingDTO entityToDTO(RatingEntity from) {
		RatingDTO to = new RatingDTO();
		to.setGameId(from.getRatingKey().getGameId());
		to.setUserId(from.getRatingKey().getUserId());
		to.setRating(from.getRating());
		to.setRatingText(from.getRatingText());
		return to;
	}
	
	}