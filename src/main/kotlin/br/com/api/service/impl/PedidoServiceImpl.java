package br.com.api.service.impl;

import br.com.api.exception.CustomException;
import br.com.api.model.entity.Pedido;
import br.com.api.model.repository.PedidoRepository;
import br.com.api.service.PedidoService;
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
public class PedidoServiceImpl implements PedidoService {

    @Autowired
    private PedidoRepository pedidoVendaRepository;

    @Override
    public Pedido save(Pedido pedido) {
        try{

            return pedidoVendaRepository.save(pedido);

        }catch (Exception e){
            log.error("Erro ao registar o pedido : " + e);
            throw new CustomException(e.getMessage());
        }
    }

    @Override
    public Optional<Pedido> find(Long id) {
        try{

            return pedidoVendaRepository.findById(id);

        }catch (Exception e){
            log.error("Erro ao buscar o pedido com id: " + id);
            throw new CustomException(e.getMessage());
        }
    }

    @Override
    public List<Pedido> findAll(Pedido pedido) {
        try{

            Example example = Example.of(pedido,
                    ExampleMatcher.matching()
                            .withIgnoreCase()
                            .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING));

            return pedidoVendaRepository.findAll(example);

        }catch (Exception e){
            log.error("Erro ao buscar a lista de pedido : " + e);
            throw new CustomException(e.getMessage());
        }
    }

    @Override
    public void deleteById(Long id) {
        try{

            pedidoVendaRepository.deleteById(id);

        }catch (Exception e){
            log.error("Erro ao deletar o pedido com id: " + id);
            throw new CustomException(e.getMessage());
        }
    }
}
