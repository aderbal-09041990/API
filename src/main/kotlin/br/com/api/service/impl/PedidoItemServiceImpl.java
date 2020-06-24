package br.com.api.service.impl;


import br.com.api.exception.CustomException;
import br.com.api.model.entity.PedidoItem;
import br.com.api.model.repository.PedidoItemRepository;
import br.com.api.service.PedidoItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
public class PedidoItemServiceImpl implements PedidoItemService {

    @Autowired
    private PedidoItemRepository pedidoItemRepository;

    @Override
    public PedidoItem save(PedidoItem pedidoItem) {
        try{

            return pedidoItemRepository.save(pedidoItem);

        }catch (Exception e){
            log.error("Erro ao registar o pedido item : " + e);
            throw new CustomException(e.getMessage());
        }
    }

    @Override
    public Optional<PedidoItem> find(Long id) {
        try{

            return pedidoItemRepository.findById(id);

        }catch (Exception e){
            log.error("Erro ao buscar o pedido item com id : " + id);
            throw new CustomException(e.getMessage());
        }
    }

    @Override
    public List<PedidoItem> findAll(PedidoItem pedidoItem) {
        try{

            Example example = Example.of(pedidoItem,
                    ExampleMatcher.matching()
                            .withIgnoreCase()
                            .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING));

            return pedidoItemRepository.findAll(example);

        }catch (Exception e){
            log.error("Erro oa buscar a lista de pedido item : " + e);
            throw new CustomException(e.getMessage());
        }
    }

    @Override
    public void deleteById(Long id) {
        try{

            pedidoItemRepository.deleteById(id);

        }catch (Exception e){
            log.error("Erro ao deletar o pedido item : " + e);
            throw new CustomException(e.getMessage());
        }
    }
}
