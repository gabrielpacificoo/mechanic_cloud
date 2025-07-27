package academy.devdojo.springboot.requests;

import jakarta.persistence.Column;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;


@Data
public class VeiculoPostRequestBody {
    private String placa;
    private Long fkModelo;
    private Long fkFilial;
    private Long fkEmpresa;
}
