package com.llo.domain.service.impl;

import com.llo.domain.exception.BusinessException;
import com.llo.domain.exception.NotFoundException;
import com.llo.domain.model.dto.MedicineDTO;
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

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

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
                .switchIfEmpty(Mono.error(new NotFoundException("Medicine not found!")))
                .single();
    }

    @Override
    public Mono<MedicineDTO> saveMedicine(MedicineDTO medicineDTO) {
        return this.medicineRepository.findByName(medicineDTO.getName())
                .filter(medicine -> {
                    if (Objects.nonNull(medicine))
                        throw new BusinessException("Medicine already registered!");
                    return true;
                })
                .switchIfEmpty(this.defineMedicine(medicineDTO))
                .flatMap(this::save)
                .flatMap(medicine -> Mono.just(medicineDTO))
                .single();
    }

    @Override
    public Mono<MedicineDTO> updateMedicine(String nameMedicine, MedicineContext medicineContext) {
        return this.medicineRepository.findByName(nameMedicine)
                .switchIfEmpty(Mono.error(new NotFoundException("Medicine not found!")))
                .flatMap(medicine -> this.defineMedicineUpdate(medicine, medicineContext))
                .flatMap(this::update)
                .flatMap(medicineUpdated -> {
                    var medicineDTO = MedicineDTO.builder()
                            .name(medicineUpdated.getName())
                            .description(medicineUpdated.getDescription())
                            .risk(medicineUpdated.getRisk())
                            .dateUpdate(medicineUpdated.getDateUpdate())
                            .dataCreate(medicineUpdated.getDateCreate())
                            .build();
                    return Mono.just(medicineDTO);
                })
                .single();
    }

    @Override
    public Mono<Void> deleteMedicine(String nameMedicine) {
        return this.medicineRepository.deleteByName(nameMedicine)
                .doOnSuccess(l -> log.info("Register deleted sucess!"));
    }

    private Mono<Medicine> save(Medicine medicine) {
        return this.medicineRepository.save(medicine);
    }

    private Mono<Medicine> update(Medicine medicine) {
        return this.medicineRepository.update(medicine);
    }

    private Mono<Medicine> defineMedicine(MedicineDTO medicineDTO) {
        var medicine = Medicine.builder()
                .id(UUID.randomUUID()) //TODO adicionar lógica na entidade
                .risk(medicineDTO.getRisk())
                .dateCreate(LocalDateTime.now())
                .name(medicineDTO.getName())
                .description(medicineDTO.getDescription())
                .build();

        return Mono.just(medicine);
    }

    private Mono<Medicine> defineMedicineUpdate(Medicine medicine, MedicineContext medicineContext) {
        medicine.setDateUpdate(LocalDateTime.now());
        medicine.setDescription(medicineContext.getDescription());
        medicine.setRisk(medicineContext.getRisk());
        return Mono.just(medicine);
    }
}
