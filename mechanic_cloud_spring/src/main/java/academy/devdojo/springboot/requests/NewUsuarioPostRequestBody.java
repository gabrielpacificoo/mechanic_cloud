package academy.devdojo.springboot.requests;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class NewUsuarioPostRequestBody {
    private String nome;
    private String sobrenome;
    private Date dataNascimento;
    private String cpf;
    private String senha;
    private Long fkOficina;
}
