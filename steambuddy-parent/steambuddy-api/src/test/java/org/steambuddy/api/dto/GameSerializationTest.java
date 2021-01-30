package org.steambuddy.api.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import io.github.benas.randombeans.EnhancedRandomBuilder;
import io.github.benas.randombeans.api.EnhancedRandom;

public class GameSerializationTest {
	
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
		GameDTO game = randomizer.nextObject(GameDTO.class);
		
		/* WHEN */
		String json = mapper.writeValueAsString(game);
		GameDTO rGame = mapper.readValue(json, GameDTO.class);
		
		/* THEN */
		assertThat(rGame).isNotNull();
		assertThat(rGame.getId()).isEqualTo(game.getId());
		assertThat(rGame.getName()).isEqualTo(game.getName());
		assertThat(rGame.getYear()).isEqualTo(game.getYear());
		assertThat(rGame.getImage()).isEqualTo(game.getImage());
		assertThat(rGame.getText()).isEqualTo(game.getText());
		assertThat(rGame.getGenres().size()).isEqualTo(game.getGenres().size());
	}
	
}
