package ru.itis.stockmarket.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpServerErrorException;


public class AlreadyExistsException extends HttpServerErrorException {
    public AlreadyExistsException(String statusText) {
        super(HttpStatus.CONFLICT, statusText);
    }
}
