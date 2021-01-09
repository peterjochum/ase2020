package org.steambuddy.app.service;

import java.util.List;

import org.steambuddy.api.dto.GroupDTO;

public interface GroupService {

	List<GroupDTO> getGroups();
	
	GroupDTO createGroup(GroupDTO group);
	
	void deleteGroup(Long id);
	
	List<GroupDTO> getGroupsByUserId(Long user_id);
	
}
