package com.example.encurtlink.controller;

import com.example.encurtlink.entity.EncurtadorGerado;
import com.example.encurtlink.exception.ExceptionAlias;
import com.example.encurtlink.mapper.EncurtadorMapperDTO;
import com.example.encurtlink.repository.EncurtadorRepository;
import com.example.encurtlink.request.EncurtadorGeradoRequest;
import com.example.encurtlink.response.EncurtadorGeradoResponse;
import com.example.encurtlink.response.StatisticsResponse;
import com.example.encurtlink.service.EncurtadorService;
import com.example.encurtlink.utils.TempoRequisicao;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/encurtador")
public class EncurtadorController {

    @Autowired
    private EncurtadorService encurtadorService;

    @Autowired
    private TempoRequisicao tempoRequisicao;

    @Autowired
    private EncurtadorRepository encurtadorRepository;

    @Operation(summary = "Create Alias", description = "Create Alias", tags = {"Alies"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Ok",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = EncurtadorGeradoResponse.class)))),
            @ApiResponse(responseCode = "400", description = "Request invalid!"),
            @ApiResponse(responseCode = "404", description = "Not found"),
    })
    @PostMapping("/genereteUrl")
    public ResponseEntity GenereteAlias(@RequestBody EncurtadorGeradoRequest request) throws Exception {
        tempoRequisicao.StartTime();
        log.info("Request: "+ request);
        try {
            EncurtadorGeradoResponse response = encurtadorService.genereteUrl(EncurtadorMapperDTO.toDto(request));
            response.setStatisticsResponse(new StatisticsResponse(tempoRequisicao.Endtime()));
            log.info("Encurtador Criado: " + response);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }catch (ExceptionAlias e){
            return new ResponseEntity<>(e.toString(), HttpStatus.CONFLICT);
        }
    }

    @Operation(summary = "Get Alias", description = "Get Alias", tags = {"Alies"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = EncurtadorGeradoResponse.class)))),
            @ApiResponse(responseCode = "400", description = "Request invalid!"),
            @ApiResponse(responseCode = "404", description = "Not found"),
    })
    @GetMapping("/ConsultAlias")
    public ResponseEntity ConsultAlias(@RequestParam String alias) throws Exception {
        try {
            tempoRequisicao.StartTime();
            EncurtadorGeradoResponse response = encurtadorService.ConsultAlias(alias);
            response.setStatisticsResponse(new StatisticsResponse(tempoRequisicao.Endtime()));
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (ExceptionAlias e){
            return new ResponseEntity<>(e.toString(), HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Get All Alias", description = "Get All Alias", tags = {"Alies"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = EncurtadorGeradoResponse.class)))),
            @ApiResponse(responseCode = "400", description = "Request invalid!"),
            @ApiResponse(responseCode = "404", description = "Not found"),
    })
    @GetMapping("/consultAll")
    public List<EncurtadorGerado> consultAll(){
        return encurtadorRepository.findAll();
    }
}
