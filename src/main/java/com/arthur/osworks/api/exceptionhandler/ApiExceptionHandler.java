package com.arthur.osworks.api.exceptionhandler;

import java.time.LocalDateTime;
import java.util.ArrayList;

import com.arthur.osworks.domain.model.Cliente;
import com.arthur.osworks.exception.NegocioException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler { // Para simplificar os erros que mostra ao cliente porem precisa tratar

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<Object> handleNegocio(NegocioException ex, WebRequest request) {
       
        var status = HttpStatus.BAD_REQUEST; 
        var problema = new Problema();
        problema.setStatus(status.value());
        problema.setTitulo(ex.getMessage());
        problema.setDataHora(LocalDateTime.now());

        return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
    }
    
    // Gerar auto handleMEthodArgumentNotValid
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {

        var campo = new ArrayList<Problema.Campo>();

        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            String nome = ((FieldError) error).getField();
            String mensagem = error.getDefaultMessage();

            campo.add(new Problema.Campo(nome, mensagem));
        }

        var problema = new Problema();
        problema.setStatus(status.value());
        problema.setTitulo("Um ou mais campos então invalidos. " + "Faça o preenchimento correto e tente novamente");
        problema.setDataHora(LocalDateTime.now());
        problema.setCampo(campo);

        return super.handleExceptionInternal(ex, problema, headers, status, request);

    }
}