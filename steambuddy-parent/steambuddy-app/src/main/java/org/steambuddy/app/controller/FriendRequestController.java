package org.steambuddy.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.steambuddy.api.FriendRequestResource;
import org.steambuddy.api.dto.FriendRequestDTO;
import org.steambuddy.app.service.FriendRequestService;

@RestController
public class FriendRequestController implements FriendRequestResource {

	@Autowired
	private FriendRequestService friendRequestService;
	
	@Override
	public List<FriendRequestDTO> getIncoming(Long id) {
		return friendRequestService.getIncoming(id);
	}

	@Override
	public List<FriendRequestDTO> getOutgoing(Long id) {
		return friendRequestService.getOutgoing(id);
	}

	@Override
	public Long createRequest(Long sender_id, Long receiver_id) {
		return friendRequestService.createRequest(sender_id, receiver_id);
	}

	@Override
	public void acceptRequest(Long id) {
		friendRequestService.acceptRequest(id);
	}

	@Override
	public void declineRequest(Long id) {
		friendRequestService.declineRequest(id);
	}

	@Override
	public FriendRequestDTO getRequestById(Long id) {
		return friendRequestService.getRequestById(id);
	}

}
