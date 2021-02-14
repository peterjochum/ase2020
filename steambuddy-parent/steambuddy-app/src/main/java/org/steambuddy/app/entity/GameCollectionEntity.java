package org.steambuddy.app.entity;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "game_collection")
public class GameCollectionEntity extends AbstractEntity {

	@ManyToMany
	private Set<GameEntity> games;

	public Set<GameEntity> getGames() {
		if (games == null) {
			games = new HashSet<>();
		}
		return games;
	}

	public void setGames(Set<GameEntity> games) {
		this.games = games;
	}

	public void addGame(GameEntity game) {
		getGames().add(game);
	}

	public void removeGame(GameEntity gameEntity) {
		getGames().remove(gameEntity);
	}
	
	public boolean containsGame(GameEntity gameEntity) {
		//There is probably a more efficient way to find them, that we could add later
		return getGames().contains(gameEntity);
	}
	
	public List<Long> getBestGenres(){ //get top genres of user
		
		Set<GameEntity> userGames=getGames();
		
		//count how often each genre appears in total in the users library
		SortedMap<Long,Integer> genrePrefs=new TreeMap<Long,Integer>();
		long genreID;
		for (GameEntity game: userGames) {
			Set<GenreEntity> genres=game.getGenres();
			for (GenreEntity genre: genres) {
				genreID=genre.getId();
				if(!genrePrefs.containsKey(genreID)) 
					genrePrefs.put(genreID,1);
				else 
					genrePrefs.put(genreID,genrePrefs.get(genreID)+1);
						
			}
		}
		
		
		List<Long> bestGenres=new LinkedList<Long>();
		int genreCount=0;
		long worstGenreCount=0;

		//replace with comparator later, see gameservice logic
		
		//find the top 5 genres of user
		for (long genre: genrePrefs.keySet()) {
			if(genreCount<5) {
				bestGenres.add(genre);
				genreCount++;
			}
			else {
				
			}
			if(genrePrefs.get(genre)>worstGenreCount) {//if genre is more common, remove currently least common genre and add new one instead
				for (long genre2: bestGenres) {
					if(genrePrefs.get(genre2)==worstGenreCount) {
						bestGenres.remove(genre2);
						bestGenres.add(genre);
						break;
					}
					
				}
				worstGenreCount=Long.MAX_VALUE;//set new worst value
				for (long genre2: bestGenres) {
					if(genrePrefs.get(genre2)<worstGenreCount) {
						worstGenreCount=genrePrefs.get(genre2);
					}
				}
				
			}
			
		}
		
		
		
		return bestGenres;
		
		
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
