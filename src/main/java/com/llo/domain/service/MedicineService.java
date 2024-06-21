package com.llo.domain.service;

import com.llo.domain.model.dto.MedicineDTO;
import com.llo.domain.model.entity.Medicine;
import com.llo.workflow.context.MedicineContext;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MedicineService {

    Flux<Medicine> getAllMedicines();

    Mono<Medicine> getMedicine(String name);

    Mono<MedicineDTO> saveMedicine(MedicineDTO medicine);

    Mono<MedicineDTO> updateMedicine(String nameMedicine, MedicineContext medicineContext);

    Mono<Void> deleteMedicine(String nameMedicine);

}
