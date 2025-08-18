package academy.devdojo.springboot.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder

public class Veiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_veiculo")
    private Long idVeiculo;

    private String placa;

    @Column(name = "data_cadastro")
    private LocalDateTime dataCadastrada;

    @Column(name = "data_atualizacao")
    private LocalDateTime dataAtualizacao;

    @Column(name = "data_ultima_visita")
    private Date dataUltimaVisita;

    @Column(name = "fk_modelo")
    private Long fkModelo;

    @Column(name = "fk_filial")
    private Long fkFilial;

    @Column(name = "fk_empresa")
    private Long fkEmpresa;

}
