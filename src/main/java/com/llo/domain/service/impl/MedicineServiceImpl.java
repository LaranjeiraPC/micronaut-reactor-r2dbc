package com.llo.domain.service.impl;

import com.llo.domain.exception.BusinessException;
import com.llo.domain.model.entity.Medicine;
import com.llo.domain.repository.MedicineRepository;
import com.llo.domain.service.MedicineService;
import com.llo.workflow.context.MedicineContext;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Singleton
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class MedicineServiceImpl implements MedicineService {

    private final MedicineRepository medicineRepository;

    @Override
    public Flux<Medicine> getAllMedicines() {
        return this.medicineRepository.findAll();
    }

    @Override
    public Mono<Medicine> getMedicine(String name) {
        return this.medicineRepository.findByName(name)
                .switchIfEmpty(Mono.error(new BusinessException("Medicine not found!")))
                .singleOrEmpty();
    }

    @Override
    public Mono<Medicine> saveMedicine(Medicine medicine) {
        return null;
    }

    @Override
    public Mono<Medicine> updateMedicine(String nameMedicine, MedicineContext medicineContext) {
        return null;
    }

    @Override
    public void deleteMedicine(String nameMedicine) {

    }

}
