package org.steambuddy.app.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "game")
public class GameEntity {

	@Id
	private Long id;

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

	@ManyToOne
	private PublisherEntity publisher;

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
		if(genres == null) {
			genres =  new HashSet<>();
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

	public GameEntity(String name, Integer year, String image, String text) {
		this.name = name;
		this.year = year;
		this.image = image;
		this.text = text;
	}

	public GameEntity() {
	}

}
