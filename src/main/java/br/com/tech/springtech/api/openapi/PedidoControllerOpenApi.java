package br.com.tech.springtech.api.openapi;

import org.springdoc.core.converters.models.PageableAsQueryParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import br.com.tech.springtech.api.dto.input.PedidoInput;
import br.com.tech.springtech.api.dto.model.PedidoModel;
import br.com.tech.springtech.domain.model.Usuario;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Pedidos", description = "Endpoints de pedidos")
public interface PedidoControllerOpenApi {

    @PageableAsQueryParam
    @Operation(summary = "Listar todos os pedidos", parameters = {
            @Parameter(name = "page", example = "0"),
            @Parameter(name = "size", example = "10"),
            @Parameter(name = "sort", example = "")
    })
    @SecurityRequirement(name = "Bearer Authentication")
    public Page<PedidoModel> listar(@Parameter(hidden = true) Pageable pageable);

    @Operation(summary = "Buscar um pedido por ID", responses = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "400", description = "ID de pedido inválido", content = @Content(schema = @Schema(ref = "Problema"))),
            @ApiResponse(responseCode = "404", description = "Pedido não encontrado", content = @Content(schema = @Schema(ref = "Problema"))),
    })
    @SecurityRequirement(name = "Bearer Authentication")
    public PedidoModel buscar(@Parameter(description = "ID do pedido", example = "1", required = true) Long pedidoId);

    @Operation(summary = "Gerar um Novo Pedido", responses = {
            @ApiResponse(responseCode = "201"),
            @ApiResponse(responseCode = "400", description = "ID de Pedido inválido", content = @Content(schema = @Schema(ref = "Problema"))),
            @ApiResponse(responseCode = "404", description = "Pedido não encontrado", content = @Content(schema = @Schema(ref = "Problema"))),
    })
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<PedidoModel> gerarNovoPedido(
        @RequestBody(description = "Inputs do pedido") PedidoInput pedidoInput,
        @Parameter(description = "Usuario que pediu") @AuthenticationPrincipal Usuario usuario);

    @Operation(summary = "Mudar Status do Pedido Para Pendente")
    @SecurityRequirement(name = "Bearer Authentication")
    public PedidoModel mudarStatusPendente(@Parameter(description = "ID de um pedido") Long pedidoId);

    @Operation(summary = "Mudar Status do Pedido para em espera")
    @SecurityRequirement(name = "Bearer Authentication")
    public PedidoModel mudarStatusEmEspera(@Parameter(description = "ID de um pedido") Long pedidoId);

    @Operation(summary = "Mudar Status do Pedido para Enviado")
    @SecurityRequirement(name = "Bearer Authentication")
    public PedidoModel mudarStatusEnviado(@Parameter(description = "ID de um pedido") Long pedidoId);

    @Operation(summary = "Mudar Status do Pedido para Entregue")
    @SecurityRequirement(name = "Bearer Authentication")
    public PedidoModel mudarStatusEntregue(@Parameter(description = "ID de um pedido") Long pedidoId);

    @Operation(summary = "Mudar Status do Pedido para Finalizado")
    @SecurityRequirement(name = "Bearer Authentication")
    public PedidoModel mudarStatusFinalizado(@Parameter(description = "ID de um pedido") Long pedidoId);

}
