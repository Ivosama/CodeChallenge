package com.idts.codechallenge.api;

import com.idts.codechallenge.api.controller.advice.PlayerNotFoundAdvice;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.Assert;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class WebApplicationWithMockMvcTest {
    @Autowired
    private MockMvc mockMvc;

    private MockHttpServletResponse response;

    @Test
    public void getAllPlayersTest() throws Exception {
        response = this.mockMvc.perform(get("/api/players/getAll"))
                .andDo(print())
                .andReturn().getResponse();

        Assert.isTrue(response.getContentType().contains("hal"), "The response is not producing hypermedia");
        Assert.isNull(response.getErrorMessage(), "The error message is not null");
        Assert.isTrue(response.getStatus() == 200, "The expected response was 200");
    }

    @Test
    public void assignGameToPlayerTest() throws Exception {
        int validPlayerId = 1;
        String validGameName = "POE";

        response = this.mockMvc.perform(get("/api/players/getById/" + validPlayerId))
                .andReturn().getResponse();

        Assert.isTrue(!response.getContentAsString().contains(validGameName),
                "The game assigned to the player was already: " + validGameName);

        response = this.mockMvc.perform(put("/api/players/associateGameToPlayer/" +
                        validPlayerId + "/" +
                        validGameName))
                .andDo(print())
                .andReturn().getResponse();

        Assert.isTrue(response.getContentAsString().contains(validGameName),
                "The request did not assign the game: " + validGameName + "to the player.");
    }

    @Test
    public void getInvalidPlayerTest() throws Exception {
        int invalidId = 100;
        response = this.mockMvc.perform(get("/api/players/getById/" + invalidId))
                .andDo(print())
                .andReturn().getResponse();

        Assert.isTrue(response.getStatus() == 404, "Expected a 404, not found response.");
        Assert.isTrue(response.getContentAsString().equals("Could not find player: " + invalidId),
                "Expected a custom message from: " + PlayerNotFoundAdvice.class.getSimpleName() + " handler.");
    }

    @Test
    public void assignInvalidGameToPlayerTest() throws Exception {
        int validPlayerId = 1;
        String invalidGameName = "BOOM";

        response = this.mockMvc.perform(get("/api/players/getById/" + validPlayerId))
                .andReturn().getResponse();

        Assert.isTrue(response.getStatus() == 200, "The player does not exist.");

        response = this.mockMvc.perform(put("/api/players/associateGameToPlayer/" +
                        validPlayerId + "/" +
                        invalidGameName))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn().getResponse();

        Assert.isTrue(response.getStatus() == 400, "Expected a 400, bad request response.");
        Assert.isTrue(response.getContentAsString().contains("Invalid parameter for game"),
                "The request should have not assigned the game: " + invalidGameName + " to the player.");
    }
}
