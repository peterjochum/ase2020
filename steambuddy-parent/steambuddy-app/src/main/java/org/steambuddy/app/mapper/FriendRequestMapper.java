package org.steambuddy.app.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.steambuddy.api.dto.FriendRequestDTO;
import org.steambuddy.app.entity.FriendRequestEntity;

@Service
public class FriendRequestMapper {

	@Autowired
	private UserMapper userMapper;
	
	public List<FriendRequestDTO> mapEntityToDTO(List<FriendRequestEntity> from) {
		return from.stream().map(fr -> entityToDTO(fr)).collect(Collectors.toList());
	}
	
	public FriendRequestDTO entityToDTO(FriendRequestEntity from) {
		FriendRequestDTO to = new FriendRequestDTO();
		to.setId(from.getId());
		to.setAccepted(from.getAccepted());
		to.setTimestamp(from.getTimestamp());
		to.setSender(userMapper.entityToDTO(from.getSender()));
		to.setReceiver(userMapper.entityToDTO(from.getReceiver()));
		return to;
	}
}
