package com.example.monolith.model.service.impl;

import com.example.monolith.TestAppConfig;
import com.example.monolith.model.repository.ActivationReferenceRepository;
import com.example.monolith.model.repository.ClientRepository;
import com.example.monolith.model.service.iface.ActivationReferenceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockBeans;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = ActivationReferenceServiceImpl.class)
@Import(TestAppConfig.class)
@MockBeans({
        @MockBean(ActivationReferenceRepository.class),
        @MockBean(ClientRepository.class)
})
class ActivationReferenceServiceImplTest {

    @Autowired
    private ActivationReferenceService activationReferenceService;

    @Test
    void smokeTest() {
        assertNotNull(activationReferenceService);
    }

}