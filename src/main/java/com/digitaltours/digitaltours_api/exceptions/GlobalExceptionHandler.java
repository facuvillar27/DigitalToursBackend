package com.digitaltours.digitaltours_api.exceptions;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

import com.digitaltours.digitaltours_api.dto.ApiResponseDTO;
import com.digitaltours.digitaltours_api.utils.AppMessages;
import com.digitaltours.digitaltours_api.utils.Meta;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Path;

@SuppressWarnings("rawtypes")
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {ResponseStatusException.class})
    public ResponseEntity<Object> handleResponseStatusException(ResponseStatusException ex) {
        Meta meta = new Meta();
        meta.setDevMessage(null);
        meta.setStatus(AppMessages.CLIENT_ERROR);
        meta.setStatusCode(ex.getStatusCode().value());
        meta.setMessage(ex.getReason());
        meta.setTimestamp(LocalDateTime.now().toString());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        ApiResponseDTO apiResponse = new ApiResponseDTO(meta, null);
        return new ResponseEntity<>(apiResponse, httpHeaders, ex.getStatusCode());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleInvalidArgument(MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();
        StringBuilder mensajeError = new StringBuilder();
        Meta meta = new Meta();

        meta.setDevMessage(null);
        meta.setStatus(AppMessages.BAD_REQUEST_ERROR);
        meta.setStatusCode(ex.getStatusCode().value());
        meta.setTransactionID(java.util.UUID.randomUUID().toString());

        ex.getBindingResult().getFieldErrors().forEach(fieldError -> {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        });

        for (String key : errors.keySet()) {
            mensajeError.append(errors.get(key) + ", ");
        }
        mensajeError.delete(mensajeError.length()-2, mensajeError.length());

        meta.setMessage(mensajeError.toString());
        meta.setTimestamp(LocalDateTime.now().toString());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        ApiResponseDTO apiResponse = new ApiResponseDTO(meta, null);
        return new ResponseEntity<>(apiResponse, httpHeaders, ex.getStatusCode());

    }

    @ExceptionHandler(ConstraintViolationException.class)
    public  ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex) {

        java.util.Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
        Map<Path, String> fieldException = new HashMap<>();
        StringBuilder mensajeError = new StringBuilder();
        Meta meta = new Meta();

        meta.setDevMessage(null);
        meta.setStatus(AppMessages.BAD_REQUEST_ERROR);
        meta.setStatusCode(HttpStatus.BAD_REQUEST.value());

        constraintViolations.stream().forEach(e -> {
            fieldException.put(e.getPropertyPath(), e.getMessage());
        });

        for (Path key : fieldException.keySet()) {
            mensajeError.append(fieldException.get(key) + ", ");
        }
        mensajeError.delete(mensajeError.length()-2, mensajeError.length());

        meta.setMessage(mensajeError.toString());
        meta.setTimestamp(LocalDateTime.now().toString());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        ApiResponseDTO apiResponse = new ApiResponseDTO(meta, null);
        return new ResponseEntity<>(apiResponse, httpHeaders, meta.getStatusCode());
    }

    /**
     * Cualquier excepción que no sea atendida será tratada en en este método.
     * @param runtimeException
     * @param webRequest
     * @return ResponseEntity<Object>
     */
    @ExceptionHandler(value = {Exception.class})
    protected ResponseEntity<Object> handleException(Exception ex, RuntimeException runtimeException) {
        Meta meta = new Meta();
        meta.setStatus(AppMessages.ERROR);
        meta.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        meta.setDevMessage(runtimeException.getMessage() == null ? runtimeException.getClass().toString() : runtimeException.getMessage());
        meta.setTimestamp(LocalDateTime.now().toString());
        meta.setTransactionID(null);
        ApiResponseDTO apiResponse = new ApiResponseDTO(meta, null);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        return new ResponseEntity<>(apiResponse, httpHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}