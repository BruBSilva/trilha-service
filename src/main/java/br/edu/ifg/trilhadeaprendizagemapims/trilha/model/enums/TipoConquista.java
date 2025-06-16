package br.edu.ifg.trilhadeaprendizagemapims.trilha.model.enums;

import lombok.Getter;

@Getter
public enum TipoConquista {
    MODULOS_CONCLUIDOS("Conclusão do Módulos"),
    HORAS_ESTUDADAS("Horas Estudadas"),
    CONCLUSAO_TRILHA("Conclusão da Trilha"),
    CONQUISTA_ESPECIAL("Conquista Especial");

    private final String descricao;

    TipoConquista(String descricao) {
        this.descricao = descricao;
    }
}
