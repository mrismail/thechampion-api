package dev.mredis.thechampion.matches.repository;

import dev.mredis.thechampion.matches.entity.Round;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoundRepository extends JpaRepository<Round, Integer> {
}
