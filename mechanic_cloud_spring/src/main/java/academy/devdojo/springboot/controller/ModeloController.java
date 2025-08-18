package academy.devdojo.springboot.controller;


import academy.devdojo.springboot.domain.Modelo;
import academy.devdojo.springboot.requests.NewModeloPostRequestBody;
import academy.devdojo.springboot.service.ModeloService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("modelos")
public class ModeloController {
    public final ModeloService modeloService;





}
