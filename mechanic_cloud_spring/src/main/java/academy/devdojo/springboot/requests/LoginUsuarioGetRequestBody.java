package academy.devdojo.springboot.requests;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class LoginUsuarioGetRequestBody {
    private String cpf;
    private String senha;
}
