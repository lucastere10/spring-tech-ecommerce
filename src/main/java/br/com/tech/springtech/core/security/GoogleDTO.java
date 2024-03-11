package br.com.tech.springtech.core.security;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Registration data")
public record GoogleDTO(
    @Schema(description = "Nome do Usuário", example = "Lucas Caldas") String nome,
    @Schema(description = "Email do Usuário", example = "lucas.caldas@mail.com") String login,
    @Schema(description = "Senha do Usuário", example = "senha123") String senha
) {}
