package com.example.encurtlink.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EncurtadorGeradoExisteResponse{
    private String alias;
    private int err_code;
    private String description;

}
