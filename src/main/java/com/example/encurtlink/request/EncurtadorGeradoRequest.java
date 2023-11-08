package com.example.encurtlink.request;


import lombok.Builder;
import lombok.Data;

@Builder
public record EncurtadorGeradoRequest(String original_url, String nome) {

    public String getOriginal_url(){
        return this.original_url;
    }

}
