package com.example.monolith.model.repository;

import com.example.monolith.model.dto.ActivationReference;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ActivationReferenceRepository extends JpaRepository<ActivationReference, UUID> {
}
