package br.com.api.controller;

import br.com.api.dto.LoginRequestDTO;
import br.com.api.exception.CustomException;
import br.com.api.model.entity.Usuario;
import br.com.api.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "autenticar")
public class AutenticacaoController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity autenticar(@RequestBody LoginRequestDTO loginRequestDTO){
        try{
            return new ResponseEntity(usuarioService.autenticar(loginRequestDTO), HttpStatus.CREATED);
        }catch (CustomException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
