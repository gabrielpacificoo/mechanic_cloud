package academy.devdojo.springboot.controller;

import academy.devdojo.springboot.domain.Animal;
import academy.devdojo.springboot.requests.AnimalPostRequestBody;
import academy.devdojo.springboot.requests.AnimalPutRequestBody;
import academy.devdojo.springboot.service.AnimalService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("animais")
@RequiredArgsConstructor
// END POINTS
public class AnimalController {
    private final AnimalService animalService;

    @GetMapping
    public ResponseEntity<List<Animal>> list() {
        return ResponseEntity.ok(animalService.listAll()) ;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Animal> findById(@PathVariable long id) {
        return ResponseEntity.ok(animalService.findByIdOrThrowBadRequestException(id));
    }

    @PostMapping
    @ResponseStatus
    public ResponseEntity<Animal> save(@RequestBody AnimalPostRequestBody animalPostRequestBody){
        return new ResponseEntity<>(animalService.save(animalPostRequestBody), HttpStatus.CREATED);

    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id){
        animalService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<Void> replace(@RequestBody AnimalPutRequestBody animalPutRequestBody){
        animalService.replace(animalPutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
