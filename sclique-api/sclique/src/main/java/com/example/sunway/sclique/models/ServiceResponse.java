package com.example.sunway.sclique.models;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ServiceResponse<T> {
    private boolean isSuccess = false;
    private String errorMessage;
    private T data;
}
