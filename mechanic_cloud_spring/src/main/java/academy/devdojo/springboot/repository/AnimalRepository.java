package academy.devdojo.springboot.repository;

import academy.devdojo.springboot.domain.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//CONEXÃO COM O BANCO DE DADOS
public interface AnimalRepository extends JpaRepository<Animal, Long> {
     List<Animal> findByType(String type);
}
