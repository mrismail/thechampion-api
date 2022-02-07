package dev.mredis.thechampion.participants.entity;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;


@Data
@Entity
@Table(name = "participants")
@SequenceGenerator(name = "participant_sequence", sequenceName = "participants_sequence", allocationSize = 1, initialValue = 1)
public class Participant {
	@Id
	@Column(name = "participant_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "participant_sequence")
	private int id;

	private String name;

	private String mail;

	@ColumnDefault("0")
	private int score;

	@ManyToOne(targetEntity = Group.class, cascade = {PERSIST, MERGE})
	@JoinColumn(name = "group_id")
	private Group group;

}
