package dev.mredis.thechampion.matches.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class MatchResultDto implements Serializable {

	private static final long serialVersionUID = 128685047293423934L;

	private int matchId;
	private int playerOneScore;
	private int playerTwoScore;
	
	 
}
