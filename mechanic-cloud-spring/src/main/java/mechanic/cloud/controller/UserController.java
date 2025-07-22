package mechanic.cloud.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/user")
public class UserController {

    @GetMapping(path = "/")
    public ResponseEntity<String> retorno() {
        return ResponseEntity.ok("Sim");
    }
}