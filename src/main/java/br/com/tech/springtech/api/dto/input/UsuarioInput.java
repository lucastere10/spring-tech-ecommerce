package br.com.tech.springtech.api.dto.input;

import br.com.tech.springtech.domain.enums.UsuarioStatus;
import br.com.tech.springtech.domain.enums.UsuarioTipo;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioInput {
  
	@NotBlank
	@Schema(example = "Lucas Caldas")
	private String nome;
    
	@NotBlank
	@Schema(example = "lucas.caldas@mail.com")
	private String email;

	@NotBlank
	@Schema(example = "ADMIN")
	private UsuarioTipo usuarioTipo;

	@NotBlank
	@Schema(example = "ATIVO")
	private UsuarioStatus usuarioStatus;

}
