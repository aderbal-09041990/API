package br.com.api.service;

import br.com.api.model.entity.Pedido;
import br.com.api.model.entity.PedidoItem;

import java.util.List;
import java.util.Optional;

public interface PedidoItemService {

    PedidoItem save(PedidoItem pedidoItem);
    Optional<PedidoItem> find(Long id);
    List<PedidoItem> findAll(PedidoItem pedidoItem);
    void deleteById(Long id);
}
