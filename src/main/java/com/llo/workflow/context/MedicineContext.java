package com.llo.workflow.context;

import io.micronaut.serde.annotation.Serdeable;
import lombok.Builder;
import lombok.Data;

@Serdeable
@Builder
@Data
public class MedicineContext {

    private String description;
    private int risk;
}
