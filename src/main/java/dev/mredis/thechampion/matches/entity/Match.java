package dev.mredis.thechampion.matches.entity;

import dev.mredis.thechampion.participants.entity.Participant;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "matches")
public class Match {

	@Id
	@Column(name = "match_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@ManyToOne(fetch = FetchType.LAZY)
	private Participant playerOne;

	@ManyToOne(fetch = FetchType.LAZY)
	private Participant playerTwo;

	private LocalDate matchDate;

	private int playerOneScore;

	private int playerTwoScore;

	@ManyToOne(fetch = FetchType.LAZY)
	private Round round;

	@ManyToOne(fetch = FetchType.LAZY)
	private Participant winner;

}
