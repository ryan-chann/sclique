package com.example.sunway.sclique.utils;

import com.example.sunway.sclique.models.ServiceResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseUtil {
    private ResponseUtil() {

    }

    public static <T> ResponseEntity<?> handleServiceResponse(ServiceResponse<T> response, HttpStatus successStatus) {
        if (!response.isSuccess()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response.getErrorMessage());
        }

        return ResponseEntity.status(successStatus).body(response.getData());
    }
}
