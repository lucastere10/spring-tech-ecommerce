package br.com.tech.springtech.domain.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tech.springtech.domain.model.Carrinho;
import br.com.tech.springtech.domain.model.Carteira;
import br.com.tech.springtech.domain.model.Usuario;
import br.com.tech.springtech.domain.repository.CarrinhoRepository;
import br.com.tech.springtech.domain.repository.CarteiraRepository;
import br.com.tech.springtech.domain.repository.UsuarioRepository;
import jakarta.transaction.Transactional;

@Service
public class CadastroUsuarioService {

  @Autowired
  private UsuarioRepository usuarioRepository;

  @Autowired
  private CarrinhoRepository carrinhoRepository;

  @Autowired
  CarteiraRepository carteiraRepository;

  @Transactional
  public Usuario salvar(Usuario usuario) {

    usuario = usuarioRepository.save(usuario);

    // Criar carrinho e definir valor 0
    Carrinho carrinho = new Carrinho();
    carrinho.setUsuario(usuario);
    carrinho.setValor(new BigDecimal(0));

    // Criar carteira e definir valor 0
    Carteira carteira = new Carteira();
    carteira.setUsuario(usuario);
    carteira.setSaldo(new BigDecimal(0));

    // Salvar carrinho e carteira
    carrinho = carrinhoRepository.save(carrinho);
    carteira = carteiraRepository.save(carteira);

    // Set the Carrinho to the Usuario and save again
    usuario.setCarrinho(carrinho);
    return usuarioRepository.save(usuario);
  }

  public Usuario buscarOuFalhar(Long usuarioId) {
    return usuarioRepository.findById(usuarioId)
        .orElseThrow();
  }

}
