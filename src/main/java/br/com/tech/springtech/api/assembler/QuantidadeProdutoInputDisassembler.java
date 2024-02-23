package br.com.tech.springtech.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.tech.springtech.api.dto.input.QuantidadeProdutoInput;
import br.com.tech.springtech.domain.model.Produto;
import br.com.tech.springtech.domain.model.ProdutoCarrinho;

@Component
public class QuantidadeProdutoInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public ProdutoCarrinho toDomainObject(QuantidadeProdutoInput produtoInput, Produto produto) {
        ProdutoCarrinho produtoCarrinho = modelMapper.map(produtoInput, ProdutoCarrinho.class);
        produtoCarrinho.setProduto(produto);
        return produtoCarrinho;
    }

    public void copyToDomainObject(QuantidadeProdutoInput produtoInput, ProdutoCarrinho produtoCarrinho) {
        modelMapper.map(produtoInput, produtoCarrinho);
    }
}
