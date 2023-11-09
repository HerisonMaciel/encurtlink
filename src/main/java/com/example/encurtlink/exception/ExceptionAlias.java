package com.example.encurtlink.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ExceptionAlias extends RuntimeException{

    private String alias;
    private String errCode;
    private String description;

}
