package br.edu.ifg.trilhadeaprendizagemapims.trilha.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class TrilhaDTO {
    private Long id;
    private String nome;
    private String descricao;
    private CategoriaDTO categoria;
    private ArrayList<ModuloDTO> modulos;
    private ConquistaDTO conquista;
}
