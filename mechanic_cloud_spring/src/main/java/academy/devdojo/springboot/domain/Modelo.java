package academy.devdojo.springboot.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Year;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Modelo{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_modelo")
    private Long idModelo;
    private String modelo;
    @Column(name = "ano_modelo")
    private Year anoModelo;
    @Column(name = "ano_fabricacao")
    private Year anoFabricacao;
    private Long fkFabricante;
}
