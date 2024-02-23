package br.com.tech.springtech.api.dto.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

import br.com.tech.springtech.domain.enums.PedidoStatus;
import br.com.tech.springtech.domain.model.ProdutoPedido;
import br.com.tech.springtech.domain.model.Usuario;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoModel {


    @Schema(example = "1")
    private Long pedidoId;

    @Schema(example = "ENVIADO")
    private PedidoStatus pedidoStatus;

    @Schema(example = "122.40")
    private BigDecimal valor;

    @Schema(example = "0.00")
    private BigDecimal desconto;

    @Schema(example = "Rua das Flores, 123")
    private String endereco;

    @Schema(example = "2007-12-03T10:15:30+01:00")
    private OffsetDateTime dataCadastro;

    @Schema(example = "2007-12-03T10:15:30+01:00")
    private OffsetDateTime dataAtualizacao;

    // private Usuario usuario;

    @Schema(example = "John Doe")
    private String usuarioNome;


    private List<ProdutoPedido> produtoPedido;
    
}
