package org.steambuddy.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.steambuddy.api.GroupResource;
import org.steambuddy.api.dto.GroupDTO;
import org.steambuddy.app.service.GroupService;

@RestController
public class GroupController implements GroupResource{

	@Autowired
	private GroupService groupService;
	
	@Override
	public List<GroupDTO> getGroups() {
		return groupService.getGroups();
	}

	@Override
	public GroupDTO createGroup(GroupDTO group) {
		return groupService.createGroup(group);
	}

	@Override
	public void deleteGroup(Long id) {
		groupService.deleteGroup(id);
	}

	@Override
	public List<GroupDTO> getGroupsByUserId(Long user_id) {
		return groupService.getGroupsByUserId(user_id);
	}

}
