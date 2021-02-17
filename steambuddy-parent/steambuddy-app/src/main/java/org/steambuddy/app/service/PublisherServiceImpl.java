package org.steambuddy.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.steambuddy.api.dto.PublisherDTO;
import org.steambuddy.app.entity.PublisherEntity;
import org.steambuddy.app.mapper.PublisherMapper;
import org.steambuddy.app.repository.PublisherRepository;

@Service
@Transactional
public class PublisherServiceImpl implements PublisherService {

	@Autowired
	private PublisherMapper publisherMapper;

	@Autowired
	private PublisherRepository publisherRepository;

	@Override
	public List<PublisherDTO> getPublisher(Pageable pageable) {
		return publisherMapper
				.mapEntityToDTO((List<PublisherEntity>) publisherRepository.findAll(pageable).getContent());
	}

	@Override
	public List<PublisherDTO> getPublisherLikeName(String name, Pageable pageable) {
		return publisherMapper.mapEntityToDTO(publisherRepository.findByName(name, pageable));
	}

	@Override
	public PublisherDTO getPublisher(Long id) {
		Optional<PublisherEntity> optPublisher = publisherRepository.findById(id);
		if (optPublisher.isPresent())
			return publisherMapper.entityToDTO(optPublisher.get());
		return null;
	}

}
