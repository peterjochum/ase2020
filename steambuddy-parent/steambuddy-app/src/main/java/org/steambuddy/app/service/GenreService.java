package org.steambuddy.app.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.steambuddy.api.dto.GenreDTO;

public interface GenreService {

	public List<GenreDTO> getGenres(Pageable pageable);

	public List<GenreDTO> getGenresLikeName(String name,Pageable pageable);

	public GenreDTO getGenre(Long id);

}
