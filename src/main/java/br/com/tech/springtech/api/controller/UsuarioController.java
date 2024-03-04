package br.com.tech.springtech.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.tech.springtech.api.assembler.CarrinhoModelAssembler;
import br.com.tech.springtech.api.assembler.UsuarioInputDisassembler;
import br.com.tech.springtech.api.assembler.UsuarioModelAssembler;
import br.com.tech.springtech.api.dto.input.UsuarioComSenhaInput;
import br.com.tech.springtech.api.dto.input.UsuarioInput;
import br.com.tech.springtech.api.dto.model.CarrinhoModel;
import br.com.tech.springtech.api.dto.model.UsuarioModel;
import br.com.tech.springtech.api.openapi.UsuarioControllerOpenApi;
import br.com.tech.springtech.domain.model.Carrinho;
import br.com.tech.springtech.domain.model.Usuario;
import br.com.tech.springtech.domain.repository.UsuarioRepository;
import br.com.tech.springtech.domain.service.CadastroUsuarioService;
import br.com.tech.springtech.domain.service.CarrinhoService;

@RestController
@RequestMapping(value = "/api/usuarios")
public class UsuarioController implements UsuarioControllerOpenApi {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CadastroUsuarioService cadastroUsuario;

    @Autowired
    private UsuarioModelAssembler usuarioModelAssembler;

    @Autowired
    private UsuarioInputDisassembler usuarioInputDisassembler;

    @Autowired
    private CarrinhoService carrinhoService;

    @Autowired
    private CarrinhoModelAssembler carrinhoModelAssembler;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<UsuarioModel> listar(@PageableDefault(size = 10) Pageable pageable) {
        Page<Usuario> usuariosPage = usuarioRepository.findAll(pageable);

        List<UsuarioModel> usuariosModel = usuarioModelAssembler
                .toCollectionModel(usuariosPage.getContent());

        return new PageImpl<>(usuariosModel, pageable,
                usuariosPage.getTotalElements());
    }

    @GetMapping(value = "/{usuarioId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public UsuarioModel buscar(@PathVariable Long usuarioId) {
        Usuario usuario = cadastroUsuario.buscarOuFalhar(usuarioId);

        return usuarioModelAssembler.toModel(usuario);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioModel adicionar(@RequestBody UsuarioComSenhaInput usuarioInput) {
        Usuario usuario = usuarioInputDisassembler.toDomainObject(usuarioInput);
        usuario = cadastroUsuario.salvar(usuario);

        return usuarioModelAssembler.toModel(usuario);
    }

    @PutMapping(value = "/{usuarioId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public UsuarioModel atualizar(@PathVariable Long usuarioId,
            @RequestBody UsuarioInput usuarioInput) {
        Usuario usuarioAtual = cadastroUsuario.buscarOuFalhar(usuarioId);
        usuarioInputDisassembler.copyToDomainObject(usuarioInput, usuarioAtual);
        usuarioAtual = cadastroUsuario.salvar(usuarioAtual);

        return usuarioModelAssembler.toModel(usuarioAtual);
    }

    @DeleteMapping(value = "/{usuarioId}", produces = {})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long usuarioId) {
        cadastroUsuario.excluir(usuarioId);
    }

    @GetMapping(value = "/carrinho/{usuarioId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CarrinhoModel buscarCarrinho(@PathVariable Long usuarioId) {
        Carrinho carrinho = carrinhoService.buscar(usuarioId);

        return carrinhoModelAssembler.toModel(carrinho);
    }

    @PutMapping(value = "/status/ativar/{usuarioId}")
    public UsuarioModel ativar(@PathVariable Long usuarioId) {
        Usuario usuarioAtual = cadastroUsuario.buscarOuFalhar(usuarioId);
        usuarioAtual = cadastroUsuario.ativarUsuario(usuarioAtual);

        return usuarioModelAssembler.toModel(usuarioAtual);
    }

    @PutMapping(value = "/status/desativar/{usuarioId}")
    public UsuarioModel desativar(@PathVariable Long usuarioId) {
        Usuario usuarioAtual = cadastroUsuario.buscarOuFalhar(usuarioId);
        usuarioAtual = cadastroUsuario.desativarUsuario(usuarioAtual);

        return usuarioModelAssembler.toModel(usuarioAtual);
    }

    @PutMapping(value = "/status/banir/{usuarioId}")
    public UsuarioModel banir(@PathVariable Long usuarioId) {
        Usuario usuarioAtual = cadastroUsuario.buscarOuFalhar(usuarioId);
        usuarioAtual = cadastroUsuario.banirUsuario(usuarioAtual);

        return usuarioModelAssembler.toModel(usuarioAtual);
    }

    @PutMapping(value = "/status/bloquear/{usuarioId}")
    public UsuarioModel bloquear(@PathVariable Long usuarioId) {
        Usuario usuarioAtual = cadastroUsuario.buscarOuFalhar(usuarioId);
        usuarioAtual = cadastroUsuario.bloquearUsuario(usuarioAtual);

        return usuarioModelAssembler.toModel(usuarioAtual);
    }

}