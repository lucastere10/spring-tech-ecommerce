package br.com.tech.springtech.api.openapi;

import org.springdoc.core.converters.models.PageableAsQueryParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.tech.springtech.api.dto.input.AvaliacaoInput;
import br.com.tech.springtech.api.dto.model.AvaliacaoModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Avaliacoes", description = "Endpoints de avaliações")
public interface AvaliacaoControllerOpenApi {

        @PageableAsQueryParam
        @Operation(summary = "Listar todas as avaliações", parameters = {
                        @Parameter(name = "page", example = "0"),
                        @Parameter(name = "size", example = "10"),
                        @Parameter(name = "sort", example = "")
        })
        @SecurityRequirement(name = "Bearer Authentication")
        public Page<AvaliacaoModel> listar(@Parameter(hidden = true) Pageable pageable);

        @Operation(summary = "Buscar uma avaliação por ID", responses = {
                        @ApiResponse(responseCode = "200"),
                        @ApiResponse(responseCode = "400", description = "ID de avaliação inválido", content = @Content(schema = @Schema(ref = "Problema"))),
                        @ApiResponse(responseCode = "404", description = "Avaliação não encontrada", content = @Content(schema = @Schema(ref = "Problema"))),
        })
        @SecurityRequirement(name = "Bearer Authentication")
        public AvaliacaoModel buscar(
                        @Parameter(description = "ID da avaliação", example = "1", required = true) Long avaliacaoId);

        @Operation(summary = "Adicionar nova avaliação", responses = @ApiResponse(responseCode = "201", description = "Avaliação Cadastrada com Sucesso"))
        @SecurityRequirement(name = "Bearer Authentication")
        public AvaliacaoModel adicionar(
                        @RequestBody(description = "Cadastro de uma nova avaliação", required = true) AvaliacaoInput avaliacaoInput);

        @Operation(summary = "Atualizar uma avaliação por ID", responses = {
                        @ApiResponse(responseCode = "200", description = "Avaliação atualizada com sucecsso"),
                        @ApiResponse(responseCode = "400", description = "ID de avaliação inválido", content = @Content(schema = @Schema(ref = "Problema"))),
                        @ApiResponse(responseCode = "404", description = "Avaliação não encontrada", content = @Content(schema = @Schema(ref = "Problema"))),
        })
        @SecurityRequirement(name = "Bearer Authentication")
        public AvaliacaoModel atualizar(
                        @Parameter(description = "ID de uma avaliação") Long avaliacaoId,
                        @RequestBody(description = "Representação de uma avaliação com dados atualizados") AvaliacaoInput avaliacaoInput);

}
