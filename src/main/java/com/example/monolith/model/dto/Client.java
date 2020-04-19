package com.example.monolith.model.dto;

import lombok.*;
import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Getter
@EqualsAndHashCode(of = {"email", "name", "lastName", "middleName"})
@ToString(of = {"id"})
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Entity
@Table(
        name = "clients",
        indexes = {
                @Index(
                        name = "client_id",
                        columnList = "id",
                        unique = true),
                @Index(
                        name = "client_email",
                        columnList = "email",
                        unique = true)})
@EntityListeners(AuditingEntityListener.class)
public class Client {

    @Id
    @Type(type = "uuid-char")
    @Column(unique = true, nullable = false, updatable = false)
    @GeneratedValue
    private UUID id;

    @Column(unique = true, nullable = false)
    @Setter
    private String email;

    @Column(nullable = false)
    @Setter
    private String name;

    @Column(nullable = false)
    @Setter
    private String lastName;

    @Column
    @Setter
    private String middleName;

    @Column(nullable = false, updatable = false)
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
