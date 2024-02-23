package br.com.tech.springtech.api.assembler;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.tech.springtech.api.dto.model.PedidoModel;
import br.com.tech.springtech.domain.model.Pedido;

@Component
public class PedidoModelAssembler {
    
	@Autowired
	private ModelMapper modelMapper;
	
	public PedidoModel toModel(Pedido pedido) {
		PedidoModel pedidoModel = modelMapper.map(pedido, PedidoModel.class);
		pedidoModel.setUsuarioNome(pedido.getUsuario().getNome()); // set the user's name
		return pedidoModel;
	}	
	
	public List<PedidoModel> toCollectionModel(Collection<Pedido> pedidos) {
		return pedidos.stream()
				.map(this::toModel)
				.collect(Collectors.toList());
	}
    
}
