package academy.devdojo.springboot.requests;

import lombok.Data;

@Data
public class UpdateNameFabricantePutRequestBody {
    private Long id_fabricante;
    private String fabricante;
}
