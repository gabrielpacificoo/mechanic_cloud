package academy.devdojo.springboot.mapper;


import academy.devdojo.springboot.domain.Animal;
import academy.devdojo.springboot.requests.AnimalPostRequestBody;
import academy.devdojo.springboot.requests.AnimalPutRequestBody;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract  class  AnimalMapper {

    public static final AnimalMapper INSTANCE = Mappers.getMapper(AnimalMapper.class);
    public abstract Animal toAnimal(AnimalPostRequestBody animalPostRequestBody);
    public abstract Animal toAnimal(AnimalPutRequestBody animalPutRequestBody);

}
