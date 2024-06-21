package com.llo.domain.repository;

import com.llo.domain.model.entity.Medicine;
import io.micronaut.data.annotation.Query;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.r2dbc.annotation.R2dbcRepository;
import io.micronaut.data.repository.reactive.ReactorCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@R2dbcRepository(dialect = Dialect.POSTGRES)
public interface MedicineRepository extends ReactorCrudRepository<Medicine, UUID> {
    Flux<Medicine> findAll();

    Flux<Medicine> findByName(String name);

    @Query(value = "DELETE FROM overdose.medicine WHERE name = :nameMedicine")
    Mono<Void> deleteByName(String nameMedicine);
}
