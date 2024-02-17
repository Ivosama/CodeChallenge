package com.idts.codechallenge.api.controller.advice;

import com.idts.codechallenge.api.constants.Game;
import com.idts.codechallenge.api.constants.Level;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Arrays;

@ControllerAdvice
public class CustomExceptionHandler {
    private final String INVALID_GAME = "Invalid parameter for game. Valid games are: " + Arrays.toString(Game.values());
    private final String INVALID_LEVEL = "Invalid parameter for level. Valid levels are: " + Arrays.toString(Level.values());

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<String> handleTypeMismatch(MethodArgumentTypeMismatchException exception) {
        String paramName = exception.getName();
        String message;
        if(paramName.equals("game")) {
            message = INVALID_GAME;
        } else if(paramName.equals("level")) {
            message = INVALID_LEVEL;
        } else {
            Class<?> target = exception.getRequiredType();
            assert target != null;
            message = "Type mismatch for parameter: '" + paramName + "'." +
                    "Expected type: " + target.getSimpleName() + ".";
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }

}
