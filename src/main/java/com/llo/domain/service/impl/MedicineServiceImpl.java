package com.llo.domain.service.impl;

import com.llo.domain.model.entity.Medicine;
import com.llo.domain.repository.MedicineRepository;
import com.llo.domain.service.MedicineService;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@Singleton
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class MedicineServiceImpl implements MedicineService {

    private final MedicineRepository medicineRepository;

    @Override
    public Flux<Medicine> getAllMedicines() {
        return this.medicineRepository.findAll();
    }

}
