package br.com.tech.springtech.domain.exception;

public class SaldoInsuficienteException extends NegocioException {

    private static final long serialVersionUID = 1L;

    public SaldoInsuficienteException(String mensagem) {
        super(mensagem);
    }

    public SaldoInsuficienteException(Long carteiraId) {
        this(String.format("Saldo insuficiente para realizar a operação. ID conta: %d",
                carteiraId));
    }

}
