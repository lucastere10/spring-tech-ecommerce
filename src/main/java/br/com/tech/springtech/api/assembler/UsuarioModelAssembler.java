package br.com.tech.springtech.api.assembler;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.tech.springtech.api.dto.model.UsuarioModel;
import br.com.tech.springtech.domain.model.Usuario;

@Component
public class UsuarioModelAssembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public UsuarioModel toModel(Usuario usuario) {
		return modelMapper.map(usuario, UsuarioModel.class);
	}
	
	public List<UsuarioModel> toCollectionModel(Collection<Usuario> usuarios) {
		return usuarios.stream()
				.map(this::toModel)
				.collect(Collectors.toList());
	}
	
}
