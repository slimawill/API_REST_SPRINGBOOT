package com.example.OAutoDaCompadecida.Model.DTO;

import com.example.OAutoDaCompadecida.Validation.PersonagemValidation;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@RequiredArgsConstructor
public class PersonagemDTO {

    @NotBlank(groups = PersonagemValidation.OnNotPatch.class)
    private String nome;

    @NotBlank(groups = PersonagemValidation.OnNotPatch.class)
    private String papelNaHistoria;

    @NotNull(groups = PersonagemValidation.OnNotPatch.class)
    @Min(value = 0, groups = {PersonagemValidation.OnNotPatch.class, PersonagemValidation.OnPatch.class})
    @Max(value = 3, groups = {PersonagemValidation.OnNotPatch.class, PersonagemValidation.OnPatch.class})
    private Float altura;

    private LocalDateTime horaDeCriacao;

    public PersonagemDTO(String nome, String papelNaHistoria, Float altura) {
        this.nome = nome;
        this.papelNaHistoria = papelNaHistoria;
        this.altura = altura;
    }
}


