package br.com.tech.springtech.api.dto.input;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UsuarioComSenhaInput extends UsuarioInput{
 
	@NotBlank
	@Schema(example = "senha123")
	private String senha;

}
