package academy.devdojo.springboot.controller;

import academy.devdojo.springboot.domain.Veiculo;
import academy.devdojo.springboot.requests.VeiculoPostRequestBody;
import academy.devdojo.springboot.requests.VeiculoPutRequestPlaca;
import academy.devdojo.springboot.service.VeiculoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("veiculos")
@RequiredArgsConstructor
public class VeiculoController {
    private final VeiculoService veiculoService;

    @GetMapping
    public ResponseEntity<List<Veiculo>> list(){
        return ResponseEntity.ok(veiculoService.veiculoRepository.findAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Veiculo> findById(@PathVariable long id){
        return ResponseEntity.ok(veiculoService.findByIdOrThrowBadRequestException(id));
    }

//    @GetMapping(path = "/find")
//    public ResponseEntity<Veiculo> findByPlaca(@RequestParam String placa){
//        return ResponseEntity.ok(veiculoService.findByPlaca(placa));
//    }

    @PostMapping(path = "/cadastrar")
    public ResponseEntity<Veiculo> save(@RequestBody VeiculoPostRequestBody veiculoPostRequestBody){
       return new ResponseEntity<>(veiculoService.save(veiculoPostRequestBody), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id){
        veiculoService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(path = "/atualizar/placa")
    public ResponseEntity<Void> replace(@RequestBody VeiculoPutRequestPlaca veiculoPutRequestPlaca){
        veiculoService.replace(veiculoPutRequestPlaca);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }




}
