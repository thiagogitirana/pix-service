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
@Table(name = "ACCOUNT")
@Data
public class AccountJpa implements Serializable {

    @Id
    @Column(name = "ACCOUNT_ID", nullable = false)
    @GeneratedValue(strategy = IDENTITY, generator = "account_id_seq")
    private Integer accountId;

    @Column(name = "BRANCH_NUMBER", nullable = false, length = ACCOUNT_BRANCH_NUMBER_SIZE)
    private Integer branchNumber;

    @Column(name = "ACCOUNT_NUMBER", nullable = false, length = ACCOUNT_NUMBER_SIZE)
    private Integer accountNumber;

    @Column(name = "ACCOUNT_TYPE", nullable = false, length = ACCOUNT_TYPE_SIZE)
    private String accountType;

    @OneToMany(mappedBy = "account", cascade = ALL, fetch = LAZY)
    private List<PixKeyJpa> pixKeys;

    @ManyToOne
    @JoinColumn(name = "CLIENT_ID")
    private ClientJpa client;

    @Column(name = "CREATED_AT", nullable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "UPDATED_AT")
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
