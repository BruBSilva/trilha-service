package br.edu.ifg.trilhadeaprendizagemapims.trilha.controller;

import br.edu.ifg.trilhadeaprendizagemapims.trilha.dto.TrilhaDTO;
import br.edu.ifg.trilhadeaprendizagemapims.trilha.services.TrilhaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/trilhas")
public class TrilhaController {
    
    @Autowired
    private TrilhaService trilhaService;

    @GetMapping
    public Object listarTrilhas(@PageableDefault(size = 10) Pageable pageable) {
        return trilhaService.listarTrilhas(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrilhaDTO> detalharTrilha(@PathVariable Long id) {
        TrilhaDTO dto = trilhaService.obterTrilhaPorId(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<TrilhaDTO> cadastrarTrilha(@RequestBody @Valid TrilhaDTO dto, UriComponentsBuilder uriBuilder) {
        TrilhaDTO trilha = trilhaService.cadastrarTrilha(dto);
        URI uri = uriBuilder.path("/api/trilhas/{id}").buildAndExpand(trilha.getId()).toUri();
        return ResponseEntity.created(uri).body(trilha);
    }
}
