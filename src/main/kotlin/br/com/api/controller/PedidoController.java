package br.com.api.controller;

import br.com.api.exception.CustomException;
import br.com.api.model.entity.Pedido;
import br.com.api.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping(value="/pedido")
public class PedidoController {

    @Autowired
    private PedidoService PedidoService;

    @PostMapping
    public ResponseEntity save(@RequestBody Pedido Pedido){
        try{
            return new ResponseEntity(PedidoService.save(Pedido), HttpStatus.CREATED);
        }catch (CustomException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity find(@PathVariable Long id){
        try{
            return ResponseEntity.ok(PedidoService.find(id));
        }catch (CustomException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity findAll(@RequestParam(required = false) Long codigo,
                                  @RequestParam(required = false) Date date){
        try{

            Pedido pedido = Pedido.builder()
                    .codigo(codigo)
                    .data(date).build();

            return ResponseEntity.ok(PedidoService.findAll(pedido));

        }catch (CustomException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable Long id){
        try{
            PedidoService.deleteById(id);
            return new ResponseEntity(HttpStatus.OK);
        }catch (CustomException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
