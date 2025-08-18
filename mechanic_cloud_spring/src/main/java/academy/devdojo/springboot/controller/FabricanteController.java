package academy.devdojo.springboot.controller;

import academy.devdojo.springboot.domain.Fabricante;
import academy.devdojo.springboot.requests.NewFabricantePostRequestBody;
import academy.devdojo.springboot.requests.UpdateFabricantePutRequestBody;
import academy.devdojo.springboot.requests.UpdateNameFabricantePutRequestBody;
import academy.devdojo.springboot.requests.UpdateSiglaPutRequestBody;
import academy.devdojo.springboot.service.FabricanteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("fabricantes")
@RequiredArgsConstructor
public class FabricanteController {
    private final FabricanteService fabricanteService;

    @GetMapping
    public ResponseEntity<List<Fabricante>> list(){
        return ResponseEntity.ok(fabricanteService.fabricanteRepository.findAll());
    }

    @GetMapping("/findFabrincate")
    public ResponseEntity<List<Fabricante>> findByFabricante(@RequestParam String fabricante){
        return ResponseEntity.ok(fabricanteService.findByFabricante(fabricante));
    }

    @GetMapping("/findSigla")
    public ResponseEntity<List<Fabricante>> findBySigla(@RequestParam String sigla){
        return ResponseEntity.ok(fabricanteService.findBySigla(sigla));
    }

    @PostMapping(path = "/cadastrarFabricante")
    public ResponseEntity<Fabricante> save(@RequestBody @Valid NewFabricantePostRequestBody fabricantePostRequestBody){
        return new ResponseEntity<>(fabricanteService.save(fabricantePostRequestBody), HttpStatus.CREATED);
    }

    @DeleteMapping("/delFabricante")
    public ResponseEntity<Fabricante> delete(@RequestParam Long id ){
        fabricanteService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(path = "/atualizarDomainFabricante")
    public ResponseEntity<Fabricante> replace(@RequestBody UpdateFabricantePutRequestBody fabricantePutRequestBody){
        fabricanteService.replace(fabricantePutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(path = "/atualizarSigla")
    public ResponseEntity<Fabricante> replace(@RequestBody UpdateSiglaPutRequestBody siglaPutRequestBody){
      fabricanteService.replaceSigla(siglaPutRequestBody);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(path = "/atualizarFabrincante")
    public ResponseEntity<Fabricante> replace(@RequestBody UpdateNameFabricantePutRequestBody nameFabriPutRequestBody){
        fabricanteService.replaceNameFabri(nameFabriPutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }













}
