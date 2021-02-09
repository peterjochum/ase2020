package org.steambuddy.api.dto;

import static org.assertj.core.api.Assertions.*;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import io.github.benas.randombeans.EnhancedRandomBuilder;
import io.github.benas.randombeans.api.EnhancedRandom;

public class PublisherSerializationTest {

	private ObjectMapper mapper;
	private EnhancedRandom randomizer;
	
	@BeforeClass
	public void onBeforeClass() {
		mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		
		randomizer = EnhancedRandomBuilder.aNewEnhancedRandom();
	}
	
	@Test
	public void testSerialization() throws Exception {
		/* GIVEN */
		PublisherDTO publisher = randomizer.nextObject(PublisherDTO.class);
		
		/* WHEN */
		String json = mapper.writeValueAsString(publisher);
		PublisherDTO rPublisher = mapper.readValue(json, PublisherDTO.class);
		
		/* THEN */
		assertThat(rPublisher).isNotNull();
		assertThat(rPublisher.getId()).isEqualTo(publisher.getId());
		assertThat(rPublisher.getName()).isEqualTo(publisher.getName());
	}
	
}
