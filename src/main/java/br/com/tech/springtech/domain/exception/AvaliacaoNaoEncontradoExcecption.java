package br.com.tech.springtech.domain.exception;

public class AvaliacaoNaoEncontradoExcecption extends NegocioException {
    
	private static final long serialVersionUID = 1L;

	public AvaliacaoNaoEncontradoExcecption(String mensagem) {
		super(mensagem);
	}
	
	public AvaliacaoNaoEncontradoExcecption(Long pedidoId) {
		this(String.format("Não existe um cadastro de avaliacao com código %d", 
				pedidoId));
	}

}
