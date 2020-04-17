package com.example.monolith.model.repository;

import com.example.monolith.model.dto.Client;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ClientRepositoryTests {

    @Autowired
    private ClientRepository clientRepository;

    @Test
    void smokeTest() {
        assertNotNull(clientRepository);
    }

    @Test
    void create() {
        Client source = Client.builder()
                .email("test@test.com")
                .name("test_name")
                .lastName("test_last_name")
                .middleName("test_middle_name")
                .build();
        Client saved = clientRepository.saveAndFlush(source);
        assertEquals(source, saved);
    }
}