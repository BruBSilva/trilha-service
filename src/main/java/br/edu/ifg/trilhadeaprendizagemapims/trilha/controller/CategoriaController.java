package br.edu.ifg.trilhadeaprendizagemapims.trilha.controller;

import br.edu.ifg.trilhadeaprendizagemapims.trilha.dto.CategoriaDTO;
import br.edu.ifg.trilhadeaprendizagemapims.trilha.model.ECategoria;
import br.edu.ifg.trilhadeaprendizagemapims.trilha.services.CategoriaService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {
    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public Object listarCategorias(@PageableDefault(size = 10) Pageable pageable) {
        return categoriaService.listarCategorias(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDTO> detalharCategoria(@PathVariable @NotNull Long id) {
        CategoriaDTO dto = categoriaService.obterCategoriaPorId(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<CategoriaDTO> cadastrarCategoria(@RequestBody @Valid CategoriaDTO dto, UriComponentsBuilder uriBuilder) {
        CategoriaDTO categoria = categoriaService.cadastrarCategoria(dto);
        URI uri = uriBuilder.path("/api/categorias/{id}").buildAndExpand(categoria.getId()).toUri();
        return ResponseEntity.created(uri).body(categoria);
    }

    @PostMapping("/lote")
    public ResponseEntity<List<CategoriaDTO>> cadastrarCategoriasEmLote(@RequestBody @Valid List<CategoriaDTO> dtos, UriComponentsBuilder uriBuilder) {
        List<CategoriaDTO> categoriasSalvas = new ArrayList<>();

        for (CategoriaDTO dto : dtos) {
            CategoriaDTO categoriaSalva = categoriaService.cadastrarCategoria(dto);
            categoriasSalvas.add(categoriaSalva);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriasSalvas);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaDTO> atualizarCategoria(@PathVariable @NotNull Long id, @RequestBody CategoriaDTO dto, UriComponentsBuilder uriBuilder) {
        CategoriaDTO categoria = categoriaService.atualizarCategoria(id, dto);
        return ResponseEntity.ok(categoria);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCategoria(@PathVariable @NotNull Long id) {
        categoriaService.deletarCategoria(id);
        return ResponseEntity.noContent().build();
    }

}
