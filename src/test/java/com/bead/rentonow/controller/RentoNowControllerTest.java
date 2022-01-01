package com.bead.rentonow.controller;

import com.bead.rentonow.dto.PersonDto;
import com.bead.rentonow.front.ApiResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RentoNowControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void greetingShouldReturnDefaultMessage() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/ping", String.class)).contains("The API is working");
    }

    @Test
    @DisplayName("Test for the number of users")
    public void usersShouldBeAtLeastOneAdmin() throws Exception {

        ApiResponse<List<PersonDto>> response = this.restTemplate.withBasicAuth("admin","admin@123").getForObject("http://localhost:" + port + "/person", ApiResponse.class);

        List<PersonDto> currentUsers = response.getData();

        assertNotNull(currentUsers);
        assertTrue(currentUsers.size() >= 1, "At least an admin user should exist in the application!");
    }
}