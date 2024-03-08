package com.example.OAutoDaCompadecida.Model.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Personagem {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    private String nome;
    private String papelNaHistoria;


    private Float altura;
    private LocalDateTime horaDeCriacao;

    public Personagem(String nome, String papelNaHistoria, Float altura, LocalDateTime horaDeCriacao) {
        this.nome = nome;
        this.papelNaHistoria = papelNaHistoria;
        this.altura = altura;
        this.horaDeCriacao = horaDeCriacao;
    }

    public Personagem(String nome, String papelNaHistoria, Float altura) {
        this.nome = nome;
        this.papelNaHistoria = papelNaHistoria;
        this.altura = altura;
    }
}


