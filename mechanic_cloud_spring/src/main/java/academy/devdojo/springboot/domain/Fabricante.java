package academy.devdojo.springboot.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Fabricante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_fabricante")
    private Long idFabricante;
    private String fabricante;
    private String sigla;
    @Column(name = "data_criacao")
    private LocalDate dataCriacao;
    @Column(name = "data_atualizacao")
    private LocalDate dataAtualizacao;
}
