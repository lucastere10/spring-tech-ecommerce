package br.com.tech.springtech.core.security;

import br.com.tech.springtech.domain.enums.UsuarioTipo;

public record RegisterDTO(String nome, String login, String senha, UsuarioTipo role) {
    
}
