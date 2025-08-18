package academy.devdojo.springboot.mapper;

import academy.devdojo.springboot.domain.Fabricante;
import academy.devdojo.springboot.requests.NewFabricantePostRequestBody;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")
public abstract class FabricanteMapper {
    public static final FabricanteMapper INSTANCE = Mappers.getMapper(FabricanteMapper.class);
    public abstract Fabricante toFabricante(NewFabricantePostRequestBody fabricantePostRequestBody);
}
