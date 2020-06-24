package br.com.api.controller;

import br.com.api.exception.CustomException;
import br.com.api.model.entity.Produto;
import br.com.api.model.entity.Usuario;
import br.com.api.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/produto")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping
    public ResponseEntity save(@RequestBody Produto produto){
        try{
            return new ResponseEntity(produtoService.save(produto), HttpStatus.CREATED);
        }catch (CustomException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity find(@PathVariable Long id){
        try{
            return ResponseEntity.ok(produtoService.find(id));
        }catch (CustomException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity findAll(@RequestParam(required = false) Long codigo,
                                  @RequestParam(required = false) String nome,
                                  @RequestParam(required = false) String codigoBarras){
        try{

            Produto produto = Produto.builder()
                                    .codigo(codigo)
                                    .nome(nome)
                                    .codigoBarras(codigoBarras).build();

            return ResponseEntity.ok(produtoService.findAll(produto));

        }catch (CustomException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable Long id){
        try{
            produtoService.delete(id);
            return new ResponseEntity(HttpStatus.OK);
        }catch (CustomException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
