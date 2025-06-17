package br.edu.ifg.trilhadeaprendizagemapims.trilha.services;

import br.edu.ifg.trilhadeaprendizagemapims.trilha.dto.TrilhaDTO;
import br.edu.ifg.trilhadeaprendizagemapims.trilha.model.ECategoria;
import br.edu.ifg.trilhadeaprendizagemapims.trilha.model.EConquista;
import br.edu.ifg.trilhadeaprendizagemapims.trilha.model.EModulo;
import br.edu.ifg.trilhadeaprendizagemapims.trilha.model.ETrilha;
import br.edu.ifg.trilhadeaprendizagemapims.trilha.repository.CategoriaRepository;
import br.edu.ifg.trilhadeaprendizagemapims.trilha.repository.ConquistaRepository;
import br.edu.ifg.trilhadeaprendizagemapims.trilha.repository.TrilhaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrilhaService {

    @Autowired
    TrilhaRepository trilhaRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ConquistaRepository conquistaRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Page<TrilhaDTO> listarTrilhas(Pageable pageable) {
        return trilhaRepository.findAll(pageable)
                .map(entidade -> modelMapper.map(entidade, TrilhaDTO.class));
    }

    public TrilhaDTO obterTrilhaPorId(Long id) {
        ETrilha entidade = trilhaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Trilha não encontrada"));
        return modelMapper.map(entidade, TrilhaDTO.class);
    }

    public TrilhaDTO cadastrarTrilha(TrilhaDTO dto) {
        ETrilha entidade = modelMapper.map(dto, ETrilha.class);

        ECategoria categoria = categoriaRepository.findById(dto.getCategoria().getId())
                .orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada"));
        entidade.setCategoria(categoria);

        List<EConquista> conquistas = dto.getConquistas().stream()
                .map(conquistaDTO -> conquistaRepository.findById(conquistaDTO.getId())
                        .orElseThrow(() -> new EntityNotFoundException("Conquista não encontrada: " + conquistaDTO.getId())))
                .toList();
        entidade.setConquistas(conquistas);

        if (entidade.getModulos() != null) {
            for (EModulo modulo : entidade.getModulos()) {
                modulo.setTrilha(entidade);
            }
        }

        trilhaRepository.save(entidade);
        return modelMapper.map(entidade, TrilhaDTO.class);
    }

    public void deletarTrilha(Long id) {
        ETrilha entidade = trilhaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Trilha não encontrada"));
        trilhaRepository.delete(entidade);
    }

    public TrilhaDTO atualizarTrilha(Long id, TrilhaDTO dto) {
        ETrilha entidade = trilhaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Trilha não encontrada"));

        modelMapper.map(dto, entidade);

        ECategoria categoria = categoriaRepository.findById(dto.getCategoria().getId())
                .orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada"));
        entidade.setCategoria(categoria);

        List<EConquista> conquistas = dto.getConquistas().stream()
                .map(conquistaDTO -> conquistaRepository.findById(conquistaDTO.getId())
                        .orElseThrow(() -> new EntityNotFoundException("Conquista não encontrada: " + conquistaDTO.getId())))
                .toList();
        entidade.setConquistas(conquistas);

        if (entidade.getModulos() != null) {
            for (EModulo modulo : entidade.getModulos()) {
                modulo.setTrilha(entidade);
            }
        }

        trilhaRepository.save(entidade);
        return modelMapper.map(entidade, TrilhaDTO.class);
    }
}
