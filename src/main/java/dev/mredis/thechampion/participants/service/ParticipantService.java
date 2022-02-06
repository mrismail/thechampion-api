package dev.mredis.thechampion.participants.service;

import dev.mredis.thechampion.participants.dto.ParticipantRequest;
import dev.mredis.thechampion.participants.entity.Participant;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ParticipantService {
	
	ResponseEntity<String> addParticipant(ParticipantRequest participantRequest);
	
	List<Participant> getAllParticipants();

	ResponseEntity<String> joinGroups(int numberOfGroups);
}
