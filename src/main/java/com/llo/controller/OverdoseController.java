package com.llo.controller;

import com.llo.domain.model.entity.Medicine;
import com.llo.domain.service.MedicineService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.inject.Inject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Tag(name = "Medicine")
@Controller
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class OverdoseController {

    private final MedicineService medicineService;

    @Get(uri = "/medicines", produces = "application/json")
    @Operation(summary = "Get all medicines", description = "Get all medicines with details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Data queried successfully!"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error - Erro inesperado no processamento de dados")
    })
    Flux<Medicine> getMedicines() {
        return this.medicineService.getAllMedicines();
    }

    @Get(uri = "/medicine/{name}", produces = "application/json")
    @Operation(summary = "Get a respective medicine search", description = "Get a respective medicine search by name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Data queried successfully!"),
            @ApiResponse(responseCode = "404", description = "Not Found - Medicine not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error - Erro inesperado no processamento de dados")
    })
    Mono<HttpResponse<Medicine>> getMedicine(@PathVariable(name = "name") String name) {
        return this.medicineService.getMedicine(name)
                .flatMap(result -> Mono.just(HttpResponse.ok(result)));
    }


}