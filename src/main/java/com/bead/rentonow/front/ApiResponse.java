package com.bead.rentonow.front;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiResponse<T> {

    private int statusCode;
    private String description;
    private T data;
}