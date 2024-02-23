package br.com.tech.springtech.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.tech.springtech.domain.model.ProdutoPedido;

public interface ProdutoPedidoRepository extends JpaRepository<ProdutoPedido, Long> {
    
}
