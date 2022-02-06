package dev.mredis.thechampion.participants.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ParticipantRequest implements Serializable {
	
	private String name;
	private String mail;
	
}
