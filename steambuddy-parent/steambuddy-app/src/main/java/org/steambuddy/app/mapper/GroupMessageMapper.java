package org.steambuddy.app.mapper;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.steambuddy.api.dto.GameDTO;
import org.steambuddy.api.dto.GroupMessageDTO;
import org.steambuddy.api.dto.MessageDTO;
import org.steambuddy.api.dto.RatingDTO;
import org.steambuddy.app.entity.GameEntity;
import org.steambuddy.app.entity.GroupMessageEntity;
import org.steambuddy.app.entity.MessageEntity;
import org.steambuddy.app.entity.RatingEntity;

@Service
public class GroupMessageMapper {
	
	public List<GroupMessageDTO> mapEntityToDTO(Collection<GroupMessageEntity> from) {
		return from.stream().map(g -> entityToDTO(g)).collect(Collectors.toList());
	}
	
	public GroupMessageDTO entityToDTO(GroupMessageEntity from) {
		GroupMessageDTO to = new GroupMessageDTO();
		to.setGroupId(from.getGroupMessageKey().getGroupId());
		to.setUserId(from.getGroupMessageKey().getUserId());
		to.setMessage(from.getMessage());
		to.setTimeStamp(from.getGroupMessageKey().getTimeStamp());
		return to;
	}
	
	}