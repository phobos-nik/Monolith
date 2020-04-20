package com.example.monolith.model.repository;

import com.example.monolith.TestAppConfig;
import com.example.monolith.model.dto.Client;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Import(TestAppConfig.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class RepositoryTests {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ActivationReferenceRepository activationReferenceRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private Client source;
    private final String emailToSet = "updated@test.com";

    @PostConstruct
    public void setUp() {
        source = Client.builder()
                .email("test@test.com")
                .hashedPassword(passwordEncoder.encode("s3(urep@$$\\/\\/0r|)"))
                .name("test_name")
                .lastName("test_last_name")
                .middleName("test_middle_name")
                .build();
    }

    @Test
    void smokeTest() {
        assertNotNull(clientRepository);
    }

    @Test
    void jpa() {
        Client saved = clientRepository.saveAndFlush(source);
        assertEquals(source, saved);
        assertEquals(1, clientRepository.countByValidTrue());
        assertEquals(1, clientRepository.countByValidFalse());
        assertEquals(2, clientRepository.count());
//        assertThrows(DataIntegrityViolationException.class, () -> clientRepository.saveAndFlush(source));
        assertEquals(
                source,
                clientRepository.getByEmail(source.getEmail()).orElse(Client.builder().build()));

        Client client = clientRepository
                .getByEmail(source.getEmail())
                .orElse(Client.builder().build());
        client.setEmail(emailToSet);
        assertEquals(emailToSet, client.getEmail());
        assertEquals(client, clientRepository.saveAndFlush(client));

        clientRepository.setValid(emailToSet);
        assertEquals(0, clientRepository.countByValidFalse());
        assertEquals(2, clientRepository.countByValidTrue());

        clientRepository.setInvalid(emailToSet);
        assertEquals(1, clientRepository.countByValidTrue());
        assertEquals(1, clientRepository.countByValidFalse());
    }

}