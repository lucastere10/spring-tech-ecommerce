package br.com.tech.springtech.api.dto.model;

import br.com.tech.springtech.domain.enums.UsuarioStatus;
import br.com.tech.springtech.domain.enums.UsuarioTipo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioModel {

    @Schema(example = "1")
    private Long usuarioId;

    @Schema(example = "Lucas Caldas")
    private String nome;

    @Schema(example = "lucas.caldas@mail.com")
    private String email;

    @Schema(example = "ADMIN")
    private UsuarioTipo usuarioTipo;

    @Schema(example = "ATIVO")
    private UsuarioStatus usuarioStatus;

}
