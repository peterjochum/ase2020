package org.steambuddy.app.entity;

// TODO: annotate with JPA
public class GameEntity {

	private Long id;

	private String name;

	private int year;

	private String image;

	private String text;

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

	public GameEntity(String name, int year, String image, String text) {
		this.name = name;
		this.year = year;
		this.image = image;
		this.text = text;
	}

}
