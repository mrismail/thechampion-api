package dev.mredis.thechampion.participants.repository;

import dev.mredis.thechampion.participants.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group, Integer> {
}
