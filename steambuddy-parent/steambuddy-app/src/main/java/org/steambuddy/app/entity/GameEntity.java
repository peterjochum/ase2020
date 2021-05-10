package org.steambuddy.app.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "game")
public class GameEntity extends AbstractEntity {

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "year")
	private Integer year;

	@Column(name = "image")
	private String image;

	@Column(name = "text", columnDefinition = "TEXT")
	private String text;

	@ManyToMany(targetEntity = GenreEntity.class)
	private Set<GenreEntity> genres;

	@OneToMany(targetEntity = RatingEntity.class)
	private Set<RatingEntity> ratings;
	
	@ManyToOne
	private PublisherEntity publisher;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Set<GenreEntity> getGenres() {
		if (genres == null) {
			genres = new HashSet<>();
		}
		return genres;
	}

	public void setGenres(Set<GenreEntity> genres) {
		this.genres = genres;
	}

	public PublisherEntity getPublisher() {
		return publisher;
	}

	public void setPublisher(PublisherEntity publisher) {
		this.publisher = publisher;
	}

	public GameEntity(Long id, String name, Integer year, String image, String text, Set<GenreEntity> genres,Set<RatingEntity> ratings,
			PublisherEntity publisher) {
		this.id = id;
		this.name = name;
		this.year = year;
		this.image = image;
		this.text = text;
		this.genres = genres;
		this.ratings=ratings;
		this.publisher = publisher;
	}

	public GameEntity() {
	}

	public Set<RatingEntity> getRatings() {
		return ratings;
	}

	public void setRatings(Set<RatingEntity> ratings) {
		this.ratings = ratings;
	}

}
