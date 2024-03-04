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

import br.com.tech.springtech.api.assembler.AvaliacaoInputDisassembler;
import br.com.tech.springtech.api.assembler.AvaliacaoModelAssembler;
import br.com.tech.springtech.api.dto.input.AvaliacaoInput;
import br.com.tech.springtech.api.dto.model.AvaliacaoModel;
import br.com.tech.springtech.api.openapi.AvaliacaoControllerOpenApi;
import br.com.tech.springtech.domain.exception.AvaliacaoNaoEncontradoException;
import br.com.tech.springtech.domain.exception.NegocioException;
import br.com.tech.springtech.domain.model.Avaliacao;
import br.com.tech.springtech.domain.repository.AvaliacaoRepository;
import br.com.tech.springtech.domain.service.CadastroAvaliacaoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/api/avaliacoes")
public class AvaliacaoController implements AvaliacaoControllerOpenApi {

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    @Autowired
    private CadastroAvaliacaoService cadastroAvaliacao;

    @Autowired
    private AvaliacaoModelAssembler avaliacaoModelAssembler;

    @Autowired
    private AvaliacaoInputDisassembler avaliacaoInputDisassembler;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<AvaliacaoModel> listar(@PageableDefault(size = 20, page = 0) Pageable pageable) {
        Page<Avaliacao> avaliacoesPage = avaliacaoRepository.findAll(pageable);

        List<AvaliacaoModel> avaliacoesModel = avaliacaoModelAssembler
                .toCollectionModel(avaliacoesPage.getContent());

        return new PageImpl<>(avaliacoesModel, pageable,
                avaliacoesPage.getTotalElements());
    }

    @GetMapping(value = "/{avaliacaoId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public AvaliacaoModel buscar(@PathVariable Long avaliacaoId) {
        Avaliacao avaliacao = cadastroAvaliacao.buscar(avaliacaoId);

        return avaliacaoModelAssembler.toModel(avaliacao);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public AvaliacaoModel adicionar(@RequestBody @Valid AvaliacaoInput avaliacaoInput) {
        try {
            Avaliacao avaliacao = avaliacaoInputDisassembler.toDomainObject(avaliacaoInput);
            avaliacao = cadastroAvaliacao.salvar(avaliacao, avaliacaoInput);

            return avaliacaoModelAssembler.toModel(avaliacao);
        } catch (AvaliacaoNaoEncontradoException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @PutMapping(value = "/{avaliacaoId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public AvaliacaoModel atualizar(@PathVariable Long avaliacaoId,
            @RequestBody @Valid AvaliacaoInput avaliacaoInput) {

        try {
            Avaliacao avaliacaoAtual = cadastroAvaliacao.buscar(avaliacaoId);
            avaliacaoInputDisassembler.copyToDomainObject(avaliacaoInput, avaliacaoAtual);
            avaliacaoAtual = cadastroAvaliacao.salvar(avaliacaoAtual, avaliacaoInput);

            return avaliacaoModelAssembler.toModel(avaliacaoAtual);

        } catch (AvaliacaoNaoEncontradoException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

}
