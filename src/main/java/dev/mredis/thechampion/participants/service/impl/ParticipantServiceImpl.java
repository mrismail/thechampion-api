package dev.mredis.thechampion.participants.service.impl;

import dev.mredis.thechampion.participants.dto.ParticipantRequest;
import dev.mredis.thechampion.participants.entity.Participant;
import dev.mredis.thechampion.participants.entity.Group;
import dev.mredis.thechampion.participants.repository.ParticipantRepository;
import dev.mredis.thechampion.participants.repository.GroupRepository;
import dev.mredis.thechampion.participants.service.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
public class ParticipantServiceImpl implements ParticipantService {

	@Autowired
	private ParticipantRepository participantRepository;
	@Autowired
	private GroupRepository participantsGroupRepository;

	@Transactional
	@Override
	public ResponseEntity<String> addParticipant(ParticipantRequest participantRequest) {

		List<Participant> participants = participantRepository.findAll();

		if(participants.size() < 12) {
			Participant participant = new Participant();
			participant.setName(participantRequest.getName());
			participant.setMail(participantRequest.getMail());
			participantRepository.save(participant);

			return new ResponseEntity<>("Participant added", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Participants reached the maximum capacity", HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@Transactional(readOnly = true)
	@Override
	public List<Participant> getAllParticipants() {
		return participantRepository.findAll();
	}

	@Transactional
	@Override
	public ResponseEntity<String> joinGroups(int numberOfGroups) {

		List<Participant> participants = participantRepository.findAll();
		if(participants != null && participants.size() < 2) {
			return new ResponseEntity<>("Number of participants not sufficient to join groups", HttpStatus.NOT_ACCEPTABLE);
		}

		int maxValidNumberOfGroups = participants.size() / 2;
		if(numberOfGroups >= 1 && numberOfGroups <= maxValidNumberOfGroups) {
			addParticipantsToGroups(numberOfGroups, participants);
			return new ResponseEntity<>("Participants joined groups", HttpStatus.OK);
		} else {
			String body;
			if(maxValidNumberOfGroups > 1) {
				body = "Please enter a valid group number between (1 - " + maxValidNumberOfGroups + ")";
			} else {
				body = "Maximum group number is (1)";
			}
			return new ResponseEntity<>(body, HttpStatus.NOT_ACCEPTABLE);
		}
	}

	private void addParticipantsToGroups(int numberOfGroups, List<Participant> participants) {

		List<Participant> currentParticipants = participantRepository.findAll();
		Collections.shuffle(currentParticipants);
		int participantsCount = currentParticipants.size();
		int participantsPerGroup = participantsCount / numberOfGroups;
		int groupNumberCounter = 1;
		Group participantsGroup = new Group();

		for(int currentParticipantsCounter = 0; currentParticipantsCounter < participantsCount; currentParticipantsCounter++) {

			if(currentParticipantsCounter % participantsPerGroup == 0) {

				participantsGroup = createGroup(groupNumberCounter);
				participantsGroupRepository.save(participantsGroup);
				++groupNumberCounter;
			}

			currentParticipants.get(currentParticipantsCounter).setGroup(participantsGroup);
			participantRepository.save(currentParticipants.get(currentParticipantsCounter));
		}
	}

	private Group createGroup(int groupNumber) {
		Group participantsGroup = new Group();
		participantsGroup.setName("Group Number " + groupNumber);
		participantsGroup.setActive(true);
		return participantsGroup;
	}

}
