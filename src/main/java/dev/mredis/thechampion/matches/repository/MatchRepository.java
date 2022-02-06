package dev.mredis.thechampion.matches.repository;

import dev.mredis.thechampion.matches.entity.Match;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchRepository extends JpaRepository<Match, Integer> {
}
