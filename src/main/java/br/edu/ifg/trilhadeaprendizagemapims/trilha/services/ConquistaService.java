package br.edu.ifg.trilhadeaprendizagemapims.trilha.services;

import br.edu.ifg.trilhadeaprendizagemapims.trilha.dto.ConquistaDTO;
import br.edu.ifg.trilhadeaprendizagemapims.trilha.model.EConquista;
import br.edu.ifg.trilhadeaprendizagemapims.trilha.repository.ConquistaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ConquistaService {
    
    @Autowired
    ConquistaRepository conquistaRepository;
    
    @Autowired
    private ModelMapper modelMapper;

    public Page<ConquistaDTO> listarConquistas(Pageable pageable) {
        return conquistaRepository.findAll(pageable)
                .map(entidade -> modelMapper.map(entidade, ConquistaDTO.class));
    }

    public ConquistaDTO obterConquistaPorId(Long id){
        return modelMapper.map(conquistaRepository.findById(id).orElseThrow(() -> new RuntimeException("Conquista não encontrada")), ConquistaDTO.class);
    }

    public ConquistaDTO cadastrarConquista(ConquistaDTO dto) {
        EConquista entidade = modelMapper.map(dto, EConquista.class);
        conquistaRepository.save(entidade);
        return modelMapper.map(entidade, ConquistaDTO.class);
    }

    public void deletarConquista(Long id) {
        EConquista entidade = conquistaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        conquistaRepository.delete(entidade);
    }

    public ConquistaDTO atualizarConquista(Long id, ConquistaDTO dto) {
        EConquista entidade = conquistaRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        modelMapper.map(dto, entidade);
        conquistaRepository.save(entidade);
        return modelMapper.map(entidade, ConquistaDTO.class);
    }
}
