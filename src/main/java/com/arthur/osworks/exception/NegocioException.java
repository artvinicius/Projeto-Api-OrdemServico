package com.arthur.osworks.exception;

//Representa um erro de domínio um erro de negócio
public class NegocioException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public NegocioException(String message) {
        super(message);
    }

}