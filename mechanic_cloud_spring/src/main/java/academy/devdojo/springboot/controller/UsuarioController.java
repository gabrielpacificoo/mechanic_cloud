package academy.devdojo.springboot.controller;

import academy.devdojo.springboot.domain.Animal;
import academy.devdojo.springboot.domain.Usuario;
import academy.devdojo.springboot.requests.AnimalPostRequestBody;
import academy.devdojo.springboot.requests.AnimalPutRequestBody;
import academy.devdojo.springboot.requests.LoginUsuarioGetRequestBody;
import academy.devdojo.springboot.requests.NewUsuarioPostRequestBody;
import academy.devdojo.springboot.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("usuario")
@RequiredArgsConstructor
public class UsuarioController {
    public final UsuarioService usuarioService;

    @GetMapping(path = "/{id}")
    public ResponseEntity<Usuario> getUser(@PathVariable UUID id) {
        return ResponseEntity.ok(usuarioService.getUsuario(id)) ;
    }

    @PostMapping(path = "/login")
    public ResponseEntity<Usuario> getLoginUser(@RequestBody LoginUsuarioGetRequestBody body) {
        return ResponseEntity.ok(usuarioService.getLoginUsuario(body));
    }

    @PostMapping(path = "/cadastrar")
    public ResponseEntity<Usuario> addUser(@RequestBody NewUsuarioPostRequestBody body){
        return ResponseEntity.ok(usuarioService.addUsuario(body));
    }

    @DeleteMapping(path = "/deletar/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id){
        usuarioService.delUsuario(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
