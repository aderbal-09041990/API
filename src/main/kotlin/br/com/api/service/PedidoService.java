package br.com.api.service;

import br.com.api.model.entity.Pedido;

import java.util.List;
import java.util.Optional;

public interface PedidoService {

    Pedido save(Pedido pedido);
    Optional<Pedido> find(Long id);
    List<Pedido> findAll(Pedido pedido);
    void deleteById(Long id);
}
