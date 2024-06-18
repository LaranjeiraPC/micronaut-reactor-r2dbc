package com.llo.domain.model.dto;

import io.micronaut.serde.annotation.Serdeable;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Builder
@Data
@Serdeable
public class ErrorDTO implements Serializable {

    private String message;

}
