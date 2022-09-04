package com.example.templates.controllers;

import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class AdviceController extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {NullPointerException.class})
    public ResponseEntity<Object> error(NullPointerException e, WebRequest request) {
        String bodyOfResponse = "This should be application specific";
        return handleExceptionInternal(e, bodyOfResponse, 
          new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> exception(Exception ex, WebRequest request) {
        return handleExceptionInternal(ex, "Exception", 
          new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @ExceptionHandler(value = {CustomizedException.class, CustomizedException2.class})
    public ResponseEntity<Object> customizedException(Exception ex, WebRequest request) {
        return handleExceptionInternal(ex, "CustomizedException", 
          new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
}
