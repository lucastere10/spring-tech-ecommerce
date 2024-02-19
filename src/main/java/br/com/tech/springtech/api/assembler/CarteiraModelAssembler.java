package br.com.tech.springtech.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.tech.springtech.api.dto.model.CarteiraModel;
import br.com.tech.springtech.domain.model.Carteira;

@Component
public class CarteiraModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public CarteiraModel toModel(Carteira carteira) {
        return modelMapper.map(carteira, CarteiraModel.class);
    }

    public List<CarteiraModel> toCollectionModel(List<Carteira> carteiras) {
        return carteiras.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
    
}
