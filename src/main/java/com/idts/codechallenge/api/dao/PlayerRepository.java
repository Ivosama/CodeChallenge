package com.idts.codechallenge.api.dao;

import com.idts.codechallenge.api.constants.Game;
import com.idts.codechallenge.api.constants.Level;
import com.idts.codechallenge.api.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface PlayerRepository extends JpaRepository<Player, Long> {
    @Query("SELECT p FROM Player p WHERE p.level = :level")
    List<Player> findByLevel(@Param("level") Level level);
    @Query("SELECT p FROM Player p WHERE p.game = :game")
    List<Player> findByGame(@Param("game") Game game);
    @Query("SELECT p FROM Player p WHERE p.level = :level AND p.game = :game")
    List<Player> findByLevelAndGame(@Param("level") Level level, @Param("game") Game game);
    @Query("SELECT p FROM Player p WHERE p.geography = :geography")
    List<Player> findByGeography(@Param("geography") String geography);
}
