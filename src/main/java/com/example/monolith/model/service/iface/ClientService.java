package com.example.monolith.model.service.iface;

import com.example.monolith.model.dto.Client;

public interface ClientService {
    Client create(String email);
    Client create(String email, String name);
    Client create(String email, String name, String lastName);
    Client create(String email, String name, String lastName, String middleName);
    Client get(String email);
    Client update(Client client);
    void delete(String email);
    void restore(String email);
}
