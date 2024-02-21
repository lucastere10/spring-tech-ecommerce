package br.com.tech.springtech.api.dto.input;

import java.math.BigDecimal;

import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CarteiraInput {

    @Positive
    private BigDecimal valor;

}
