package org.steambuddy.app.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.steambuddy.api.dto.PublisherDTO;
import org.steambuddy.app.entity.PublisherEntity;

@Service
public class PublisherMapper {

	public List<PublisherDTO> mapEntityToDTO(List<PublisherEntity> from) {
		return from.stream().map(g -> entityToDTO(g)).collect(Collectors.toList());
	}

	private PublisherDTO entityToDTO(PublisherEntity from) {
		PublisherDTO to = new PublisherDTO();
		to.setId(from.getId());
		to.setName(from.getName());
		return to;
	}

}
