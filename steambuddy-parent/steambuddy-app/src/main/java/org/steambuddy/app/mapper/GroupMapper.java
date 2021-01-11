package org.steambuddy.app.mapper;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.steambuddy.api.dto.GroupDTO;
import org.steambuddy.app.entity.GroupEntity;
import org.steambuddy.app.entity.UserEntity;

@Service
public class GroupMapper {

	@Autowired
	private UserMapper userMapper;
	
	public List<GroupDTO> mapEntityToDTO(List<GroupEntity> from) {
		return from.stream().map(g -> entityToDTO(g)).collect(Collectors.toList());
	}
	
	public Set<GroupDTO> mapEntityToDTO(Set<GroupEntity> from) {
		return from.stream().map(g -> entityToDTO(g)).collect(Collectors.toSet());
	}
	
	public Set<GroupEntity> mapDTOtoEntity(Set<GroupDTO> from) {
		return from.stream().map(g -> dtoToEntity(g)).collect(Collectors.toSet());
	}
	
	public GroupDTO entityToDTO(GroupEntity from) {
		GroupDTO to = new GroupDTO();
		to.setId(from.getId());
		to.setName(from.getName());
		to.setDescription(from.getDescription());
		UserEntity owner = ignoreFriendsAndGroups(from.getOwner());
		to.setOwner(userMapper.entityToDTO(owner));
		return to;
	}
	
	public GroupEntity dtoToEntity(GroupDTO from) {
		GroupEntity to = new GroupEntity();
		to.setId(from.getId());
		to.setName(from.getName());
		to.setDescription(from.getDescription());
		to.setOwner(userMapper.dtoToEntity(from.getOwner()));
		return to;
	}
	
	private UserEntity ignoreFriendsAndGroups(UserEntity user) {
		UserEntity temp = new UserEntity();
		temp.setId(user.getId());
		temp.setName(user.getName());
		temp.setFriends(user.getFriends());
		temp.setFriends(null);
		temp.setGroups(null);
		return temp;
	}
}
