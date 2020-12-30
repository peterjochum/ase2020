package org.steambuddy.app.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "game")
public class GameEntity {

	@Id
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "year")
	private int year;

	@Column(name = "image")
	private String image;

	@Column(name = "text")
	private String text;

	@ManyToMany(targetEntity = GenreEntity.class)
	private Set<GenreEntity> genres;

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

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
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
		return genres;
	}

	public void setGenres(Set<GenreEntity> genres) {
		this.genres = genres;
	}

	public GameEntity(String name, int year, String image, String text) {
		this.name = name;
		this.year = year;
		this.image = image;
		this.text = text;
	}

	public GameEntity() {
	}

}
