package org.steambuddy.api.dto;

import java.util.Set;

public class UserDTO {

	private Long id;	
	private String name;
	private String password;
	private Set<UserDTO> friends;
	private Set<GroupDTO> groups;
		
	public UserDTO(Long id, String name, String password, Set<UserDTO> friends, Set<GroupDTO> groups) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.friends = friends;
		this.groups = groups;
	}
	
	public UserDTO() {}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Set<UserDTO> getFriends() {
		return friends;
	}
	
	public void setFriends(Set<UserDTO> friends) {
		this.friends = friends;
	}

	public Set<GroupDTO> getGroups() {
		return groups;
	}

	public void setGroups(Set<GroupDTO> groups) {
		this.groups = groups;
	}
	
}
