package com.rikkei.session7.exception;

import com.rikkei.session7.model.dto.response.ApiDataResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiDataResponse<Map<String,String>>> handlerMethodArg(MethodArgumentNotValidException ex){
        Map<String,String> errors = new HashMap<>();
        for(FieldError fieldError : ex.getFieldErrors()){
            errors.put(fieldError.getField(),fieldError.getDefaultMessage());
        }
        return new ResponseEntity<>(new ApiDataResponse<>(
                false,
                "Lỗi thêm dữ liệu",
                null,
                errors,
                HttpStatus.BAD_REQUEST
        ), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ApiDataResponse<String>> handlerMethodArg(MissingServletRequestParameterException ex){
        return new ResponseEntity<>(new ApiDataResponse<>(
                false,
                "Dữ liệu không hơp lệ",
                null,
                ex.getLocalizedMessage(),
                HttpStatus.BAD_REQUEST
        ), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(CustomerException.class)
    public ResponseEntity<ApiDataResponse<String>> handleBusinessException(
            CustomerException ex
    ) {

        return new ResponseEntity<>(
                new ApiDataResponse<>(
                        false,
                        ex.getMessage(),
                        null,
                        ex.getMessage(),
                        HttpStatus.BAD_REQUEST
                ),
                HttpStatus.BAD_REQUEST
        );
    }
}
