package com.llo.domain.model.dto;

import io.micronaut.serde.annotation.Serdeable;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Serdeable
@Builder
@Data
public class MedicineDTO implements Serializable {

    private String name;
    private String description;
    private int risk;
    private LocalDateTime dataCreate;
    private LocalDateTime dateUpdate;
}
