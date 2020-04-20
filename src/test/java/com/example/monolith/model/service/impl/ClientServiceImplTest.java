package com.example.monolith.model.service.impl;

import com.example.monolith.TestAppConfig;
import com.example.monolith.model.repository.ClientRepository;
import com.example.monolith.model.service.iface.ClientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockBeans;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = ClientServiceImpl.class)
@Import(TestAppConfig.class)
@MockBeans({
        @MockBean(ClientRepository.class)
})
class ClientServiceImplTest {

    @Autowired
    private ClientService clientService;

    @Test
    void smokeTest() {
        assertNotNull(clientService);
    }
}