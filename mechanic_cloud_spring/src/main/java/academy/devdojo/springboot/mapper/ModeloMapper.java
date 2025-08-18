package academy.devdojo.springboot.mapper;

import academy.devdojo.springboot.domain.Modelo;
import academy.devdojo.springboot.requests.NewModeloPostRequestBody;
import org.mapstruct.factory.Mappers;

public abstract class ModeloMapper {
    public final static ModeloMapper INSTANCE = Mappers.getMapper(ModeloMapper.class);
    public abstract Modelo toModelo(NewModeloPostRequestBody modeloPostRequestBody);
}
