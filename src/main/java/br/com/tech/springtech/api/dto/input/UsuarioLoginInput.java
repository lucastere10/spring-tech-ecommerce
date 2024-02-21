package br.com.tech.springtech.api.dto.input;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioLoginInput {

    @NotBlank
    private String nome;

    @NotBlank
    private String senha;

}
