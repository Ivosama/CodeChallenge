package com.idts.codechallenge.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.util.Assert;

import java.util.LinkedHashMap;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HttpRequestWithPortTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void returnTheFirstPlayerInTheDatabaseTest() throws Exception {

        LinkedHashMap firstPlayerResponseObject = this.testRestTemplate.getForObject("http://localhost:" + port + "/" +
                "/api/players/getById/1", LinkedHashMap.class);

        Assert.isTrue(firstPlayerResponseObject.containsKey("name"),
                "The response object does not contain the name field");
        Assert.isTrue(firstPlayerResponseObject.get("name").equals("Mathias"),
                "The name of the first player is not right");
    }
}
