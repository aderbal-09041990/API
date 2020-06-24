package br.com.api.controller;

import br.com.api.exception.CustomException;
import br.com.api.model.entity.Pedido;
import br.com.api.model.entity.PedidoItem;
import br.com.api.service.PedidoItemService;
import br.com.api.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping(value="/pedido/item")
public class PedidoItemController {

    @Autowired
    private PedidoItemService pedidoItemService;

    @PostMapping
    public ResponseEntity save(@RequestBody PedidoItem pedidoItem){
        try{
            return new ResponseEntity(pedidoItemService.save(pedidoItem), HttpStatus.CREATED);
        }catch (CustomException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity find(@PathVariable Long id){
        try{
            return ResponseEntity.ok(pedidoItemService.find(id));
        }catch (CustomException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity findAll(@RequestParam(required = false) Long codigo){
        try{

            PedidoItem pedidoItem = PedidoItem.builder()
                    .codigo(codigo).build();

            return ResponseEntity.ok(pedidoItemService.findAll(pedidoItem));

        }catch (CustomException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable Long id){
        try{
            pedidoItemService.deleteById(id);
            return new ResponseEntity(HttpStatus.OK);
        }catch (CustomException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
