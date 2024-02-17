package com.idts.codechallenge.api.utils;

import com.idts.codechallenge.api.constants.Game;
import com.idts.codechallenge.api.constants.Level;
import com.idts.codechallenge.api.dao.PlayerRepository;
import com.idts.codechallenge.api.model.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {

    private static final Logger logger = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(PlayerRepository playerRepository) {
        return args -> {
            logger.info("Preloading " + playerRepository.save(new Player("Mathias", "Aalborg", Game.LOL)));
            logger.info("Preloading " + playerRepository.save(new Player("Peter", "Esbjerg", Level.PRO, Game.DOTA)));
            logger.info("Preloading " + playerRepository.save(new Player("Poul", "Aalborg", Level.NOOB, Game.DIABLO)));
            logger.info("Preloading " + playerRepository.save(new Player("Tom", "Copenhagen", Level.INVINCIBLE, Game.POKEMON)));
        };
    }
}
