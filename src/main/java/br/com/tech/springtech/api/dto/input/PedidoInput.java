package br.com.tech.springtech.api.dto.input;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoInput {
    
    @Schema(example = "Rua das flores, 123")
    private String endereco;

}
