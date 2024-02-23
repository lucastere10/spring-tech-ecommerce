package br.com.tech.springtech.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.tech.springtech.api.dto.model.ProdutoCarrinhoModel;
import br.com.tech.springtech.domain.model.ProdutoCarrinho;

@Component
public class ProdutoCarrinhoModelAssembler {
    
    @Autowired
    private ModelMapper modelMapper;
    
    public ProdutoCarrinhoModel toModel(ProdutoCarrinho produtoCarrinho) {
        return modelMapper.map(produtoCarrinho, ProdutoCarrinhoModel.class);
    }

    public List<ProdutoCarrinhoModel> toCollectionModel(List<ProdutoCarrinho> produtoCarrinhos) {
        return produtoCarrinhos.stream()
        .map(this::toModel)
        .collect(Collectors.toList());
    }

}
