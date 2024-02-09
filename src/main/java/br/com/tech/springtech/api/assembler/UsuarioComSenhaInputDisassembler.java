package br.com.tech.springtech.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.tech.springtech.api.dto.input.UsuarioComSenhaInput;
import br.com.tech.springtech.domain.model.Usuario;

@Component
public class UsuarioComSenhaInputDisassembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public Usuario toDomainObject(UsuarioComSenhaInput usuarioComSenhaInput) {
		return modelMapper.map(usuarioComSenhaInput, Usuario.class);
	}
	
	public void copyToDomainObject(UsuarioComSenhaInput usuarioComSenhaInput, Usuario usuario) {
		modelMapper.map(usuarioComSenhaInput, usuario);
	}
	
}