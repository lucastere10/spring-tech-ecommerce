package br.com.tech.springtech.domain.service;

import java.math.BigDecimal;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import br.com.tech.springtech.domain.enums.PedidoStatus;
import br.com.tech.springtech.domain.model.Pedido;
import br.com.tech.springtech.domain.model.Produto;
import br.com.tech.springtech.domain.model.ProdutoPedido;
import br.com.tech.springtech.domain.model.Usuario;
import br.com.tech.springtech.domain.repository.PedidoRepository;
import br.com.tech.springtech.domain.repository.ProdutoPedidoRepository;
import jakarta.transaction.Transactional;

@Service
public class CadastroPedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProdutoPedidoRepository produtoPedidoRepository;

    @Transactional
    public Pedido salvar(@AuthenticationPrincipal Usuario usuario, Pedido pedido) {

        pedido.setUsuario(usuario);
        pedido.setPedidoStatus(PedidoStatus.NOVO);
        pedido.setDesconto(BigDecimal.valueOf(0));

        return pedidoRepository.save(pedido);
    }

    public void adicionarProdutoAoPedido(Long pedidoId, Produto produto, Long quantidade) {


    }

}
