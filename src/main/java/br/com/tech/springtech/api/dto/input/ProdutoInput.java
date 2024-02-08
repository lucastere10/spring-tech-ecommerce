package br.com.tech.springtech.api.dto.input;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoInput {
 
	private String nome;

	private String descricao;

	private BigDecimal preco;

}
