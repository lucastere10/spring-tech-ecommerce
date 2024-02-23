package br.com.tech.springtech.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.tech.springtech.domain.model.Carrinho;
import br.com.tech.springtech.domain.model.Produto;
import br.com.tech.springtech.domain.model.ProdutoCarrinho;

public interface ProdutoCarrinhoRepository  extends JpaRepository<ProdutoCarrinho, Long>  {
    
Optional<ProdutoCarrinho> findByCarrinhoAndProduto(Carrinho carrinho, Produto produto);

Optional<ProdutoCarrinho> findByCarrinhoCarrinhoIdAndProdutoProdutoId(Long carrinhoId, Long produtoId);

}
