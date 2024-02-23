package br.com.tech.springtech.api.openapi;

import org.springdoc.core.converters.models.PageableAsQueryParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.tech.springtech.api.dto.input.ProdutoInput;
import br.com.tech.springtech.api.dto.input.QuantidadeProdutoInput;
import br.com.tech.springtech.api.dto.model.ProdutoCarrinhoModel;
import br.com.tech.springtech.api.dto.model.ProdutoModel;
import br.com.tech.springtech.domain.model.Usuario;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Produtos", description = "Endpoints de produtos")
public interface ProdutoControllerOpenApi {

        @PageableAsQueryParam
        @Operation(summary = "Listar todos os produtos", parameters = {
                        @Parameter(name = "page", example = "0"),
                        @Parameter(name = "size", example = "10"),
                        @Parameter(name = "sort", example = "")
        })
        @SecurityRequirement(name = "Bearer Authentication")
        public Page<ProdutoModel> listar(@Parameter(hidden = true) Pageable pageable);

        @Operation(summary = "Buscar um produto por ID", responses = {
                        @ApiResponse(responseCode = "200"),
                        @ApiResponse(responseCode = "400", description = "ID de produto inválido", content = @Content(schema = @Schema(ref = "Problema"))),
                        @ApiResponse(responseCode = "404", description = "Produto não encontrado", content = @Content(schema = @Schema(ref = "Problema"))),
        })
        @SecurityRequirement(name = "Bearer Authentication")
        public ProdutoModel buscar(
                        @Parameter(description = "ID do produto", example = "1", required = true) @PathVariable Long produtoId);

        @Operation(summary = "Adicionar novo produto")
        public ProdutoModel adicionar(
                        @RequestBody(description = "Cadastro de um novo produto", required = true) ProdutoInput produtoInput);

        @Operation(summary = "Atualizar um produto por ID", responses = {
                        @ApiResponse(responseCode = "200"),
                        @ApiResponse(responseCode = "400", description = "ID de produto inválido", content = @Content(schema = @Schema(ref = "Problema"))),
                        @ApiResponse(responseCode = "404", description = "Produto não encontrado", content = @Content(schema = @Schema(ref = "Problema"))),
        })
        @SecurityRequirement(name = "Bearer Authentication")
        public ProdutoModel atualizar(
                        @Parameter(description = "ID de um produto") @PathVariable Long produtoId,
                        @RequestBody(description = "Representação de uma produto com dados atualizados") ProdutoInput produtoInput);

        @Operation(summary = "Remover um produto por ID")
        @SecurityRequirement(name = "Bearer Authentication")
        public void remover(@Parameter(description = "ID de um produto") @PathVariable Long produtoId);

        @Operation(summary = "Adicionar um produto ao carrinho", responses = {
                        @ApiResponse(responseCode = "204"),
                        @ApiResponse(responseCode = "400", description = "ID de produto inválido", content = @Content(schema = @Schema(ref = "Problema"))),
                        @ApiResponse(responseCode = "404", description = "Produto não encontrado", content = @Content(schema = @Schema(ref = "Problema"))),
        })
        @SecurityRequirement(name = "Bearer Authentication")
        public ProdutoCarrinhoModel addCarrinho(
                        @Parameter(description = "ID de um produto") Long produtoId,
                        @RequestBody(description = "Adicionar a quantidade a ser adicionada") QuantidadeProdutoInput quantidadeProdutoInput,
                        @Parameter(description = "Usuário Logado") @AuthenticationPrincipal Usuario usuario);

        @Operation(summary = "Atualizar quantidade", responses = {
                        @ApiResponse(responseCode = "204"),
                        @ApiResponse(responseCode = "400", description = "ID de produto inválido", content = @Content(schema = @Schema(ref = "Problema"))),
                        @ApiResponse(responseCode = "404", description = "Carteira não encontrada", content = @Content(schema = @Schema(ref = "Problema"))),
        })
        @SecurityRequirement(name = "Bearer Authentication")
        public ProdutoCarrinhoModel atualizarQuantidade(
                        @Parameter(description = "ID de um produto") Long produtoId,
                        @RequestBody(description = "Adicionar a quantidade a ser alterada") QuantidadeProdutoInput quantidadeProdutoInput,
                        @Parameter(description = "Usuário Logado") @AuthenticationPrincipal Usuario usuario);
}
