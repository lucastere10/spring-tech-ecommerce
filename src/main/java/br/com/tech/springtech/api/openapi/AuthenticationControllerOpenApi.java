package br.com.tech.springtech.api.openapi;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.tech.springtech.core.security.AuthenticationDTO;
import br.com.tech.springtech.core.security.RegisterDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Autenticação", description = "Endpoints do sistema de autenticação")
public interface AuthenticationControllerOpenApi {

    @Operation(summary = "Fazer Login", responses = {
            @ApiResponse(responseCode = "200", description = "Login realizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Login ou Senha incorretos", content = @Content(schema = @Schema(ref = "Problema"))),
    })
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data);

    @Operation(summary = "Cadastrar novo usuário", responses = {
            @ApiResponse(responseCode = "200", description = "Cadastro realizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Verifique o corpo da requisição", content = @Content(schema = @Schema(ref = "Problema"))),
    })
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data);

}
