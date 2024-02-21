package br.com.tech.springtech.api.dto.input;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CarteiraInput {

    @Positive
    @NotNull
    @Schema(example = "50")
    private BigDecimal valor;

}
