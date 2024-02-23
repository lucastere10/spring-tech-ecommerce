package br.com.tech.springtech.core.security;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.tech.springtech.api.assembler.UsuarioModelAssembler;
import br.com.tech.springtech.api.dto.model.UsuarioModel;
import br.com.tech.springtech.api.openapi.AuthenticationControllerOpenApi;
import br.com.tech.springtech.domain.enums.UsuarioStatus;
import br.com.tech.springtech.domain.model.Carrinho;
import br.com.tech.springtech.domain.model.Carteira;
import br.com.tech.springtech.domain.model.Usuario;
import br.com.tech.springtech.domain.repository.CarrinhoRepository;
import br.com.tech.springtech.domain.repository.CarteiraRepository;
import br.com.tech.springtech.domain.repository.UsuarioRepository;
import br.com.tech.springtech.domain.service.CadastroUsuarioService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthenticationController implements AuthenticationControllerOpenApi {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioModelAssembler usuarioModelAssembler;

    @Autowired
    private CadastroUsuarioService usuarioService;

    @Autowired
    private CarrinhoRepository carrinhoRepository;

    @Autowired
    CarteiraRepository carteiraRepository;

    @Autowired
    TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.senha());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((Usuario) auth.getPrincipal());
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data) {
        if (this.usuarioRepository.findLoginByEmail(data.login()) != null)
            return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.senha());
        Usuario usuario = new Usuario(data.nome(), data.login(), encryptedPassword, data.role());

        usuario.setUsuarioStatus(UsuarioStatus.ATIVO);

        this.usuarioRepository.save(usuario);

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

        // Set the Carrinho and Carteira to the Usuario and save again
        usuario.setCarrinho(carrinho);
        usuario.setCarteira(carteira);
        usuarioRepository.save(usuario);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/user")
    public ResponseEntity<UsuarioModel> currentUser(@AuthenticationPrincipal Usuario usuario) {
        UsuarioModel usuarioModel = usuarioModelAssembler.toModel(usuario);
        return ResponseEntity.ok(usuarioModel);
    }

}
