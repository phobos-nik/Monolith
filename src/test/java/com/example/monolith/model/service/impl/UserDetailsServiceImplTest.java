package com.example.monolith.model.service.impl;

import com.example.monolith.TestAppConfig;
import com.example.monolith.model.repository.ClientRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockBeans;
import org.springframework.context.annotation.Import;
import org.springframework.security.core.userdetails.UserDetailsService;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = UserDetailsServiceImpl.class)
@Import(TestAppConfig.class)
@MockBeans({
        @MockBean(ClientRepository.class)
})
class UserDetailsServiceImplTest {

    @Autowired
    private UserDetailsService userDetailsService;

    @Test
    void smokeTest() {
        assertNotNull(userDetailsService);
    }

}