package br.com.tech.springtech.api.dto.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AvaliacaoModel {
    
    @Schema(example = "1")
    private Long avaliacaoId;

    @Schema(example = "comentário nota 10")
    private String comentario;

    @Schema(example = "10")
    private Long nota;


}
