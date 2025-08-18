package academy.devdojo.springboot.repository.veiculo;

import academy.devdojo.springboot.domain.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//CONEXÃO COM O BANCO DE DADOS
public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {

}
