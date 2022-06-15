package com.itau.pixservice.resources.repositories.entities;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import static com.itau.pixservice.domain.constants.EntitiesSize.*;
import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "CLIENT")
@Data
public class ClientJpa implements Serializable {

    @Id
    @Column(name = "CLIENT_ID", nullable = false)
    @GeneratedValue(strategy = IDENTITY, generator = "client_id_seq")
    private Integer clientId;

    @Column(name = "FIRST_NAME", nullable = false, length = CLIENT_FIRST_NAME_SIZE)
    private String firstName;

    @Column(name = "LAST_NAME", nullable = true, length = CLIENT_LAST_NAME_SIZE)
    private String lastName;

    @Column(name = "PERSON_TYPE", nullable = false, length = CLIENT_PERSON_TYPE_SIZE)
    private String personType;

    @OneToMany(mappedBy = "client", cascade = ALL, fetch = LAZY)
    private List<AccountJpa> accounts;

    @Column(name = "CREATED_AT", nullable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "UPDATED_AT")
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
