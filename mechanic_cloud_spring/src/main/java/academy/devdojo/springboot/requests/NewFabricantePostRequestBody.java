package academy.devdojo.springboot.requests;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class NewFabricantePostRequestBody {
    @NotEmpty(message = "The fabricate name cannot be empyt")
    @NotNull(message = "The fabricate name cannot be null")
    private String fabricante;
    private String sigla;
}
