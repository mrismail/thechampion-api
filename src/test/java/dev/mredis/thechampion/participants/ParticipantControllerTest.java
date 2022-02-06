package dev.mredis.thechampion.participants;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.mredis.thechampion.participants.controller.ParticipantController;
import dev.mredis.thechampion.participants.dto.ParticipantRequest;
import dev.mredis.thechampion.participants.service.ParticipantService;
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
@WebMvcTest(ParticipantController.class)
class ParticipantControllerTest {

	@MockBean
	ParticipantService participantService;

	@Autowired
	MockMvc mockMvc;

	@Test
	void addParticipantTest() throws Exception {
		ParticipantRequest participantRequest = new ParticipantRequest();
		participantRequest.setName("M.Amer");
		participantRequest.setMail("m.amer@m.com");

		this.mockMvc
				.perform(MockMvcRequestBuilders.post("/participants/submit")
						.content(new ObjectMapper().writeValueAsString(participantRequest))
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	void getAllParticipantsTest() throws Exception {
		this.mockMvc.perform(get("/participants/list")).andExpect(status().isOk());
	}

	@Test
	void closeRoundTest() throws Exception {
		this.mockMvc.perform(get("/participants/join/2/groups")).andExpect(status().isOk());
	}

}
