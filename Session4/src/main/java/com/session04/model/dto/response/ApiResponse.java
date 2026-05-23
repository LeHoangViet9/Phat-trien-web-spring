package com.session04.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ApiResponse<T>{
    private boolean success;
    private String msg;
    private T data;
    private HttpStatus status;
}
