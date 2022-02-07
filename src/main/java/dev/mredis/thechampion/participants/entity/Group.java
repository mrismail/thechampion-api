package dev.mredis.thechampion.participants.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "groups")
@SequenceGenerator(name = "group_sequence", sequenceName = "groups_sequence", allocationSize = 1, initialValue = 1)
public class Group {

	@Id
	@Column(name = "group_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "group_sequence")
	private Long id;

	private String name;

	private boolean active;
}
