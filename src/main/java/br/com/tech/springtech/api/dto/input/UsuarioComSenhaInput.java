package br.com.tech.springtech.api.dto.input;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UsuarioComSenhaInput extends UsuarioInput{
 
	private String senha;

}
