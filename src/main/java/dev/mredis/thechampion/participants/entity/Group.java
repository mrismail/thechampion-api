package dev.mredis.thechampion.participants.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "groups")
public class Group {

	@Id
	@Column(name = "group_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String name;

	private boolean active;

//	@JsonInclude(JsonInclude.Include.NON_EMPTY)
//	@OneToMany(fetch = LAZY, mappedBy = "participantGroup")
//	private List<Participant> groupParticipants;

}
