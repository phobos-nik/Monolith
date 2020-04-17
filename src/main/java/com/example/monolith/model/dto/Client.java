package com.example.monolith.model.dto;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Getter
@EqualsAndHashCode(of = {"email", "name", "lastName", "middleName"})
@ToString(of = {"id"})
@EntityListeners(AuditingEntityListener.class)
public class Client {

    @Id
    @GeneratedValue
    private UUID id;

    @Column
    @Setter
    private String email;

    @Column
    @Setter
    private String name;

    @Column
    @Setter
    private String lastName;

    @Column
    @Setter
    private String middleName;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    @Getter
    @CreatedDate
    private Date createdDate;

    @Builder(toBuilder = true)
    Client(String email,
           String name,
           String lastName,
           String middleName) {
        this.email = email;
        this.name = name;
        this.lastName = lastName;
        this.middleName = middleName;
        this.createdDate = new Date();
    }
}
