package br.com.tech.springtech.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tech.springtech.domain.model.Produto;
import br.com.tech.springtech.domain.repository.ProdutoRepository;
import jakarta.transaction.Transactional;

@Service
public class CadastroProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Transactional
    public Produto salvar(Produto produto) {
        return produtoRepository.save(produto);
    }

    public Produto buscar(Long id) {
        return produtoRepository.findById(id).orElseThrow();
    }

}
