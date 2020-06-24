package br.com.api.service.impl;

import br.com.api.exception.CustomException;
import br.com.api.model.entity.Produto;
import br.com.api.model.repository.ProdutoRepository;
import br.com.api.service.ProdutoService;
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
public class ProdutoServiceImpl implements ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Override
    public Produto save(Produto produto) {
        try{

            return produtoRepository.save(produto);

        }catch (Exception e){
            log.error("Erro ao registar o produto : " + e);
            throw new CustomException(e.getMessage());
        }
    }

    @Override
    public Optional<Produto> find(Long id) {
        try{

            return produtoRepository.findById(id);

        }catch (Exception e){
            log.error("Erro ao buscar o produto com id: " + id);
            throw new CustomException(e.getMessage());
        }
    }

    @Override
    public List<Produto> findAll(Produto produto) {
        try{

            Example example = Example.of(produto,
                    ExampleMatcher.matching()
                            .withIgnoreCase()
                            .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING));

            return produtoRepository.findAll(example);

        }catch (Exception e){
            log.error("Erro ao buscar a lista de produtos.");
            throw new CustomException(e.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
        try{

            produtoRepository.deleteById(id);

        }catch (Exception e){
            log.error("Erro ao deletar o produto com id: " + id);
            throw new CustomException(e.getMessage());
        }
    }
}
