package com.example.monolith.model.service.impl;

import com.example.monolith.model.dto.ActivationReference;
import com.example.monolith.model.dto.Client;
import com.example.monolith.model.repository.ActivationReferenceRepository;
import com.example.monolith.model.repository.ClientRepository;
import com.example.monolith.model.service.iface.ActivationReferenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ActivationReferenceServiceImpl implements ActivationReferenceService {

    private ActivationReferenceRepository activationReferenceRepository;
    private ClientRepository clientRepository;

    @Override
    public ActivationReference create(String email) throws UsernameNotFoundException {
        return activationReferenceRepository.saveAndFlush(ActivationReference.builder()
                .client(clientRepository.getByEmail(email).orElseThrow(
                        () -> new UsernameNotFoundException("")))
                .build());
    }

    @Override
    public ActivationReference create(Client client) throws UsernameNotFoundException {
        if (clientRepository.exists(Example.of(client, ExampleMatcher.matching()))) {
            return activationReferenceRepository.saveAndFlush(ActivationReference.builder()
                    .client(client)
                    .build());
        }
        throw new UsernameNotFoundException("");
    }

    @Override
    public void setValid(ActivationReference activationReference) {

    }

    @Override
    public void setInvalid(ActivationReference activationReference) {

    }
}
