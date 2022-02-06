package dev.mredis.thechampion.matches.controller;


import dev.mredis.thechampion.matches.dto.MatchResultDto;
import dev.mredis.thechampion.matches.entity.Match;
import dev.mredis.thechampion.matches.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/matches")
public class MatchContoller {

	@Autowired
	private MatchService matchService;

	@GetMapping("/first-round-matches")
	public List<Match> getFirstRoundMatches() {
		return matchService.getFirstRoundMatches();

	}

	@PostMapping("/update-match-result")
	public ResponseEntity<String> updateMatchWinner(@RequestBody MatchResultDto matchResultDto) {
		return matchService.updateMatchWinner(matchResultDto);

	}
	
	@GetMapping("/close-round")
	public ResponseEntity<String> closeRound(@RequestParam(name = "round-id") int roundId) {
		return matchService.closeRound(roundId);
	}

}
