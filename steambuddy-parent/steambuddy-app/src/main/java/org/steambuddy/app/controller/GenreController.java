package org.steambuddy.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.steambuddy.api.GenreResource;
import org.steambuddy.api.dto.GenreDTO;
import org.steambuddy.app.service.GenreService;

@RestController
public class GenreController implements GenreResource {

	@Autowired
	private GenreService genreService;

	@Override
	public List<GenreDTO> getGenres() {
		return genreService.getGenres();
	}

	@Override
	public List<GenreDTO> getGenres(String name) {
		return genreService.getGenresLikeName(name);
	}

	@Override
	public GenreDTO getGenre(Long id) {
		return genreService.getGenre(id);
	}

}
