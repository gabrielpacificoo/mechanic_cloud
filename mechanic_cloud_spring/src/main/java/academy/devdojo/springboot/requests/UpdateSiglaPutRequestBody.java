package academy.devdojo.springboot.requests;

import lombok.Data;

@Data
public class UpdateSiglaPutRequestBody {
    private Long id_fabricante;
    private String sigla;
}
