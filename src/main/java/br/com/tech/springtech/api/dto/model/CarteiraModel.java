package br.com.tech.springtech.api.dto.model;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarteiraModel {
    
    private Long carteiraId;

    private BigDecimal saldo;

}
