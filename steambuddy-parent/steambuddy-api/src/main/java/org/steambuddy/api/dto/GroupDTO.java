package org.steambuddy.api.dto;

public class GroupDTO {
	
	private Long id;
	
	private String name;
	
	private String description;
	
	private UserDTO owner;

	public GroupDTO(Long id, String name, String description, UserDTO owner) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.owner = owner;
	}
	
	public GroupDTO() {};

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public UserDTO getOwner() {
		return owner;
	}

	public void setOwner(UserDTO owner) {
		this.owner = owner;
	}
}
