package br.com.tech.springtech.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.tech.springtech.api.assembler.ProdutoInputDisassembler;
import br.com.tech.springtech.api.assembler.ProdutoModelAssembler;
import br.com.tech.springtech.api.dto.input.ProdutoInput;
import br.com.tech.springtech.api.dto.model.ProdutoModel;
import br.com.tech.springtech.domain.model.Produto;
import br.com.tech.springtech.domain.repository.ProdutoRepository;
import br.com.tech.springtech.domain.service.CadastroProdutoService;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CadastroProdutoService cadastroProduto;

    @Autowired
    private ProdutoModelAssembler produtoModelAssembler;

    @Autowired
    private ProdutoInputDisassembler produtoInputDisassembler;

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
    public ProdutoModel adicionar(@RequestBody ProdutoInput produtoInput) {
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

}

