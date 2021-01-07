package org.steambuddy.app.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;	
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "password")
	private String password;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "friend", joinColumns = {
			@JoinColumn(name  = "friendLeft", referencedColumnName = "id",  nullable = false)}, inverseJoinColumns = {
		    @JoinColumn(name  = "friendRight", referencedColumnName = "id",  nullable = false)})
	private Set<UserEntity> friends;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "group_member", joinColumns = {
			@JoinColumn(name  = "user_id", referencedColumnName = "id",  nullable = false)}, inverseJoinColumns = {
		    @JoinColumn(name  = "group_id", referencedColumnName = "id",  nullable = false)})
	private Set<GroupEntity> groups;
		
	public UserEntity(Long id, String name, String password, Set<UserEntity> friends, Set<GroupEntity> groups) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.friends = friends;
		this.groups = groups;
	}
	
	public UserEntity() {};
	
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
	
	public Set<UserEntity> getFriends() {
		return friends;
	}
	
	public void setFriends(Set<UserEntity> friends) {
		this.friends = friends;
	}

	public Set<GroupEntity> getGroups() {
		return groups;
	}

	public void setGroups(Set<GroupEntity> groups) {
		this.groups = groups;
	}
		
}
