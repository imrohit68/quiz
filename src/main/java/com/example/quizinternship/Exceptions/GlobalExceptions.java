package com.example.quizinternship.Exceptions;

import com.example.quizinternship.DTO.InvalidRequestResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptions extends ResponseEntityExceptionHandler {
    @ExceptionHandler(InvalidRequestException.class)
    public ResponseEntity<InvalidRequestResponse> invalidRequestResponseResponse(InvalidRequestException ex){
        String message = ex.getMessage();
        InvalidRequestResponse invalidRequestResponse = new InvalidRequestResponse();
        invalidRequestResponse.setMessage(message);
        return ResponseEntity.badRequest().body(invalidRequestResponse);
    }
}
