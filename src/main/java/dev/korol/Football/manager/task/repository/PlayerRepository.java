package dev.korol.Football.manager.task.repository;

import dev.korol.Football.manager.task.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Korol Artur
 * 31.07.2025
 */
@Repository
public interface PlayerRepository extends JpaRepository<Player, Integer> {
}
