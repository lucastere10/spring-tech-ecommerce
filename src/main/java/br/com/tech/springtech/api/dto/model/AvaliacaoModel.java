package br.com.tech.springtech.api.dto.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AvaliacaoModel {
    
    private Long avaliacaoId;

    private String comentario;

    private Long nota;


}
