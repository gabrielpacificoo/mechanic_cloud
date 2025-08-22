package academy.devdojo.springboot.mapper;


import academy.devdojo.springboot.domain.Veiculo;
import academy.devdojo.springboot.requests.VeiculoPostRequestBody;
import academy.devdojo.springboot.requests.VeiculoPutRequestPlaca;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class VeiculoMapper {
    public static final VeiculoMapper INSTANCE = Mappers.getMapper(VeiculoMapper.class);
    public abstract Veiculo toVeiculo(VeiculoPostRequestBody veiculoPostRequestBody);
    public abstract Veiculo toVeiculo(VeiculoPutRequestPlaca VeiculoPutRequestPlacaRequestBody);
}
