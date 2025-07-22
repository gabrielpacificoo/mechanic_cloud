package academy.devdojo.springboot.service;

import academy.devdojo.springboot.domain.Animal;
import academy.devdojo.springboot.mapper.AnimalMapper;
import academy.devdojo.springboot.repository.AnimalRepository;
import academy.devdojo.springboot.requests.AnimalPostRequestBody;
import academy.devdojo.springboot.requests.AnimalPutRequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

//onde fica a logica de negocio
@Service
@RequiredArgsConstructor
public class AnimalService {
    public final AnimalRepository animalRepository;

    public List<Animal> listAll(){
        return animalRepository.findAll();
    }

    public Animal findByIdOrThrowBadRequestException(long id){
        return animalRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Animal not found"));

    }

    public Animal save(AnimalPostRequestBody animalPostRequestBody){
        return animalRepository.save(AnimalMapper.INSTANCE.toAnimal(animalPostRequestBody));

    }

    public void delete(long id){
        animalRepository.delete(findByIdOrThrowBadRequestException(id));
    }

    public void replace(AnimalPutRequestBody animalPutRequestBody){
        Animal savedAnimal = findByIdOrThrowBadRequestException(animalPutRequestBody.getId());
        Animal animal = AnimalMapper.INSTANCE.toAnimal(animalPutRequestBody);
        animal.setId(savedAnimal.getId());
        animalRepository.save(animal);
    }

    public Animal findByTypedOrThrowBadRequestException(String type){
        return animalRepository.findByType(type)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Animal not found"));

    }
}
