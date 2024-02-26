package br.com.tech.springtech.domain.exception;

public class AvaliacaoNaoEncontradoException extends NegocioException {
    
	private static final long serialVersionUID = 1L;

	public AvaliacaoNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public AvaliacaoNaoEncontradoException(Long pedidoId) {
		this(String.format("Não existe um cadastro de avaliacao com código %d", 
				pedidoId));
	}

}
