package com.llo.domain.exception;

import com.llo.domain.model.dto.ErrorDTO;
import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.server.exceptions.ExceptionHandler;
import jakarta.inject.Singleton;

@Produces
@Singleton
@Requires(classes = {BusinessException.class, ExceptionHandler.class})
public class BusinessExceptionHandler implements ExceptionHandler<BusinessException, HttpResponse<ErrorDTO>> {

    @Override
    public HttpResponse<ErrorDTO> handle(HttpRequest request, BusinessException exception) {
        var error = ErrorDTO.builder()
                .message(exception.getMessage())
                .build();
        return HttpResponse.badRequest(error);
    }

}