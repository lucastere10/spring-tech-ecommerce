package br.com.tech.springtech.domain.service;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tech.springtech.domain.exception.UsuarioNaoEncontradoException;
import br.com.tech.springtech.domain.model.Carrinho;
import br.com.tech.springtech.domain.model.Produto;
import br.com.tech.springtech.domain.model.ProdutoCarrinho;
import br.com.tech.springtech.domain.repository.CarrinhoRepository;
import br.com.tech.springtech.domain.repository.ProdutoCarrinhoRepository;

@Service
public class CarrinhoService {

    @Autowired
    private CarrinhoRepository carrinhoRepository;

    @Autowired
    private ProdutoCarrinhoRepository produtoCarrinhoRepository;

    public Carrinho buscar(Long carrinhoId) {
        return carrinhoRepository.findById(carrinhoId)
                .orElseThrow(() -> new UsuarioNaoEncontradoException(carrinhoId));
    }

    public ProdutoCarrinho adicionarProdutoAoCarrinho(Long carrinhoId, Produto produto, Long quantidade) {
        Carrinho carrinho = carrinhoRepository.findById(carrinhoId)
                .orElseThrow(() -> new UsuarioNaoEncontradoException("Carrinho não encontrado"));

        // Verifique se o ProdutoCarrinho já existe
        Optional<ProdutoCarrinho> optionalProdutoCarrinho = produtoCarrinhoRepository.findByCarrinhoAndProduto(carrinho,
                produto);
        if (optionalProdutoCarrinho.isPresent()) {
            // Se o ProdutoCarrinho já existir, apenas incremente a quantidade
            ProdutoCarrinho produtoCarrinhoExistente = optionalProdutoCarrinho.get();
            produtoCarrinhoExistente.setQuantidade(produtoCarrinhoExistente.getQuantidade() + quantidade);
            produtoCarrinhoExistente.setPrecoTotal(produtoCarrinhoExistente.getPrecoUnitario()
                    .multiply(new BigDecimal(produtoCarrinhoExistente.getQuantidade())));
            return produtoCarrinhoRepository.save(produtoCarrinhoExistente);
        } else {
            // Se o ProdutoCarrinho não existir, crie um novo
            ProdutoCarrinho produtoCarrinho = new ProdutoCarrinho();
            produtoCarrinho.setProduto(produto);
            produtoCarrinho.setCarrinho(carrinho);
            produtoCarrinho.setQuantidade(quantidade);
            produtoCarrinho.setPrecoUnitario(produto.getPreco());
            produtoCarrinho.setPrecoTotal(produto.getPreco().multiply(new BigDecimal(quantidade)));
            return produtoCarrinhoRepository.save(produtoCarrinho);
        }
    }

    public ProdutoCarrinho atualizarProdutoCarrinho(ProdutoCarrinho produtoCarrinho, Long quantidade) {

        produtoCarrinho.setQuantidade(quantidade);
        produtoCarrinho.setPrecoTotal(
                produtoCarrinho.getPrecoUnitario()
                        .multiply(new BigDecimal(quantidade)));

        produtoCarrinhoRepository.save(produtoCarrinho);

        return produtoCarrinho;
    }

}
