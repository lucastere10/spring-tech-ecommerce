package br.com.tech.springtech.core.security;

import io.swagger.v3.oas.annotations.media.Schema;
import br.com.tech.springtech.domain.enums.UsuarioTipo;

@Schema(description = "Registration data")
public record RegisterDTO(
    @Schema(description = "Nome do Usuário", example = "Lucas Caldas") String nome,
    @Schema(description = "Email do Usuário", example = "lucas.caldas@mail.com") String login,
    @Schema(description = "Senha do Usuário", example = "senha123") String senha,
    @Schema(description = "Tipo de Usuário", example = "ADMIN") UsuarioTipo role
) {}
