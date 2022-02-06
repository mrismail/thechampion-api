package dev.mredis.thechampion.participants.repository;

import dev.mredis.thechampion.participants.entity.Participant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParticipantRepository extends JpaRepository<Participant, Integer> {

    List<Participant> findPaticipantsByGroup_Id(Long group_id);
}
