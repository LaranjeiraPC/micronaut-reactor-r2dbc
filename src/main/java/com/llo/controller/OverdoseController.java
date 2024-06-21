package com.llo.controller;

import com.llo.domain.model.dto.MedicineDTO;
import com.llo.domain.model.entity.Medicine;
import com.llo.domain.service.MedicineService;
import com.llo.workflow.context.MedicineContext;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.inject.Inject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.llo.AppConstants.*;

@Slf4j
@Tag(name = "Medicine")
@Controller
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class OverdoseController {

    private final MedicineService medicineService;

    @Get(uri = "/medicines", produces = "application/json")
    @Operation(summary = "Get all medicines", description = "Get all medicines with details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = DATA_QUERIED_SUCCESSFULLY),
            @ApiResponse(responseCode = "500", description = INTERNAL_SERVER_ERROR_UNEXPECTED_ERROR_DATA_PROCESSING)
    })
    Flux<Medicine> getMedicines() {
        return this.medicineService.getAllMedicines();
    }

    @Get(uri = "/medicine/{name}", produces = "application/json")
    @Operation(summary = "Get a respective medicine search", description = "Get a respective medicine search by name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = DATA_QUERIED_SUCCESSFULLY),
            @ApiResponse(responseCode = "404", description = NOT_FOUND_MEDICINE),
            @ApiResponse(responseCode = "500", description = INTERNAL_SERVER_ERROR_UNEXPECTED_ERROR_DATA_PROCESSING)
    })
    Mono<HttpResponse<Medicine>> getMedicine(@PathVariable(name = "name") String name) {
        return this.medicineService.getMedicine(name)
                .flatMap(result -> Mono.just(HttpResponse.ok(result)));
    }

    @Post(uri = "/medicine", produces = "application/json")
    @Operation(summary = "Save a medicine", description = "Save a medicine")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = MEDICINE_CREATE),
            @ApiResponse(responseCode = "400", description = BAD_REQUEST_MEDICINE_ALREADY_REGISTERED),
            @ApiResponse(responseCode = "500", description = INTERNAL_SERVER_ERROR_UNEXPECTED_ERROR_DATA_PROCESSING)
    })
    Mono<HttpResponse<MedicineDTO>> saveMedicine(@RequestBody @Body MedicineDTO medicineDTO) {
        return this.medicineService.saveMedicine(medicineDTO)
                .flatMap(result -> Mono.just(HttpResponse.created(result)));
    }

    @Delete(uri = "/medicine/{name}", produces = "application/json")
    @Operation(summary = "Delete a respective medicine", description = "Delete a respective medicine search by name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = MEDICINE_DELETED),
            @ApiResponse(responseCode = "404", description = NOT_FOUND_MEDICINE),
            @ApiResponse(responseCode = "500", description = INTERNAL_SERVER_ERROR_UNEXPECTED_ERROR_DATA_PROCESSING)
    })
    Mono<HttpResponse<Void>> deleteMedicine(@PathVariable(name = "name") String name) {
        return this.medicineService.deleteMedicine(name)
                .then(Mono.just(HttpResponse.noContent()));
    }

    @Put(uri = "/medicine/{name}", produces = "application/json")
    @Operation(summary = "Update a medicine", description = "Update a medicine by name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = MEDICINE_UPDATE),
            @ApiResponse(responseCode = "404", description = NOT_FOUND_MEDICINE),
            @ApiResponse(responseCode = "500", description = INTERNAL_SERVER_ERROR_UNEXPECTED_ERROR_DATA_PROCESSING)
    })
    Mono<HttpResponse<MedicineDTO>> updateMedicine(@RequestBody @Body MedicineContext medicineContext, @PathVariable(name = "name") String name) {
        return this.medicineService.updateMedicine(name, medicineContext)
                .flatMap(result -> Mono.just(HttpResponse.ok(result)));
    }

}