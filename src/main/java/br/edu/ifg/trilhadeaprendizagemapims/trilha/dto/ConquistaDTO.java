package br.edu.ifg.trilhadeaprendizagemapims.trilha.dto;

import br.edu.ifg.trilhadeaprendizagemapims.trilha.model.enums.TipoConquista;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConquistaDTO {
    private Long id;
    private String nome;
    private String descricao;
    private TipoConquista tipo;
    private int xpGanho;
}
