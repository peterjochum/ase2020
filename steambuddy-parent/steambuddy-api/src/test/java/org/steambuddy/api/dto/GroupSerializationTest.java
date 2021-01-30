package org.steambuddy.api.dto;

import static org.assertj.core.api.Assertions.*;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import io.github.benas.randombeans.EnhancedRandomBuilder;
import io.github.benas.randombeans.api.EnhancedRandom;

public class GroupSerializationTest {

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
		GroupDTO group = randomizer.nextObject(GroupDTO.class);
		
		/* WHEN */
		String json = mapper.writeValueAsString(group);
		GroupDTO rGroup = mapper.readValue(json, GroupDTO.class);
		
		/* THEN */
		assertThat(rGroup).isNotNull();
		assertThat(rGroup.getId()).isEqualTo(group.getId());
		assertThat(rGroup.getName()).isEqualTo(group.getName());
		assertThat(rGroup.getDescription()).isEqualTo(group.getDescription());
		assertThat(rGroup.getOwner()).isEqualToComparingFieldByField(group.getOwner());
	}
	
}
