package br.com.api.controller;

import br.com.api.dto.LoginRequestDTO;
import br.com.api.exception.CustomException;
import br.com.api.model.entity.Usuario;
import br.com.api.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity save(@RequestBody Usuario usuario){
        try{
            return new ResponseEntity(usuarioService.save(usuario), HttpStatus.CREATED);
        }catch (CustomException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity find(@PathVariable Long id){
        try{
            return ResponseEntity.ok(usuarioService.find(id));
        }catch (CustomException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity findAll(){
        try{
            return ResponseEntity.ok(usuarioService.findAll());
        }catch (CustomException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable Long id){
        try{
            usuarioService.delete(id);
            return new ResponseEntity(HttpStatus.OK);
        }catch (CustomException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
