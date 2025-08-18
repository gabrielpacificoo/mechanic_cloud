package academy.devdojo.springboot.requests;

import jakarta.persistence.Column;

import java.time.Year;

public class NewModeloPostRequestBody {
    private String modelo;
    private Year anoModelo;
    private Year anoFabricacao;
    private Long fkFabricante;
}
