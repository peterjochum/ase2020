package org.steambuddy.app.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "game_collection")
public class GameCollectionEntity {

	@Id
	private Long id;

	@ManyToMany
	private Set<GameEntity> games;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<GameEntity> getGames() {
		return games;
	}

	public void setGames(Set<GameEntity> games) {
		this.games = games;
	}

	public void addGame(GameEntity game) {
		getGames().add(game);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((games == null) ? 0 : games.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		GameCollectionEntity gce = (GameCollectionEntity) obj;
		if (gce.getId() == null)
			return false;
		return gce.getId().equals(getId());
	}

}