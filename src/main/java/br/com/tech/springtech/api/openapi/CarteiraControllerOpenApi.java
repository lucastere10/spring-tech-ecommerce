package br.com.tech.springtech.api.openapi;

import org.springdoc.core.converters.models.PageableAsQueryParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.tech.springtech.api.dto.input.CarteiraInput;
import br.com.tech.springtech.api.dto.model.CarteiraModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@Tag(name = "Carteiras", description = "Endpoints de carteiras")
public interface CarteiraControllerOpenApi {

    @PageableAsQueryParam
    @Operation(summary = "Listar todas as carteiras", parameters = {
            @Parameter(name = "page", example = "0"),
            @Parameter(name = "size", example = "10"),
            @Parameter(name = "sort", example = "")
    })
    public Page<CarteiraModel> listar(@Parameter(hidden = true) Pageable pageable);

    @Operation(summary = "Buscar uma carteira por ID", responses = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "400", description = "ID de carteira inválido", content = @Content(schema = @Schema(ref = "Problema"))),
            @ApiResponse(responseCode = "404", description = "Carteira não encontrada", content = @Content(schema = @Schema(ref = "Problema"))),
    })
    public CarteiraModel buscar(
            @Parameter(description = "ID da carteira", example = "1", required = true) Long carteiraId);

    @Operation(summary = "Fazer um depósito", responses = {
            @ApiResponse(responseCode = "204"),
            @ApiResponse(responseCode = "400", description = "ID de carteira inválido", content = @Content(schema = @Schema(ref = "Problema"))),
            @ApiResponse(responseCode = "404", description = "Carteira não encontrada", content = @Content(schema = @Schema(ref = "Problema"))),
    })
    public ResponseEntity<Void> adicionarSaldo(
            @PathVariable @Parameter(description = "ID de uma carteira") Long carteiraId,
            @RequestBody(description = "Adicionar o valor do depósito") CarteiraInput carteiraInput);

    @Operation(summary = "Fazer um saque", responses = {
            @ApiResponse(responseCode = "204"),
            @ApiResponse(responseCode = "400", description = "ID de carteira inválido", content = @Content(schema = @Schema(ref = "Problema"))),
            @ApiResponse(responseCode = "404", description = "Carteira não encontrada", content = @Content(schema = @Schema(ref = "Problema"))),
    })
    public ResponseEntity<Void> removerSaldo(
            @PathVariable @Parameter(description = "ID de uma carteira") Long carteiraId,
            @RequestBody(description = "Adicionar o valor do depósito") CarteiraInput carteiraInput);

}
