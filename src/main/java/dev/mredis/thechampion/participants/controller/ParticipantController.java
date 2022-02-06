package dev.mredis.thechampion.participants.controller;

import dev.mredis.thechampion.participants.dto.ParticipantRequest;
import dev.mredis.thechampion.participants.entity.Participant;
import dev.mredis.thechampion.participants.service.ParticipantService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/participants")
public class ParticipantController {

	private final ParticipantService participantService;

	public ParticipantController(ParticipantService participantService) {
		this.participantService = participantService;
	}

	@PostMapping("/submit")
	public ResponseEntity<String> addParticipant(@RequestBody ParticipantRequest participantRequest) {
		return participantService.addParticipant(participantRequest);
	}

	@GetMapping("/list")
	public ResponseEntity<List<Participant>> getAllParticipants() {
		return new ResponseEntity<>(participantService.getAllParticipants(), HttpStatus.OK);
	}

	@GetMapping("/join/{group-numbers}/groups")
	public ResponseEntity<String> joinGroups(@PathVariable(name = "group-numbers") int numberOfGroups) {
		return participantService.joinGroups(numberOfGroups);
	}

}