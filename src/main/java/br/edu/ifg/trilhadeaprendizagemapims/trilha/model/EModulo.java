package br.edu.ifg.trilhadeaprendizagemapims.trilha.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "modulos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EModulo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String titulo;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private ETrilha trilha;

    @NotNull
    private int ordem;

    @NotNull
    private String conteudo;
}
