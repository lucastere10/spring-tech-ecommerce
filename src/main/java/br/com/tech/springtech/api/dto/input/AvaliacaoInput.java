package br.com.tech.springtech.api.dto.input;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AvaliacaoInput {

    private String comentario;

    private Long nota;

    private Long usuarioId;

    private Long produtoId;
    
}
