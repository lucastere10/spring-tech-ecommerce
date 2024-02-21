package br.com.tech.springtech.api.dto.model;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarteiraModel {
    
    @Schema(example = "1")
    private Long carteiraId;

    @Schema(example = "400.00")
    private BigDecimal saldo;

}
