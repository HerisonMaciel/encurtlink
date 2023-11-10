package com.example.encurtlink.service;

import com.example.encurtlink.DTOs.EncurtadorGeradoDTO;
import com.example.encurtlink.entity.EncurtadorGerado;
import com.example.encurtlink.exception.ExceptionAlias;
import com.example.encurtlink.mapper.EncurtadorMapper;
import com.example.encurtlink.repository.EncurtadorRepository;
import com.example.encurtlink.response.EncurtadorGeradoResponse;
import com.example.encurtlink.utils.VariaveisGlobais;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Optional;

@Log4j2
@AllArgsConstructor
@Service
public class EncurtadorService {

    @Autowired
    private EncurtadorRepository encurtadorRepository;

    @Autowired
    private VariaveisGlobais variaveisGlobais;

    private static final int MINLENGTH = 5;
    private static final int MAXLENGTH = 9;

    public EncurtadorGeradoResponse genereteUrl(EncurtadorGeradoDTO encurtadorGeradoDTO) throws Exception {
        if(encurtadorGeradoDTO.getId_alias() != null){
            log.info("Requisição com Alias: " + encurtadorGeradoDTO);
            return SavaAlias(encurtadorGeradoDTO);
        }
        log.info("Requisição sem Alias: " + encurtadorGeradoDTO);
        return SaveAliasRandom(encurtadorGeradoDTO);
    }

    public EncurtadorGeradoResponse ConsultAlias(String alias) throws Exception {
        Optional<EncurtadorGerado> encurtadorGeradoOptional = encurtadorRepository.findById(alias);
        try {
            if(encurtadorGeradoOptional.isPresent()){
                EncurtadorGerado encurtadorGerado = encurtadorGeradoOptional.get();
                encurtadorGerado.setAccess(encurtadorGeradoOptional.get().getAccess()+1);
                EncurtadorGerado encurtadorSave = encurtadorRepository.save(encurtadorGerado);
                EncurtadorGeradoResponse encurtadorGeradoResponse = EncurtadorMapper.toResponse(encurtadorSave);
                log.info("Alias consultado com sucesso!" + encurtadorSave.toString());
                return encurtadorGeradoResponse;
            }
            throw new ExceptionAlias(alias, variaveisGlobais.getErrCode002(), variaveisGlobais.getDescriptionNoExists());
        }catch (ExceptionAlias e){
            log.info("Não foi possivel consultar o Alias! " + e.toString());
            throw e;
        }
    }

    private EncurtadorGeradoResponse SavaAlias(EncurtadorGeradoDTO encurtadorGeradoDTO) throws Exception {

        try{
            if(VerificationAlias(encurtadorGeradoDTO.getId_alias())){
                throw new ExceptionAlias(encurtadorGeradoDTO.getId_alias(), variaveisGlobais.getErrCode001(), variaveisGlobais.getDescriptionExists());
            }
        }catch (ExceptionAlias e){
            log.info("Não foi possivel salvar o Alias! " + e.toString());
            throw e;
        }
        encurtadorGeradoDTO.setCreated_ad(LocalDateTime.now());
        EncurtadorGerado encurtadorGerado = EncurtadorMapper.toEntity(encurtadorGeradoDTO);
        EncurtadorGerado encurtadorSave = encurtadorRepository.save(encurtadorGerado);
        EncurtadorGeradoResponse encurtadorGeradoResponse = EncurtadorMapper.toResponse(encurtadorSave);
        log.info("Alias salvo com sucesso!" + encurtadorSave.toString());
        return encurtadorGeradoResponse;
    }

    private EncurtadorGeradoResponse SaveAliasRandom(EncurtadorGeradoDTO encurtadorGeradoDTO){

        boolean existe = true;
        while (existe){
            encurtadorGeradoDTO.setId_alias(randomAlias());
            if(!VerificationAlias(encurtadorGeradoDTO.getId_alias())){
                existe = false;
            }
        }
        encurtadorGeradoDTO.setCreated_ad(LocalDateTime.now());
        EncurtadorGerado encurtadorGerado = EncurtadorMapper.toEntity(encurtadorGeradoDTO);
        EncurtadorGerado encurtadorSave = encurtadorRepository.save(encurtadorGerado);
        EncurtadorGeradoResponse encurtadorGeradoResponse = EncurtadorMapper.toResponse(encurtadorSave);
        log.info("Alias salvo com sucesso!" + encurtadorSave.toString());
        return encurtadorGeradoResponse;
    }

    private String randomAlias(){
        SecureRandom random = new SecureRandom();
        int length = random.nextInt(MAXLENGTH - MINLENGTH + 1) + MINLENGTH;

        byte[] randomBytes = new byte[length];
        random.nextBytes(randomBytes);

        String randomBase64Combination = java.util.Base64.getEncoder().encodeToString(randomBytes);
        randomBase64Combination = randomBase64Combination.substring(0, length);
        randomBase64Combination = randomBase64Combination.replace("+", "-").replace("/", "_").replace("%", "");
        log.info("Alias criado Randomicamente: " + randomBase64Combination);
        return randomBase64Combination;
    }

    private boolean VerificationAlias(String alias){
        Optional<EncurtadorGerado> encurtadorGerado = encurtadorRepository.findById(alias);
        if(encurtadorGerado.isPresent()){
            log.info("Alias já existe!");
            return true;
        }
        log.info("Alias Não existe!");
        return false;
    }

}
