package academy.devdojo.springboot.requests;

import lombok.Data;

@Data
public class UpdateFabricantePutRequestBody {
    private Long id_fabricante;
    private String fabricante;
    private String sigla;
}

