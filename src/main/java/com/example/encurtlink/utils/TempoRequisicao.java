package com.example.encurtlink.utils;

import org.springframework.stereotype.Component;

@Component
public class TempoRequisicao {

    private static long startTime;
    private static long endTime;
    private static long tempoDecorrido;

    public void StartTime(){
         startTime = System.currentTimeMillis();
    }

    public long Endtime( ){
        endTime = System.currentTimeMillis();
        tempoDecorrido = endTime - startTime;
        startTime = 0;
        endTime = 0;
        return tempoDecorrido;
    }
}
