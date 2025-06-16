package br.edu.ifg.trilhadeaprendizagemapims.trilha.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ModuloDTO {
    private Long id;
    private String titulo;
    private TrilhaDTO trilha;
    private int ordem;
    private String conteudo;
}
