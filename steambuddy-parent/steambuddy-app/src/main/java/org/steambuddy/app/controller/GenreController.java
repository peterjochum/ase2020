package org.steambuddy.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RestController;
import org.steambuddy.api.GenreResource;
import org.steambuddy.api.dto.GenreDTO;
import org.steambuddy.app.service.GenreService;

@RestController
public class GenreController implements GenreResource {

	@Autowired
	private GenreService genreService;

	@Override
	public List<GenreDTO> getGenres(Pageable pageable) {
		return genreService.getGenres(pageable);
	}

	@Override
	public List<GenreDTO> getGenres(String name, Pageable pageable) {
		return genreService.getGenresLikeName(name, pageable);
	}

	@Override
	public GenreDTO getGenre(Long id) {
		return genreService.getGenre(id);
	}

}
