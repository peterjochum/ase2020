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
		if (from.getFriends() != null && !from.getFriends().isEmpty()) {
			from.setFriends(from.getFriends().stream().map(friend -> ignoreFriends(friend)).collect(Collectors.toSet()));
			to.setFriends(mapEntityToDTO(from.getFriends()));
		}
		if (from.getGroups() != null && !from.getGroups().isEmpty()) {
			to.setGroups(groupMapper.mapEntityToDTO(from.getGroups()));
		}
		return to;
	}
	
	public UserEntity dtoToEntity(UserDTO from) {
		UserEntity to = new UserEntity();
		to.setId(from.getId());
		to.setName(from.getName());
		to.setPassword(from.getPassword());
		if (from.getFriends() != null && !from.getFriends().isEmpty()) {
			to.setFriends(mapDTOtoEntity(from.getFriends()));
		}
		if (from.getGroups() != null && !from.getGroups().isEmpty()) {
			to.setGroups(groupMapper.mapDTOtoEntity(from.getGroups()));
		}
		return to;
	}
	
	public UserEntity updateEntity(UserEntity toUpdate, UserDTO from) {
		toUpdate.setName(from.getName());
		if (from.getFriends() != null && !from.getFriends().isEmpty()) {
			toUpdate.setFriends(toUpdate.getFriends().stream().map(friend -> ignoreFriends(friend)).collect(Collectors.toSet()));
			toUpdate.setFriends(mapDTOtoEntity(from.getFriends()));
		} else {
			toUpdate.setFriends(null);
		}
		if (from.getGroups() != null && !from.getGroups().isEmpty()) {
			toUpdate.setGroups(groupMapper.mapDTOtoEntity(from.getGroups()));
		} else {
			toUpdate.setGroups(null);
		}
		return toUpdate;
	}
	
	private UserEntity ignoreFriends(UserEntity user) {
		UserEntity temp = new UserEntity();
		temp.setId(user.getId());
		temp.setName(user.getName());
		temp.setFriends(null);
		temp.setGroups(user.getGroups());
		return temp;
	}
	
}
