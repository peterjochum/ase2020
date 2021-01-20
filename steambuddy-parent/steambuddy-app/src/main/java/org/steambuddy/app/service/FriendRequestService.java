package org.steambuddy.app.service;

import java.util.List;

import org.steambuddy.api.dto.FriendRequestDTO;
import org.steambuddy.api.dto.UserDTO;

public interface FriendRequestService {

	List<FriendRequestDTO> getIncoming(Long id);
	
	List<FriendRequestDTO> getOutgoing(Long id);
	
	Long createRequest(Long sender_id, Long receiver_id);
	
	void acceptRequest(Long id);
	
	void declineRequest(Long id);
	
	FriendRequestDTO getRequestById(Long id);
	
}
