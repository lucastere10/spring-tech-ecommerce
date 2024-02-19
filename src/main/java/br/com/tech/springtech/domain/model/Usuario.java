package br.com.tech.springtech.domain.model;

import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.tech.springtech.domain.Enum.UsuarioStatus;
import br.com.tech.springtech.domain.Enum.UsuarioTipo;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Usuario implements UserDetails {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long usuarioId;

	@Column(nullable = false)
    private String nome;

	@Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String senha;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UsuarioTipo usuarioTipo;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UsuarioStatus usuarioStatus;

    @CreationTimestamp
    @Column(nullable = false, columnDefinition = "datetime")
    private OffsetDateTime dataCadastro;

    @OneToOne(mappedBy = "usuario")
    private Carteira carteira;

    @OneToMany(mappedBy = "usuario")
    private List<Pedido> pedidos;

    @OneToMany(mappedBy = "usuario")
    private List<Avaliacao> avaliacoes;

    @OneToOne(mappedBy = "usuario")
    private Carrinho carrinho;

    public Usuario() {
    }    

    public Usuario(String nome, String login, String senha, UsuarioTipo usuarioTipo){
        this.nome = nome;
        this.email = login;
        this.senha = senha;
        this.usuarioTipo = usuarioTipo;
    }

    public Usuario(String nome, String email, String senha, UsuarioTipo usuarioTipo, UsuarioStatus usuarioStatus) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.usuarioTipo = usuarioTipo;
        this.usuarioStatus = usuarioStatus;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.usuarioTipo == UsuarioTipo.ADMIN) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        else return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    
    @Override
    public boolean isEnabled() {
        return true;
    }
}
