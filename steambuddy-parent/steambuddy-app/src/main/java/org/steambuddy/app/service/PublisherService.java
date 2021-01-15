package org.steambuddy.app.service;

import java.util.List;

import org.steambuddy.api.dto.PublisherDTO;

public interface PublisherService {

	public List<PublisherDTO> getPublisher();

	public List<PublisherDTO> getPublisherLikeName(String name);

	public PublisherDTO getPublisher(Long id);

}
