package br.com.tech.springtech.api.dto.model;

import java.math.BigDecimal;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarrinhoModel {
    
    @Schema(example = "1")
    private Long carrinhoId;

    @Schema(example = "700")
    private BigDecimal valor;

    private UsuarioModel usuario;

    private List<ProdutoCarrinhoModel> produtoCarrinho;

}
