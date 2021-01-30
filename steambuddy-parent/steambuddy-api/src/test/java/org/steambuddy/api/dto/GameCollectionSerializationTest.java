package org.steambuddy.api.dto;

import static org.assertj.core.api.Assertions.*;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import io.github.benas.randombeans.EnhancedRandomBuilder;
import io.github.benas.randombeans.api.EnhancedRandom;

public class GameCollectionSerializationTest {

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
	public void testGameCollectionSerialization() throws Exception {
		/* GIVEN */
		GameCollectionDTO gameCollection = randomizer.nextObject(GameCollectionDTO.class);
		
		/* WHEN */
		String json = mapper.writeValueAsString(gameCollection);
		GameCollectionDTO rGameCollection = mapper.readValue(json, GameCollectionDTO.class);
		
		/* THEN */
		assertThat(rGameCollection).isNotNull();
		assertThat(rGameCollection.getId()).isEqualTo(gameCollection.getId());
		assertThat(rGameCollection.getGames().size()).isEqualTo(gameCollection.getGames().size());
	}
	
}
