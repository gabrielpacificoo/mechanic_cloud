package academy.devdojo.springboot.service;

import academy.devdojo.springboot.domain.Usuario;
import academy.devdojo.springboot.repository.UsuarioRepository;
import academy.devdojo.springboot.requests.LoginUsuarioGetRequestBody;
import academy.devdojo.springboot.requests.NewUsuarioPostRequestBody;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    public final UsuarioRepository usuarioRepository;

    public Usuario getUsuario(UUID id) {
        return usuarioRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nenhum usuário foi encontrado"));
    }

    public Usuario getLoginUsuario(LoginUsuarioGetRequestBody dto) {
        return usuarioRepository.findByCpfAndSenha(dto.getCpf(), dto.getSenha()).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nenhum usuário foi encontrado"));
    }

    public Usuario addUsuario(NewUsuarioPostRequestBody dto) {
        UUID id = UUID.randomUUID();
        Usuario u = new Usuario(id, dto.getNome(), dto.getSobrenome(), dto.getDataNascimento(), dto.getCpf(), dto.getSenha(), dto.getFkOficina());
        return usuarioRepository.save(u);
    }

    public void delUsuario(UUID id) {
        Usuario u = usuarioRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nenhum usuário foi encontrado para ser deletado"));
        usuarioRepository.delete(u);
    }
}
