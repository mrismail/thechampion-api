package dev.mredis.thechampion.matches;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.mredis.thechampion.matches.controller.MatchContoller;
import dev.mredis.thechampion.matches.dto.MatchResultDto;
import dev.mredis.thechampion.matches.service.MatchService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(MatchContoller.class)
class MatchControllerTest {

	@MockBean
	MatchService matchService;

	@Autowired
	MockMvc mockMvc;

	@Test
	void getFirstRoundMatches() throws Exception {
		this.mockMvc.perform(get("/matches/first-round-matches")).andExpect(status().isOk());
	}

	@Test
	void updateMatchWinnerTest() throws Exception {
		MatchResultDto matchResultDto = new MatchResultDto();
		matchResultDto.setMatchId(2);
		matchResultDto.setPlayerOneScore(5);
		matchResultDto.setPlayerTwoScore(13);

		this.mockMvc
				.perform(MockMvcRequestBuilders.post("/matches/update-match-result")
						.content(new ObjectMapper().writeValueAsString(matchResultDto))
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	void closeRoundTest() throws Exception {
		this.mockMvc.perform(get("/matches/close-round?round-id=1")).andExpect(status().isOk());
	}

}
