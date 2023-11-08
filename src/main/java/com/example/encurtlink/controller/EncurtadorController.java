package com.example.encurtlink.controller;

import com.example.encurtlink.DTOs.EncurtadorGeradoDTO;
import com.example.encurtlink.entity.EncurtadorGerado;
import com.example.encurtlink.mapper.EncurtadorMapperDTO;
import com.example.encurtlink.request.EncurtadorGeradoRequest;
import com.example.encurtlink.service.EncurtadorService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequestMapping("/encurtador")
public class EncurtadorController {

    @Autowired
    private EncurtadorService encurtadorService;

    @PostMapping("/genereteUrl")
    public ResponseEntity genereteUrl(@RequestBody EncurtadorGeradoRequest request){

        EncurtadorGerado encurtadorGerado = encurtadorService.genereteUrl(EncurtadorMapperDTO.toDto(request));
        log.info("Encurtador Criado: " + encurtadorGerado);
        return new ResponseEntity<>(encurtadorGerado, HttpStatus.CREATED);
    }
}
