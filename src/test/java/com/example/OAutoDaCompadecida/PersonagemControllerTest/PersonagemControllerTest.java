package com.example.OAutoDaCompadecida.PersonagemControllerTest;

import com.example.OAutoDaCompadecida.Controller.PersonagemController;
import com.example.OAutoDaCompadecida.Model.DTO.PersonagemDTO;
import com.example.OAutoDaCompadecida.Model.Entity.Personagem;
import com.example.OAutoDaCompadecida.Service.PersonagemService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PersonagemControllerTest {

    @Mock
    private PersonagemService personagemService;

    @InjectMocks
    private PersonagemController personagemController;

    @Test
    void getTodosPersonagens() {
        Personagem personagem1 = new Personagem(UUID.randomUUID(), "Personagem 1", "Papel 1", 1.75F, LocalDateTime.now());
        Personagem personagem2 = new Personagem(UUID.randomUUID(), "Personagem 2", "Papel 2", 1.80F, LocalDateTime.now());
        List<Personagem> personagens = Arrays.asList(personagem1, personagem2);
        when(personagemService.getTodosPersonagens()).thenReturn(personagens);

        ResponseEntity<List<Personagem>> response = personagemController.getTodosPersonagens();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(personagens, response.getBody());
    }

    @Test
    void getPersonagemPorIdExistente() {
        UUID personagemId = UUID.randomUUID();
        Personagem personagem = new Personagem(personagemId, "Personagem", "Papel", 1.75f, LocalDateTime.now());
        when(personagemService.getPersonagemPorId(personagemId)).thenReturn(Optional.of(personagem));

        ResponseEntity<Personagem> response = personagemController.getPersonagemPorId(personagemId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(personagem, response.getBody());
    }

    @Test
    void getPersonagemPorIdNaoExistente() {
        UUID personagemId = UUID.randomUUID();
        when(personagemService.getPersonagemPorId(personagemId)).thenReturn(Optional.empty());

        ResponseEntity<Personagem> response = personagemController.getPersonagemPorId(personagemId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void getPersonagemPorNomeExistente() {
        String nome = "Personagem";
        Personagem personagem = new Personagem(UUID.randomUUID(), nome, "Papel", 1.75F, LocalDateTime.now());
        when(personagemService.getPersonagemPorNome(nome)).thenReturn(Optional.of(personagem));

        ResponseEntity<Personagem> response = personagemController.getPersonagemPorNome(nome);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(personagem, response.getBody());
    }

    @Test
    void getPersonagemPorNomeNaoExistente() {
        String nome = "Personagem";
        when(personagemService.getPersonagemPorNome(nome)).thenReturn(Optional.empty());

        ResponseEntity<Personagem> response = personagemController.getPersonagemPorNome(nome);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void putPersonagemPorIdExistente() {
        UUID personagemId = UUID.randomUUID();
        PersonagemDTO personagemDTO = new PersonagemDTO("Personagem Atualizado", "Papel Atualizado", 1.80F);
        Personagem personagemAtualizado = new Personagem(personagemId, "Personagem Atualizado", "Papel Atualizado", 1.80F, LocalDateTime.now());
        when(personagemService.PutPersonagemPorId(personagemId, personagemDTO)).thenReturn(Optional.of(personagemAtualizado));

        ResponseEntity<Personagem> response = personagemController.PutPersonagemPorId(personagemId, personagemDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(personagemAtualizado, response.getBody());
    }

    @Test
    void putPersonagemPorIdNaoExistente() {
        UUID personagemId = UUID.randomUUID();
        PersonagemDTO personagemDTO = new PersonagemDTO("Personagem Atualizado", "Papel Atualizado", 1.80f);
        when(personagemService.PutPersonagemPorId(personagemId, personagemDTO)).thenReturn(Optional.empty());

        ResponseEntity<Personagem> response = personagemController.PutPersonagemPorId(personagemId, personagemDTO);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void patchPersonagemPorIdExistente() {
        UUID personagemId = UUID.randomUUID();
        PersonagemDTO personagemDTO = new PersonagemDTO();
        personagemDTO.setAltura(1.80F);
        Personagem personagemAtualizado = new Personagem(personagemId, "Personagem", "Papel", 1.80f, LocalDateTime.now());
        when(personagemService.PatchPersonagemPorId(personagemId, personagemDTO)).thenReturn(Optional.of(personagemAtualizado));

        ResponseEntity<Personagem> response = personagemController.PatchPersonagemPorId(personagemId, personagemDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(personagemAtualizado, response.getBody());
    }

    @Test
    void patchPersonagemPorIdNaoExistente() {
        UUID personagemId = UUID.randomUUID();
        PersonagemDTO personagemDTO = new PersonagemDTO();
        personagemDTO.setAltura(1.80F);
        when(personagemService.PatchPersonagemPorId(personagemId, personagemDTO)).thenReturn(Optional.empty());

        ResponseEntity<Personagem> response = personagemController.PatchPersonagemPorId(personagemId, personagemDTO);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void putPersonagemPorNomeExistente() {
        String nome = "Personagem";
        PersonagemDTO personagemDTO = new PersonagemDTO("Personagem Atualizado", "Papel Atualizado", 1.80f);
        Personagem personagemAtualizado = new Personagem(UUID.randomUUID(), "Personagem Atualizado", "Papel Atualizado", 1.80f, LocalDateTime.now());
        when(personagemService.PutPersonagemPorNome(nome, personagemDTO)).thenReturn(Optional.of(personagemAtualizado));

        ResponseEntity<Personagem> response = personagemController.PutPersonagemPorNome(nome, personagemDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(personagemAtualizado, response.getBody());
    }

    @Test
    void putPersonagemPorNomeNaoExistente() {
        String nome = "Personagem";
        PersonagemDTO personagemDTO = new PersonagemDTO("Personagem Atualizado", "Papel Atualizado", 1.80f);
        when(personagemService.PutPersonagemPorNome(nome, personagemDTO)).thenReturn(Optional.empty());

        ResponseEntity<Personagem> response = personagemController.PutPersonagemPorNome(nome, personagemDTO);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void patchPersonagemPorNomeExistente() {
        String nome = "Personagem";
        PersonagemDTO personagemDTO = new PersonagemDTO();
        personagemDTO.setAltura(1.80F);
        Personagem personagemAtualizado = new Personagem(UUID.randomUUID(), "Personagem", "Papel", 1.80f, LocalDateTime.now());
        when(personagemService.PatchPersonagemPorNome(nome, personagemDTO)).thenReturn(Optional.of(personagemAtualizado));

        ResponseEntity<Personagem> response = personagemController.PatchPersonagemPorNome(nome, personagemDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(personagemAtualizado, response.getBody());
    }

    @Test
    void patchPersonagemPorNomeNaoExistente() {
        String nome = "Personagem";
        PersonagemDTO personagemDTO = new PersonagemDTO();
        personagemDTO.setAltura(1.80F);
        when(personagemService.PatchPersonagemPorNome(nome, personagemDTO)).thenReturn(Optional.empty());

        ResponseEntity<Personagem> response = personagemController.PatchPersonagemPorNome(nome, personagemDTO);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void deletarPersonagemPorId() {
        UUID personagemId = UUID.randomUUID();

        ResponseEntity<Void> response = personagemController.deletarPersonagemPorId(personagemId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(personagemService, times(1)).deletarPersonagem(personagemId);
    }

    @Test
    void salvarPersonagem() {
        // Arrange
        PersonagemDTO personagemDTO = new PersonagemDTO("Novo Personagem", "Novo Papel", 1.75F);
        Personagem personagemSalvo = new Personagem(UUID.randomUUID(), "Novo Personagem", "Novo Papel", 1.75F, LocalDateTime.now());
        when(personagemService.salvarPersonagem(personagemDTO)).thenReturn(personagemSalvo);

        // Act
        ResponseEntity<Personagem> response = personagemController.salvarPersonagem(personagemDTO);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(personagemSalvo, response.getBody());
    }
}