package br.com.tech.springtech.api.openapi;

import org.springdoc.core.converters.models.PageableAsQueryParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import br.com.tech.springtech.api.dto.input.ProdutoInput;
import br.com.tech.springtech.api.dto.model.ProdutoModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Produtos", description = "Endpoints de produtos")
public interface ProdutoControllerOpenApi {

    @PageableAsQueryParam
    @Operation(summary = "Listar todos os produtos", parameters = {
            @Parameter(name = "page", example = "0"),
            @Parameter(name = "size", example = "10"),
            @Parameter(name = "sort", example = "")
    })
    public Page<ProdutoModel> listar(@Parameter(hidden = true) Pageable pageable);

    @Operation(summary = "Buscar um produto por ID", responses = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "400", description = "ID de produto inválido", content = @Content(schema = @Schema(ref = "Problema"))),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado", content = @Content(schema = @Schema(ref = "Problema"))),
    })
    public ProdutoModel buscar(
            @Parameter(description = "ID do produto", example = "1", required = true) @PathVariable Long produtoId);

    @Operation(summary = "Adicionar novo produto")
    public ProdutoModel adicionar(@RequestBody(description = "Cadastro de um novo produto", required = true) ProdutoInput produtoInput);

    @Operation(summary = "Atualizar um produto por ID", responses = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "400", description = "ID de produto inválido", content = @Content(schema = @Schema(ref = "Problema"))),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado", content = @Content(schema = @Schema(ref = "Problema"))),
    })
    public ProdutoModel atualizar(
            @Parameter(description = "ID de um produto") @PathVariable Long produtoId,
            @RequestBody(description = "Representação de uma produto com dados atualizados") ProdutoInput produtoInput);

    @Operation(summary = "Remover um produto por ID")
    public void remover(@Parameter(description = "ID de um produto")  @PathVariable Long produtoId);

}
