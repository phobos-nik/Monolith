package com.example.monolith.model.repository;

import com.example.monolith.model.dto.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface ClientRepository extends JpaRepository<Client, UUID> {
    Optional<Client> getByEmail(String email);

    Long countByValidTrue();

    Long countByValidFalse();

    @Modifying
    @Query("update Client client set client.valid=false where client.email = :email")
    void setInvalid(@Param("email") String email);

    @Modifying
    @Query("update Client client set client.valid=true where client.email = :email")
    void setValid(@Param("email") String email);
}
