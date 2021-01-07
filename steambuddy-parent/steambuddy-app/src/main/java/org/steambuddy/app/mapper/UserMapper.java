package org.steambuddy.app.mapper;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.steambuddy.api.dto.UserDTO;
import org.steambuddy.app.entity.UserEntity;

@Service
public class UserMapper {

	@Autowired
	private GroupMapper groupMapper;
	
	public List<UserDTO> mapEntityToDTO(List<UserEntity> from) {
		return from.stream().map(g -> entityToDTO(g)).collect(Collectors.toList());
	}
	
	public Set<UserDTO> mapEntityToDTO(Set<UserEntity> from) {
		return from.stream().map(g -> entityToDTO(g)).collect(Collectors.toSet());
	}
	
	public Set<UserEntity> mapDTOtoEntity(Set<UserDTO> from) {
		return from.stream().map(g -> dtoToEntity(g)).collect(Collectors.toSet());
	}
	
	public UserDTO entityToDTO(UserEntity from) {
		UserDTO to = new UserDTO();
		to.setId(from.getId());
		to.setName(from.getName());
		if (from.getFriends() != null) {
			to.setFriends(mapEntityToDTO(from.getFriends()));
		}
		if (from.getGroups() != null) {
			to.setGroups(groupMapper.mapEntityToDTO(from.getGroups()));
		}
		return to;
	}
	
	public UserEntity dtoToEntity(UserDTO from) {
		UserEntity to = new UserEntity();
		to.setId(from.getId());
		to.setName(from.getName());
		to.setPassword(from.getPassword());
		if (from.getFriends() != null) {
			to.setFriends(mapDTOtoEntity(from.getFriends()));
		}
		if (from.getGroups() != null) {
			to.setGroups(groupMapper.mapDTOtoEntity(from.getGroups()));
		}
		return to;
	}
	
	public UserEntity updateEntity(UserEntity toUpdate, UserDTO from) {
		toUpdate.setName(from.getName());
		if (from.getFriends() != null) {
			toUpdate.setFriends(mapDTOtoEntity(from.getFriends()));
		}
		if (from.getGroups() != null) {
			toUpdate.setGroups(groupMapper.mapDTOtoEntity(from.getGroups()));
		}
		return toUpdate;
	}
	
}
