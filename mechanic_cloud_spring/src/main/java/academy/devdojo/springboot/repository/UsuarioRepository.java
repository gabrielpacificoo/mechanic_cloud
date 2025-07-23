package academy.devdojo.springboot.repository;

import academy.devdojo.springboot.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {
    Optional<Usuario> findByCpfAndSenha(String cpf, String senha);
}
