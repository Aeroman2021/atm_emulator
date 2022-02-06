package com.example.atmemulator.controler.api.error;

import com.example.atmemulator.controler.api.core.ServiceResult;
import com.example.atmemulator.controler.api.error.api.ApiError;
import com.example.atmemulator.exceptions.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.handler.ResponseStatusExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(AccountException.class)
    protected ResponseEntity<ServiceResult<Void>> handleAccountException(AccountException ex) {
        ApiError apiError = new ApiError(NOT_FOUND);
        apiError.setMessage(ex.getMessage());
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(CreditCardException.class)
    protected ResponseEntity<ServiceResult<Void>> handleCardException(CreditCardException ex) {
        ApiError apiError = new ApiError(NOT_FOUND);
        apiError.setMessage(ex.getMessage());
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(TransactionException.class)
    protected ResponseEntity<ServiceResult<Void>> handleTransactionException(TransactionException ex) {
        ApiError apiError = new ApiError(BAD_REQUEST);
        apiError.setMessage(ex.getMessage());
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(NoSuficientBalanceException.class)
    protected ResponseEntity<ServiceResult<Void>> handleNoSufficientBalanceExceptionException(NoSuficientBalanceException ex) {
        ApiError apiError = new ApiError(BAD_REQUEST);
        apiError.setMessage(ex.getMessage());
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(NotFoundException.class)
    protected ResponseEntity<ServiceResult<Void>> handleNotFoundException(NotFoundException ex) {
        ApiError apiError = new ApiError(NOT_FOUND);
        apiError.setMessage(ex.getMessage());
        return buildResponseEntity(apiError);
    }

    public ResponseEntity<ServiceResult<Void>> buildResponseEntity(ApiError apiError) {
        var sr = ServiceResult.fail(apiError);
        return new ResponseEntity<>(sr,apiError.getHttpStatus());
    }

}
