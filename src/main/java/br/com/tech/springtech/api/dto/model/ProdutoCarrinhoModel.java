package br.com.tech.springtech.api.dto.model;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoCarrinhoModel {

    @Schema(example = "1")
    private Long produtoCarrinhoId;

    @Schema(example = "2")
    private Long quantidade;

    @Schema(example = "10.40")
    private BigDecimal precoUnitario;

    @Schema(example = "20.80")
    private BigDecimal precoTotal;

    @Schema(example = "0.00")
    private BigDecimal desconto;

    private ProdutoModel produto;

}
