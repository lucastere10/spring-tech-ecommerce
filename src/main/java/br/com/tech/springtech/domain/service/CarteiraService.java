package br.com.tech.springtech.domain.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tech.springtech.domain.exception.SaldoInsuficienteException;
import br.com.tech.springtech.domain.model.Carteira;
import br.com.tech.springtech.domain.repository.CarteiraRepository;

@Service
public class CarteiraService {

    @Autowired
    private CarteiraRepository carteiraRepository;

    public void adicionarSaldo(Long carteiraId, BigDecimal valor) {
        Carteira carteira = carteiraRepository.findById(carteiraId)
                .orElseThrow();

        BigDecimal saldoAtual = carteira.getSaldo();
        BigDecimal novoSaldo = saldoAtual.add(valor);

        carteira.setSaldo(novoSaldo);
        carteiraRepository.save(carteira);
    }

    public void removerSaldo(Long carteiraId, BigDecimal valor) {
        Carteira carteira = carteiraRepository.findById(carteiraId)
                .orElseThrow();

        BigDecimal saldoAtual = carteira.getSaldo();
        BigDecimal novoSaldo = saldoAtual.subtract(valor);

        if (novoSaldo.compareTo(BigDecimal.ZERO) < 0) {
            throw new SaldoInsuficienteException(carteira.getCarteiraId());
        }

        carteira.setSaldo(novoSaldo);
        carteiraRepository.save(carteira);
    }

    public Carteira buscar(Long carteiraId) {
        return carteiraRepository.findById(carteiraId)
                .orElseThrow();
    }
}
