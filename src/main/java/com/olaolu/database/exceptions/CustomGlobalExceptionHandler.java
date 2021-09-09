package com.olaolu.database.exceptions;

import com.olaolu.database.controllers.ArticleController;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

@ControllerAdvice(assignableTypes =  {ArticleController.class})
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        /*Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", new Date());
        body.put("status", status.value());

        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> x.getDefaultMessage())
                .collect(Collectors.toList());

        body.put("errors", "Method not catered for ");*/

        CustomErrorResponse errors = new CustomErrorResponse();
        errors.setTimestamp(LocalDateTime.now());
        errors.setError(ex.getMessage());
        errors.setStatus(HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(errors, headers, status);
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", new Date());
        body.put("status", status.value());
        body.put("errors", "Method not available");

        return new ResponseEntity<Object>(body, headers, status);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public void constraintViolationException(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value());
    }
    @ExceptionHandler(EmptyObjectException.class)
    public void objectWithIdNotfound(HttpServletResponse response) throws IOException {

        response.sendError(HttpStatus.NOT_FOUND.value(),"Article not found");
    }
    @ExceptionHandler(EmptyListObject.class)
    public ResponseEntity<ApiError> listNotFound(EmptyListObject emptyListObject){
        List<String> errors = Collections.singletonList(emptyListObject.getMessage());
        return new ResponseEntity<>(new ApiError(errors), HttpStatus.NOT_FOUND);

    }
    @ExceptionHandler(InvalidClientException.class)
    public void clientNotFound(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.NOT_FOUND.value(),"Client Not found");
    }

    public static class EmptyObjectException extends RuntimeException{

    }
    public static class EmptyListObject extends RuntimeException{
        String message;

        public EmptyListObject(String message) {
            super(message);
            this.message = message;
        }

        @Override
        public String getMessage() {
            return message;
        }
    }

    public class ApiError{
        List<String> errors;
        public ApiError(List<String> errors) {
            this.errors = errors;
        }
    }

}
