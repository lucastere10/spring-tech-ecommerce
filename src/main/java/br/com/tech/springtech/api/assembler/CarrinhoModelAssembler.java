package br.com.tech.springtech.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.tech.springtech.api.dto.model.CarrinhoModel;
import br.com.tech.springtech.domain.model.Carrinho;

@Component
public class CarrinhoModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public CarrinhoModel toModel(Carrinho carrinho) {
        return modelMapper.map(carrinho, CarrinhoModel.class);
    }

    public List<CarrinhoModel> toCollectionModel(List<Carrinho> carrinhos) {
        return carrinhos.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

    
}
