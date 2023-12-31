package com.example.encurtlink.DTOs;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class EncurtadorGeradoDTO {

    private String id_alias;

    private String original_url;

    private LocalDateTime created_ad;

    private int access;

}
