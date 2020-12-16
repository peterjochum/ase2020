package org.steambuddy.api.models;

public class Game {

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

	public static Game getInstance(String name, int year, String image, String text) {
		Game instance = new Game();
		instance.setName(name);
		instance.setYear(year);
		instance.setImage(image);
		instance.setText(text);
		return instance;
	}

}
