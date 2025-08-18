package academy.devdojo.springboot.requests;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;


@Data
public class VeiculoPostRequestBody {
    @NotEmpty(message = "The veiculo placa cannot be empyt")
    @NotNull(message = "The veiculo placa cannot be null")
    private String placa;
    @NotEmpty(message = "The veiculo fkmodelo cannot be empyt")
    @NotNull(message = "The veiculo fkmodelo cannot be null")
    private Long fkModelo;
    @NotEmpty(message = "The veiculo fkfilial cannot be empyt")
    @NotNull(message = "The veiculo fkfilial cannot be null")
    private Long fkFilial;
    @NotEmpty(message = "The veiculo fkEmpresa cannot be empyt")
    @NotNull(message = "The veiculo fkEmpresa cannot be null")
    private Long fkEmpresa;
}
