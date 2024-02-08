package br.com.tech.springtech.api.dto.model;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoModel {
    
	private Long produtoId;

	private String nome;

	private String descricao;

	private BigDecimal preco;

}
