package com.idts.codechallenge.api.constants;

import java.util.Arrays;
import java.util.stream.Collectors;

public enum Game {
    DIABLO("diablo"),
    POE("poe"),
    LOL("lol"),
    DOTA("dota"),
    POKEMON("pokemon");

    private final String name;

    Game(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static boolean isMember(final String game) {
        return Arrays.stream(Game.values())
                .map(Game::name)
                .collect(Collectors.toSet())
                .contains(game);
    }

    public static String findByName (String value) {
        if (isMember(value)) {
            return Arrays.stream(values()).filter(game -> game.name().equalsIgnoreCase(value)).findFirst().toString();
        } else {
            throw new IllegalArgumentException();
        }
    }
}
