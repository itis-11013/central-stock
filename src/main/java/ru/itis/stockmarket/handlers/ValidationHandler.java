package ru.itis.stockmarket.handlers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.itis.stockmarket.models.ValidationError;

import java.util.HashMap;

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
                .messages(errors)
                .timestamp(System.currentTimeMillis())
                .error("Bad Request")
                .path(path)
                .status(status.value())
                .build();
        return new ResponseEntity<Object>(err,headers,HttpStatus.BAD_REQUEST);
    }
}
