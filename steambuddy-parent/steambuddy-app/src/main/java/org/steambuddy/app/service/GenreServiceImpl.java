package org.steambuddy.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.steambuddy.api.dto.GenreDTO;
import org.steambuddy.app.entity.GenreEntity;
import org.steambuddy.app.mapper.GenreMapper;
import org.steambuddy.app.repository.GenreRepository;

@Service
@Transactional
public class GenreServiceImpl implements GenreService {

	@Autowired
	private GenreMapper genreMapper;

	@Autowired
	private GenreRepository genreRepository;

	@Override
	public List<GenreDTO> getGenres() {
		return genreMapper.mapEntityToDTOWithGames(((List<GenreEntity>) genreRepository.findAll()));
	}

	@Override
	public List<GenreDTO> getGenresLikeName(String name) {
		return genreMapper.mapEntityToDTOWithGames(genreRepository.findByName(name));
	}

	@Override
	public GenreDTO getGenre(Long id) {
		Optional<GenreEntity> optGenre = genreRepository.findById(id);
		if (optGenre.isPresent())
			return genreMapper.entityToDTO(optGenre.get(), true);
		return null;
	}

}
