package org.steambuddy.api.dto;

import static org.assertj.core.api.Assertions.*;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import io.github.benas.randombeans.EnhancedRandomBuilder;
import io.github.benas.randombeans.api.EnhancedRandom;

public class GenreSerializationTest {

	private ObjectMapper mapper;
	private EnhancedRandom randomizer;
	
	@BeforeClass
	public void onBeforeClass() {
		mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		
		randomizer = EnhancedRandomBuilder.aNewEnhancedRandomBuilder().randomizationDepth(1).build();
	}
	
	@Test
	public void testSerialization() throws Exception {
		/* GIVEN */
		GenreDTO genre = randomizer.nextObject(GenreDTO.class);
		
		/* WHEN */
		String json = mapper.writeValueAsString(genre);
		GenreDTO rGenre = mapper.readValue(json, GenreDTO.class);
		
		/* THEN */
		assertThat(rGenre).isNotNull();
		assertThat(rGenre.getId()).isEqualTo(genre.getId());
		assertThat(rGenre.getName()).isEqualTo(genre.getName());
		assertThat(rGenre.getGames().size()).isEqualTo(genre.getGames().size());
	}
	
}
