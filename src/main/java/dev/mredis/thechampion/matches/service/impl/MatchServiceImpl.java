package dev.mredis.thechampion.matches.service.impl;

import dev.mredis.thechampion.matches.dto.MatchResultDto;
import dev.mredis.thechampion.matches.entity.Match;
import dev.mredis.thechampion.matches.entity.Round;
import dev.mredis.thechampion.matches.repository.MatchRepository;
import dev.mredis.thechampion.matches.repository.RoundRepository;
import dev.mredis.thechampion.matches.service.MatchService;
import dev.mredis.thechampion.participants.entity.Group;
import dev.mredis.thechampion.participants.entity.Participant;
import dev.mredis.thechampion.participants.repository.GroupRepository;
import dev.mredis.thechampion.participants.repository.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class MatchServiceImpl implements MatchService {

	@Autowired
	private MatchRepository matchRepository;

	@Autowired
	private RoundRepository roundRepository;

	@Autowired
	private GroupRepository groupRepository;

	@Autowired
	private ParticipantRepository participantRepository;

	@Transactional
	@Override
	public List<Match> getFirstRoundMatches() {

		List<Group> firstRoundGroups = groupRepository.findAll();
		List<Match> firstRoundMatches = new ArrayList<>();

		Round round = createRound(1);

		for (int i = 0; i < firstRoundGroups.size(); i++) {
			List<Participant> groupParticipants = participantRepository.findPaticipantsByGroup_Id(firstRoundGroups.get(i).getId());
			firstRoundMatches.addAll(generateMatches(groupParticipants, round));
		}

		return firstRoundMatches;
	}

	@Transactional
	@Override
	public ResponseEntity<String> updateMatchWinner(MatchResultDto matchResultDto) {

		Optional<Match> optionalMatch = matchRepository.findById(matchResultDto.getMatchId());
		if(optionalMatch.isPresent()) {
			optionalMatch.get().setPlayerOneScore(matchResultDto.getPlayerOneScore());
			optionalMatch.get().setPlayerTwoScore(matchResultDto.getPlayerTwoScore());
			if (matchResultDto.getPlayerOneScore() > matchResultDto.getPlayerTwoScore()) {
				optionalMatch.get().setWinner(optionalMatch.get().getPlayerOne());
			} else {
				optionalMatch.get().setWinner(optionalMatch.get().getPlayerTwo());
			}

			matchRepository.save(optionalMatch.get());
		}
		return new ResponseEntity<>("Match winner updated", HttpStatus.OK);

	}

	@Transactional
	@Override
	public ResponseEntity<String> closeRound(int roundId) {
		Optional<Round> roundOptional = roundRepository.findById(roundId);

		if (roundOptional.isPresent()) {
			roundOptional.get().setClosed(true);
			roundRepository.save(roundOptional.get());
		}

		return new ResponseEntity<>("Round closed ", HttpStatus.OK);
	}

	private List<Match> generateMatches(List<Participant> participants, Round round) {

		List<Match> matches = new ArrayList<>();
		for(int i = 0; i < participants.size(); i++) {
			for(int j = i + 1; j < participants.size(); j++) {
				matches.add(createMatch(participants.get(i), participants.get(j), round));
			}
		}

		Collections.shuffle(matches);
		int matchCounter = 0;
		LocalDate localDate = LocalDate.now();
		for(int i = 0; i < matches.size(); i++) {

			if(matchCounter == 3) {
				matchCounter = 0;
				localDate = localDate.plusDays(1);
			}

			matches.get(i).setMatchDate(localDate);
			matchRepository.save(matches.get(i));

			matchCounter++;
		}

		return matches;
	}

	private Match createMatch(Participant participantOne, Participant participantTwo, Round round) {
		Match match = new Match();
		match.setPlayerOne(participantOne);
		match.setPlayerTwo(participantTwo);
		match.setRound(round);
		return match;
	}

	private Round createRound(int roundNo) {
		Round round = new Round();

		round.setRoundNo(roundNo);
		round.setName("Round "+ roundNo);
		round.setClosed(false);
		round.setRoundStartDate(LocalDate.now());

		roundRepository.save(round);

		return round;
	}

}
