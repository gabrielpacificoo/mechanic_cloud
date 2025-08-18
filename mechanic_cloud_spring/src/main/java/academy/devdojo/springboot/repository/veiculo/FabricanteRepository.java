package academy.devdojo.springboot.repository.veiculo;

import academy.devdojo.springboot.domain.Fabricante;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FabricanteRepository extends JpaRepository<Fabricante, Long> {
    List<Fabricante> findByFabricante(String fabricante);
    List<Fabricante> findBySigla(String sigla);
}
