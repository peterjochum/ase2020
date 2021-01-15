package org.steambuddy.app.service;

import java.util.List;

import org.steambuddy.api.dto.GenreDTO;

public interface GenreService {

	public List<GenreDTO> getGenres();

	public List<GenreDTO> getGenresLikeName(String name);

	public GenreDTO getGenre(Long id);

}
