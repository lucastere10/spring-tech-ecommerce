package br.com.tech.springtech.api.dto.model;

import br.com.tech.springtech.domain.enums.UsuarioStatus;
import br.com.tech.springtech.domain.enums.UsuarioTipo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioModel {

    private Long usuarioId;

    private String nome;

    private String email;

    private UsuarioTipo usuarioTipo;

    private UsuarioStatus usuarioStatus;

}
