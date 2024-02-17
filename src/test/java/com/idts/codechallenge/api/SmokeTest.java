package com.idts.codechallenge.api;

import com.idts.codechallenge.api.controller.PlayerController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class SmokeTest {
    @Autowired
    private PlayerController playerController;

    @Test
    public void contextLoads() throws Exception {
        assertThat(playerController).isNotNull();
    }
}
