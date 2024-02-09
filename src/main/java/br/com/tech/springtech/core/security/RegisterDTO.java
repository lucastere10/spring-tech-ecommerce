package br.com.tech.springtech.core.security;

import br.com.tech.springtech.domain.Enum.UsuarioTipo;

public record RegisterDTO(String nome, String login, String senha, UsuarioTipo role) {
    
}
