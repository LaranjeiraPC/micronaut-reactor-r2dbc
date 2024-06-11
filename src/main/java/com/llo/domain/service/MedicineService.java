package com.llo.domain.service;

import com.llo.domain.model.entity.Medicine;
import reactor.core.publisher.Flux;

public interface MedicineService {

    Flux<Medicine> getAllMedicines();

}
