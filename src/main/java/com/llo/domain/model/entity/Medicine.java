package com.llo.domain.model.entity;

import io.micronaut.data.annotation.DateCreated;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.persistence.Column;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.UuidGenerator;
import reactor.util.annotation.Nullable;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Serdeable
@MappedEntity
@Builder
@Data
@ToString
@Table(name = "medicine", schema = "overdose")
public class Medicine implements Serializable {
    @Id
    @UuidGenerator(style = UuidGenerator.Style.AUTO)
    private UUID id;
    private String name;
    private String description;
    private int risk;

    @DateCreated
    @Column(name = "date_create")
    private LocalDateTime dateCreate;

    @Nullable
    @Column(name = "date_update", nullable = true)
    private LocalDateTime dateUpdate;
}
