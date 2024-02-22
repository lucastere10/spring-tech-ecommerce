package br.com.tech.springtech.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.tech.springtech.domain.exception.EntidadeEmUsoException;
import br.com.tech.springtech.domain.exception.ProdutoNaoEncontradoException;
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
        return produtoRepository.findById(id)
                .orElseThrow(() -> new ProdutoNaoEncontradoException(id));
    }

    @Transactional
    public void excluir(Long produtoId) {
        try {
            produtoRepository.deleteById(produtoId);
            produtoRepository.flush();

        } catch (EmptyResultDataAccessException e) {
            throw new ProdutoNaoEncontradoException(produtoId);
        }
    }

}
