package ru.itis.stockmarket.handlers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNullApi;
import org.springframework.lang.Nullable;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.itis.stockmarket.dtos.GeneralMessage;
import ru.itis.stockmarket.dtos.Status;
import ru.itis.stockmarket.dtos.ValidationError;
import ru.itis.stockmarket.exceptions.AlreadyExistsException;
import ru.itis.stockmarket.exceptions.NotFoundException;

import java.util.HashMap;
import java.util.Objects;

/**
 * Created by IntelliJ IDEA
 * Date: 14.05.2022
 * Time: 11:22 AM
 *
 * @author lordvidex
 * Name: Овамойо Олувадамилола Эванс
 * <p>
 * Desc:
 */
@ControllerAdvice
public class ValidationHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        // map all validation errors
        HashMap<String,String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });

        // get the request path
        String path = ((ServletWebRequest) request).getRequest().getRequestURI();
        // return our ValidationError class
        ValidationError err = ValidationError.builder()
                .errors(errors)
                .timestamp(System.currentTimeMillis())
                .description("Bad Request")
                .path(path)
                .status(Status.failure)
                .statusCode(status.value())
                .build();

        return new ResponseEntity<Object>(err,headers,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    ResponseEntity<ValidationError> handleNotFoundException(NotFoundException ex) {
        ValidationError err = ValidationError.builder()
                .description(ex.getStatusText())
                .status(Status.failure)
                .statusCode(404)
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }

    @ExceptionHandler(AlreadyExistsException.class)
    ResponseEntity<ValidationError> handleNotFoundException(AlreadyExistsException ex) {
        ValidationError err = ValidationError.builder()
                .description(ex.getStatusText())
                .status(Status.failure)
                .statusCode(ex.getStatusCode().value())
                .build();
        return ResponseEntity.status(err.getStatusCode()).body(err);
    }
    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String path = ((ServletWebRequest) request).getRequest().getRequestURI();
        ValidationError err = ValidationError.builder()
                .description(ex.getLocalizedMessage())
                .status(Status.failure)
                .statusCode(status.value())
                .path(path)
                .build();
        return ResponseEntity.status(status).headers(headers).body(err);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String path = ((ServletWebRequest) request).getRequest().getRequestURI();
        String _body = Objects.isNull(body) ? ex.getLocalizedMessage():body.toString();
        ValidationError err = ValidationError.builder()
                .description(_body)
                .statusCode(status.value())
                .status(Status.failure)
                .timestamp(System.currentTimeMillis())
                .path(path)
                .build();
        return ResponseEntity.status(status).headers(headers).body(err);
    }

    @ExceptionHandler(Exception.class)
    ResponseEntity<GeneralMessage<?>> handleAllErrors(Exception e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                body(GeneralMessage.builder()
                        .status(Status.failure)
                        .description("Something went wrong")
                        .data(e.getMessage())
                        .build());
    }
}
