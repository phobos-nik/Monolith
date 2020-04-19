package com.example.monolith.model.repository;

import com.example.monolith.model.dto.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.UUID;

public interface ClientRepository extends JpaRepository<Client, UUID> {
    Client getByEmail(String email);

    @Transactional
    void deleteByEmail(String email);
}
