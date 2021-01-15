package org.steambuddy.app.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.steambuddy.api.dto.FriendRequestDTO;
import org.steambuddy.app.entity.FriendRequestEntity;
import org.steambuddy.app.mapper.FriendRequestMapper;
import org.steambuddy.app.mapper.UserMapper;
import org.steambuddy.app.repository.FriendRequestRepository;

@Service
public class FriendRequestServiceImpl implements FriendRequestService {

	@Autowired
	private FriendRequestRepository friendRequestRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private FriendRequestMapper mapper;
	
	@Autowired
	private UserMapper userMapper;
	
	
	@Override
	public List<FriendRequestDTO> getIncoming(Long id) {
		return mapper.mapEntityToDTO((List<FriendRequestEntity>) friendRequestRepository.findByReceiverId(id));
	}

	@Override
	public List<FriendRequestDTO> getOutgoing(Long id) {
		return mapper.mapEntityToDTO((List<FriendRequestEntity>) friendRequestRepository.findBySenderId(id));
	}

	@Override
	public Long createRequest(Long sender_id, Long receiver_id) {
		FriendRequestEntity request = new FriendRequestEntity();
		request.setTimestamp(new Timestamp(System.currentTimeMillis()));
		request.setSender(userMapper.dtoToEntity(userService.getUserById(sender_id)));
		request.setReceiver(userMapper.dtoToEntity(userService.getUserById(receiver_id)));
		return friendRequestRepository.save(request).getId();
	}

	@Override
	public void acceptRequest(Long id) {
		FriendRequestEntity request = friendRequestRepository.findById(id).get();
		request.setAccepted(true);
		request.setTimestamp(new Timestamp(System.currentTimeMillis()));
		friendRequestRepository.save(request);
		userService.addFriend(request.getSender(), request.getReceiver());
		userService.addFriend(request.getReceiver(), request.getSender());
	}

	@Override
	public void declineRequest(Long id) {
		FriendRequestEntity request = friendRequestRepository.findById(id).get();
		request.setAccepted(false);
		request.setTimestamp(new Timestamp(System.currentTimeMillis()));
		friendRequestRepository.save(request);
	}

	@Override
	public FriendRequestDTO getRequestById(Long id) {
		return mapper.entityToDTO(friendRequestRepository.findById(id).get());
	}

}
