package com.example.encurtlink.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EncurtadorGeradoResponse {

    private String alias;
    private String url;
    private int access;
    private StatisticsResponse statisticsResponse;

}
