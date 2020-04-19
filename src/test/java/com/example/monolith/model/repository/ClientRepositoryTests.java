package com.example.monolith.model.repository;

import com.example.monolith.model.dto.Client;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ClientRepositoryTests {

    @Autowired
    private ClientRepository clientRepository;

    private final Client source = Client.builder()
            .email("test@test.com")
            .name("test_name")
            .lastName("test_last_name")
            .middleName("test_middle_name")
            .build();
    private final String emailToSet = "updated@test.com";

    @Test
    void smokeTest() {
        assertNotNull(clientRepository);
    }

    @Test
    @Order(1)
    void create() {
        Client saved = clientRepository.saveAndFlush(source);
        assertEquals(source, saved);
    }

    @Test
    @Order(1)
    void createEmailCollision() {
        assertThrows(DataIntegrityViolationException.class, () -> clientRepository.saveAndFlush(source));
    }

    @Test
    @Order(2)
    void countUsers() {
        assertEquals(2, clientRepository.count());
    }

    @Test
    @Order(2)
    void find() {
        assertEquals(source, clientRepository.getByEmail(source.getEmail()));
    }

    @Test
    @Order(3)
    void update() {
        Client client = clientRepository.getByEmail(source.getEmail());
        client.setEmail(emailToSet);
        assertEquals(emailToSet, client.getEmail());
        assertEquals(client, clientRepository.saveAndFlush(client));
        assertEquals(2, clientRepository.count());
    }

    @Test
    @Order(4)
    void delete() {
        clientRepository.deleteByEmail(emailToSet);
        assertEquals(1, clientRepository.count());
    }

    @Test
    @Order(5)
    void deleteNotExistingUser() {
        clientRepository.deleteByEmail(emailToSet);
    }
}