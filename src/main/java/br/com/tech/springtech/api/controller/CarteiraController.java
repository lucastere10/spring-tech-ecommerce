package br.com.tech.springtech.api.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.tech.springtech.api.assembler.CarteiraModelAssembler;
import br.com.tech.springtech.api.dto.input.CarteiraInput;
import br.com.tech.springtech.api.dto.model.CarteiraModel;
import br.com.tech.springtech.api.openapi.CarteiraControllerOpenApi;
import br.com.tech.springtech.domain.exception.SaldoInsuficienteException;
import br.com.tech.springtech.domain.model.Carteira;
import br.com.tech.springtech.domain.repository.CarteiraRepository;
import br.com.tech.springtech.domain.service.CarteiraService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/carteiras")
public class CarteiraController implements CarteiraControllerOpenApi {

    @Autowired
    private CarteiraService carteiraService;

    @Autowired
    private CarteiraRepository carteiraRepository;

    @Autowired
    private CarteiraModelAssembler carteiraModelAssembler;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<CarteiraModel> listar(@PageableDefault(size = 10) Pageable pageable) {
        Page<Carteira> carteirasPage = carteiraRepository.findAll(pageable);

        List<CarteiraModel> carteirasModel = carteiraModelAssembler
                .toCollectionModel(carteirasPage.getContent());

        return new PageImpl<>(carteirasModel, pageable,
                carteirasPage.getTotalElements());
    }

    @GetMapping(value = "/{carteiraId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CarteiraModel buscar(@PathVariable Long carteiraId) {
        Carteira carteira = carteiraService.buscar(carteiraId);

        return carteiraModelAssembler.toModel(carteira);
    }

    @PutMapping("/{carteiraId}/deposito")
    public ResponseEntity<Void> adicionarSaldo(@PathVariable Long carteiraId,
            @RequestBody @Valid CarteiraInput carteiraInput) {
        BigDecimal valor = carteiraInput.getValor();
        carteiraService.adicionarSaldo(carteiraId, valor);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{carteiraId}/saque")
    public ResponseEntity<Void> removerSaldo(@PathVariable Long carteiraId,
            @RequestBody @Valid CarteiraInput carteiraInput) {
        try {
            BigDecimal valor = carteiraInput.getValor();
            carteiraService.removerSaldo(carteiraId, valor);
            return ResponseEntity.noContent().build();
        } catch (SaldoInsuficienteException e) {
            throw new SaldoInsuficienteException(e.getMessage());
        }
    }

}
