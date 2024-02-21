package br.com.tech.springtech.api.dto.input;

import br.com.tech.springtech.domain.enums.UsuarioStatus;
import br.com.tech.springtech.domain.enums.UsuarioTipo;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioInput {
  
	@NotBlank
	private String nome;
    
	@NotBlank
	private String email;

	@NotBlank
	private UsuarioTipo usuarioTipo;

	@NotBlank
	private UsuarioStatus usuarioStatus;

}
