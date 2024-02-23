package br.com.tech.springtech.api.dto.input;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuantidadeProdutoInput {
    
    @NotNull
    @Min(1)
    @Schema(example = "2")
    private Long quantidade;

}
