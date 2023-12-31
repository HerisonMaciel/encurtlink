package com.example.encurtlink.mapper;

import com.example.encurtlink.DTOs.EncurtadorGeradoDTO;
import com.example.encurtlink.request.EncurtadorGeradoRequest;

public class EncurtadorMapperDTO {

    public static EncurtadorGeradoDTO toDto(EncurtadorGeradoRequest encurtadorGeradoRequest){
        return EncurtadorGeradoDTO.builder()
                .original_url(encurtadorGeradoRequest.original_url())
                .id_alias(encurtadorGeradoRequest.alias())
                .build();
    }

}
