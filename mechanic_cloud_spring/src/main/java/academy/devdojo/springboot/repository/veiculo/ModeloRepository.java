package academy.devdojo.springboot.repository.veiculo;

import academy.devdojo.springboot.domain.Modelo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModeloRepository extends JpaRepository<Modelo, Long > {
}
