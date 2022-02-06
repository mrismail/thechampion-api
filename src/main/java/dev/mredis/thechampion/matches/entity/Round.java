package dev.mredis.thechampion.matches.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "rounds")
public class Round {
	
	@Id
	@Column(name = "round_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String name;

	private LocalDate roundStartDate;

	private boolean closed;

	private int roundNo;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "round")
	private List<Match> matches;
	
}
