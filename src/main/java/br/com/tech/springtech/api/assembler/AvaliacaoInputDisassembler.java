package br.com.tech.springtech.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.tech.springtech.api.dto.input.AvaliacaoInput;
import br.com.tech.springtech.domain.model.Avaliacao;

@Component
public class AvaliacaoInputDisassembler {
    
	@Autowired
	private ModelMapper modelMapper;
	
	public Avaliacao toDomainObject(AvaliacaoInput avaliacaoInput) {
		return modelMapper.map(avaliacaoInput, Avaliacao.class);
	}
	
	public void copyToDomainObject(AvaliacaoInput avaliacaoInput, Avaliacao avaliacao) {
		modelMapper.map(avaliacaoInput, avaliacao);
	}
	

}
