package br.com.tech.springtech.api.dto.input;

import java.math.BigDecimal;

import org.hibernate.validator.constraints.UniqueElements;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoInput {
 
	@NotBlank
	private String nome;

	private String descricao;

	@Positive
	private BigDecimal preco;

}
