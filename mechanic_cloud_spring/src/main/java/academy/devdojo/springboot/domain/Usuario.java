package academy.devdojo.springboot.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Usuario {
    @Id
    @Column(name = "id_usuario")
    private UUID idUsuario;
    private String nome;
    private String sobrenome;
    @Column(name = "data_nascimento")
    private Date dataNascimento;
    private String cpf;
    private String senha;
    private Long fkOficina;
}
