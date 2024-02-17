package com.idts.codechallenge.api.customExceptions;

public class PlayerNotFoundException extends RuntimeException{
    public PlayerNotFoundException(Long id) {
        super("Could not find player: " + id);
    }
}
