package com.llo.domain.exception;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String mensagem) {
        super(mensagem);
    }

}
