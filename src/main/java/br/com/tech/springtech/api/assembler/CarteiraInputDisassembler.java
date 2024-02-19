package br.com.tech.springtech.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.tech.springtech.api.dto.input.CarteiraInput;
import br.com.tech.springtech.domain.model.Carteira;

@Component
public class CarteiraInputDisassembler {
    
    @Autowired
	private ModelMapper modelMapper;
	
	public Carteira toDomainObject(CarteiraInput carteiraInput) {
		return modelMapper.map(carteiraInput, Carteira.class);
	}
	
	public void copyToDomainObject(CarteiraInput carteiraInput, Carteira carteira) {
		modelMapper.map(carteiraInput, carteira);
	}
    
}
