package br.com.tech.springtech.domain.model;

import java.time.OffsetDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import br.com.tech.springtech.domain.Enum.UsuarioStatus;
import br.com.tech.springtech.domain.Enum.UsuarioTipo;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
public class Usuario {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@Column(nullable = false)
    private String nome;

	@Column(nullable = false)
    private String email;

    	@Column(nullable = false)
    private String senha;

    @Column(nullable = false)
    private UsuarioTipo usuarioTipo;

    @Column(nullable = false)
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

}
