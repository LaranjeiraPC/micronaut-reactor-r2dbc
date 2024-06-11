package com.llo.domain.model.entity;

import io.micronaut.data.annotation.GeneratedValue;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.persistence.Column;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;
import reactor.util.annotation.Nullable;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Serdeable
@MappedEntity
@Builder
@Data
@Table(name = "medicine", schema = "overdose")
public class Medicine implements Serializable {
    @Id
    @GeneratedValue(value = GeneratedValue.Type.UUID)
    private UUID id;
    private String name;
    private String description;
    private int risk;

    @Column(name = "date_create")
    private LocalDateTime dataCreate;

    @Nullable
    @Column(name = "date_update", nullable = true)
    private LocalDateTime dateUpdate;
}
