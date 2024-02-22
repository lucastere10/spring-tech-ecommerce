package br.com.tech.springtech.api.dto.input;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AvaliacaoInput {

    
    @Schema(example = ("Comentário Nota 10"))
    private String comentario;

    @Schema(example = "10")
    @NotNull
    private Long nota;
    
    @Schema(example = "1")
    @NotNull
    private Long usuarioId;
    
    @Schema(example = "1")
    @NotNull
    private Long produtoId;
    
}
