package com.itau.pixservice.resources.repositories.entities;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import static com.itau.pixservice.domain.constants.EntitiesSize.KEY_VALUE_SIZE;

@Entity
@Table(name = "PIX_KEY")
@Data
public class PixKeyJpa implements Serializable {

    @Id
    @Column(name = "PIX_KEY_ID", nullable = false)
    @GeneratedValue(generator = "custom-uuid")
    @GenericGenerator(
            name = "custom-uuid",
            strategy = "org.hibernate.id.UUIDGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(
                            name = "uuid_gen_strategy_class",
                            value = "org.hibernate.id.uuid.CustomVersionOneStrategy"
                    )
            }
    )
    private UUID pixKeyId;

    @Column(name = "VALUE", nullable = false, length = KEY_VALUE_SIZE)
    private String value;

    @ManyToOne
    @JoinColumn(name = "KEY_TYPE_ID")
    private KeyTypeJpa keyType;

    @ManyToOne
    @JoinColumn(name = "ACCOUNT_ID")
    private AccountJpa account;

    @Column(name = "CREATED_AT")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "UPDATED_AT")
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
