package com.example.monolith.model.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Getter
@EqualsAndHashCode(of = {"id"})
@ToString(of = {"id", "client"})
@Entity
@Table(name = "activation_references")
@EntityListeners(AuditingEntityListener.class)
public class ActivationReference {

    @Id
    @Type(type = "uuid-char")
    @Column(unique = true, nullable = false, updatable = false)
    @GeneratedValue
    private UUID id;

    @ManyToOne
    private final Client client;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, updatable = false)
    private final Date createdDate;

    @Column(nullable = false)
    private final boolean valid;

    @Builder(toBuilder = true)
    ActivationReference(Client client) {
        this.client = client;
        this.valid = true;
        this.createdDate = new Date();
    }
}
