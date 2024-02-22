package br.com.tech.springtech.domain.exception;

public class AcessoNegadoException extends NegocioException {
    
    private static final long serialVersionUID = 1L;

	public AcessoNegadoException(String mensagem) {
		super(mensagem);
	}

}
