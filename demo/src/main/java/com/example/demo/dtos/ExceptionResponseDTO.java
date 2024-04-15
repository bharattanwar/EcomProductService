package com.example.demo.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExceptionResponseDTO {
    private String massage;
    private int code;    // error code like 404,400 etc

    public ExceptionResponseDTO(String massage, int code) {
        this.massage = massage;
        this.code = code;
    }
}
