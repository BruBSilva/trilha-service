package br.edu.ifg.trilhadeaprendizagemapims.trilha.model;

import br.edu.ifg.trilhadeaprendizagemapims.trilha.model.enums.TipoConquista;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "conquistas")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EConquista implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String nome;

    @NotNull
    private String descricao;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TipoConquista tipo;

    @NotNull
    private int xpGanho;
}
