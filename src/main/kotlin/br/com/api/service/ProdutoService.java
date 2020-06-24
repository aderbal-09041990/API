package br.com.api.service;


import br.com.api.model.entity.Produto;

import java.util.List;
import java.util.Optional;

public interface ProdutoService {

    Produto save(Produto produto);
    Optional<Produto> find(Long id);
    List<Produto> findAll(Produto produto);
    void delete(Long id);

}
