package com.example.monolith.model.dto;

import lombok.*;
import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;
import java.util.concurrent.CopyOnWriteArraySet;

@Getter
@EqualsAndHashCode(of = {"email", "name", "lastName", "middleName"})
@ToString(of = {"id"})
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
@NamedEntityGraphs({
        @NamedEntityGraph(
                name = "client_activation_references",
                attributeNodes = @NamedAttributeNode("activationReferences"))})
@EntityListeners(AuditingEntityListener.class)
public class Client implements UserDetails {

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

    @Column(nullable = false)
    @Setter
    private String hashedPassword;

    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private final Date createdDate;

    @Column(nullable = false)
    @Setter
    private boolean valid;

    @OneToMany
    private final Set<ActivationReference> activationReferences;

    @Builder(toBuilder = true)
    Client(String email,
           String name,
           String lastName,
           String middleName,
           String hashedPassword) {
        this.email = email;
        this.name = name;
        this.lastName = lastName;
        this.middleName = middleName;
        this.hashedPassword = hashedPassword;
        this.valid = false;
        this.createdDate = new Date();
        this.activationReferences = new CopyOnWriteArraySet<>();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptySet();
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.valid;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.valid;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.valid;
    }

    @Override
    public boolean isEnabled() {
        return this.valid;
    }
}
