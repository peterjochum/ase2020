package org.steambuddy.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.steambuddy.api.PublisherResource;
import org.steambuddy.api.dto.PublisherDTO;
import org.steambuddy.app.service.PublisherService;

@RestController
public class PublisherController implements PublisherResource {

	@Autowired
	private PublisherService publisherService;

	@Override
	public List<PublisherDTO> getPublisher() {
		return publisherService.getPublisher();
	}

	@Override
	public List<PublisherDTO> getPublishers(String name) {
		return publisherService.getPublisherLikeName(name);
	}

	@Override
	public PublisherDTO getPublisher(Long id) {
		return publisherService.getPublisher(id);
	}

}
