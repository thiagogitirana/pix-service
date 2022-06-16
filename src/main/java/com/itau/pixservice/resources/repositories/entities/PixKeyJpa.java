package com.itau.pixservice.resources.repositories.entities;

import com.itau.pixservice.domain.entities.enums.Status;
import lombok.Data;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import static com.itau.pixservice.domain.constants.EntitiesSize.KEY_VALUE_SIZE;

@Entity
@Table(name = "PIX_KEY")
@Data
@SQLDelete(sql = "UPDATE PIX_KEY set STATUS = 'INACTIVE', UPDATED_AT = CURRENT_TIMESTAMP where PIX_KEY_ID=?")
@Where(clause = "STATUS = 'ACTIVE'")
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

    @Column(name = "STATUS")
    private String status;

    @Column(name = "CREATED_AT")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "UPDATED_AT")
    private LocalDateTime updatedAt;
}
