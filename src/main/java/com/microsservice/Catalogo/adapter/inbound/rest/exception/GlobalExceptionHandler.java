package com.microsservice.Catalogo.adapter.inbound.rest.exception;

import com.microsservice.Catalogo.response.ResponseApi;
import jakarta.validation.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ResponseApi<Void>> handleResponseStatusException(
            ResponseStatusException ex) {

        return ResponseEntity.status(ex.getStatusCode())
                .body(
                        new ResponseApi<>(
                                ex.getReason(),
                                null,
                                false
                        )
                );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseApi<Void>> handleException(
            Exception ex) {

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(
                        new ResponseApi<>(
                                "Erro interno no servidor. Tente novamente mais tarde.\"",
                                null,
                                false
                        )
                );
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ResponseApi<Void>> handleIllegalArgumentException(ValidationException ex) {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(
                        new ResponseApi<>(
                                ex.getMessage(),
                                null,
                                false
                        )
                );
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ResponseApi<Void>>handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(
                        new ResponseApi<>(
                                "Produto com o ID não encontrado.",
                                null,
                                false
                        )

                );
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ResponseApi<Void>>handleRuntimeException(NoSuchElementException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(
                        new ResponseApi<>(
                                ex.getMessage(),
                                null,
                                false
                        )
                );
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ResponseApi<Void>>handleRuntimeException(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(
                        new ResponseApi<>(
                                "Um erro inesperado ocorreu: " + ex.getMessage(),
                                null,
                                false
                        )
                );
    }
}