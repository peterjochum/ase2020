package org.steambuddy.app.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.steambuddy.api.dto.PublisherDTO;

public interface PublisherService {

	public List<PublisherDTO> getPublisher(Pageable pageable);

	public List<PublisherDTO> getPublisherLikeName(String name, Pageable pageable);

	public PublisherDTO getPublisher(Long id);

}
