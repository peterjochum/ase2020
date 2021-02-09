package org.steambuddy.app.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class UserEntity extends AbstractEntity {

	@Column(name = "name")
	private String name;

	@Column(name = "password")
	private String password;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "friend", joinColumns = {
			@JoinColumn(name = "friendLeft", referencedColumnName = "id", nullable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "friendRight", referencedColumnName = "id", nullable = false) })
	private Set<UserEntity> friends;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "group_member", joinColumns = {
			@JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "group_id", referencedColumnName = "id", nullable = false) })
	private Set<GroupEntity> groups;

	@OneToOne(cascade = CascadeType.ALL)
	private GameCollectionEntity gameCollection;

	public UserEntity(Long id, String name, String password, Set<UserEntity> friends, Set<GroupEntity> groups) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.friends = friends;
		this.groups = groups;
	}

	public UserEntity() {
	};

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

	public GameCollectionEntity getGameCollection() {
		if (gameCollection == null) {
			gameCollection = new GameCollectionEntity();
		}
		return gameCollection;
	}

	public void setGameCollection(GameCollectionEntity gameCollection) {
		this.gameCollection = gameCollection;
	}

}
