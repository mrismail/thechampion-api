package dev.mredis.thechampion.matches.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "rounds")
@SequenceGenerator(name = "round_sequence", sequenceName = "rounds_sequence", allocationSize = 1, initialValue = 1)
public class Round {
	
	@Id
	@Column(name = "round_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "round_sequence")
	private int id;

	private String name;

	private LocalDate roundStartDate;

	private boolean closed;

	private int roundNo;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "round")
	private List<Match> matches;
	
}
