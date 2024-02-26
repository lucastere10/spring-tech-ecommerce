package br.com.tech.springtech.domain.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tech.springtech.domain.enums.PedidoStatus;
import br.com.tech.springtech.domain.exception.PedidoNaoEncontradoException;
import br.com.tech.springtech.domain.exception.UsuarioNaoEncontradoException;
import br.com.tech.springtech.domain.model.Carrinho;
import br.com.tech.springtech.domain.model.Pedido;
import br.com.tech.springtech.domain.model.ProdutoCarrinho;
import br.com.tech.springtech.domain.model.ProdutoPedido;
import br.com.tech.springtech.domain.model.Usuario;
import br.com.tech.springtech.domain.repository.CarrinhoRepository;
import br.com.tech.springtech.domain.repository.PedidoRepository;
import br.com.tech.springtech.domain.repository.ProdutoPedidoRepository;
import br.com.tech.springtech.domain.repository.UsuarioRepository;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CarrinhoRepository carrinhoRepository;

    @Autowired
    private ProdutoPedidoRepository produtoPedidoRepository;

    public Pedido buscar(Long id) {
        return pedidoRepository.findById(id)
                .orElseThrow(() -> new PedidoNaoEncontradoException(id));
    }

    public Pedido gerarPedido(Long usuarioId, String endereco) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new UsuarioNaoEncontradoException(usuarioId));
        Carrinho carrinho = carrinhoRepository.findById(usuarioId)
                .orElseThrow(() -> new UsuarioNaoEncontradoException(usuarioId));

        Pedido pedido = new Pedido();
        pedido.setUsuario(usuario);
        pedido.setEndereco(endereco);
        pedido.setPedidoStatus(PedidoStatus.NOVO);
        pedido = pedidoRepository.save(pedido);

        BigDecimal valorTotal = BigDecimal.ZERO;

        for (ProdutoCarrinho produtoCarrinho : carrinho.getProdutoCarrinho()) {
            ProdutoPedido produtoPedido = new ProdutoPedido();
            produtoPedido.setPedido(pedido);
            produtoPedido.setProduto(produtoCarrinho.getProduto());
            produtoPedido.setQuantidade(produtoCarrinho.getQuantidade());
            produtoPedido.setPrecoUnitario(produtoCarrinho.getPrecoUnitario());
            produtoPedido.setPrecoTotal(produtoCarrinho.getPrecoTotal());
            produtoPedidoRepository.save(produtoPedido);
            valorTotal = valorTotal.add(produtoPedido.getPrecoTotal());
        }

        pedido.setValor(valorTotal);
        pedidoRepository.save(pedido);

        return pedido;
    }

    public Pedido statusPedidoPendente(Pedido pedido) {
        pedido.setPedidoStatus(PedidoStatus.PENDENTE);
        return pedidoRepository.save(pedido);
    }

    public Pedido statusPedidoEmEspera(Pedido pedido) {
        pedido.setPedidoStatus(PedidoStatus.EM_ESPERA);
        return pedidoRepository.save(pedido);
    }

    public Pedido statusPedidoEnviado(Pedido pedido) {
        pedido.setPedidoStatus(PedidoStatus.ENVIADO);
        return pedidoRepository.save(pedido);
    }

    public Pedido statusPedidoEntregue(Pedido pedido) {
        pedido.setPedidoStatus(PedidoStatus.ENTREGUE);
        return pedidoRepository.save(pedido);
    }

    public Pedido statusPedidoFinalizado(Pedido pedido) {
        pedido.setPedidoStatus(PedidoStatus.FINALIZADO);
        return pedidoRepository.save(pedido);
    }

}
