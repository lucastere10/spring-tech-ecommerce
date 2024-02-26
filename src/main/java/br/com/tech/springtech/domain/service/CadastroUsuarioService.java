package br.com.tech.springtech.domain.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import br.com.tech.springtech.domain.enums.UsuarioStatus;
import br.com.tech.springtech.domain.exception.UsuarioNaoEncontradoException;
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
        .orElseThrow(() -> new UsuarioNaoEncontradoException(usuarioId));
  }

  @Transactional
    public void excluir(Long usuarioId) {
        try {
            usuarioRepository.deleteById(usuarioId);
            usuarioRepository.flush();

        } catch (EmptyResultDataAccessException e) {
            throw new UsuarioNaoEncontradoException(usuarioId);
        }
    }

    public Usuario ativarUsuario(Usuario usuario) {
        usuario.setUsuarioStatus(UsuarioStatus.ATIVO);
        return usuarioRepository.save(usuario);
    }

    public Usuario desativarUsuario(Usuario usuario) {
        usuario.setUsuarioStatus(UsuarioStatus.INATIVO);
        return usuarioRepository.save(usuario);
    }

    public Usuario banirUsuario(Usuario usuario) {
        usuario.setUsuarioStatus(UsuarioStatus.BANIDO);
        return usuarioRepository.save(usuario);
    }

    public Usuario bloquearUsuario(Usuario usuario) {
        usuario.setUsuarioStatus(UsuarioStatus.BLOQUEADO);
        return usuarioRepository.save(usuario);
    }

}
