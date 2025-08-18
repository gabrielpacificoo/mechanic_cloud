package academy.devdojo.springboot.service;

import academy.devdojo.springboot.domain.Fabricante;
import academy.devdojo.springboot.repository.veiculo.ModeloRepository;
import academy.devdojo.springboot.requests.NewModeloPostRequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ModeloService {
    public final ModeloRepository modeloRepository;


}
