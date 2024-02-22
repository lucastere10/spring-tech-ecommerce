package br.com.tech.springtech.api.dto.model;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoModel {

	@Schema(example = "1")
	private Long produtoId;

	@Schema(example = "Placa de Video")
	private String nome;

	@Schema(example = "Descrição da placa de video")
	private String descricao;

	@Schema(example = "1935.50")
	private BigDecimal preco;

}
