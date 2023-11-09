package com.example.encurtlink.mapper;

import com.example.encurtlink.DTOs.EncurtadorGeradoDTO;
import com.example.encurtlink.entity.EncurtadorGerado;
import com.example.encurtlink.response.EncurtadorGeradoResponse;

public class EncurtadorMapper {

    public static EncurtadorGerado toEntity(EncurtadorGeradoDTO encurtadorGeradoDTO){
        return EncurtadorGerado.builder()
                .id_alias(encurtadorGeradoDTO.getId_alias())
                .original_url(encurtadorGeradoDTO.getOriginal_url())
                .created_ad(encurtadorGeradoDTO.getCreated_ad())
                .build();
    }

    public static EncurtadorGeradoResponse toResponse(EncurtadorGerado encurtadorGerado){
        return EncurtadorGeradoResponse.builder()
                .alias(encurtadorGerado.getId_alias())
                .url(encurtadorGerado.getOriginal_url())
                .build();
    }

}
