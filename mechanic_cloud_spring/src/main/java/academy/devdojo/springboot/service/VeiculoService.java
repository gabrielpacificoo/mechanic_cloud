package academy.devdojo.springboot.service;

import academy.devdojo.springboot.domain.Veiculo;
import academy.devdojo.springboot.exception.BadRequestionException;
import academy.devdojo.springboot.mapper.VeiculoMapper;
import academy.devdojo.springboot.repository.veiculo.VeiculoRepository;
import academy.devdojo.springboot.requests.VeiculoPostRequestBody;
import academy.devdojo.springboot.requests.VeiculoPutRequestPlaca;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class VeiculoService {
    public final VeiculoRepository veiculoRepository;

    public Veiculo save(VeiculoPostRequestBody veiculoPostRequestBody){
        Veiculo veiculo = VeiculoMapper.INSTANCE.toVeiculo(veiculoPostRequestBody);
        veiculo.setDataCadastrada(LocalDateTime.now());
        veiculo.setDataAtualizacao(LocalDateTime.now());
        return veiculoRepository.save(veiculo);
    }


//    public Veiculo save(VeiculoPostRequestBody veiculoPostRequestBody){
//        Veiculo veiculo = new Veiculo();
//    }

    public Veiculo findByIdOrThrowBadRequestException(long id){
        return veiculoRepository.findById(id)
            .orElseThrow(() -> new BadRequestionException("Veiculo not found"));
    }

    public void delete(long id){
        veiculoRepository.delete(findByIdOrThrowBadRequestException(id));
    }

    public void replace(VeiculoPutRequestPlaca veiculoPutRequestPlaca){
        Veiculo savedVeiculo = findByIdOrThrowBadRequestException(veiculoPutRequestPlaca.getId());
        savedVeiculo.setPlaca(veiculoPutRequestPlaca.getPlaca());
        savedVeiculo.setDataAtualizacao(LocalDateTime.now());
        veiculoRepository.save(savedVeiculo);
    }


}
