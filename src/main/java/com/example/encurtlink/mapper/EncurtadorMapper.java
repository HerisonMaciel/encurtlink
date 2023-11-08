package com.example.encurtlink.mapper;

import com.example.encurtlink.DTOs.EncurtadorGeradoDTO;
import com.example.encurtlink.entity.EncurtadorGerado;
import com.example.encurtlink.request.EncurtadorGeradoRequest;

public class EncurtadorMapper {

    public static EncurtadorGerado toEntity(EncurtadorGeradoDTO encurtadorGeradoDTO){
        return EncurtadorGerado.builder()
                .id_hash(encurtadorGeradoDTO.getId_hash())
                .original_url(encurtadorGeradoDTO.getOriginal_url())
                .created_ad(encurtadorGeradoDTO.getCreated_ad())
                .build();
    }

}
