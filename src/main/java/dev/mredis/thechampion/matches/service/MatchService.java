package dev.mredis.thechampion.matches.service;

import dev.mredis.thechampion.matches.dto.MatchResultDto;
import dev.mredis.thechampion.matches.entity.Match;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MatchService {

	List<Match> getFirstRoundMatches();

	ResponseEntity<String> updateMatchWinner(MatchResultDto matchResultDto);

	ResponseEntity<String> closeRound(int roundId);
	
}
