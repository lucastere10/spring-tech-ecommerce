package br.com.tech.springtech.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.tech.springtech.api.assembler.PedidoModelAssembler;
import br.com.tech.springtech.api.dto.input.PedidoInput;
import br.com.tech.springtech.api.dto.model.PedidoModel;
import br.com.tech.springtech.api.openapi.PedidoControllerOpenApi;
import br.com.tech.springtech.domain.model.Pedido;
import br.com.tech.springtech.domain.model.Usuario;
import br.com.tech.springtech.domain.repository.PedidoRepository;
import br.com.tech.springtech.domain.service.PedidoService;

@RestController
@RequestMapping("/pedidos")
public class PedidoController implements PedidoControllerOpenApi {

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private PedidoModelAssembler pedidoModelAssembler;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<PedidoModel> listar(@PageableDefault(size = 10) Pageable pageable) {
        Page<Pedido> pedidosPage = pedidoRepository.findAll(pageable);

        List<PedidoModel> pedidosModel = pedidoModelAssembler
                .toCollectionModel(pedidosPage.getContent());

        return new PageImpl<>(pedidosModel, pageable,
                pedidosPage.getTotalElements());
    }

    @GetMapping(value = "/{pedidoId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public PedidoModel buscar(@PathVariable Long pedidoId) {
        Pedido pedido = pedidoService.buscar(pedidoId);

        return pedidoModelAssembler.toModel(pedido);
    }

    @PostMapping
    public ResponseEntity<PedidoModel> gerarNovoPedido(@RequestBody PedidoInput pedidoInput,
            @AuthenticationPrincipal Usuario usuario) {
        Pedido pedido = pedidoService.gerarPedido(usuario.getUsuarioId(), pedidoInput.getEndereco());
        PedidoModel pedidoModel = pedidoModelAssembler.toModel(pedido);
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoModel);
    }

    @PutMapping(value = "/status/pendente/{pedidoId}")
    public PedidoModel mudarStatusPendente(@PathVariable Long pedidoId) {
        Pedido pedidoAtual = pedidoService.buscar(pedidoId);
        pedidoAtual = pedidoService.statusPedidoPendente(pedidoAtual);

        return pedidoModelAssembler.toModel(pedidoAtual);
    }

    @PutMapping(value = "/status/emespera/{pedidoId}")
    public PedidoModel mudarStatusEmEspera(@PathVariable Long pedidoId) {
        Pedido pedidoAtual = pedidoService.buscar(pedidoId);
        pedidoAtual = pedidoService.statusPedidoEmEspera(pedidoAtual);

        return pedidoModelAssembler.toModel(pedidoAtual);
    }

    @PutMapping(value = "/status/enviado/{pedidoId}")
    public PedidoModel mudarStatusEnviado(@PathVariable Long pedidoId) {
        Pedido pedidoAtual = pedidoService.buscar(pedidoId);
        pedidoAtual = pedidoService.statusPedidoEnviado(pedidoAtual);

        return pedidoModelAssembler.toModel(pedidoAtual);
    }


    @PutMapping(value = "/status/entregue/{pedidoId}")
    public PedidoModel mudarStatusEntregue(@PathVariable Long pedidoId) {
        Pedido pedidoAtual = pedidoService.buscar(pedidoId);
        pedidoAtual = pedidoService.statusPedidoEntregue(pedidoAtual);

        return pedidoModelAssembler.toModel(pedidoAtual);
    }


    @PutMapping(value = "/status/finalizar/{pedidoId}")
    public PedidoModel mudarStatusFinalizado(@PathVariable Long pedidoId) {
        Pedido pedidoAtual = pedidoService.buscar(pedidoId);
        pedidoAtual = pedidoService.statusPedidoFinalizado(pedidoAtual);

        return pedidoModelAssembler.toModel(pedidoAtual);
    }





}
