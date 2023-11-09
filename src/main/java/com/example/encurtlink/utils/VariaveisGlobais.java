package com.example.encurtlink.utils;

import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
@Component
public class  VariaveisGlobais {
    private String errCode001 = "001";
    private String errCode002 = "002";
    private String descriptionExists = "CUSTOM ALIAS ALREADY EXISTS";
    private String descriptionNoExists = "NON-EXISTENT ALIAS";
}
