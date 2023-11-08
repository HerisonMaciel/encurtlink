package com.example.encurtlink.service;

import com.example.encurtlink.DTOs.EncurtadorGeradoDTO;
import com.example.encurtlink.entity.EncurtadorGerado;
import com.example.encurtlink.mapper.EncurtadorMapper;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import java.security.SecureRandom;
import java.time.LocalDateTime;

@Log4j2
@AllArgsConstructor
@Service
public class EncurtadorService {

    private final int MINLENGTH = 5;
    private final int MAXLENGTH = 9;

    public EncurtadorGerado genereteUrl(EncurtadorGeradoDTO encurtadorGeradoDTO){

        long startTime = System.currentTimeMillis();


        encurtadorGeradoDTO.setId_hash(randomHash());
        encurtadorGeradoDTO.setCreated_ad(LocalDateTime.now());

        EncurtadorGerado encurtadorGerado = EncurtadorMapper.toEntity(encurtadorGeradoDTO);



        // Realize a operação de processamento da requisição aqui

        long endTime = System.currentTimeMillis();
        long tempoDecorrido = endTime - startTime;
        log.info("Tempo Percorrido: " + tempoDecorrido);

        return encurtadorGerado;
    }

    private String randomHash(){
        SecureRandom random = new SecureRandom();
        int length = random.nextInt(MAXLENGTH - MINLENGTH + 1) + MINLENGTH;

        byte[] randomBytes = new byte[length];
        random.nextBytes(randomBytes);

        String randomBase64Combination = java.util.Base64.getEncoder().encodeToString(randomBytes);
        randomBase64Combination = randomBase64Combination.substring(0, length);
        return randomBase64Combination;
    }

}
