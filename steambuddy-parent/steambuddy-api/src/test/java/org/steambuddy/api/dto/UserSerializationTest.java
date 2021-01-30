package org.steambuddy.api.dto;

import static org.assertj.core.api.Assertions.*;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import io.github.benas.randombeans.EnhancedRandomBuilder;
import io.github.benas.randombeans.api.EnhancedRandom;

public class UserSerializationTest {

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
		UserDTO user = randomizer.nextObject(UserDTO.class);
		user.setFriends(null);
		
		/* WHEN */
		String json = mapper.writeValueAsString(user);
		UserDTO rUser = mapper.readValue(json, UserDTO.class);
		
		/* THEN */
		assertThat(rUser).isNotNull();
		assertThat(rUser.getId()).isEqualTo(user.getId());
		assertThat(rUser.getName()).isEqualTo(user.getName());
		assertThat(rUser.getPassword()).isEqualTo(user.getPassword());
		assertThat(rUser.getGroups().size()).isEqualTo(user.getGroups().size());
	}
	
}
