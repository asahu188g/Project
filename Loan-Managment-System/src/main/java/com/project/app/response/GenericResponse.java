package com.project.app.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GenericResponse<T> {
    private T data;
    private String message;

    public GenericResponse(String message) {
        this.message = message;
    }
}
