package br.edu.ifg.trilhadeaprendizagemapims.trilha.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Entity
@Table(name = "trilhas")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ETrilha {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String nome;

    @NotNull
    private String descricao;

    @ManyToOne (optional = false, fetch = FetchType.LAZY)
    private ECategoria categoria;

    @NotNull
    private ArrayList<EModulo> modulos;

    @NotNull
    private ArrayList<EConquista> conquistas;
}
