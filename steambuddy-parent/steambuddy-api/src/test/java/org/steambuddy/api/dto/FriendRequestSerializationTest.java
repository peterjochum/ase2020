package org.steambuddy.api.dto;

import static org.assertj.core.api.Assertions.*;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import io.github.benas.randombeans.EnhancedRandomBuilder;
import io.github.benas.randombeans.api.EnhancedRandom;

public class FriendRequestSerializationTest {

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
	public void testFriendRequestSerialization() throws Exception {
		/* GIVEN */
		FriendRequestDTO request = randomizer.nextObject(FriendRequestDTO.class);
		
		/* WHEN */
		final String json = mapper.writeValueAsString(request);
		final FriendRequestDTO rRequest = mapper.readValue(json, FriendRequestDTO.class);
		
		/* THEN */
		assertThat(rRequest).isNotNull();
		assertThat(rRequest.getId()).isEqualTo(request.getId());
		assertThat(rRequest.getAccepted()).isEqualTo(request.getAccepted());
		assertThat(rRequest.getTimestamp()).isEqualTo(request.getTimestamp());	
		assertThat(rRequest.getSender()).isEqualToComparingFieldByField(request.getSender());	
		assertThat(rRequest.getReceiver()).isEqualToComparingFieldByField(request.getReceiver());	
	}
	
}
