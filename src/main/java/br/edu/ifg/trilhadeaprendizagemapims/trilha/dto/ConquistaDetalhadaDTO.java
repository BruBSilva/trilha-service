package br.edu.ifg.trilhadeaprendizagemapims.trilha.dto;

import br.edu.ifg.trilhadeaprendizagemapims.trilha.model.enums.TipoConquista;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConquistaDetalhadaDTO {
    private Long id;
    private String nome;
    private String descricao;
    private TipoConquista tipo;
    private String modulo_nome;
    private String trilha_nome;
    private int xpGanho;
}
