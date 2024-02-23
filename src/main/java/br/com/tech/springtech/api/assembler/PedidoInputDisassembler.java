package br.com.tech.springtech.api.assembler;

import org.springframework.stereotype.Component;

import br.com.tech.springtech.api.dto.input.PedidoInput;
import br.com.tech.springtech.domain.model.Pedido;

@Component
public class PedidoInputDisassembler {

    public Pedido toDomainObject(PedidoInput pedidoInput) {
        Pedido pedido = new Pedido();
        pedido.setEndereco(pedidoInput.getEndereco());
        return pedido;
    }

    public void copyToDomainObject(PedidoInput pedidoInput, Pedido pedido) {
        pedido.setEndereco(pedidoInput.getEndereco());
    }
}

