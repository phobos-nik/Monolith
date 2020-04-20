package com.example.monolith.model.service.iface;

import com.example.monolith.model.dto.ActivationReference;
import com.example.monolith.model.dto.Client;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface ActivationReferenceService {
    ActivationReference create(String email) throws UsernameNotFoundException;
    ActivationReference create(Client client) throws UsernameNotFoundException;
    void setValid(ActivationReference activationReference);
    void setInvalid(ActivationReference activationReference);
}
