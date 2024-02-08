package br.com.tech.springtech.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tech.springtech.api.dto.input.AvaliacaoInput;
import br.com.tech.springtech.domain.model.Avaliacao;
import br.com.tech.springtech.domain.model.Produto;
import br.com.tech.springtech.domain.model.Usuario;
import br.com.tech.springtech.domain.repository.AvaliacaoRepository;
import br.com.tech.springtech.domain.repository.ProdutoRepository;
import br.com.tech.springtech.domain.repository.UsuarioRepository;
import jakarta.transaction.Transactional;

@Service
public class CadastroAvaliacaoService {

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Transactional
    public Avaliacao salvar(Avaliacao avaliacao, AvaliacaoInput avaliacaoInput) {
        // Fetch the Usuario and Produto based on the provided IDs
        Usuario usuario = usuarioRepository.findById(avaliacaoInput.getUsuarioId()).orElseThrow();
        Produto produto = produtoRepository.findById(avaliacaoInput.getProdutoId()).orElseThrow();

        // Set the Usuario and Produto in the Avaliacao
        avaliacao.setUsuario(usuario);
        avaliacao.setProduto(produto);

        // Save and return the Avaliacao
        return avaliacaoRepository.save(avaliacao);
    }

    public Avaliacao buscar(Long avaliacaoId) {
        return avaliacaoRepository.findById(avaliacaoId).orElseThrow();
    }
}
