package com.itau.group.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;

import static com.itau.group.utils.ResponseStatus.FAIL;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ExceptionFormat> handleConflict(ConflictException e) {
        ExceptionFormat exceptionAnswer = createExceptionAnswer(e);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exceptionAnswer);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionFormat> handleNotFound(NotFoundException e) {
        ExceptionFormat exceptionAnswer = createExceptionAnswer(e);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionAnswer);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionFormat> handleBadRequest(MethodArgumentNotValidException e) {
        ExceptionFormat exceptionAnswer = createExceptionAnswer(e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionAnswer);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ExceptionFormat> handleMethodNotSupported(HttpRequestMethodNotSupportedException e) {
        ExceptionFormat exceptionAnswer = createExceptionAnswer(e);
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(exceptionAnswer);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ExceptionFormat> handleArgumentTypeMismatch(MethodArgumentTypeMismatchException e) {
        ExceptionFormat exceptionAnswer = createExceptionAnswer(e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionAnswer);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ExceptionFormat> handleArgumentTypeMismatch(HttpMessageNotReadableException e) {
        ExceptionFormat exceptionAnswer = createExceptionAnswer(e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionAnswer);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionFormat> handleUnmappedException(Exception e) {
        ExceptionFormat exceptionAnswer = createExceptionAnswer(e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exceptionAnswer);
    }

    private ExceptionFormat createExceptionAnswer(Exception e) {
        return ExceptionFormat.builder()
                .status(FAIL)
                .timestamp(LocalDateTime.now())
                .message(e.getMessage()).build();
    }
}
