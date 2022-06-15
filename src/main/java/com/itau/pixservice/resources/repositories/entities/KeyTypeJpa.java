package com.itau.pixservice.resources.repositories.entities;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

import static com.itau.pixservice.domain.constants.EntitiesSize.KEY_TYPE_DESCRIPTION_SIZE;

@Entity
@Table(name = "KEY_TYPE")
@Data
public class KeyTypeJpa implements Serializable {

    @Id
    @Column(name = "KEY_TYPE_ID", nullable = false)
    private Integer keyTypeId;

    @Column(name = "DESCRIPTION", nullable = false, length = KEY_TYPE_DESCRIPTION_SIZE)
    private String description;

    @Column(name = "CREATED_AT", nullable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "UPDATED_AT")
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
