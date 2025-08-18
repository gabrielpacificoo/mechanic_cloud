package academy.devdojo.springboot.service;

import academy.devdojo.springboot.domain.Fabricante;
import academy.devdojo.springboot.exception.BadRequestionException;
import academy.devdojo.springboot.mapper.FabricanteMapper;
import academy.devdojo.springboot.repository.veiculo.FabricanteRepository;
import academy.devdojo.springboot.requests.NewFabricantePostRequestBody;
import academy.devdojo.springboot.requests.UpdateFabricantePutRequestBody;
import academy.devdojo.springboot.requests.UpdateNameFabricantePutRequestBody;
import academy.devdojo.springboot.requests.UpdateSiglaPutRequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FabricanteService {

    public final FabricanteRepository fabricanteRepository;

    public List<Fabricante> findByFabricante(String fabricante){
        return fabricanteRepository.findByFabricante(fabricante);
    }

    public List<Fabricante> findBySigla(String sigla){
        return fabricanteRepository.findBySigla(sigla);
    }

    public Fabricante findByIdOrThrowBadRequestException(Long id){
        return fabricanteRepository.findById(id).orElseThrow(() -> new BadRequestionException("Fabricante not found"));
    }


    public Fabricante save(NewFabricantePostRequestBody fabricantePostRequestBody){
        Fabricante fabricante = FabricanteMapper.INSTANCE.toFabricante(fabricantePostRequestBody);
        fabricante.setDataAtualizacao(LocalDate.now());
        fabricante.setDataCriacao(LocalDate.now());
        return fabricanteRepository.save(fabricante);
    }

    public void delete(Long id){
        fabricanteRepository.delete(findByIdOrThrowBadRequestException(id));

    }

    public void replace(UpdateFabricantePutRequestBody fabricantePutRequestBody){
        Fabricante savedFabricante = findByIdOrThrowBadRequestException(fabricantePutRequestBody.getId_fabricante());
        savedFabricante.setFabricante(fabricantePutRequestBody.getFabricante());
        savedFabricante.setSigla(fabricantePutRequestBody.getSigla());
        savedFabricante.setDataAtualizacao(LocalDate.now());
        fabricanteRepository.save(savedFabricante);
    }

    public void replaceSigla(UpdateSiglaPutRequestBody siglaPutRequestBody){
        Fabricante savedFabricante = findByIdOrThrowBadRequestException(siglaPutRequestBody.getId_fabricante());
        savedFabricante.setSigla(siglaPutRequestBody.getSigla());
        savedFabricante.setDataAtualizacao(LocalDate.now());
        fabricanteRepository.save(savedFabricante);
    }

    public void replaceNameFabri(UpdateNameFabricantePutRequestBody nameFabriPutRequestBody){
        Fabricante savedFabricante = findByIdOrThrowBadRequestException(nameFabriPutRequestBody.getId_fabricante());
        savedFabricante.setFabricante(nameFabriPutRequestBody.getFabricante());
        savedFabricante.setDataAtualizacao(LocalDate.now());
        fabricanteRepository.save(savedFabricante);
    }





}
