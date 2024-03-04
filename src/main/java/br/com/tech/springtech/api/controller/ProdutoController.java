package br.com.tech.springtech.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.tech.springtech.api.assembler.ProdutoCarrinhoModelAssembler;
import br.com.tech.springtech.api.assembler.ProdutoInputDisassembler;
import br.com.tech.springtech.api.assembler.ProdutoModelAssembler;
import br.com.tech.springtech.api.dto.input.QuantidadeProdutoInput;
import br.com.tech.springtech.api.dto.input.ProdutoInput;
import br.com.tech.springtech.api.dto.model.ProdutoCarrinhoModel;
import br.com.tech.springtech.api.dto.model.ProdutoModel;
import br.com.tech.springtech.api.openapi.ProdutoControllerOpenApi;
import br.com.tech.springtech.domain.exception.ProdutoNaoEncontradoException;
import br.com.tech.springtech.domain.model.Produto;
import br.com.tech.springtech.domain.model.ProdutoCarrinho;
import br.com.tech.springtech.domain.model.Usuario;
import br.com.tech.springtech.domain.repository.ProdutoCarrinhoRepository;
import br.com.tech.springtech.domain.repository.ProdutoRepository;
import br.com.tech.springtech.domain.service.CadastroProdutoService;
import br.com.tech.springtech.domain.service.CarrinhoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/api/produtos")
@CrossOrigin(origins = "http://localhost:3000/")
public class ProdutoController implements ProdutoControllerOpenApi {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ProdutoCarrinhoRepository produtoCarrinhoRepository;

    @Autowired
    private CadastroProdutoService cadastroProduto;

    @Autowired
    private CarrinhoService carrinhoService;

    @Autowired
    private ProdutoModelAssembler produtoModelAssembler;

    @Autowired
    private ProdutoInputDisassembler produtoInputDisassembler;

    @Autowired
    private ProdutoCarrinhoModelAssembler produtoCarrinhoModelAssembler;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<ProdutoModel> listar(@PageableDefault(size = 10) Pageable pageable) {
        Page<Produto> produtosPage = produtoRepository.findAll(pageable);

        List<ProdutoModel> produtosModel = produtoModelAssembler
                .toCollectionModel(produtosPage.getContent());

        return new PageImpl<>(produtosModel, pageable,
                produtosPage.getTotalElements());
    }

    @GetMapping(value = "/{produtoId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ProdutoModel buscar(@PathVariable Long produtoId) {
        Produto produto = cadastroProduto.buscar(produtoId);

        return produtoModelAssembler.toModel(produto);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ProdutoModel adicionar(@RequestBody @Valid ProdutoInput produtoInput) {
        Produto produto = produtoInputDisassembler.toDomainObject(produtoInput);
        produto = cadastroProduto.salvar(produto);

        return produtoModelAssembler.toModel(produto);
    }

    @PutMapping(value = "/{produtoId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ProdutoModel atualizar(@PathVariable Long produtoId,
            @RequestBody ProdutoInput produtoInput) {
        Produto produtoAtual = cadastroProduto.buscar(produtoId);
        produtoInputDisassembler.copyToDomainObject(produtoInput, produtoAtual);
        produtoAtual = cadastroProduto.salvar(produtoAtual);

        return produtoModelAssembler.toModel(produtoAtual);
    }

    @DeleteMapping(value = "/{produtoId}", produces = {})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long produtoId) {
        cadastroProduto.excluir(produtoId);
    }

    @PostMapping(value = "/addcarrinho/{produtoId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ProdutoCarrinhoModel addCarrinho(
            @PathVariable Long produtoId,
            @RequestBody @Valid QuantidadeProdutoInput quantidadeProdutoInput,
            @AuthenticationPrincipal Usuario usuario) {

        Produto produto = produtoRepository.findById(produtoId)
                .orElseThrow(() -> new ProdutoNaoEncontradoException(produtoId));

        ProdutoCarrinho produtoCarrinho = carrinhoService.adicionarProdutoAoCarrinho(usuario.getUsuarioId(), produto,
                quantidadeProdutoInput.getQuantidade());

        return produtoCarrinhoModelAssembler.toModel(produtoCarrinho);
    }

    @PutMapping(value = "/atualizarquantidadecarrinho/{produtoId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ProdutoCarrinhoModel atualizarQuantidade(
            @PathVariable Long produtoId,
            @RequestBody @Valid QuantidadeProdutoInput quantidadeProdutoInput,
            @AuthenticationPrincipal Usuario usuario) {

        ProdutoCarrinho produtoCarrinho = produtoCarrinhoRepository.findByCarrinhoCarrinhoIdAndProdutoProdutoId(usuario.getUsuarioId(), produtoId)
                .orElseThrow(() -> new ProdutoNaoEncontradoException("Produto não encontrado no carrinho"));

        Long quantidade = quantidadeProdutoInput.getQuantidade();

        produtoCarrinho = carrinhoService.atualizarProdutoCarrinho(produtoCarrinho, quantidade);

        return produtoCarrinhoModelAssembler.toModel(produtoCarrinho);
    }

}
