package br.com.tech.springtech.api.dto.input;

import java.math.BigDecimal;

import org.hibernate.validator.constraints.UniqueElements;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoInput {
 
	@NotBlank
	@Schema(example = "Placa de Video")
	private String nome;

	@Schema(example = "Descrição da Placa de Video")
	private String descricao;

	@Positive
	@Schema(example = "1935.50")
	private BigDecimal preco;

}
