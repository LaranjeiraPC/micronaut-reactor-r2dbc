package com.llo.domain.repository;

import com.llo.domain.model.entity.Medicine;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.r2dbc.annotation.R2dbcRepository;
import io.micronaut.data.repository.reactive.ReactiveStreamsCrudRepository;
import reactor.core.publisher.Flux;

import java.util.UUID;

@R2dbcRepository(dialect = Dialect.POSTGRES)
public interface MedicineRepository extends ReactiveStreamsCrudRepository<Medicine, UUID> {
    Flux<Medicine> findAll();
}
