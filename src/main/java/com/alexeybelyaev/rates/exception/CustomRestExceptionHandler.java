package com.alexeybelyaev.rates.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


import java.time.format.DateTimeParseException;


@Slf4j
@ControllerAdvice
public class CustomRestExceptionHandler extends ResponseEntityExceptionHandler {
    @Value("${datetime.pattern}")
    private String DATE_PATTERN;

    @ExceptionHandler(CodeNotCorrectException.class)
    ResponseEntity<Object> constraintViolationHandler(CodeNotCorrectException e){
        BaseError baseError = new BaseError(HttpStatus.BAD_REQUEST, "Не корректные входные параметры", e.getLocalizedMessage());
        log.warn(baseError.toString());
        ResponseEntity<Object> responseEntity = new ResponseEntity<Object>(
                baseError,
                new HttpHeaders(),
                baseError.getStatus());
        return  responseEntity;
    }

    @ExceptionHandler(DateTimeParseException.class)
    ResponseEntity<Object> constraintViolationHandler(DateTimeParseException e){
        BaseError baseError = new BaseError(HttpStatus.BAD_REQUEST, "Не корректные формат даты. Должна быть "+DATE_PATTERN, e.getLocalizedMessage());
        log.warn(baseError.toString());
        ResponseEntity<Object> responseEntity = new ResponseEntity<Object>(
                baseError,
                new HttpHeaders(),
                baseError.getStatus());
        return  responseEntity;
    }
}
