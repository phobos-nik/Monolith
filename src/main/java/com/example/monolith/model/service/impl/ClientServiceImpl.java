package com.example.monolith.model.service.impl;

import com.example.monolith.model.dto.Client;
import com.example.monolith.model.repository.ClientRepository;
import com.example.monolith.model.service.iface.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    @Override
    public Client create(String email) {
        return create(email, null, null, null);
    }

    @Override
    public Client create(String email, String name) {
        return create(email, name, null, null);
    }

    @Override
    public Client create(String email, String name, String lastName) {
        return create(email, name, lastName, null);
    }

    @Override
    public Client create(String email, String name, String lastName, String middleName) {
        return clientRepository.saveAndFlush(Client.builder()
                .email(email)
                .name(name)
                .lastName(lastName)
                .middleName(middleName)
                .build());
    }

    @Override
    public Client get(String email) throws UsernameNotFoundException {
        return clientRepository.getByEmail(email).orElseThrow(() -> new UsernameNotFoundException(""));
    }

    @Override
    public Client update(Client client) {
        return null;
    }

    @Override
    public void delete(String email) {
        clientRepository.setInvalid(email);
    }

    @Override
    public void restore(String email) {
        clientRepository.setValid(email);
    }
}
