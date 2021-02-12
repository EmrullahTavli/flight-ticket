package com.finartz.flightticket.web.handler;

import com.finartz.flightticket.web.exception.FlightException;
import com.finartz.flightticket.web.model.ErrorDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@ControllerAdvice
public class FlightExceptionHandler {
    @ExceptionHandler(value = FlightException.class)
    public ResponseEntity<Object> handleNumberDocumentException(FlightException e) {
        return ResponseEntity.badRequest().body(new ErrorDto(e.getMessage()));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<List<String>> validationConstraintErrorHandler(ConstraintViolationException exception) {
        List<String> errors = new ArrayList<>(exception.getConstraintViolations().size());
        exception.getConstraintViolations().forEach(constraintViolation ->
                errors.add(constraintViolation.getPropertyPath() + ":" + constraintViolation.getMessage()));
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<String>> validationMethodArgumentErrorHandler(MethodArgumentNotValidException exception) {
        List<String> errors = new ArrayList<>(exception.getFieldErrorCount());
        exception.getFieldErrors().forEach(fieldError ->
                errors.add(fieldError.getField() + ":" + fieldError.getDefaultMessage()));
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> handleException(Exception e, WebRequest request) {
        log.error("Error on request {}", ((ServletWebRequest) request).getRequest().getServletPath(), e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorDto("Something went wrong!"));
    }
}
