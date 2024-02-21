package br.com.tech.springtech.core.security;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Authentication data")
public record AuthenticationDTO(
    @Schema(description = "Email do Usuário", example = "lucas.caldas@mail.com") String login,
    @Schema(description = "Senha do Usuário", example = "senha123") String senha
) {}
