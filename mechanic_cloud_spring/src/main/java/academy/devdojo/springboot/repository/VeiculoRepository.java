package academy.devdojo.springboot.repository;

import academy.devdojo.springboot.domain.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;

//CONEXÃO COM O BANCO DE DADOS
public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {

}
