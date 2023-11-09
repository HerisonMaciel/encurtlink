package com.example.encurtlink.request;


import lombok.Builder;

@Builder
public record EncurtadorGeradoRequest(String original_url, String alias) {

}
