package br.edu.ifg.trilhadeaprendizagemapims.trilha.controller;

import br.edu.ifg.trilhadeaprendizagemapims.trilha.dto.ConquistaDTO;
import br.edu.ifg.trilhadeaprendizagemapims.trilha.services.ConquistaService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/conquistas")
public class ConquistaController {

    @Autowired
    private ConquistaService conquistaService;

    @GetMapping
    public Object listarConquistas(@PageableDefault(size = 10) Pageable pageable) {
        return conquistaService.listarConquistas(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConquistaDTO> obterConquistaPorId(@PathVariable @NotNull Long id) {
        ConquistaDTO dto = conquistaService.obterConquistaPorId(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<ConquistaDTO> cadastrarConquista(@RequestBody @Valid ConquistaDTO dto, UriComponentsBuilder uriBuilder) {
        ConquistaDTO conquista = conquistaService.cadastrarConquista(dto);
        URI uri = uriBuilder.path("/api/conquistas/{id}").buildAndExpand(conquista.getId()).toUri();
        return ResponseEntity.created(uri).body(conquista);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConquistaDTO> atualizarConquista(@PathVariable @NotNull Long id, @RequestBody ConquistaDTO dto, UriComponentsBuilder uriBuilder) {
        ConquistaDTO conquista = conquistaService.atualizarConquista(id, dto);
        return ResponseEntity.ok(conquista);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarConquista(@PathVariable @NotNull Long id) {
        conquistaService.deletarConquista(id);
        return ResponseEntity.noContent().build();
    }

}
