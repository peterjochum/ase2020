package org.steambuddy.app.mapper;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.steambuddy.api.dto.GameDTO;
import org.steambuddy.api.dto.MessageDTO;
import org.steambuddy.api.dto.RatingDTO;
import org.steambuddy.app.entity.GameEntity;
import org.steambuddy.app.entity.MessageEntity;
import org.steambuddy.app.entity.RatingEntity;

@Service
public class MessageMapper {
	
	public List<MessageDTO> mapEntityToDTO(Collection<MessageEntity> from) {
		return from.stream().map(g -> entityToDTO(g)).collect(Collectors.toList());
	}
	
	public MessageDTO entityToDTO(MessageEntity from) {
		MessageDTO to = new MessageDTO();
		to.setFromId(from.getMessageKey().getFromId());
		to.setToId(from.getMessageKey().getToId());
		to.setMessage(from.getMessage());
		to.setTimeStamp(from.getMessageKey().getTimeStamp());
		return to;
	}
	
	}