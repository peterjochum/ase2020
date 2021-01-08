package org.steambuddy.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.steambuddy.api.dto.GroupDTO;
import org.steambuddy.app.entity.GroupEntity;
import org.steambuddy.app.entity.UserEntity;
import org.steambuddy.app.mapper.GroupMapper;
import org.steambuddy.app.repository.GroupRepository;
import org.steambuddy.app.repository.UserRepository;

@Service
@Transactional
public class GroupServiceImpl implements GroupService {

	@Autowired
	private GroupRepository groupRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private GroupMapper mapper;
	
	@Override
	public List<GroupDTO> getGroups() {
		return mapper.mapEntityToDTO((List<GroupEntity>) groupRepository.findAll());
	}

	@Override
	public GroupDTO createGroup(GroupDTO group) {
		if (groupRepository.findByName(group.getName()) != null) {
			return null;
		}
		GroupEntity toCreate = mapper.dtoToEntity(group);
		return mapper.entityToDTO(groupRepository.save(toCreate));
	}

	@Override
	public void deleteGroup(Long id) {
		GroupEntity toDelete = groupRepository.findById(id).get();
		List<UserEntity> groupMembers = userRepository.findGroupMembers(id);
		groupMembers.forEach(m -> m.getGroups().remove(toDelete));
		groupRepository.deleteById(id);
	}

	@Override
	public List<GroupDTO> getGroupsByUserId(Long user_id) {
		List<GroupEntity> groups = groupRepository.findGroupByUserId(user_id);
		if (groups == null) {
			return null;
		}
		return mapper.mapEntityToDTO(groups);
	}

}
